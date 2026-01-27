# TA-SDUI-F10 — Integration Test SDUI (nivel 4)

## Navegacion
- [Volver a Epica](03-magicsdui/epica.md)
- [Volver a Backlog](03-magicsdui/backlog.md)
- [Diseno detallado](03-magicsdui/diseno-detallado.md)

## Rama
- `feat/ht-sdui-f10-integration-test`

## Objetivo
Implementar tests de integración específicos para validar el flujo completo SDUI end-to-end (fetch → parse → render), fallback y registro en logs. Los tests deben validar que SDUI funciona correctamente y que el fallback a pantallas clásicas funciona cuando SDUI falla.

**Nota:** 
- Los tests unitarios de parser están en TA-F2.
- Los tests unitarios de renderer están en TA-F3.
- Los tests unitarios de flags/ambientes están en TA-F6.
- Los tests específicos de cada pantalla (Login, Splash, Home) están en TA-F7, TA-F8, TA-F9.
- Esta historia se enfoca en tests de integración y validación de comandos Make.

## Alcance
- Tests de integración para flujo completo SDUI (fetch → parse → render).
- Tests de fallback cuando SDUI falla (genéricos, no específicos de pantalla).
- Tests de registro en Toad (abstracción genérica).
- Validación de comandos Make para abrir pantallas SDUI.

**Nota:** Los tests unitarios de parser, renderer y flags/ambientes están definidos en sus respectivas historias técnicas (TA-F2, TA-F3, TA-F6).

## 1) Tests de integración - Flujo completo SDUI (nivel 4)

**Ubicación:** `composeApp/src/commonTest/kotlin/.../sdui/integration/`

### Test: SduiFlow_ValidContract_RendersSuccessfully

**Given:**
- Contrato SDUI válido de Login (`login.json`)
- `SduiFetchSchema` que retorna el contrato
- `SduiContractParser` implementado
- `SduiRenderer` implementado
- Mocks de `StringsProvider`, `SduiActionHandler`

**Expect:**
- Fetch retorna contrato válido
- Parser parsea exitosamente
- Renderer renderiza todos los nodos
- Sin errores en ningún paso

### Test: SduiFlow_FetchFails_FallbackToClassic

**Given:**
- `SduiFetchSchema` que lanza excepción
- `LoginRouterComponent` con flag SDUI habilitado
- Mock de `LoginComponent` (clásico)

**Expect:**
- Se intenta fetch del contrato SDUI
- Al fallar, se hace fallback a pantalla clásica
- Error registrado en `SduiToadRecorder`

### Test: SduiFlow_ParseFails_FallbackToClassic

**Given:**
- Contrato SDUI inválido (versión no soportada)
- `SduiFetchSchema` que retorna el contrato inválido
- `SduiContractParser` que rechaza el contrato
- `LoginRouterComponent` con flag SDUI habilitado
- Mock de `LoginComponent` (clásico)

**Expect:**
- Fetch retorna contrato
- Parser rechaza el contrato
- Se hace fallback a pantalla clásica
- Error registrado en `SduiToadRecorder`

### Test: SduiFlow_RenderFails_FallbackToClassic

**Given:**
- Contrato SDUI válido pero con nodo no renderizable
- `SduiFetchSchema` que retorna el contrato
- `SduiContractParser` que parsea exitosamente
- `SduiRenderer` que falla al renderizar nodo raíz
- `LoginRouterComponent` con flag SDUI habilitado
- Mock de `LoginComponent` (clásico)

**Expect:**
- Fetch y parse exitosos
- Render falla
- Se hace fallback a pantalla clásica
- Error registrado en `SduiToadRecorder`

## 2) Tests de registro en logs (nivel 4)

**Nota:** Los tests de registro específicos de cada pantalla (Login, Splash, Home) ya están definidos en sus respectivas historias técnicas (TA-F7, TA-F8, TA-F9). Estos tests son solo para validar las abstracciones genéricas.

**Ubicación:** `composeApp/src/commonTest/kotlin/.../sdui/logging/`

### Test: ToadRecorder_ErrorOccurs_RecordsError

**Given:**
- `SduiToadRecorderImpl` (implementación temporal)
- Error de parser: `SduiError(type = IncompatibleVersion, reason = "Version 2.0.0 not supported")`

**Expect:**
- Se llama a `toadRecorder.recordError("login", "parse", error)`
- Error se registra (log a consola en implementación temporal)
- Estructura del error incluye: screenId, tipo, razón

## 3) Tests de comandos Make (nivel 4)

**Ubicación:** `composeApp/src/commonTest/kotlin/.../sdui/make/`

### Test: MakeCommand_Home_OpensSduiScreen

**Given:**
- Comando Make: `make run-sdui-screen-home`
- `SduiDeeplinkParser` implementado

**Expect:**
- URI `app://sdui?id=home` se parsea correctamente
- `SduiScreenConfig.Screen(SduiScreenId("home"))` se crea
- Navegación hacia pantalla Home SDUI

### Test: MakeCommand_Login_OpensSduiScreen

**Given:**
- Comando Make: `make run-sdui-screen-login`
- `SduiDeeplinkParser` implementado

**Expect:**
- URI `app://sdui?id=login` se parsea correctamente
- `SduiScreenConfig.Screen(SduiScreenId("login"))` se crea
- Navegación hacia pantalla Login SDUI

### Test: MakeCommand_InvalidScreenId_FallbackToHome

