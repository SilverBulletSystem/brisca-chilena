# TA-SDUI-F4 — Navegación/atajos Make (SDUI)

## Navegacion
- [Volver a Epica](03-magicsdui/epica.md)
- [Volver a Backlog](03-magicsdui/backlog.md)
- [Diseno detallado](03-magicsdui/diseno-detallado.md)

## Rama
- `feat/ht-sdui-f4-make`

## Objetivo
Definir rutas/deeplinks y comandos Make para abrir pantallas SDUI de prueba sin interacción manual, integrando con Decompose Navigation.

## Alcance
- Rutas internas para samples SDUI (`app://sdui?id=...`).
- Parser de deeplinks SDUI.
- Integración con Decompose Navigation.
- Targets Make que lancen la app en cada ruta.
- Estructura del Makefile.

## 1) Interfaces y firmas (nivel 4)

### Parser de deeplinks SDUI

**Ubicación:** `core/sdui/presentation/navigation/`

```kotlin
interface SduiDeeplinkParser {
  fun parseSduiUri(uri: String): SduiScreenId?
}

data class SduiScreenId(val value: String)
```

**Responsabilidades:**
- Parsear URIs con formato `app://sdui?id=<screenId>`.
- Extraer el parámetro `id` y validar que sea un `screenId` válido.
- Retornar `null` si el URI no es SDUI o si el formato es inválido.

**Reglas de parseo:**
- `app://sdui?id=home` → `SduiScreenId("home")`
- `app://sdui?id=login` → `SduiScreenId("login")`
- `app://sdui` (sin `id`) → `null`
- `app://sdui?id=` (vacío) → `null`
- `app://home` (no SDUI) → `null`

### Configuración de navegación SDUI

**Ubicación:** `features/sdui/presentation/navigation/`

```kotlin
sealed class SduiScreenConfig {
  data class Screen(val screenId: SduiScreenId) : SduiScreenConfig()
}
```

**Regla:** Cada `SduiScreenConfig.Screen` mapea 1:1 a una pantalla SDUI renderizada por `MagicsDuiRenderScreen`.

### Stack y navegación Decompose

**Estructura esperada:**
```kotlin
val sduiNavigation = StackNavigation<SduiScreenConfig>()

val sduiChildStack = childStack(
  source = sduiNavigation,
  initialConfiguration = SduiScreenConfig.Screen(SduiScreenId("home")),
  handleBackButton = true
) { config, ctx ->
  when (config) {
    is SduiScreenConfig.Screen -> 
      SduiScreenComponent(
        componentContext = ctx,
        screenId = config.screenId,
        sduiRenderer = get(),
        sduiFetchSchema = get()
      )
  }
}
```

### Mapeo URI → configuración

**Función pura:**
```kotlin
fun parseSduiScreenUri(uri: String): SduiScreenConfig? {
  val parser: SduiDeeplinkParser = get()
  val screenId = parser.parseSduiUri(uri) ?: return null
  return SduiScreenConfig.Screen(screenId)
}
```

**Reglas explícitas:**
- `app://sdui?id=home` → `SduiScreenConfig.Screen(SduiScreenId("home"))`
- `app://sdui?id=login` → `SduiScreenConfig.Screen(SduiScreenId("login"))`
- `app://sdui?id=stories` → `SduiScreenConfig.Screen(SduiScreenId("stories"))`
- `app://sdui?id=errors` → `SduiScreenConfig.Screen(SduiScreenId("errors"))`
- `app://sdui?id=game` → `SduiScreenConfig.Screen(SduiScreenId("game"))`
- `app://sdui?id=chat` → `SduiScreenConfig.Screen(SduiScreenId("chat"))`
- `app://sdui?id=flags` → `SduiScreenConfig.Screen(SduiScreenId("flags"))`
- `app://sdui?id=invalid` → `SduiScreenConfig.Screen(SduiScreenId("invalid"))` (caso borde)
- `app://sdui?id=version` → `SduiScreenConfig.Screen(SduiScreenId("version"))` (caso borde)
- Cualquier URI que no coincida → `null` (fallback a pantalla por defecto)

### API de navegación interna

**Métodos esperados:**
```kotlin
fun navigateToSduiScreen(screenId: SduiScreenId) {
  val config = SduiScreenConfig.Screen(screenId)
  sduiNavigation.bringToFront(config)
}

fun openSduiUri(uri: String) {
  val config = parseSduiScreenUri(uri) 
    ?: SduiScreenConfig.Screen(SduiScreenId("home")) // fallback
  sduiNavigation.bringToFront(config)
}
```

## 2) Estructura del Makefile

**Ubicación:** `Makefile` (raíz del proyecto)

