# TA-SDUI-F7 — Login Screen (clásica y SDUI)

## Navegacion
- [Volver a Epica](03-magicsdui/epica.md)
- [Volver a Backlog](03-magicsdui/backlog.md)
- [Diseno detallado](03-magicsdui/diseno-detallado.md)

## Rama
- `feat/ht-sdui-f7-login-screen`

## Objetivo
Implementar pantalla Login en dos variantes (clásica y SDUI) para validar que el renderer SDUI funciona correctamente. La decisión entre variantes se hace mediante feature flag.

## Wireframe/Mockup

![Login Screen Wireframe](../assets/login-wireframe.png)

**Diseño esperado:**
- Título "Iniciar Sesión" centrado en la parte superior
- Campo de texto para correo electrónico con label y placeholder
- Campo de texto para contraseña con label, placeholder e icono de mostrar/ocultar
- Botón principal "Ingresar" de ancho completo
- Botón secundario de texto "¿Olvidaste tu contraseña?"
- Layout centrado verticalmente con espaciado consistente (ThemeDimens)
- Ambos enfoques (clásico y SDUI) deben renderizar el mismo diseño visual

## Alcance
- Pantalla Login clásica (con Dui).
- Pantalla Login SDUI (usando renderer).
- Feature flag para alternar entre ambas.
- Strings i18n para Login.
- Contrato SDUI para Login (screenId="login").
- Integración con navegación Decompose.
- Registro de navegaciones y renders en log temporal (SduiInkribbonRecorder).
- Tests para validar ambas variantes.

## 1) Interfaces y firmas (nivel 4)

### Strings i18n para Login

**Ubicación:** `features/auth/presentation/strings/`

```kotlin
interface LoginStrings {
  val title: String
  val emailLabel: String
  val emailHint: String
  val passwordLabel: String
  val passwordHint: String
  val loginButton: String
  val forgotPasswordButton: String
  val errorInvalidCredentials: String
  val errorNetwork: String
}

// Implementación ES
class LoginStringsEs : LoginStrings {
  override val title = "Iniciar Sesión"
  override val emailLabel = "Correo electrónico"
  override val emailHint = "Ingresa tu correo"
  override val passwordLabel = "Contraseña"
  override val passwordHint = "Ingresa tu contraseña"
  override val loginButton = "Ingresar"
  override val forgotPasswordButton = "¿Olvidaste tu contraseña?"
  override val errorInvalidCredentials = "Credenciales inválidas"
  override val errorNetwork = "Error de conexión"
}

// Implementación EN
class LoginStringsEn : LoginStrings {
  override val title = "Sign In"
  override val emailLabel = "Email"
  override val emailHint = "Enter your email"
  override val passwordLabel = "Password"
  override val passwordHint = "Enter your password"
  override val loginButton = "Sign In"
  override val forgotPasswordButton = "Forgot password?"
  override val errorInvalidCredentials = "Invalid credentials"
  override val errorNetwork = "Connection error"
}
```

**Regla:** Los keys i18n del contrato SDUI deben mapear a estas propiedades:
- `login_title` → `title`
- `login_email` → `emailLabel`
- `login_email_hint` → `emailHint`
- `login_password` → `passwordLabel`
- `login_password_hint` → `passwordHint`
- `login_cta` → `loginButton`
- `login_forgot_password` → `forgotPasswordButton`

### Componente Login clásico

**Ubicación:** `features/auth/presentation/component/`

```kotlin
class LoginComponent(
  componentContext: ComponentContext,
  private val login: Login, // Use case
  private val navigator: Navigator,
  private val strings: LoginStrings,
  private val inkribbonRecorder: SduiInkribbonRecorder
) : ComponentContext by componentContext {
  
  private val state = MutableValue(LoginState())
  val stateValue: Value<LoginState> = state
  
  fun onEmailChange(email: String) {
    state.value = state.value.copy(email = email)
  }
  
  fun onPasswordChange(password: String) {
    state.value = state.value.copy(password = password)
  }
  
  fun onLoginClick() {
    // Llamar use case y navegar
    inkribbonRecorder.recordNavigation("login", "home", "classic")
  }
  
  fun onForgotPasswordClick() {
    // Navegar a recover
    inkribbonRecorder.recordNavigation("login", "recover", "classic")
  }
  
  @Composable
  fun Content() {
    LoginScreen(
      state = state.value,
      strings = strings,
      onEmailChange = ::onEmailChange,
      onPasswordChange = ::onPasswordChange,
      onLoginClick = ::onLoginClick,
      onForgotPasswordClick = ::onForgotPasswordClick
    )
    
    LaunchedEffect(Unit) {
      inkribbonRecorder.recordRender("login", "classic", true)
    }
  }
}

data class LoginState(
  val email: String = "",
  val password: String = "",
  val isLoading: Boolean = false,
  val error: String? = null
)
```

