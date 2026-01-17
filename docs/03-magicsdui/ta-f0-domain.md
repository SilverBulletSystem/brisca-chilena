# TA-SDUI-F0 — Modelo de dominio SDUI (DDD)

## Rama
- `feat/ht-sdui-f0-domain`

## Objetivo
Definir el modelo de dominio SDUI (entidades/agregados, value objects, repositorio y casos de uso) independiente del transporte (HTTP/GraphQL/mock). El contrato externo (DTO) siempre se traduce a este modelo antes de renderizar.

## Alcance
- Entidades/agregados: `Schema` (root), `Node` (organismo/molécula/átomo), `Props`, `Style`, `Action`, `NodeState`.
- Value Objects: `SchemaVersion`, `NodeId`, `NodeType`, `ActionTarget` (deeplink/navigate/open_url/intent), `EnvValue` (mock/dev/prod), `FlagKey`.
- Repositorio SDUI (interfaz): `fetchSchema(schemaId, env): SchemaDto` (o resultado) abstrae origen (HTTP/GraphQL/local fixture).
- Casos de uso:
  - `FetchSchema`: orquesta la obtención de DTO desde el repositorio (capa datos/infra).
  - `ParseSchema`: traduce DTO externo → modelo de dominio `Schema`/`Node` aplicando validaciones/defaults (alineado a TA-F2).
  - `RenderSchema` (descrito en TA-F3): consume modelo de dominio para construir árbol Dui.
- Reglas:
  - Dominio no conoce transporte ni infra; solo interfaces.
  - Flags/ambientes se resuelven vía servicios de dominio (`FlagsProvider`, `EnvProvider`) antes del render.
  - i18n: textos son keys, no literales; dominio no resuelve traducciones, solo garantiza keys presentes.

## Diseño (doc)
1) Diagrama lógico (texto)
   - `Schema` { `id`, `version`: SchemaVersion, `root`: Node }
   - `Node` { `id`: NodeId, `type`: NodeType, `props`, `children`: [Node], `style`, `flags`: [FlagKey], `env`: [EnvValue], `state`: NodeState }
   - `Props` según tipo de Node (ver TA-F1 contract/TA-F2 parser para detalle de campos).
   - `Style` usa roles/tokens (no valores crudos).
   - `Action` { `type`, `target`: ActionTarget, `extras`? }
2) Interfaces
   - `SduiRepository` (infra): `suspend fun fetch(schemaId: SchemaId, env: EnvValue): SchemaDto`
   - `FlagsProvider`: obtiene flags activos (local/remoto) como VO; prioriza local → remoto → default.
   - `EnvProvider`: entrega ambiente actual (mock/dev/prod) elegido en cliente.
3) Casos de uso (firma sugerida)
   - `FetchSchema(schemaId, env)` -> `SchemaDto`
   - `ParseSchema(dto, flags, env)` -> `Schema` (dominio) + errores/warnings recolectados
   - `RenderSchema(schema)` -> árbol UI (TA-F3)
4) Servicios de dominio
   - `FlagResolutionService`: combina proveedores y entrega flags activas; resuelve defaults.
   - `EnvironmentResolutionService`: entrega `EnvValue` actual (mock/dev/prod).
   - `NodeVisibilityService`: decide inclusión del nodo según flags/env.
   - `SchemaValidationService`: valida tipos/props/estructura y versionado; clasifica errores (crítico vs recuperable).
   - `SchemaNormalizationService`: aplica defaults de props/estilos/estado a nodos válidos.
   - `StyleResolutionService`: mapea roles de estilo a tokens DS; ignora valores crudos.
   - `NavigationGuardService`: valida acciones (deeplink/navigate/open_url/intent) y produce `ActionTarget` seguro.
5) Manejo de errores (dominio)
   - Errores críticos: version incompatible, tipo raíz inválido → se devuelve Schema con nodo de error SDUI.
   - Errores recuperables: props faltantes no críticas → omitir nodo/hijo y acumular warning.
   - Acoplar con Toad/State Record: logging estructurado (id/tipo/razón/severidad).

## Entregables (documento)
- Definición de entidades/VO/casos de uso/repositorio en términos de dominio.
- Reglas de independencia de transporte y de flags/env.
- Manejo de errores y logging esperado en dominio.

## Verificación futura
- Dominio claro y desacoplado de HTTP/GraphQL/mock.
- Casos de uso definidos para que el dev implemente infra y render sin ambigüedad.

## No incluido
- Implementación en código ni elección de transporte concreto.
