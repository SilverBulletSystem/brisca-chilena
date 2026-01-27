# TA-SDUI-F5 — Samples/fixtures + casos borde

## Navegacion
- [Volver a Epica](03-magicsdui/epica.md)
- [Volver a Backlog](03-magicsdui/backlog.md)
- [Diseno detallado](03-magicsdui/diseno-detallado.md)

## Rama
- `feat/ht-sdui-f5-samples`

## Objetivo
Crear samples/fixtures SDUI (agnósticos al transporte) para escenarios felices y casos borde, almacenados como archivos locales para uso con comandos Make y pruebas del renderer. Los samples validan que el parser y renderer funcionan correctamente.

## Alcance
- Archivos de samples en formato JSON (DTO externo).
- Ubicación en recursos del proyecto.
- Cobertura de niveles (organismo/molécula/átomo) y flags/ambientes.
- Casos borde (layout inválido, versiones incompatibles).
- Tests para validar que los samples son parseables.

## 1) Estructura de archivos (nivel 4)

### Ubicación de samples

**Ubicación:** `composeApp/src/commonMain/resources/sdui/screen/`

**Estructura de directorios:**
```
composeApp/src/commonMain/resources/sdui/screen/
├── login.json              # Sample Login (caso feliz)
├── home.json               # Sample Home (caso feliz)
├── errors.json             # Sample pantallas de error
├── invalid.json            # Sample layout inválido (caso borde)
├── version-unsupported.json # Sample versión incompatible (caso borde)
├── game.json               # Sample flujos de juego
├── chat.json               # Sample chat/soporte
└── flags-monitors.json     # Sample flags/monitores
```

**Regla:** Todos los samples deben seguir el formato DTO externo definido en TA-F1 (Contract).

### Formato de archivos

**Extensión:** `.json`
**Encoding:** UTF-8
**Formato:** JSON válido con indentación de 2 espacios

**Estructura base (DTO externo):**
```json
{
  "schemaVersion": "1.0.0",
  "screenId": "<screen_id>",
  "root": {
    "id": "<node_id>",
    "type": "<node_type>",
    "level": "ATOM|MOLECULE|ORGANISM",
    "props": { ... },
    "children": [ ... ],
    "style": { ... },
    "flags": [ ... ],
    "env": [ ... ],
    "state": "enabled|disabled|error|focus|pressed",
    "actions": [ ... ]
  }
}
```

## 2) Contenido de samples (nivel 4)

### Sample: login.json

**Propósito:** Validar renderer con pantalla Login completa (caso feliz).

**Ubicación:** `composeApp/src/commonMain/resources/sdui/screen/login.json`

**Contenido completo:**
```json
{
  "schemaVersion": "1.0.0",
  "screenId": "login",
  "root": {
    "id": "login_root",
    "type": "appScaffold",
    "level": "ORGANISM",
    "children": [
      {
        "id": "login_title",
        "type": "text",
        "level": "ATOM",
        "props": {
          "textKey": "login_title"
        },
        "style": {
          "typographyRole": "title"
        }
      },
      {
        "id": "login_email_field",
        "type": "textField",
        "level": "MOLECULE",
        "props": {
          "labelKey": "login_email",
          "placeholderKey": "login_email_hint"
        }
      },
      {
        "id": "login_password_field",
        "type": "textField",
        "level": "MOLECULE",
        "props": {
          "labelKey": "login_password",
          "placeholderKey": "login_password_hint"
        },
        "state": "default"
      },
      {
        "id": "login_button",
        "type": "button",
        "level": "ATOM",
        "props": {
          "labelKey": "login_cta"
        },
        "actions": [
          {
            "type": "dispatch_intent",
            "target": "login"
          }
        ]
      },
      {
        "id": "login_forgot_password",
        "type": "button",
        "level": "ATOM",
        "props": {
          "labelKey": "login_forgot_password"
        },
        "actions": [
          {
            "type": "navigate",
            "target": "recover"
          }
        ]
      }
    ]
  }
}
```

**Componentes cubiertos:**
- Organismo: `appScaffold`
- Átomos: `text`, `button` (2 tipos)
- Moléculas: `textField` (2 instancias)

**Flags/ambiente:** Ninguno (caso feliz sin condicionales).

**Ruta/Make:** `make run-sdui-screen-login` → `app://sdui?id=login`

**Resultado esperado:** Pantalla Login renderizada correctamente con todos los componentes visibles.

### Sample: home.json

