# Templates para Cursor - Respuestas Rápidas

## 🚀 TEMPLATE: Nueva Pantalla con Internacionalización

```kotlin
// 1. Definir interfaz de strings
interface FeatureStrings {
    val title: String
    val subtitle: String
    val buttonText: String
    val errorMessage: String
}

// 2. Implementaciones por idioma
object FeatureStringsEs : FeatureStrings {
    override val title = "Título en Español"
    override val subtitle = "Subtítulo en Español"
    override val buttonText = "Botón en Español"
    override val errorMessage = "Error en Español"
}

object FeatureStringsEn : FeatureStrings {
    override val title = "Title in English"
    override val subtitle = "Subtitle in English"
    override val buttonText = "Button in English"
    override val errorMessage = "Error in English"
}

// 3. Configuración Koin
factory<FeatureStrings> { 
    val languageHandler: LanguageHandler = get()
    when (languageHandler.currentLanguage) {
        Language.SPANISH -> FeatureStringsEs
        Language.ENGLISH -> FeatureStringsEn
    }
}

// 4. Componente DECOMPOSE
class FeatureComponent(
    componentContext: ComponentContext,
    private val featureStrings: FeatureStrings,
    private val themeManager: ThemeManager
) : ComponentContext by componentContext {
    // Lógica del componente
}

// 5. UI con internacionalización
@Composable
fun FeatureContent(
    component: FeatureComponent,
    featureStrings: FeatureStrings,
    themeColors: ThemeColors
) {
    Text(
        text = featureStrings.title,
        color = themeColors.black
    )
}
```

## 🎨 TEMPLATE: Componente con Tema Dinámico

```kotlin
@Composable
fun ComponentWithTheme(
    component: SomeComponent,
    strings: SomeStrings,
    themeColors: ThemeColors
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ThemeDimens.NORMAL_SPACING)
    ) {
        Text(
            text = strings.title,
            color = themeColors.black
        )
        
        Button(
            onClick = { /* action */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = themeColors.primary
            )
        ) {
            Text(strings.buttonText)
        }
    }
}
```

## 🏗️ TEMPLATE: Caso de Uso con Validación

```kotlin
// Domain Layer
class ValidateFeatureInput {
    sealed class ValidationResult {
        object Valid : ValidationResult()
        data class Invalid(val errors: List<ValidationError>) : ValidationResult()
    }
    
    fun validate(input: String): ValidationResult {
        // Validación en Domain Layer
    }
}

class FeatureUseCase(
    private val validateInput: ValidateFeatureInput,
    private val repository: FeatureRepository
) {
    suspend fun execute(input: String): Result<FeatureResponse> {
        val validation = validateInput.validate(input)
        return when (validation) {
            is ValidateFeatureInput.ValidationResult.Valid -> repository.process(input)
            is ValidateFeatureInput.ValidationResult.Invalid -> Result.failure(ValidationException())
        }
    }
}
```

## 📝 COMANDOS RÁPIDOS PARA CURSOR

### Para nueva pantalla:
```
"Crear pantalla [NOMBRE] con internacionalización siguiendo ADR-0019"
```

### Para componente con tema:
```
"Crear componente [NOMBRE] con ThemeDimens y ThemeManager"
```

### Para caso de uso:
```
"Crear caso de uso [NOMBRE] con validación en Domain Layer"
```

### Para strings:
```
"Definir interfaz [NOMBRE]Strings siguiendo ADR-0019"
```
