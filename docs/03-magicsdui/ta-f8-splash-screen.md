# TA-SDUI-F8 — Splash Screen (clásica y SDUI)

## Navegacion
- [Volver a Epica](03-magicsdui/epica.md)
- [Volver a Backlog](03-magicsdui/backlog.md)
- [Diseno detallado](03-magicsdui/diseno-detallado.md)

## Rama
- `feat/ht-sdui-f8-splash-screen`

## Objetivo
Implementar pantalla Splash en dos variantes (clásica y SDUI) que valida versión, mantenimiento, seguridad, red y sesión, luego enruta según flags. La decisión entre variantes se hace mediante feature flag. Todas las navegaciones y renders se registran en log temporal (futuro Inkribbon).

## Alcance
- Pantalla Splash clásica (con Dui).
- Pantalla Splash SDUI (usando renderer).
- Feature flag para alternar entre ambas.
- Strings i18n para Splash.
- Contrato SDUI para Splash (screenId="splash").
- Lógica de validación (versión, mantenimiento, seguridad, red, sesión).
- Enrutamiento según validaciones.
- Integración con navegación Decompose.
- Registro de navegaciones y renders en log temporal (SduiInkribbonRecorder).
- Tests para validar ambas variantes.

## Wireframe/Mockup

![Splash Screen Wireframe](../assets/splash-wireframe.png)

**Diseño esperado:**
- Título "Brisca Chilena" centrado en la parte superior
- Mensaje de estado centrado debajo (ej: "Verificando versión", "Verificando conexión", etc.)
- Indicador de progreso circular debajo del mensaje
- Layout centrado verticalmente con espaciado consistente (ThemeDimens)
- Diseño minimalista y limpio
- Ambos enfoques (clásico y SDUI) deben renderizar el mismo diseño visual

## 1) Interfaces y firmas (nivel 4)

### Strings i18n para Splash

**Ubicación:** `features/splash/presentation/strings/`

```kotlin
interface SplashStrings {
  val appName: String
  val loadingMessage: String
  val checkingVersion: String
  val checkingMaintenance: String
  val checkingSecurity: String
  val checkingNetwork: String
  val checkingSession: String
}

// Implementación ES
class SplashStringsEs : SplashStrings {
  override val appName = "Brisca Chilena"
  override val loadingMessage = "Cargando..."
  override val checkingVersion = "Verificando versión"
  override val checkingMaintenance = "Verificando mantenimiento"
  override val checkingSecurity = "Verificando seguridad"
  override val checkingNetwork = "Verificando conexión"
  override val checkingSession = "Verificando sesión"
}

// Implementación EN
class SplashStringsEn : SplashStrings {
  override val appName = "Brisca Chilena"
  override val loadingMessage = "Loading..."
  override val checkingVersion = "Checking version"
  override val checkingMaintenance = "Checking maintenance"
  override val checkingSecurity = "Checking security"
  override val checkingNetwork = "Checking connection"
  override val checkingSession = "Checking session"
}
```

**Regla:** Los keys i18n del contrato SDUI deben mapear a estas propiedades:
- `splash_app_name` → `appName`
- `splash_loading` → `loadingMessage`
- `splash_checking_version` → `checkingVersion`
- `splash_checking_maintenance` → `checkingMaintenance`
- `splash_checking_security` → `checkingSecurity`
- `splash_checking_network` → `checkingNetwork`
- `splash_checking_session` → `checkingSession`

### Componente Splash clásico

**Ubicación:** `features/splash/presentation/component/`