**Propósito:** Validar renderer con pantalla Home completa (organismos, listas, cards, estados).

**Ubicación:** `composeApp/src/commonMain/resources/sdui/screen/home.json`

**Contenido completo:**
```json
{
  "schemaVersion": "1.0.0",
  "screenId": "home",
  "root": {
    "id": "home_root",
    "type": "appScaffold",
    "level": "ORGANISM",
    "children": [
      {
        "id": "user_card",
        "type": "card",
        "level": "MOLECULE",
        "props": {
          "titleKey": "home_user_stats_title",
          "subtitleKey": "home_user_stats_subtitle"
        },
        "children": [
          {
            "id": "user_stats_text",
            "type": "text",
            "level": "ATOM",
            "props": {
              "textKey": "home_user_stats_content"
            }
          }
        ]
      },
      {
        "id": "game_modes_carousel",
        "type": "carousel",
        "level": "ORGANISM",
        "props": {
          "titleKey": "home_game_modes_title"
        },
        "children": [
          {
            "id": "game_mode_1v1",
            "type": "itemCard",
            "level": "MOLECULE",
            "props": {
              "titleKey": "home_game_mode_1v1_title",
              "subtitleKey": "home_game_mode_1v1_subtitle"
            },
            "actions": [
              {
                "type": "navigate",
                "target": "game-1v1"
              }
            ]
          },
          {
            "id": "game_mode_1v1_machine",
            "type": "itemCard",
            "level": "MOLECULE",
            "props": {
              "titleKey": "home_game_mode_1v1_machine_title",
              "subtitleKey": "home_game_mode_1v1_machine_subtitle"
            },
            "actions": [
              {
                "type": "navigate",
                "target": "game-1v1-machine"
              }
            ]
          },
          {
            "id": "game_mode_2v2",
            "type": "itemCard",
            "level": "MOLECULE",
            "props": {
              "titleKey": "home_game_mode_2v2_title",
              "subtitleKey": "home_game_mode_2v2_subtitle"
            },
            "actions": [
              {
                "type": "navigate",
                "target": "game-2v2"
              }
            ]
          }
        ]
      },
      {
        "id": "store_card",
        "type": "card",
        "level": "MOLECULE",
        "props": {
          "titleKey": "home_store_card_title",
          "subtitleKey": "home_store_card_subtitle"
        },
        "actions": [
          {
            "type": "navigate",
            "target": "store"
          }
        ]
      },
      {
        "id": "bottom_nav",
        "type": "bottomNav",
        "level": "ORGANISM",
        "props": {
          "selectedTab": "home"
        },
        "children": [
          {
            "id": "tab_home",
            "type": "button",
            "level": "ATOM",
            "props": {
              "labelKey": "home_tab_home"
            },
            "actions": [
              {
                "type": "navigate",
                "target": "home"
              }
            ]
          },
          {
            "id": "tab_stories",
            "type": "button",
            "level": "ATOM",
            "props": {
              "labelKey": "home_tab_stories"
            },
            "actions": [
              {
                "type": "navigate",
                "target": "stories"
              }
            ]
          },
          {
            "id": "tab_chat",
            "type": "button",
            "level": "ATOM",
            "props": {
              "labelKey": "home_tab_chat"
            },
            "actions": [
              {
                "type": "navigate",
                "target": "chat"
              }
            ]
          }
        ]
      }
    ]
  }
}
```

**Componentes cubiertos:**
- Organismos: `appScaffold`, `carousel`, `bottomNav`
- Moléculas: `card` (2 instancias: user_card, store_card), `itemCard` (3 instancias: modalidades de juego)
- Átomos: `text`, `button` (3 instancias: tabs)

**Flags/ambiente:** Ninguno (caso feliz).

**Ruta/Make:** `make run-sdui-screen-home` → `app://sdui?id=home`

**Resultado esperado:** Pantalla Home renderizada con:
- Card de usuario con estadísticas de partidas
- Carrusel horizontal con 3 modalidades de juego (1 vs máquina, 1 vs 1, 2 vs 2) usando `itemCard`
- Card de tienda con CTA hacia tienda
- Navegación inferior con 3 tabs (HOME, HISTORIAS, CHAT) donde HOME está seleccionado

**Nota:** Este sample valida que el renderer puede renderizar organismos complejos (`carousel`, `bottomNav`), moléculas (`card`, `itemCard`) y la composición de múltiples niveles.

### Sample: errors.json

**Propósito:** Validar renderer con pantallas de error/empty/loading.