### Pantalla Login clásica (Compose)

**Ubicación:** `features/auth/presentation/compose/`

```kotlin
@Composable
fun LoginScreen(
  state: LoginState,
  strings: LoginStrings,
  onEmailChange: (String) -> Unit,
  onPasswordChange: (String) -> Unit,
  onLoginClick: () -> Unit,
  onForgotPasswordClick: () -> Unit
) {
  DuiAppScaffold {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(ThemeDimens.NORMAL_SPACING),
      verticalArrangement = Arrangement.spacedBy(ThemeDimens.NORMAL_SPACING)
    ) {
      DuiTitleText(text = strings.title)
      
      DuiTextField(
        label = strings.emailLabel,
        placeholder = strings.emailHint,
        value = state.email,
        onValueChange = onEmailChange,
        modifier = Modifier.fillMaxWidth()
      )
      
      DuiTextField(
        label = strings.passwordLabel,
        placeholder = strings.passwordHint,
        value = state.password,
        onValueChange = onPasswordChange,
        modifier = Modifier.fillMaxWidth(),
        isPassword = true
      )
      
      if (state.error != null) {
        DuiBodyText(
          text = state.error,
          color = MaterialTheme.colorScheme.error
        )
      }
      
      DuiButton(
        text = strings.loginButton,
        onClick = onLoginClick,
        enabled = !state.isLoading && state.email.isNotBlank() && state.password.isNotBlank(),
        modifier = Modifier.fillMaxWidth()
      )
      
      DuiTextButton(
        text = strings.forgotPasswordButton,
        onClick = onForgotPasswordClick
      )
    }
  }
}
```

### Feature Flag para Login SDUI

**Ubicación:** `features/auth/presentation/`

```kotlin
interface LoginFlagProvider {
  fun isSduiEnabled(): Boolean
}

class LoginFlagProviderImpl(
  private val flagsRepository: FlagsRepository
) : LoginFlagProvider {
  override fun isSduiEnabled(): Boolean {
    return flagsRepository.getFlag(
      key = FlagKey("sdui_login_enabled"),
      expectedType = FlagType.Boolean,
      default = false
    ).value as? Boolean ?: false
  }
}
```

**Regla:** Inicialmente el flag será hardcodeado a `false` (pantalla clásica por defecto). Cuando exista el módulo de Flags (Epica 06), se usará `LoginFlagProvider`.

### Decisión de pantalla (clásica vs SDUI)

**Ubicación:** `features/auth/presentation/component/`

```kotlin
class LoginRouterComponent(
  componentContext: ComponentContext,
  private val flagProvider: LoginFlagProvider,
  private val sduiRenderer: SduiRenderer,
  private val sduiFetchSchema: SduiFetchSchema,
  private val login: Login,
  private val navigator: Navigator,
  private val strings: LoginStrings,
  private val inkribbonRecorder: SduiInkribbonRecorder,
  private val toadRecorder: SduiToadRecorder
) : ComponentContext by componentContext {
  
  @Composable
  fun Content() {
    val isSduiEnabled = flagProvider.isSduiEnabled()
    
    if (isSduiEnabled) {
      MagicsDuiRenderScreen(
        screenId = SduiScreenId("login"),
        renderer = sduiRenderer,
        fetchSchema = sduiFetchSchema,
        onAction = { action ->
          when (action.type) {
            ActionType.DispatchIntent -> {
              if (action.target.value == "login") {
                // Llamar use case Login
              }
            }
            // ... otros tipos
          }
        },
        onRenderComplete = {
          inkribbonRecorder.recordRender("login", "sdui", true)
        },
        onRenderError = { error ->
          inkribbonRecorder.recordRender("login", "sdui", false)
          toadRecorder.recordError("login", "render", error)
        }
      )
    } else {
      LoginComponent(
        componentContext = this,
        login = login,
        navigator = navigator,
        strings = strings,
        inkribbonRecorder = inkribbonRecorder
      ).Content()
    }
  }
}
```