**Targets obligatorios:**
```makefile
# SDUI Samples
.PHONY: run-sdui-screen-home
run-sdui-screen-home:
	./gradlew :composeApp:installDebug -Penv=mock
	adb shell am start -a android.intent.action.VIEW -d "app://sdui?id=home" cl.silverbullet.multiplatform.brisca

.PHONY: run-sdui-screen-login
run-sdui-screen-login:
	./gradlew :composeApp:installDebug -Penv=mock
	adb shell am start -a android.intent.action.VIEW -d "app://sdui?id=login" cl.silverbullet.multiplatform.brisca

.PHONY: run-sdui-screen-stories
run-sdui-screen-stories:
	./gradlew :composeApp:installDebug -Penv=mock
	adb shell am start -a android.intent.action.VIEW -d "app://sdui?id=stories" cl.silverbullet.multiplatform.brisca

.PHONY: run-sdui-screen-errors
run-sdui-screen-errors:
	./gradlew :composeApp:installDebug -Penv=mock
	adb shell am start -a android.intent.action.VIEW -d "app://sdui?id=errors" cl.silverbullet.multiplatform.brisca

.PHONY: run-sdui-screen-game
run-sdui-screen-game:
	./gradlew :composeApp:installDebug -Penv=mock
	adb shell am start -a android.intent.action.VIEW -d "app://sdui?id=game" cl.silverbullet.multiplatform.brisca

.PHONY: run-sdui-screen-chat
run-sdui-screen-chat:
	./gradlew :composeApp:installDebug -Penv=mock
	adb shell am start -a android.intent.action.VIEW -d "app://sdui?id=chat" cl.silverbullet.multiplatform.brisca

.PHONY: run-sdui-screen-flags
run-sdui-screen-flags:
	./gradlew :composeApp:installDebug -Penv=mock
	adb shell am start -a android.intent.action.VIEW -d "app://sdui?id=flags" cl.silverbullet.multiplatform.brisca

.PHONY: run-sdui-screen-invalid
run-sdui-screen-invalid:
	./gradlew :composeApp:installDebug -Penv=mock
	adb shell am start -a android.intent.action.VIEW -d "app://sdui?id=invalid" cl.silverbullet.multiplatform.brisca

.PHONY: run-sdui-screen-version
run-sdui-screen-version:
	./gradlew :composeApp:installDebug -Penv=mock
	adb shell am start -a android.intent.action.VIEW -d "app://sdui?id=version" cl.silverbullet.multiplatform.brisca
```

**Notas:**
- Todos los targets usan `-Penv=mock` por defecto (flavor mock).
- Cada target instala la app y abre el deeplink correspondiente.
- El formato de deeplink es `app://sdui?id=<screenId>`.
- Para iOS, los targets deben usar `xcodebuild` y `xcrun simctl` (verificar en proyecto base).

**Variable de flavor (opcional):**
```makefile
FLAVOR ?= mock

run-sdui-screen-home:
	./gradlew :composeApp:installDebug -Penv=$(FLAVOR)
	adb shell am start -a android.intent.action.VIEW -d "app://sdui?id=home" cl.silverbullet.multiplatform.brisca
```

## 3) Implementacion esperada (nivel 4)

### Checklist de implementación

- [ ] Crear `SduiDeeplinkParser` interface en `core/sdui/presentation/navigation/`.
- [ ] Crear `SduiDeeplinkParserImpl` que implementa el parser con regex/URI parsing.
- [ ] Crear `SduiScreenId` value class en `core/sdui/domain/`.
- [ ] Crear `SduiScreenConfig` sealed class en `features/sdui/presentation/navigation/`.
- [ ] Crear `SduiScreenComponent` que recibe `screenId` y renderiza via `MagicsDuiRenderScreen`.
- [ ] Configurar `StackNavigation<SduiScreenConfig>` y `childStack` en el componente raíz SDUI.
- [ ] Implementar `parseSduiScreenUri(uri: String): SduiScreenConfig?`.
- [ ] Implementar `navigateToSduiScreen(screenId: SduiScreenId)`.
- [ ] Implementar `openSduiUri(uri: String)` con fallback.
- [ ] Agregar targets Make al `Makefile` (8 targets: home, login, stories, errors, game, chat, flags, invalid, version).
- [ ] Configurar Koin para inyectar `SduiDeeplinkParser` y dependencias de `SduiScreenComponent`.
- [ ] Integrar `openSduiUri` con el router principal de la app (cuando se recibe deeplink externo).

### Estructura de paquetes esperada

```
core/sdui/
├── domain/
│   └── SduiScreenId.kt
└── presentation/
    └── navigation/
        └── SduiDeeplinkParser.kt

features/sdui/
└── presentation/
    ├── component/
    │   └── SduiScreenComponent.kt
    └── navigation/
        ├── SduiScreenConfig.kt
        └── SduiNavigation.kt
```