**Ubicación:** `composeApp/src/commonMain/resources/sdui/screen/errors.json`

**Contenido completo:**
```json
{
  "schemaVersion": "1.0.0",
  "screenId": "errors",
  "root": {
    "id": "errors_root",
    "type": "appScaffold",
    "level": "ORGANISM",
    "children": [
      {
        "id": "error_state",
        "type": "errorState",
        "level": "ORGANISM",
        "props": {
          "titleKey": "error_state_title",
          "messageKey": "error_state_message"
        },
        "children": [
          {
            "id": "error_retry_button",
            "type": "button",
            "level": "ATOM",
            "props": {
              "labelKey": "error_retry_button"
            },
            "actions": [
              {
                "type": "dispatch_intent",
                "target": "retry"
              }
            ]
          }
        ]
      },
      {
        "id": "empty_state",
        "type": "emptyState",
        "level": "ORGANISM",
        "props": {
          "titleKey": "empty_state_title",
          "messageKey": "empty_state_message"
        }
      },
      {
        "id": "loading_state",
        "type": "loadingState",
        "level": "ORGANISM",
        "props": {
          "messageKey": "loading_state_message"
        }
      }
    ]
  }
}
```

**Componentes cubiertos:**
- Organismos: `appScaffold`, `errorState`, `emptyState`, `loadingState`
- Átomos: `button` (retry en errorState)

**Flags/ambiente:** Ninguno.

**Ruta/Make:** `make run-sdui-screen-errors` → `app://sdui?id=errors`

**Resultado esperado:** Pantalla con tres estados renderizados:
- **ErrorState**: Icono + título + mensaje + botón "Reintentar" que dispara intent `retry`
- **EmptyState**: Icono + título + mensaje (sin acciones)
- **LoadingState**: Spinner + mensaje de carga

**Nota:** Este sample valida que el renderer puede renderizar todos los estados de error/empty/loading definidos en el Design System.

### Sample: invalid.json

**Propósito:** Validar fallback cuando el contrato es inválido (caso borde).

**Ubicación:** `composeApp/src/commonMain/resources/sdui/screen/invalid.json`

**Estructura esperada (inválida intencionalmente):**
```json
{
  "schemaVersion": "1.0.0",
  "screenId": "invalid",
  "root": {
    "id": "invalid_root",
    "type": "unknownType",
    "level": "ORGANISM",
    "children": [
      {
        "id": "missing_props",
        "type": "text",
        "level": "ATOM"
      }
    ]
  }
}
```

**Problemas intencionales:**
- `type: "unknownType"` no existe en el catálogo de tipos.
- Nodo `text` sin `props.textKey` (requerido).

**Componentes cubiertos:** Ninguno válido (caso borde).

**Flags/ambiente:** Ninguno.

**Ruta/Make:** `make run-sdui-screen-invalid` → `app://sdui?id=invalid`

**Resultado esperado:** 
- Parser rechaza el contrato y registra errores en Toad.
- Fallback a pantalla clásica equivalente.

### Sample: version-unsupported.json

**Propósito:** Validar rechazo por versión incompatible (caso borde).

**Ubicación:** `composeApp/src/commonMain/resources/sdui/screen/version-unsupported.json`

**Estructura esperada:**
```json
{
  "schemaVersion": "2.0.0",
  "screenId": "version",
  "root": {
    "id": "version_root",
    "type": "appScaffold",
    "level": "ORGANISM",
    "children": []
  }
}
```

**Problema intencional:**
- `schemaVersion: "2.0.0"` es incompatible (app solo soporta `1.x.x`).

**Componentes cubiertos:** Ninguno (rechazado antes de parsear).

**Flags/ambiente:** Ninguno.

**Ruta/Make:** `make run-sdui-screen-version` → `app://sdui?id=version`

**Resultado esperado:**
- Parser rechaza el contrato por versión incompatible.
- Error registrado en Toad.
- Fallback a pantalla clásica equivalente.

### Sample: game.json (OPCIONAL - Para más adelante)

**Propósito:** Validar renderer con flujos de juego (naipe, hand, score, acciones).

**Ubicación:** `composeApp/src/commonMain/resources/sdui/screen/game.json`

**Estado:** Este sample se definirá en una fase posterior cuando se diseñen los flujos de juego.

**Componentes esperados:**
- Organismos de juego: `cardFace`, `cardHand`, `scoreboard1v1`, `scoreboard2v2`, etc.

**Flags/ambiente:** Por definir.