**Regla:** Si SDUI falla (fetch o parse), se hace fallback automático a Login clásico y se registra en Toad (usando `SduiToadRecorder`). Todas las navegaciones y renders se registran en Inkribbon (usando `SduiInkribbonRecorder`).

### Contrato SDUI para Login

**Estructura esperada (DTO externo):**

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

**Mapeo a dominio (Entity):**

El parser (TA-F2) convierte este DTO a:
- `Schema(screenId=ScreenId("login"), root=Node(...))`
- `Node` con `NodeType.Organism.AppScaffold` como raíz
- `Node` hijos con `NodeType.Atom.Text`, `NodeType.Molecule.TextField`, `NodeType.Atom.Button`

## 2) Integración con navegación Decompose

**Ubicación:** `features/auth/presentation/navigation/`

```kotlin
sealed class AuthConfig {
  object Login : AuthConfig()
  object Recover : AuthConfig()
  object Register : AuthConfig()
}

val authNavigation = StackNavigation<AuthConfig>()

val authChildStack = childStack(
  source = authNavigation,
  initialConfiguration = AuthConfig.Login,
  handleBackButton = true
) { config, ctx ->
  when (config) {
    is AuthConfig.Login -> 
      LoginRouterComponent(
        componentContext = ctx,
        flagProvider = get(),
        sduiRenderer = get(),
        sduiFetchSchema = get(),
        login = get(),
        navigator = authNavigation,
        strings = get()
      )
    // ... otros
  }
}
```

**Rutas:**
- `app://login` → `AuthConfig.Login`
- `app://sdui?id=login` → `AuthConfig.Login` (si flag SDUI está habilitado, se renderiza SDUI)

## 3) Implementacion esperada (nivel 4)

### Checklist de implementación

- [ ] Crear DTOs en `data/model/` (`LoginRequestDto`, `LoginResponseDto`, `UserDto`).
- [ ] Usar `ErrorResponseDto` de `error/data/model/` (compartido entre features).
- [ ] Crear mappers en `data/mapper/` para convertir DTOs a entidades de dominio.
- [ ] Crear `LoginStrings` interface y implementaciones `LoginStringsEs` y `LoginStringsEn`.
- [ ] Crear `LoginState` data class.
- [ ] Crear `LoginComponent` (Decompose) con estado y handlers.
- [ ] Crear `LoginScreen` composable (clásico) usando componentes Dui.
- [ ] Crear `LoginFlagProvider` interface e implementación (hardcodeada inicialmente a `false`).
- [ ] Crear `LoginRouterComponent` que decide entre clásico y SDUI según flag.
- [ ] Integrar `LoginRouterComponent` con navegación Decompose (`AuthConfig`).
- [ ] Crear `SduiInkribbonRecorder` interface e implementación temporal (log a consola).
- [ ] Registrar todas las navegaciones en `SduiInkribbonRecorder`.
- [ ] Registrar todos los renders (exitosos y fallidos) en `SduiInkribbonRecorder`.
- [ ] Configurar Koin para inyectar dependencias (`LoginStrings`, `LoginFlagProvider`, `LoginComponent`, `SduiInkribbonRecorder`, etc.).
- [ ] Crear contrato SDUI para Login (JSON/DTO) con estructura completa.
- [ ] Validar que el parser (TA-F2) puede parsear el contrato Login.
- [ ] Validar que el renderer (TA-F3) puede renderizar todos los nodos del Login.
- [ ] Implementar handler de acciones SDUI (`dispatch_intent` para login, `navigate` para recover).
- [ ] Implementar fallback a Login clásico si SDUI falla (fetch/parse).
- [ ] Registrar fallback en Toad usando `SduiToadRecorder`.

### DTOs para Login (data/model)

**Ubicación:** `features/auth/data/model/`

