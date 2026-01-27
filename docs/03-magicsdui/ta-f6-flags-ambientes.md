# TA-SDUI-F6 — Flags/ambientes/inyección de datos

## Navegacion
- [Volver a Epica](03-magicsdui/epica.md)
- [Volver a Backlog](03-magicsdui/backlog.md)

## Rama
- `feat/ht-sdui-f6-flags`

## Objetivo
Documentar cómo SDUI consume flags y ambiente (mock/dev/prod), integra proveedores y maneja caching/resolución de datos.

## Alcance
- Resolución de flags: interfaz de dominio, múltiples proveedores (JSON local, Firestore u otros), prioridad y fallback.
- Ambiente: mock/dev/prod (seleccionado en cliente), cómo afecta base URL/endpoints SDUI.
- Caching/fetch: orden de lectura, invalidación, tiempos de vida (doc).
- Independencia de transporte: flags/ambiente no dependen del origen (HTTP/GraphQL); se resuelven en dominio antes de render.
- Offline/fixtures: permitir usar último schema válido o fixture local si el fetch falla.

## Diseño (doc)
1) Flags
   - Interfaz de dominio: `FlagsProvider` con métodos sync/async para obtener valores por key.
   - Prioridad: primero JSON local por flavor, luego remoto (Firestore u otro), fallback a default definido en contrato o app.
   - Comportamiento: si flag requerida falta o es false → nodo oculto; variantes opcionales aplican overrides si flag true.
   - Logging: cuando falte flag requerida, log warning.
2) Ambientes
   - Ambiente actual viene del cliente (selector de ambientes, fuera de SDUI). SDUI usa `env` para decidir si muestra nodos y para elegir base URL de fetch del JSON (Mockoon para mock; endpoints dev/prod para otros).
   - Documentar cómo se mapearía `FLAVOR` → base URL (mock→Mockoon, dev/prod→ futuros backends).
3) Datos/caching
   - Orden de resolución: cache en memoria → cache local (opcional) → fetch remoto según ambiente.
   - TTL sugerido y momento de invalidación (doc, sin implementar): p.ej. invalidar al cambiar de ambiente o cada lanzamiento.
   - Retry/backoff documentado para fetch remoto; límite de reintentos y luego fallback.
   - Si fetch falla: usar última versión cacheada o fallback a pantalla clásica equivalente.
4) Integración con SDUI
   - Parser usa flags/ambiente para decidir visibilidad/variantes antes de render.
   - Renderer no vuelve a consultar flags; trabaja con árbol ya filtrado.
   - Contrato SDUI puede incluir `flags` y `env` por nodo; la app resuelve con este flujo.
   - Flags/ambiente se obtienen vía interfaces de dominio; el origen (HTTP/GraphQL) es transparente al parser.
   - En falla de red/origen, usar cache/último schema válido como fallback documentado.

## 3) Sample SDUI para Flags (nivel 4)

### Sample: flags.json

**Propósito:** Validar resolución de flags y ambientes (nodos condicionados).

**Ubicación:** `composeApp/src/commonMain/resources/sdui/screen/flags.json`

**Contenido completo:**
```json
{
  "schemaVersion": "1.0.0",
  "screenId": "flags",
  "root": {
    "id": "flags_root",
    "type": "appScaffold",
    "level": "ORGANISM",
    "children": [
      {
        "id": "always_visible",
        "type": "text",
        "level": "ATOM",
        "props": {
          "textKey": "flags_always_visible_text"
        },
        "style": {
          "typographyRole": "title"
        }
      },
      {
        "id": "flag_conditional_true",
        "type": "text",
        "level": "ATOM",
        "props": {
          "textKey": "flags_feature_x_enabled_text"
        },
        "flags": ["feature_x_enabled"]
      },
      {
        "id": "flag_conditional_false",
        "type": "text",
        "level": "ATOM",
        "props": {
          "textKey": "flags_feature_y_disabled_text"
        },
        "flags": ["feature_y_enabled"]
      },
      {
        "id": "env_conditional_dev_mock",
        "type": "text",
        "level": "ATOM",
        "props": {
          "textKey": "flags_env_dev_mock_text"
        },
        "env": ["dev", "mock"]
      },
      {
        "id": "env_conditional_prod",
        "type": "text",
        "level": "ATOM",
        "props": {
          "textKey": "flags_env_prod_text"
        },
        "env": ["prod"]
      },
      {
        "id": "flag_and_env_conditional",
        "type": "card",
        "level": "MOLECULE",
        "props": {
          "titleKey": "flags_conditional_card_title"
        },
        "flags": ["premium_feature_enabled"],
        "env": ["dev", "prod"]
      }
    ]
  }
}
```