**Given:**
- Comando Make: `make run-sdui-screen-invalid`
- `SduiDeeplinkParser` implementado

**Expect:**
- URI `app://sdui?id=invalid` se parsea
- Fallback a `SduiScreenConfig.Screen(SduiScreenId("home"))` o pantalla clásica
- Error registrado en `SduiToadRecorder`

## 7) Implementacion esperada (nivel 4)

### Checklist de implementación

- [ ] Crear tests unitarios para parser (6 casos explícitos).
- [ ] Crear tests unitarios para renderer (5 casos explícitos).
- [ ] Crear tests unitarios para flags/ambientes (4 casos explícitos).
- [ ] Crear tests de integración para flujo completo SDUI (4 casos explícitos).
- [ ] Crear tests de registro en logs (1 caso explícito - solo abstracción genérica de Toad).
- [ ] Crear tests de comandos Make (3 casos explícitos).
- [ ] Configurar mocks para `SduiFetchSchema`, `SduiRenderer`, `SduiContractParser`.
- [ ] Configurar mocks para `FlagsRepository`, `StringsProvider`, `SduiActionHandler`.
- [ ] Configurar mocks para `SduiToadRecorder` (implementación temporal).
- [ ] Validar que todos los tests pasan (`./gradlew :composeApp:commonTest`).

### Estructura de paquetes esperada

```
composeApp/src/commonTest/kotlin/.../sdui/
├── integration/
│   ├── SduiFlowTest.kt
│   └── FallbackTest.kt
├── logging/
│   └── ToadRecorderTest.kt
└── make/
    └── MakeCommandTest.kt
```

## 5) Pull Request (contenido esperado)

### Título
```
test(sdui): integration tests for sdui flow and make commands
```

### Incluye
- Tests de integración para flujo completo SDUI (4 casos).
- Tests de registro en logs (1 caso - solo abstracción genérica de Toad).
- Tests de comandos Make (3 casos).
- Mocks configurados para todas las dependencias.
- **Nota:** 
  - Los tests unitarios de parser están en TA-F2.
  - Los tests unitarios de renderer están en TA-F3.
  - Los tests unitarios de flags/ambientes están en TA-F6.
  - Los tests de pantallas específicas (Login, Splash, Home) están en TA-F7, TA-F8, TA-F9.
- Total: 8 casos de test explícitos (solo integración, logs y Make).

### Checklist PR
- [ ] Código compila sin errores.
- [ ] `./gradlew detekt` pasa sin errores.
- [ ] `./gradlew :composeApp:commonTest` pasa todos los tests (8 casos).
- [ ] Todos los tests son de integración o validación de comandos Make.
- [ ] Tests validan flujo completo SDUI, fallback y registro en logs.
- [ ] Mocks están correctamente configurados.
- [ ] Tests son independientes y pueden ejecutarse en cualquier orden.

## 6) Notas de implementación

### Dependencias de otras TAs

- **TA-F0 (Domain)**: Entidades y value objects deben estar implementados.
- **TA-F1 (Contract)**: DTOs externos deben estar definidos.
- **TA-F2 (Parser)**: `SduiContractParser` debe estar implementado con sus tests unitarios.
- **TA-F3 (Renderer)**: `SduiRenderer` debe estar implementado con sus tests unitarios.
- **TA-F4 (Make)**: Comandos Make deben estar implementados.
- **TA-F5 (Samples)**: Samples JSON deben estar disponibles.
- **TA-F6 (Flags)**: Resolución de flags/ambientes debe estar implementada con sus tests unitarios.
- **TA-F7 (Login)**: Pantalla Login debe estar implementada con sus tests.
- **TA-F8 (Splash)**: Pantalla Splash debe estar implementada con sus tests.
- **TA-F9 (Home)**: Pantalla Home debe estar implementada con sus tests.

### Mocks necesarios

- `SduiFetchSchema`: Mock que retorna contratos SDUI o lanza excepciones.
- `SduiContractParser`: Mock o implementación real para tests de integración.
- `SduiRenderer`: Mock o implementación real para tests de integración.
- `FlagsRepository`: Mock que retorna flags según configuración del test.
- `StringsProvider`: Mock que retorna strings según keys.
- `SduiActionHandler`: Mock que registra acciones ejecutadas.
- `SduiToadRecorder`: Implementación temporal (log a consola) o mock.

### Cobertura esperada

Los tests deben cubrir:
- ✅ Flujo completo SDUI end-to-end (fetch → parse → render)
- ✅ Fallback a pantallas clásicas cuando SDUI falla (genérico)
- ✅ Registro correcto de errores (abstracción genérica de Toad)
- ✅ Comandos Make funcionan correctamente

**Nota:** 
- Los tests de parseo están en TA-F2.
- Los tests de render están en TA-F3.
- Los tests de flags/ambientes están en TA-F6.
- Los tests de alternancia entre SDUI y clásico según flags, y los tests de registro de navegaciones/renderes específicos de cada pantalla, están en TA-F7 (Login), TA-F8 (Splash) y TA-F9 (Home).

### Base de tests KMP

- **Ubicación:** `composeApp/src/commonTest/kotlin/...`
- **Framework:** `kotlin.test` + `kotlinx.coroutines.test`
- **Comando:** `./gradlew :composeApp:commonTest`
- **Dependencias requeridas en `commonTest.dependencies`:**
  - `libs.kotlin.test`
  - `libs.jetbrains.kotlinx.coroutines.test`
  - `libs.kotlinx.serialization.json` (para parsear JSON de samples)