## 4) Tests unitarios obligatorios

**Ubicación:** `composeApp/src/commonTest/kotlin/.../sdui/navigation/`

### Test: ParseSduiUri_ValidFormat_ReturnsScreenId

**Given:**
- URI: `"app://sdui?id=home"`

**Expect:**
- `SduiScreenId("home")`

### Test: ParseSduiUri_ValidFormat_WithDifferentScreenId_ReturnsCorrectScreenId

**Given:**
- URI: `"app://sdui?id=login"`

**Expect:**
- `SduiScreenId("login")`

### Test: ParseSduiUri_MissingId_ReturnsNull

**Given:**
- URI: `"app://sdui"`

**Expect:**
- `null`

### Test: ParseSduiUri_EmptyId_ReturnsNull

**Given:**
- URI: `"app://sdui?id="`

**Expect:**
- `null`

### Test: ParseSduiUri_NonSduiUri_ReturnsNull

**Given:**
- URI: `"app://home"`

**Expect:**
- `null`

### Test: ParseSduiUri_InvalidFormat_ReturnsNull

**Given:**
- URI: `"app://sdui?screen=home"` (parámetro incorrecto)

**Expect:**
- `null`

### Test: ParseSduiScreenUri_ValidUri_ReturnsConfig

**Given:**
- URI: `"app://sdui?id=home"`

**Expect:**
- `SduiScreenConfig.Screen(SduiScreenId("home"))`

### Test: ParseSduiScreenUri_InvalidUri_ReturnsNull

**Given:**
- URI: `"app://home"`

**Expect:**
- `null`

### Test: OpenSduiUri_ValidUri_NavigatesToScreen

**Given:**
- URI: `"app://sdui?id=login"`
- Navigation stack inicializado

**Expect:**
- `sduiNavigation.bringToFront` llamado con `SduiScreenConfig.Screen(SduiScreenId("login"))`

### Test: OpenSduiUri_InvalidUri_FallbackToHome

**Given:**
- URI: `"app://invalid"`
- Navigation stack inicializado

**Expect:**
- `sduiNavigation.bringToFront` llamado con `SduiScreenConfig.Screen(SduiScreenId("home"))`

**Base de tests KMP:**
- **Ubicación:** `composeApp/src/commonTest/kotlin/...`
- **Framework:** `kotlin.test` + `kotlinx.coroutines.test`
- **Comando:** `./gradlew :composeApp:commonTest`
- **Dependencias requeridas en `commonTest.dependencies`:**
  - `libs.kotlin.test`
  - `libs.jetbrains.kotlinx.coroutines.test`

## 5) Pull Request (contenido esperado)

### Título
```
feat(sdui): navigation and make targets for sdui samples
```

### Incluye
- Implementación de `SduiDeeplinkParser` y `SduiDeeplinkParserImpl`.
- `SduiScreenId` value class.
- `SduiScreenConfig` sealed class y navegación Decompose.
- `SduiScreenComponent` que renderiza pantallas SDUI.
- Funciones `parseSduiScreenUri`, `navigateToSduiScreen`, `openSduiUri`.
- 8 targets Make en `Makefile` (home, login, stories, errors, game, chat, flags, invalid, version).
- Configuración Koin para inyección de dependencias.
- Tests unitarios (10 casos explícitos).
- Integración con router principal (wiring de deeplinks externos).

### Checklist PR
- [ ] Código compila sin errores.
- [ ] `./gradlew detekt` pasa sin errores.
- [ ] `./gradlew :composeApp:commonTest` pasa todos los tests.
- [ ] Cada target Make funciona correctamente (abre la pantalla SDUI correspondiente).
- [ ] Deeplinks `app://sdui?id=...` navegan correctamente.
- [ ] Fallback a home funciona cuando URI es inválida.

## 6) Notas de implementación

### Integración con router principal

El router principal de la app debe:
1. Recibir deeplinks externos (intent filters en Android, URL schemes en iOS).
2. Llamar a `openSduiUri(uri)` cuando el deeplink es SDUI.
3. Manejar fallback si el deeplink no es SDUI.

### Casos borde

- **`screenId=invalid`**: Debe hacer fallback a pantalla clásica equivalente (si existe) o mostrar error genérico.
- **`screenId=version`**: Debe probar rechazo por versión incompatible (definido en TA-F2).

### Dependencias de TA-F3

Esta historia asume que `MagicsDuiRenderScreen` y `SduiRenderer` están implementados (TA-F3). Si no, `SduiScreenComponent` puede usar un placeholder temporal.

### iOS

Los targets Make para iOS deben usar `xcodebuild` y `xcrun simctl openurl`. Verificar en proyecto base (`ipchile-multiplatform`) cómo se manejan los deeplinks en iOS.