```kotlin
@Serializable
data class LoginRequestDto(
  val email: String,
  val password: String
)

@Serializable
data class LoginResponseDto(
  val token: String,
  val refreshToken: String? = null,
  val user: UserDto
)

@Serializable
data class UserDto(
  val id: String,
  val email: String,
  val name: String? = null,
  val avatar: String? = null
)
```

**Regla:** Los DTOs están en la capa `data` y usan `@Serializable` de kotlinx.serialization para parsear JSON de la API.

**Nota:** `ErrorResponseDto` es genérico y está ubicado en `error/data/model/ErrorResponseDto.kt` para ser compartido entre todas las features.

### Estructura de paquetes esperada

```
features/auth/
├── domain/
│   ├── entity/
│   │   └── User.kt
│   ├── valueobject/
│   │   └── Credentials.kt
│   ├── repository/
│   │   └── AuthRepository.kt
│   └── usecase/
│       └── Login.kt
├── data/
│   ├── model/
│   │   ├── LoginRequestDto.kt
│   │   ├── LoginResponseDto.kt
│   │   └── UserDto.kt
│   ├── source/
│   │   └── AuthRemoteSource.kt
│   ├── repository/
│   │   └── AuthRepositoryImpl.kt
│   └── mapper/
│       ├── UserDtoMapper.kt
│       └── CredentialsMapper.kt
└── presentation/
    ├── component/
    │   ├── LoginComponent.kt
    │   └── LoginRouterComponent.kt
    ├── compose/
    │   └── LoginScreen.kt
    ├── strings/
    │   ├── LoginStrings.kt
    │   ├── LoginStringsEs.kt
    │   └── LoginStringsEn.kt
    └── navigation/
        └── AuthConfig.kt

error/data/model/
└── ErrorResponseDto.kt

core/sdui/presentation/recorder/
├── SduiInkribbonRecorder.kt
└── SduiInkribbonRecorderImpl.kt
```

## 4) Tests unitarios obligatorios

**Ubicación:** `composeApp/src/commonTest/kotlin/.../auth/`

### Test: LoginFlagProvider_Default_ReturnsFalse

**Given:**
- `LoginFlagProvider` con flag hardcodeado

**Expect:**
- `isSduiEnabled()` retorna `false`

### Test: LoginFlagProvider_WhenEnabled_ReturnsTrue

**Given:**
- `LoginFlagProvider` con flag hardcodeado a `true`

**Expect:**
- `isSduiEnabled()` retorna `true`

### Test: LoginRouterComponent_WhenSduiDisabled_RendersClassic

**Given:**
- `LoginRouterComponent` con flag `false`
- Mock de `SduiRenderer` y `SduiFetchSchema`

**Expect:**
- Se renderiza `LoginComponent` (clásico)

### Test: LoginRouterComponent_WhenSduiEnabled_RendersSdui

**Given:**
- `LoginRouterComponent` con flag `true`
- Mock de `SduiRenderer` y `SduiFetchSchema`

**Expect:**
- Se llama a `MagicsDuiRenderScreen` con `screenId="login"`

### Test: LoginRouterComponent_WhenSduiFails_FallbackToClassic

**Given:**
- `LoginRouterComponent` con flag `true`
- `SduiFetchSchema` lanza excepción

**Expect:**
- Se renderiza `LoginComponent` (clásico)
- Se registra error en `SduiToadRecorder`

### Test: LoginScreen_ValidInput_EnablesButton

**Given:**
- `LoginState(email="test@test.com", password="123456")`

**Expect:**
- Botón de login está habilitado

### Test: LoginScreen_EmptyInput_DisablesButton

**Given:**
- `LoginState(email="", password="")`

**Expect:**
- Botón de login está deshabilitado

### Test: LoginStringsEs_AllKeysPresent_ReturnsSpanishTexts

**Given:**
- `LoginStringsEs()`

**Expect:**
- Todos los strings están en español y no vacíos

### Test: LoginStringsEn_AllKeysPresent_ReturnsEnglishTexts

**Given:**
- `LoginStringsEn()`

**Expect:**
- Todos los strings están en inglés y no vacíos

### Test: LoginComponent_OnLoginClick_CallsUseCase

**Given:**
- `LoginComponent` con email y password
- Mock de `Login` use case
- Mock de `SduiInkribbonRecorder`