**Componentes cubiertos:**
- Organismos: `appScaffold`
- Moléculas: `card` (condicionada por flag y env)
- Átomos: `text` (múltiples instancias con flags/env)

**Flags/ambiente:** 
- Flags: `feature_x_enabled`, `feature_y_enabled`, `premium_feature_enabled`
- Ambientes: `dev`, `mock`, `prod`

**Ruta/Make:** `make run-sdui-screen-flags` → `app://sdui?id=flags`

**Resultado esperado:** 
- **Nodo siempre visible**: Se renderiza siempre (sin flags/env)
- **Nodo con flag activa**: Se renderiza solo si `feature_x_enabled=true`
- **Nodo con flag inactiva**: No se renderiza si `feature_y_enabled=false`
- **Nodo con env dev/mock**: Se renderiza solo en ambientes `dev` o `mock`
- **Nodo con env prod**: Se renderiza solo en ambiente `prod`
- **Nodo con flag y env**: Se renderiza solo si `premium_feature_enabled=true` Y ambiente es `dev` o `prod`

**Nota:** Este sample valida que el parser filtra correctamente los nodos según flags/env antes de pasar al renderer. El renderer recibe solo los nodos visibles.

## 4) Tests unitarios obligatorios (nivel 4)

**Ubicación:** `composeApp/src/commonTest/kotlin/.../sdui/flags/`

### Test: ResolveFlags_FlagEnabled_NodeVisible

**Given:**
- `Node` con `flags: ["feature_x_enabled"]`
- Flag `feature_x_enabled = true` en `FlagsRepository`
- `NodeVisibilityService` implementado

**Expect:**
- `NodeVisibilityService.isVisible(node)` retorna `true`

### Test: ResolveFlags_FlagDisabled_NodeHidden

**Given:**
- `Node` con `flags: ["feature_x_enabled"]`
- Flag `feature_x_enabled = false` en `FlagsRepository`
- `NodeVisibilityService` implementado

**Expect:**
- `NodeVisibilityService.isVisible(node)` retorna `false`

### Test: ResolveEnv_EnvMatches_NodeVisible

**Given:**
- `Node` con `env: ["dev", "mock"]`
- Ambiente actual: `dev`
- `NodeVisibilityService` implementado

**Expect:**
- `NodeVisibilityService.isVisible(node)` retorna `true`

### Test: ResolveEnv_EnvNotMatches_NodeHidden

**Given:**
- `Node` con `env: ["prod"]`
- Ambiente actual: `dev`
- `NodeVisibilityService` implementado

**Expect:**
- `NodeVisibilityService.isVisible(node)` retorna `false`

**Base de tests KMP:**
- **Ubicación:** `composeApp/src/commonTest/kotlin/...`
- **Framework:** `kotlin.test` + `kotlinx.coroutines.test`
- **Comando:** `./gradlew :composeApp:commonTest`
- **Dependencias requeridas en `commonTest.dependencies`:**
  - `libs.kotlin.test`
  - `libs.jetbrains.kotlinx.coroutines.test`

## Entregables (documento)
- Flujo de resolución de flags y ambiente.
- Puntos de integración con proveedores y selector de ambientes.
- Sample SDUI `flags.json` para validar resolución de flags y ambientes.
- Tests unitarios (4 casos explícitos).

## Verificación futura
- Claridad de cómo SDUI decide flags/ambiente y qué hacer si faltan datos.
- Prioridad de proveedores y fallback documentados.
- Impacto en base URL/endpoints según ambiente definido.

## No incluido
- Implementación de proveedores ni de caching.