**Ruta/Make:** `make run-sdui-screen-game` → `app://sdui?id=game`

**Nota:** Este sample se completará cuando se diseñen los flujos de juego en detalle.

### Sample: chat.json (OPCIONAL - Para más adelante)

**Propósito:** Validar renderer con chat/soporte (bubbles, input, typing).

**Ubicación:** `composeApp/src/commonMain/resources/sdui/screen/chat.json`

**Estado:** Este sample se definirá en una fase posterior cuando se diseñen los flujos de chat/soporte.

**Componentes esperados:**
- Organismos: `messageList`, `messageInput`
- Moléculas: `typingIndicator`

**Flags/ambiente:** Por definir.

**Ruta/Make:** `make run-sdui-screen-chat` → `app://sdui?id=chat`

**Nota:** Este sample se completará cuando se diseñen los flujos de chat/soporte en detalle.

### Sample: flags-monitors.json (OPCIONAL - Para más adelante)

**Propósito:** Validar renderer con flags/monitores (flag list, logs, timeline, filtros).

**Ubicación:** `composeApp/src/commonMain/resources/sdui/screen/flags-monitors.json`

**Estado:** Este sample se definirá en una fase posterior cuando se diseñen los monitores de flags.

**Componentes esperados:**
- Organismos: `flagList`, `logList`, `timeline`

**Flags/ambiente:** Por definir.

**Ruta/Make:** `make run-sdui-screen-flags-monitors` → `app://sdui?id=flags-monitors`

**Nota:** Este sample se completará cuando se diseñen los monitores de flags en detalle.

## 3) Implementacion esperada (nivel 4)

### Checklist de implementación

- [ ] Crear directorio `composeApp/src/commonMain/resources/sdui/screen/`.
- [ ] Crear archivo `login.json` con contrato completo de Login.
- [ ] Crear archivo `home.json` con contrato completo de Home.
- [ ] Crear archivo `errors.json` con estados de error/empty/loading.
- [ ] Crear archivo `invalid.json` con contrato inválido intencionalmente.
- [ ] Crear archivo `version-unsupported.json` con versión incompatible.
- [ ] Crear archivo `game.json` con flujos de juego (OPCIONAL - para más adelante).
- [ ] Crear archivo `chat.json` con chat/soporte (OPCIONAL - para más adelante).
- [ ] Crear archivo `flags-monitors.json` con flags/monitores (OPCIONAL - para más adelante).
- [ ] Validar que todos los archivos JSON son válidos (sintaxis correcta).
- [ ] Validar que todos los samples son parseables por el parser (TA-F2).
- [ ] Validar que todos los samples son renderizables por el renderer (TA-F3).
- [ ] Documentar cada sample con propósito, componentes cubiertos, flags/env, ruta/Make y resultado esperado.

### Estructura de paquetes esperada

```
composeApp/src/commonMain/resources/
└── sdui/
    └── screen/
        ├── login.json
        ├── home.json
        ├── errors.json
        ├── invalid.json
        └── version-unsupported.json
        # game.json, chat.json, flags-monitors.json se crearán en fases posteriores
```

## 4) Tests unitarios obligatorios

**Ubicación:** `composeApp/src/commonTest/kotlin/.../sdui/screen/`

### Test: LoadSample_Login_ReturnsValidJson

**Given:**
- Archivo `login.json` en recursos

**Expect:**
- JSON es válido y parseable
- `SduiScreenDto` creado correctamente con `screenId="login"`

### Test: LoadSample_Home_ReturnsValidJson

**Given:**
- Archivo `home.json` en recursos

**Expect:**
- JSON es válido y parseable
- `SduiScreenDto` creado correctamente con `screenId="home"`

### Test: LoadSample_Invalid_ReturnsInvalidJson

**Given:**
- Archivo `invalid.json` en recursos

**Expect:**
- JSON es válido (sintaxis)
- Parser rechaza el contrato (tipo desconocido, props faltantes)

### Test: LoadSample_VersionUnsupported_RejectsContract

**Given:**
- Archivo `version-unsupported.json` en recursos

**Expect:**
- Parser rechaza el contrato por versión incompatible
- Error registrado en `SduiToadRecorder`

### Test: ParseSample_Login_ParsesCorrectly

**Given:**
- Contenido de `login.json`
- `SduiContractParser` (TA-F2)

**Expect:**
- `ParseResult` exitoso
- `Schema` creado con todos los nodos correctos

### Test: RenderSample_Login_RendersCorrectly

