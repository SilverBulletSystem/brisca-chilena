# Backlog técnico — Épica 3 MagicsDui (SDUI)

Referencia: `03-magicsdui/epica.md`. Solo documentación, sin implementación.

## F0 – Modelo de dominio SDUI
- Definir entidades/agregados: `Schema`, `Node`, `Props`, `Style`, `Action`, `Env`, `Flag`, `NodeState`.
- Value Objects: `NodeId`, `SchemaVersion`, `NodeType`, `ActionTarget`, `EnvValue`, `FlagKey`.
- Repositorio SDUI (interfaz): obtiene DTO externo (HTTP/GraphQL/mock/local fixture) y entrega DTO al caso de uso.
- Casos de uso: `FetchSchema` (orquesta origen), `ParseSchema` (DTO→modelo dominio), `RenderSchema` (modelo→árbol UI se documenta en F3).
- Reglas: sin dependencias a transporte en dominio; infraestructura provee implementación del repositorio.
- Verificación futura: modelo de dominio listo; repositorio y casos de uso definidos como interfaces/contratos.
- Rama: `feat/ht-sdui-f0-domain`.
- Servicios de dominio: FlagResolution, EnvironmentResolution, NodeVisibility, SchemaValidation, SchemaNormalization, StyleResolution, NavigationGuard.

## F1 – Contrato SDUI + versionado
- Definir esquema JSON (Mockoon) con versionado y granularidad organismo/molécula/átomo.
- Incluir flags/ambientes, estilos y navegación.
- Verificación futura: contrato cubre todos los componentes Dui y soporta fallback de versión.
- Rama: `feat/ht-sdui-f1-contract`.

## F2 – Parser/validador SDUI
- Reglas de parseo y validación (tipos, layout, props, estilos).
- Estrategia de fallback y errores (qué mostrar cuando falla).
- Verificación futura: parser diseñado para casos felices y borde.
- Rama: `feat/ht-sdui-f2-parser`.
- Integra servicios de dominio (en diseño): SchemaValidation, NodeVisibility, FlagResolution, EnvironmentResolution, SchemaNormalization, NavigationGuard, StyleResolution.

## F3 – Renderer SDUI → Dui
- Tabla de mapeo SDUI a Dui por nivel (organismo/molécula/átomo) y props.
- Soporte de estilos/tokens y estados.
- Verificación futura: mapeo cubre todos los nodos definidos en el contrato.
- Rama: `feat/ht-sdui-f3-renderer`.
- Consume el AST de dominio (no el DTO), aplica StyleResolution y NavigationGuard para acciones/estilos.

## F4 – Navegación/atajos Make (SDUI)
- Rutas/deeplinks para pantallas SDUI de prueba.
- Targets Make (ej. `run-sdui-sample-X`) para abrir sin interacción manual.
- Verificación futura: comandos y rutas documentados.
- Rama: `feat/ht-sdui-f4-make`.

## F5 – Mockoon samples + casos borde
- Catálogo de JSONs de prueba: casos felices y borde (layout inválido, flags, versiones).
- Verificación futura: lista completa y descrita.
- Rama: `feat/ht-sdui-f5-samples`.

## F6 – Flags/ambientes/inyección de datos
- Cómo SDUI consume flags y ambiente (mock/dev/prod); integración con providers; caching/resolución.
- Verificación futura: flujo descrito y puntos de integración claros.
- Rama: `feat/ht-sdui-f6-flags`.
  - Nota DDD: flags/ambiente se resuelven en dominio; el contrato es un DTO externo (HTTP/GraphQL/mock) que se mapea a un modelo de dominio antes de render.

## F7 – Plan de pruebas SDUI (QA/Dev)
- Checklist de validación: parseo, render, navegación, fallback, flags/ambientes.
- Uso de comandos Make y samples Mockoon para QA/Dev.
- Verificación futura: plan listo para ejecutar.
- Rama: `feat/ht-sdui-f7-testing`.