```kotlin
class SplashComponent(
  componentContext: ComponentContext,
  private val checkVersion: CheckVersion, // Use case
  private val checkMaintenance: CheckMaintenance, // Use case
  private val checkSecurity: CheckSecurity, // Use case
  private val checkNetwork: CheckNetwork, // Use case
  private val checkSession: CheckSession, // Use case
  private val navigator: Navigator,
  private val strings: SplashStrings,
  private val inkribbonRecorder: SduiInkribbonRecorder
) : ComponentContext by componentContext {
  
  private val state = MutableValue(SplashState())
  val stateValue: Value<SplashState> = state
  
  suspend fun validateAndNavigate() {
    // 1. Validar versión
    state.value = state.value.copy(status = "checking_version")
    val versionResult = checkVersion()
    if (versionResult.isFailure) {
      navigateToForceUpdate()
      return
    }
    
    // 2. Validar mantenimiento
    state.value = state.value.copy(status = "checking_maintenance")
    val maintenanceResult = checkMaintenance()
    if (maintenanceResult.isFailure) {
      navigateToMaintenance()
      return
    }
    
    // 3. Validar seguridad
    state.value = state.value.copy(status = "checking_security")
    val securityResult = checkSecurity()
    if (securityResult.isFailure) {
      navigateToSecurityError()
      return
    }
    
    // 4. Validar red
    state.value = state.value.copy(status = "checking_network")
    val networkResult = checkNetwork()
    if (networkResult.isFailure) {
      navigateToOffline()
      return
    }
    
    // 5. Validar sesión
    state.value = state.value.copy(status = "checking_session")
    val sessionResult = checkSession()
    
    // 6. Navegar según sesión
    if (sessionResult.isSuccess) {
      navigateToHome()
    } else {
      navigateToLogin()
    }
  }
  
  private fun navigateToForceUpdate() {
    inkribbonRecorder.recordNavigation("splash", "force-update", "classic")
    navigator.navigate("app://force-update")
  }
  
  private fun navigateToMaintenance() {
    inkribbonRecorder.recordNavigation("splash", "maintenance", "classic")
    navigator.navigate("app://maintenance")
  }
  
  private fun navigateToSecurityError() {
    inkribbonRecorder.recordNavigation("splash", "security-error", "classic")
    navigator.navigate("app://security-error")
  }
  
  private fun navigateToOffline() {
    inkribbonRecorder.recordNavigation("splash", "offline", "classic")
    navigator.navigate("app://offline")
  }
  
  private fun navigateToHome() {
    inkribbonRecorder.recordNavigation("splash", "home", "classic")
    navigator.navigate("app://home")
  }
  
  private fun navigateToLogin() {
    inkribbonRecorder.recordNavigation("splash", "login", "classic")
    navigator.navigate("app://login")
  }
}

data class SplashState(
  val status: String = "idle",
  val isLoading: Boolean = true
)
```

### Pantalla Splash clásica (Compose)

**Ubicación:** `features/splash/presentation/compose/`

```kotlin
@Composable
fun SplashScreen(
  state: SplashState,
  strings: SplashStrings
) {
  DuiAppScaffold {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(ThemeDimens.NORMAL_SPACING),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      DuiTitleText(
        text = strings.appName,
        modifier = Modifier.padding(bottom = ThemeDimens.LARGE_SPACING)
      )
      
      DuiBodyText(
        text = when (state.status) {
          "checking_version" -> strings.checkingVersion
          "checking_maintenance" -> strings.checkingMaintenance
          "checking_security" -> strings.checkingSecurity
          "checking_network" -> strings.checkingNetwork
          "checking_session" -> strings.checkingSession
          else -> strings.loadingMessage
        }
      )
      
      DuiSpacer(size = ThemeDimens.NORMAL_SPACING)
      
      DuiProgress()
    }
  }
}
```

### Feature Flag para Splash SDUI

**Ubicación:** `features/splash/presentation/`

```kotlin
interface SplashFlagProvider {
  fun isSduiEnabled(): Boolean
}

class SplashFlagProviderImpl(
  private val flagsRepository: FlagsRepository
) : SplashFlagProvider {
  override fun isSduiEnabled(): Boolean {
    return flagsRepository.getFlag(
      key = FlagKey("sdui_splash_enabled"),
      expectedType = FlagType.Boolean,
      default = false
    ).value as? Boolean ?: false
  }
}
```

**Regla:** Inicialmente el flag será hardcodeado a `false` (pantalla clásica por defecto). Cuando exista el módulo de Flags (Epica 06), se usará `SplashFlagProvider`.

### Decisión de pantalla (clásica vs SDUI)

**Ubicación:** `features/splash/presentation/component/`