**Given:**
- `Schema` parseado de `login.json`
- `SduiRenderer` (TA-F3)

**Expect:**
- Renderer puede renderizar todos los nodos
- No hay errores de render

**Base de tests KMP:**
- **Ubicación:** `composeApp/src/commonTest/kotlin/...`
- **Framework:** `kotlin.test` + `kotlinx.coroutines.test`
- **Comando:** `./gradlew :composeApp:commonTest`
- **Dependencias requeridas en `commonTest.dependencies`:**
  - `libs.kotlin.test`
  - `libs.jetbrains.kotlinx.coroutines.test`
  - `libs.kotlinx.serialization.json` (para parsear JSON)

## 5) Pull Request (contenido esperado)

### Título
```
feat(sdui): samples and fixtures for sdui testing
```

### Incluye
- Directorio `composeApp/src/commonMain/resources/sdui/screen/` creado.
- 5 archivos JSON de samples obligatorios (login, home, errors, invalid, version-unsupported).
- Todos los samples son JSON válidos y parseables.
- Documentación completa de cada sample (propósito, componentes cubiertos, flags/env, ruta/Make, resultado esperado).
- JSON completo para cada sample (no solo estructura, sino contenido detallado con todos los nodos).
- Tests unitarios (6 casos explícitos).
- Validación de que samples son parseables por parser (TA-F2).
- Validación de que samples son renderizables por renderer (TA-F3).

**Nota:** Los samples `game.json`, `chat.json` y `flags-monitors.json` son opcionales y se definirán en fases posteriores. El sample `flags.json` se define en TA-F6 (Flags y ambientes).

### Checklist PR
- [ ] Código compila sin errores.
- [ ] `./gradlew detekt` pasa sin errores.
- [ ] `./gradlew :composeApp:commonTest` pasa todos los tests.
- [ ] Todos los archivos JSON son válidos (sintaxis correcta).
- [ ] Todos los samples son parseables por el parser.
- [ ] Todos los samples (excepto invalid y version-unsupported) son renderizables.
- [ ] Samples invalid y version-unsupported son rechazados correctamente.
- [ ] Sample flags.json se define en TA-F6 (Flags y ambientes).
- [ ] Cada sample tiene documentación completa.

## 6) Notas de implementación

### Dependencias de otras TAs

- **TA-F1 (Contract)**: Los samples deben seguir el formato DTO externo (`SduiScreenDto`, `SduiNodeDto`, `SduiActionDto`).
- **TA-F2 (Parser)**: Los samples deben ser parseables por `SduiContractParser`.
- **TA-F3 (Renderer)**: Los samples deben ser renderizables por `SduiRenderer`.
- **TA-F4 (Make)**: Los samples deben tener rutas Make asociadas (`make run-sdui-screen-*`).

### Samples opcionales para MVP

Los siguientes samples **NO se implementarán en esta historia técnica** y se definirán en fases posteriores:
- `game.json` (flujos de juego) - Se definirá cuando se diseñen los flujos de juego
- `chat.json` (chat/soporte) - Se definirá cuando se diseñen los flujos de chat/soporte
- `flags-monitors.json` (flags/monitores) - Se definirá cuando se diseñen los monitores de flags

**Samples obligatorios para esta historia:**
- `login.json` ✅ (completo)
- `home.json` ✅ (completo)
- `errors.json` ✅ (completo)
- `invalid.json` ✅ (completo)
- `version-unsupported.json` ✅ (completo)

**Nota:** El sample `flags.json` se define en TA-F6 (Flags y ambientes) ya que está directamente relacionado con la funcionalidad de flags y ambientes.

### Validación de samples

Antes de commitear, validar:
1. Sintaxis JSON correcta (usar `jq` o validador online).
2. Parseo exitoso con `SduiContractParser`.
3. Renderizado exitoso con `SduiRenderer` (excepto casos borde).

### Casos borde

Los samples `invalid.json` y `version-unsupported.json` son intencionalmente inválidos para probar:
- Rechazo del parser.
- Registro de errores en Toad.
- Fallback a pantalla clásica equivalente.

### Transporte agnóstico

Los samples están en formato JSON, pero SDUI es agnóstico al transporte. En el futuro, estos samples pueden:
- Servirse desde Mockoon (mock server).
- Servirse desde endpoints dev/prod.
- Leerse desde archivos locales (como ahora).

El parser (TA-F2) debe poder parsear desde cualquier fuente que proporcione el DTO externo.