**Expect:**
- Se llama a `login(credentials)` con credenciales correctas
- Se registra navegación en `SduiInkribbonRecorder` con `from="login"`, `to="home"`, `type="classic"`

### Test: LoginComponent_OnRender_RecordsRender

**Given:**
- `LoginComponent` renderizado
- Mock de `SduiInkribbonRecorder`

**Expect:**
- Se registra render en `SduiInkribbonRecorder` con `screenId="login"`, `variant="classic"`, `success=true`

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
feat(auth): login screen classic and sdui variants
```

### Incluye
- DTOs en `data/model/` (`LoginRequestDto`, `LoginResponseDto`, `UserDto`).
- Uso de `ErrorResponseDto` de `error/data/model/` (compartido).
- Mappers en `data/mapper/` para convertir DTOs a entidades de dominio.
- `LoginStrings` interface e implementaciones ES/EN.
- `LoginComponent` (Decompose) con estado y handlers.
- `LoginScreen` composable clásico (Dui).
- `LoginFlagProvider` para alternar entre variantes.
- `LoginRouterComponent` que decide clásico vs SDUI.
- Integración con navegación Decompose (`AuthConfig`).
- `SduiInkribbonRecorder` interface e implementación temporal (log a consola).
- Registro de todas las navegaciones en `SduiInkribbonRecorder`.
- Registro de todos los renders en `SduiInkribbonRecorder`.
- Contrato SDUI para Login (JSON/DTO).
- Handler de acciones SDUI (`dispatch_intent`, `navigate`).
- Fallback a clásico si SDUI falla.
- Configuración Koin para inyección de dependencias.
- Tests unitarios (12 casos explícitos).

### Checklist PR
- [ ] Código compila sin errores.
- [ ] `./gradlew detekt` pasa sin errores.
- [ ] `./gradlew :composeApp:commonTest` pasa todos los tests.
- [ ] Login clásico funciona correctamente (inputs, validación, navegación).
- [ ] Login SDUI renderiza correctamente cuando flag está habilitado.
- [ ] Fallback a clásico funciona cuando SDUI falla.
- [ ] Strings i18n están completos (ES y EN).
- [ ] Contrato SDUI para Login es válido y parseable.
- [ ] Renderer puede renderizar todos los nodos del Login.
- [ ] Acciones SDUI (`dispatch_intent`, `navigate`) funcionan correctamente.

## 6) Notas de implementación

### Dependencias de otras TAs

- **TA-F0 (Domain)**: `SduiScreenId`, `Schema`, `Node`, etc. deben estar implementados.
- **TA-F1 (Contract)**: `SduiScreenDto`, `SduiNodeDto`, `SduiActionDto` deben estar definidos.
- **TA-F2 (Parser)**: `SduiContractParser` debe poder parsear el contrato Login.
- **TA-F3 (Renderer)**: `SduiRenderer` debe poder renderizar todos los tipos de nodos del Login (`appScaffold`, `text`, `textField`, `button`).
- **TA-F4 (Make)**: Target `make run-sdui-screen-login` debe abrir la pantalla Login.

### Feature Flag inicial

El flag `sdui_login_enabled` será hardcodeado inicialmente:
```kotlin
class LoginFlagProviderImpl : LoginFlagProvider {
  override fun isSduiEnabled(): Boolean = false // Hardcoded inicialmente
}
```

Cuando exista el módulo de Flags (Epica 06), se actualizará para usar `FlagsRepository`.

### Validación del renderer

Esta historia técnica es crítica para validar que el renderer SDUI funciona:
1. El contrato Login debe ser parseable (TA-F2).
2. Todos los nodos deben ser renderizables (TA-F3).
3. Las acciones deben funcionar correctamente.
4. El fallback debe funcionar si hay errores.

### Contrato SDUI

El contrato SDUI para Login debe estar disponible en:
- Mockoon (mock server) en la ruta `/api/sdui/login` (cuando exista).
- Archivo local para pruebas: `composeApp/src/commonMain/resources/sdui/login.json`.

### i18n y contrato SDUI

Los keys del contrato SDUI (`login_title`, `login_email`, etc.) deben mapear exactamente a las propiedades de `LoginStrings`. El renderer debe resolver estos keys usando el `StringsProvider` inyectado.