```kotlin
class SplashRouterComponent(
  componentContext: ComponentContext,
  private val flagProvider: SplashFlagProvider,
  private val sduiRenderer: SduiRenderer,
  private val sduiFetchSchema: SduiFetchSchema,
  private val checkVersion: CheckVersion,
  private val checkMaintenance: CheckMaintenance,
  private val checkSecurity: CheckSecurity,
  private val checkNetwork: CheckNetwork,
  private val checkSession: CheckSession,
  private val navigator: Navigator,
  private val strings: SplashStrings,
  private val inkribbonRecorder: SduiInkribbonRecorder,
  private val toadRecorder: SduiToadRecorder
) : ComponentContext by componentContext {
  
  @Composable
  fun Content() {
    val isSduiEnabled = flagProvider.isSduiEnabled()
    
    if (isSduiEnabled) {
      MagicsDuiRenderScreen(
        screenId = SduiScreenId("splash"),
        renderer = sduiRenderer,
        fetchSchema = sduiFetchSchema,
        onAction = { action ->
          when (action.type) {
            ActionType.DispatchIntent -> {
              handleIntent(action.target.value)
            }
            // ... otros tipos
          }
        },
        onRenderComplete = {
          inkribbonRecorder.recordRender("splash", "sdui", true)
        },
        onRenderError = { error ->
          inkribbonRecorder.recordRender("splash", "sdui", false)
          toadRecorder.recordError("splash", "render", error)
        }
      )
    } else {
      SplashComponent(
        componentContext = this,
        checkVersion = checkVersion,
        checkMaintenance = checkMaintenance,
        checkSecurity = checkSecurity,
        checkNetwork = checkNetwork,
        checkSession = checkSession,
        navigator = navigator,
        strings = strings,
        inkribbonRecorder = inkribbonRecorder
      ).Content()
    }
  }
  
  private suspend fun handleIntent(intent: String) {
    when (intent) {
      "validate_and_navigate" -> {
        validateAndNavigate()
      }
    }
  }
  
  private suspend fun validateAndNavigate() {
    // Misma lógica que SplashComponent clásico
    // pero registrando navegaciones SDUI
  }
}
```

**Regla:** Si SDUI falla (fetch o parse), se hace fallback automático a Splash clásico y se registra en Toad (usando `SduiToadRecorder`). Todas las navegaciones se registran en Inkribbon (usando `SduiInkribbonRecorder`).

### Contrato SDUI para Splash

**Estructura esperada (DTO externo):**

```json
{
  "schemaVersion": "1.0.0",
  "screenId": "splash",
  "root": {
    "id": "splash_root",
    "type": "appScaffold",
    "level": "ORGANISM",
    "children": [
      {
        "id": "splash_app_name",
        "type": "text",
        "level": "ATOM",
        "props": {
          "textKey": "splash_app_name"
        },
        "style": {
          "typographyRole": "title"
        }
      },
      {
        "id": "splash_loading_message",
        "type": "text",
        "level": "ATOM",
        "props": {
          "textKey": "splash_loading"
        }
      },
      {
        "id": "splash_status_message",
        "type": "text",
        "level": "ATOM",
        "props": {
          "textKey": "splash_checking_version"
        }
      },
      {
        "id": "splash_progress",
        "type": "progress",
        "level": "ATOM"
      }
    ]
  }
}
```

**Mapeo a dominio (Entity):**

El parser (TA-F2) convierte este DTO a:
- `Schema(screenId=ScreenId("splash"), root=Node(...))`
- `Node` con `NodeType.Organism.AppScaffold` como raíz
- `Node` hijos con `NodeType.Atom.Text`, `NodeType.Atom.Progress`

## 2) Integración con navegación Decompose

**Ubicación:** `features/splash/presentation/navigation/`

```kotlin
sealed class RootConfig {
  object Splash : RootConfig()
  object Login : RootConfig()
  object Home : RootConfig()
  object ForceUpdate : RootConfig()
  object Maintenance : RootConfig()
  object Offline : RootConfig()
  object SecurityError : RootConfig()
}

val rootNavigation = StackNavigation<RootConfig>()

val rootChildStack = childStack(
  source = rootNavigation,
  initialConfiguration = RootConfig.Splash,
  handleBackButton = false
) { config, ctx ->
  when (config) {
    is RootConfig.Splash -> 
      SplashRouterComponent(
        componentContext = ctx,
        flagProvider = get(),
        sduiRenderer = get(),
        sduiFetchSchema = get(),
        checkVersion = get(),
        checkMaintenance = get(),
        checkSecurity = get(),
        checkNetwork = get(),
        checkSession = get(),
        navigator = rootNavigation,
        strings = get(),
        inkribbonRecorder = get(),
        toadRecorder = get()
      )
    // ... otros
  }
}
```

**Rutas:**
- `app://splash` → `RootConfig.Splash`
- `app://sdui?id=splash` → `RootConfig.Splash` (si flag SDUI está habilitado, se renderiza SDUI)

## 3) Registro en log temporal (Inkribbon)

**Abstracción temporal:**

**Ubicación:** `core/sdui/presentation/recorder/`

```kotlin
interface SduiInkribbonRecorder {
  fun recordNavigation(from: String, to: String, type: String)
  fun recordRender(screenId: String, variant: String, success: Boolean)
}

class SduiInkribbonRecorderImpl : SduiInkribbonRecorder {
  override fun recordNavigation(from: String, to: String, type: String) {
    // Temporal: log a consola
    println("[Inkribbon] Navigation: $from -> $to (type: $type)")
    // Futuro: cuando exista Inkribbon, registrar evento de navegación
  }
  
  override fun recordRender(screenId: String, variant: String, success: Boolean) {
    // Temporal: log a consola
    println("[Inkribbon] Render: screenId=$screenId, variant=$variant, success=$success")
    // Futuro: cuando exista Inkribbon, registrar evento de render
  }
}
```

**Reglas de registro:**
- Todas las navegaciones se registran (clásica y SDUI).
- Todos los renders exitosos se registran (clásica y SDUI).
- Los renders fallidos también se registran (con `success=false`).

## 4) Implementacion esperada (nivel 4)

### Checklist de implementación

- [ ] Crear `SplashStrings` interface e implementaciones `SplashStringsEs` y `SplashStringsEn`.
- [ ] Crear `SplashState` data class.
- [ ] Crear `SplashComponent` (Decompose) con lógica de validación y navegación.
- [ ] Crear `SplashScreen` composable (clásico) usando componentes Dui.
- [ ] Crear `SplashFlagProvider` interface e implementación (hardcodeada inicialmente a `false`).
- [ ] Crear `SplashRouterComponent` que decide entre clásico y SDUI según flag.
- [ ] Implementar lógica de validación (versión, mantenimiento, seguridad, red, sesión).
- [ ] Implementar enrutamiento según validaciones (force-update, maintenance, offline, security-error, home, login).
- [ ] Integrar `SplashRouterComponent` con navegación Decompose (`RootConfig`).
- [ ] Crear `SduiInkribbonRecorder` interface e implementación temporal (log a consola).
- [ ] Registrar todas las navegaciones en `SduiInkribbonRecorder`.
- [ ] Registrar todos los renders (exitosos y fallidos) en `SduiInkribbonRecorder`.
- [ ] Configurar Koin para inyectar dependencias (`SplashStrings`, `SplashFlagProvider`, `SplashComponent`, `SduiInkribbonRecorder`, etc.).
- [ ] Crear contrato SDUI para Splash (JSON/DTO) con estructura completa.
- [ ] Validar que el parser (TA-F2) puede parsear el contrato Splash.
- [ ] Validar que el renderer (TA-F3) puede renderizar todos los nodos del Splash.
- [ ] Implementar handler de acciones SDUI (`dispatch_intent` para validate_and_navigate).
- [ ] Implementar fallback a Splash clásico si SDUI falla (fetch/parse).
- [ ] Registrar fallback en Toad usando `SduiToadRecorder`.

### Estructura de paquetes esperada

```
features/splash/
├── domain/
│   ├── entity/
│   ├── valueobject/
│   ├── repository/
│   └── usecase/
│       ├── CheckVersion.kt
│       ├── CheckMaintenance.kt
│       ├── CheckSecurity.kt
│       ├── CheckNetwork.kt
│       └── CheckSession.kt
├── data/
│   ├── model/
│   ├── source/
│   ├── repository/
│   └── mapper/
└── presentation/
    ├── component/
    │   ├── SplashComponent.kt
    │   └── SplashRouterComponent.kt
    ├── compose/
    │   └── SplashScreen.kt
    ├── strings/
    │   ├── SplashStrings.kt
    │   ├── SplashStringsEs.kt
    │   └── SplashStringsEn.kt
    └── navigation/
        └── RootConfig.kt

core/sdui/presentation/recorder/
├── SduiInkribbonRecorder.kt
└── SduiInkribbonRecorderImpl.kt
```

## 5) Tests unitarios obligatorios

**Ubicación:** `composeApp/src/commonTest/kotlin/.../splash/`

### Test: SplashFlagProvider_Default_ReturnsFalse

**Given:**
- `SplashFlagProvider` con flag hardcodeado

**Expect:**
- `isSduiEnabled()` retorna `false`

### Test: SplashFlagProvider_WhenEnabled_ReturnsTrue

**Given:**
- `SplashFlagProvider` con flag hardcodeado a `true`

**Expect:**
- `isSduiEnabled()` retorna `true`

### Test: SplashRouterComponent_WhenSduiDisabled_RendersClassic

**Given:**
- `SplashRouterComponent` con flag `false`
- Mock de `SduiRenderer` y `SduiFetchSchema`

**Expect:**
- Se renderiza `SplashComponent` (clásico)

### Test: SplashRouterComponent_WhenSduiEnabled_RendersSdui

**Given:**
- `SplashRouterComponent` con flag `true`
- Mock de `SduiRenderer` y `SduiFetchSchema`

**Expect:**
- Se llama a `MagicsDuiRenderScreen` con `screenId="splash"`

### Test: SplashRouterComponent_WhenSduiFails_FallbackToClassic

**Given:**
- `SplashRouterComponent` con flag `true`
- `SduiFetchSchema` lanza excepción

**Expect:**
- Se renderiza `SplashComponent` (clásico)
- Se registra error en `SduiToadRecorder`

### Test: SplashComponent_WhenVersionCheckFails_NavigatesToForceUpdate

**Given:**
- `SplashComponent` con `checkVersion` que retorna error
- Mock de `SduiInkribbonRecorder`

**Expect:**
- Se llama a `navigator.navigate("app://force-update")`
- Se registra navegación en `SduiInkribbonRecorder` con `from="splash"`, `to="force-update"`, `type="classic"`

### Test: SplashComponent_WhenSessionValid_NavigatesToHome

**Given:**
- `SplashComponent` con todas las validaciones exitosas y sesión válida
- Mock de `SduiInkribbonRecorder`

**Expect:**
- Se llama a `navigator.navigate("app://home")`
- Se registra navegación en `SduiInkribbonRecorder` con `from="splash"`, `to="home"`, `type="classic"`

### Test: SplashComponent_WhenSessionInvalid_NavigatesToLogin

**Given:**
- `SplashComponent` con todas las validaciones exitosas pero sesión inválida
- Mock de `SduiInkribbonRecorder`

**Expect:**
- Se llama a `navigator.navigate("app://login")`
- Se registra navegación en `SduiInkribbonRecorder` con `from="splash"`, `to="login"`, `type="classic"`

### Test: SduiInkribbonRecorder_RecordNavigation_LogsCorrectly

**Given:**
- `SduiInkribbonRecorderImpl`
- Parámetros: `from="splash"`, `to="home"`, `type="classic"`

**Expect:**
- Se imprime log: `[Inkribbon] Navigation: splash -> home (type: classic)`

### Test: SduiInkribbonRecorder_RecordRender_LogsCorrectly

**Given:**
- `SduiInkribbonRecorderImpl`
- Parámetros: `screenId="splash"`, `variant="sdui"`, `success=true`

**Expect:**
- Se imprime log: `[Inkribbon] Render: screenId=splash, variant=sdui, success=true`

**Base de tests KMP:**
- **Ubicación:** `composeApp/src/commonTest/kotlin/...`
- **Framework:** `kotlin.test` + `kotlinx.coroutines.test`
- **Comando:** `./gradlew :composeApp:commonTest`
- **Dependencias requeridas en `commonTest.dependencies`:**
  - `libs.kotlin.test`
  - `libs.jetbrains.kotlinx.coroutines.test`

## 6) Pull Request (contenido esperado)

### Título
```
feat(splash): splash screen classic and sdui variants with navigation logging
```

### Incluye
- `SplashStrings` interface e implementaciones ES/EN.
- `SplashComponent` (Decompose) con lógica de validación y navegación.
- `SplashScreen` composable clásico (Dui).
- `SplashFlagProvider` para alternar entre variantes.
- `SplashRouterComponent` que decide clásico vs SDUI.
- Lógica de validación (versión, mantenimiento, seguridad, red, sesión).
- Enrutamiento según validaciones.
- Integración con navegación Decompose (`RootConfig`).
- `SduiInkribbonRecorder` interface e implementación temporal (log a consola).
- Registro de todas las navegaciones en `SduiInkribbonRecorder`.
- Registro de todos los renders en `SduiInkribbonRecorder`.
- Contrato SDUI para Splash (JSON/DTO).
- Handler de acciones SDUI (`dispatch_intent`).
- Fallback a clásico si SDUI falla.
- Configuración Koin para inyección de dependencias.
- Tests unitarios (10 casos explícitos).

### Checklist PR
- [ ] Código compila sin errores.
- [ ] `./gradlew detekt` pasa sin errores.
- [ ] `./gradlew :composeApp:commonTest` pasa todos los tests.
- [ ] Splash clásico funciona correctamente (validaciones, navegación).
- [ ] Splash SDUI renderiza correctamente cuando flag está habilitado.
- [ ] Fallback a clásico funciona cuando SDUI falla.
- [ ] Todas las navegaciones se registran en `SduiInkribbonRecorder`.
- [ ] Todos los renders se registran en `SduiInkribbonRecorder`.
- [ ] Strings i18n están completos (ES y EN).
- [ ] Contrato SDUI para Splash es válido y parseable.
- [ ] Renderer puede renderizar todos los nodos del Splash.

## 7) Notas de implementación

### Dependencias de otras TAs

- **TA-F0 (Domain)**: `SduiScreenId`, `Schema`, `Node`, etc. deben estar implementados.
- **TA-F1 (Contract)**: `SduiScreenDto`, `SduiNodeDto`, `SduiActionDto` deben estar definidos.
- **TA-F2 (Parser)**: `SduiContractParser` debe poder parsear el contrato Splash.
- **TA-F3 (Renderer)**: `SduiRenderer` debe poder renderizar todos los tipos de nodos del Splash (`appScaffold`, `text`, `progress`).
- **TA-F4 (Make)**: Target `make run-sdui-screen-splash` debe abrir la pantalla Splash.

### Feature Flag inicial

El flag `sdui_splash_enabled` será hardcodeado inicialmente:
```kotlin
class SplashFlagProviderImpl : SplashFlagProvider {
  override fun isSduiEnabled(): Boolean = false // Hardcoded inicialmente
}
```

Cuando exista el módulo de Flags (Epica 06), se actualizará para usar `FlagsRepository`.

### Validación del renderer

Esta historia técnica es crítica para validar que el renderer SDUI funciona:
1. El contrato Splash debe ser parseable (TA-F2).
2. Todos los nodos deben ser renderizables (TA-F3).
3. Las acciones deben funcionar correctamente.
4. El fallback debe funcionar si hay errores.

### Contrato SDUI

El contrato SDUI para Splash debe estar disponible en:
- Mockoon (mock server) en la ruta `/api/sdui/splash` (cuando exista).
- Archivo local para pruebas: `composeApp/src/commonMain/resources/sdui/screen/splash.json`.

### i18n y contrato SDUI

Los keys del contrato SDUI (`splash_app_name`, `splash_loading`, etc.) deben mapear exactamente a las propiedades de `SplashStrings`. El renderer debe resolver estos keys usando el `StringsProvider` inyectado.

### Registro en Inkribbon

Todas las navegaciones y renders se registran usando `SduiInkribbonRecorder`. Inicialmente esta implementación solo loguea a consola. Cuando exista el módulo Inkribbon (Epica 04), se actualizará para registrar eventos reales.
