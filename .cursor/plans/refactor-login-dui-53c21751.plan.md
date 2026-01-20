<!-- 53c21751-9dc3-4bf4-8ff7-9bdf56815762 411ac310-ee2c-4de5-bb5e-243fe101e7a8 -->
# Plan: Refactor de Noticias con Sistema de Strings

## Objetivo

Refactorizar la pantalla de Noticias eliminando todos los textos hardcodeados, implementando un sistema de strings centralizado siguiendo ADR-0019, y **eliminando el botón "Ver todas" con su callback** `onNavigateToFullNews`.

## ANÁLISIS CRÍTICO - Dependencias Identificadas

### ¿Dónde se usa NewsListComponent?

**ÚNICO LUGAR**: `HomeComponent.kt` líneas 102-110

```kotlin
val newsListComponent = NewsListComponent(
    componentContext = componentContext,
    getNewsList = getNewsList,
    onNavigateToFullNews = { onNavigateToNews() }, // ❌ A ELIMINAR
    onNavigateToNewsDetail = { newsId ->
        loadAndStoreNewsByIdAndNavigate(newsId)
    }
)
```

**CONCLUSIÓN**: Solo hay 1 lugar donde se instancia `NewsListComponent`, por lo que el cambio es seguro y controlado.

### Constructor Actual de NewsListComponent (4 parámetros):

```kotlin
class NewsListComponent(
    componentContext: ComponentContext,
    private val getNewsList: GetNewsList,
    private val onNavigateToFullNews: () -> Unit, // ❌ A ELIMINAR
    private val onNavigateToNewsDetail: (String) -> Unit
)
```

### Constructor Propuesto (4 parámetros - mismo número, diferentes):

```kotlin
class NewsListComponent(
    componentContext: ComponentContext,
    private val getNewsList: GetNewsList,
    val newsStrings: NewsStrings, // ✅ NUEVO - reemplaza onNavigateToFullNews
    private val onNavigateToNewsDetail: (String) -> Unit
)
```

## Textos Hardcodeados Identificados

En `NewsListContent.kt`:

- Línea 114: "⚠️ Error al cargar noticias"
- Línea 137: "Reintentar"
- Línea 164: "📰"
- Línea 171: "No hay noticias disponibles"
- Línea 224: "📰 Noticias Recientes"
- Línea 235: "Ver todas" ❌ **A ELIMINAR COMPLETO (botón y texto)**

En `NewsListComponent.kt`:

- Línea 54: "Error desconocido" (fallback para error.message)
- Línea 69-71: Función `onViewAllNewsClick()` ❌ **A ELIMINAR**

## Plan de Implementación

### 1. Crear rama de trabajo

```bash
git checkout main
git pull origin main
git checkout -b refactor/news-screen-dui
```

### 2. Crear sistema de strings NewsStrings

**Archivo NUEVO**: `composeApp/src/commonMain/kotlin/cl/ipchile/multiplatform/features/news/domain/strings/NewsStrings.kt`

```kotlin
package cl.ipchile.multiplatform.features.news.domain.strings

interface NewsStrings {
    // Error states
    val errorTitle: String
    val unknownError: String
    val retryButton: String
    
    // Empty state
    val emptyIcon: String
    val emptyMessage: String
    
    // News list
    val recentNewsTitle: String
    // ❌ NO incluir viewAllButton - se elimina completamente
}

object NewsStringsEs : NewsStrings {
    override val errorTitle = "⚠️ Error al cargar noticias"
    override val unknownError = "Error desconocido"
    override val retryButton = "Reintentar"
    override val emptyIcon = "📰"
    override val emptyMessage = "No hay noticias disponibles"
    override val recentNewsTitle = "📰 Noticias Recientes"
}

object NewsStringsEn : NewsStrings {
    override val errorTitle = "⚠️ Error loading news"
    override val unknownError = "Unknown error"
    override val retryButton = "Retry"
    override val emptyIcon = "📰"
    override val emptyMessage = "No news available"
    override val recentNewsTitle = "📰 Recent News"
}
```

### 3. Registrar NewsStrings en Koin DI

**Archivo**: `composeApp/src/commonMain/kotlin/cl/ipchile/multiplatform/features/news/di/NewsModule.kt`

Agregar import y registro:

```kotlin
import cl.ipchile.multiplatform.features.news.domain.strings.NewsStrings
import cl.ipchile.multiplatform.features.news.domain.strings.NewsStringsEs

// Agregar en el module:
single<NewsStrings> { NewsStringsEs }
```

### 4. Actualizar NewsListComponent

**Archivo**: `composeApp/src/commonMain/kotlin/cl/ipchile/multiplatform/features/news/presentation/components/NewsListComponent.kt`

**CAMBIO 1 - Constructor (líneas 17-22)**:

```kotlin
// ANTES:
class NewsListComponent(
    componentContext: ComponentContext,
    private val getNewsList: GetNewsList,
    private val onNavigateToFullNews: () -> Unit, // ❌ ELIMINAR
    private val onNavigateToNewsDetail: (String) -> Unit
) : ComponentContext by componentContext {

// DESPUÉS:
class NewsListComponent(
    componentContext: ComponentContext,
    private val getNewsList: GetNewsList,
    val newsStrings: NewsStrings, // ✅ NUEVO
    private val onNavigateToNewsDetail: (String) -> Unit
) : ComponentContext by componentContext {
```

**CAMBIO 2 - Uso de unknownError (línea 54)**:

```kotlin
// ANTES:
error = error.message ?: "Error desconocido"

// DESPUÉS:
error = error.message ?: newsStrings.unknownError
```

**CAMBIO 3 - Eliminar función onViewAllNewsClick (líneas 69-71)**:

```kotlin
// ❌ ELIMINAR COMPLETAMENTE:
fun onViewAllNewsClick() {
    onNavigateToFullNews.invoke()
}
```

### 5. CRÍTICO: Actualizar HomeComponent

**Archivo**: `composeApp/src/commonMain/kotlin/cl/ipchile/multiplatform/features/home/presentation/HomeComponent.kt`

**PASO A**: Agregar import:

```kotlin
import cl.ipchile.multiplatform.features.news.domain.strings.NewsStrings
```

**PASO B**: Inyectar NewsStrings (después de línea 83):

```kotlin
private val newsStrings: NewsStrings by inject()
```

**PASO C**: Actualizar creación de NewsListComponent (líneas 102-110):

```kotlin
// ANTES:
val newsListComponent = NewsListComponent(
    componentContext = componentContext,
    getNewsList = getNewsList,
    onNavigateToFullNews = { onNavigateToNews() }, // ❌ ELIMINAR
    onNavigateToNewsDetail = { newsId ->
        loadAndStoreNewsByIdAndNavigate(newsId)
    }
)

// DESPUÉS:
val newsListComponent = NewsListComponent(
    componentContext = componentContext,
    getNewsList = getNewsList,
    newsStrings = newsStrings, // ✅ NUEVO
    onNavigateToNewsDetail = { newsId ->
        loadAndStoreNewsByIdAndNavigate(newsId)
    }
)
```

### 6. Refactorizar NewsListContent.kt

**Archivo**: `composeApp/src/commonMain/kotlin/cl/ipchile/multiplatform/features/news/ui/NewsListContent.kt`

**Cambio 1 - ErrorContent título (línea 114)**:

```kotlin
text = component.newsStrings.errorTitle
```

**Cambio 2 - ErrorContent botón (línea 137)**:

```kotlin
text = component.newsStrings.retryButton
```

**Cambio 3 - EmptyContent icono (línea 164)**:

```kotlin
text = component.newsStrings.emptyIcon
```

**Cambio 4 - EmptyContent mensaje (línea 171)**:

```kotlin
text = component.newsStrings.emptyMessage
```

**Cambio 5 - NewsListItemsWithHeader título (línea 224)**:

```kotlin
text = component.newsStrings.recentNewsTitle
```

**Cambio 6 - ELIMINAR botón "Ver todas" completamente**:

En la función `NewsListItemsWithHeader`, eliminar el `Row` con el botón (aproximadamente líneas 229-241):

```kotlin
// ❌ ELIMINAR ESTE BLOQUE COMPLETO:
Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    TextButton(
        onClick = onViewAllNewsClick
    ) {
        Text(
            text = "Ver todas", // ❌
            color = themeColors.primary,
            style = MaterialTheme.typography.labelLarge
        )
    }
}
```

Y simplificar el header a solo mostrar el título:

```kotlin
// ✅ SIMPLIFICADO - Solo título, sin botón:
item {
    Text(
        text = component.newsStrings.recentNewsTitle,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = ThemeDimens.SMALL_SPACING)
    )
}
```

**Cambio 7 - Actualizar firma de NewsListItemsWithHeader**:

Eliminar parámetro `onViewAllNewsClick`:

```kotlin
// ANTES:
@Composable
private fun NewsListItemsWithHeader(
    news: List<News>,
    scrollPosition: Int,
    hasMore: Boolean,
    onNewsClick: (String) -> Unit,
    onViewAllNewsClick: () -> Unit, // ❌ ELIMINAR
    onScrollPositionChange: (Int) -> Unit,
    themeColors: ThemeColors
)

// DESPUÉS:
@Composable
private fun NewsListItemsWithHeader(
    news: List<News>,
    scrollPosition: Int,
    hasMore: Boolean,
    onNewsClick: (String) -> Unit,
    onScrollPositionChange: (Int) -> Unit,
    themeColors: ThemeColors
)
```

**Cambio 8 - Actualizar llamada a NewsListItemsWithHeader (línea 71)**:

```kotlin
// ANTES:
NewsListItemsWithHeader(
    news = state.news,
    scrollPosition = state.scrollPosition,
    hasMore = state.hasMore,
    onNewsClick = { newsId -> component.onNewsItemClick(newsId) },
    onViewAllNewsClick = { component.onViewAllNewsClick() }, // ❌ ELIMINAR
    onScrollPositionChange = { position -> component.updateScrollPosition(position) },
    themeColors = themeColors
)

// DESPUÉS:
NewsListItemsWithHeader(
    news = state.news,
    scrollPosition = state.scrollPosition,
    hasMore = state.hasMore,
    onNewsClick = { newsId -> component.onNewsItemClick(newsId) },
    onScrollPositionChange = { position -> component.updateScrollPosition(position) },
    themeColors = themeColors
)
```

### 7. Compilar y verificar

```bash
make fast-install
```

### 8. Ejecutar Detekt y corregir issues

```bash
./gradlew detekt
```

### 9. Testing funcional completo

- Ejecutar `make run-home` para probar en Home
- Verificar tab "Noticias" carga correctamente
- Verificar estado Loading (spinner)
- Verificar estado Error (botón "Reintentar")
- Verificar estado Empty ("No hay noticias disponibles")
- Verificar estado Success (lista de noticias)
- **Verificar que NO aparece el botón "Ver todas"**
- Verificar navegación a detalle (click en noticia)
- Verificar scroll se preserva al cambiar tabs

### 10. Actualizar CHANGELOG.md

Agregar en `## [Unreleased]`:

```markdown
### Added
- **HT-XX: Refactor de Noticias con sistema de strings NewsStrings**
-  - Sistema de internacionalización NewsStrings siguiendo ADR-0019
-  - Implementaciones en español (NewsStringsEs) e inglés (NewsStringsEn)
-  - Eliminación de 6 textos hardcodeados (5 en NewsListContent + 1 en NewsListComponent)
-  - Integración con Koin DI para inyección de strings
-  - Soporte multiidioma preparado para expansión futura

### Changed
- **HT-XX: Mejoras en NewsListComponent**
-  - Inyección de NewsStrings para mejor testabilidad
-  - Actualizado HomeComponent para pasar NewsStrings a NewsListComponent
-  - Refactorizado NewsListContent para usar strings centralizados
-  - Uso de newsStrings.unknownError en lugar de string hardcodeado

### Removed
- **HT-XX: Eliminación de funcionalidad "Ver todas" en Noticias**
-  - Eliminado botón "Ver todas" de la UI de noticias
-  - Eliminado callback onNavigateToFullNews de NewsListComponent
-  - Eliminada función onViewAllNewsClick() de NewsListComponent
-  - Simplificado constructor de NewsListComponent (de 4 a 4 parámetros, reemplazando onNavigateToFullNews por newsStrings)
-  - Simplificado header de lista de noticias (solo título, sin botón)

### Technical Details
- **Archivos modificados**: 5 archivos en features/news y features/home
- **Nuevos archivos**: NewsStrings.kt con interfaces e implementaciones
- **Pattern seguido**: ADR-0019 (Interfaces segregadas por pantalla)
- **Compatibilidad**: Mantiene funcionalidad existente de navegación a detalle
- **Breaking changes**: Ninguno externo (eliminación de navegación interna no utilizada)
- **Testing**: Verificado en Home con estados Loading, Error, Empty, Success
```

### 11. Commit y Push

```bash
git add .
git commit -m "feat: Refactor de Noticias con NewsStrings y eliminación de 'Ver todas'

- Implementado sistema de internacionalización NewsStrings siguiendo ADR-0019
- Eliminados 6 textos hardcodeados (5 en NewsListContent + 1 en NewsListComponent)
- Integración con Koin DI para inyección de strings
- Soporte multiidioma (español e inglés) preparado
- Actualizado NewsListComponent para inyectar NewsStrings
- Actualizado HomeComponent para pasar NewsStrings a NewsListComponent
- Refactorizado NewsListContent para usar strings centralizados

ELIMINACIONES:
- Eliminado botón 'Ver todas' de la UI de noticias
- Eliminado callback onNavigateToFullNews de NewsListComponent
- Eliminada función onViewAllNewsClick() de NewsListComponent
- Simplificado header de lista de noticias (solo título)
- Simplificado constructor de NewsListComponent (newsStrings reemplaza onNavigateToFullNews)

Archivos modificados:
- NewsStrings.kt (nuevo): Interfaces e implementaciones
- NewsModule.kt: Registro en Koin DI
- NewsListComponent.kt: Inyección de NewsStrings + eliminación de onNavigateToFullNews
- HomeComponent.kt: Inyección y paso de NewsStrings + eliminación de onNavigateToNews callback
- NewsListContent.kt: Uso de strings + eliminación de botón Ver todas
- CHANGELOG.md: Documentación de cambios"

git push origin refactor/news-screen-dui
```

## Archivos a Modificar

1. **CREAR**: `composeApp/src/commonMain/kotlin/cl/ipchile/multiplatform/features/news/domain/strings/NewsStrings.kt`
2. **MODIFICAR**: `composeApp/src/commonMain/kotlin/cl/ipchile/multiplatform/features/news/di/NewsModule.kt` (agregar registro de NewsStrings)
3. **MODIFICAR**: `composeApp/src/commonMain/kotlin/cl/ipchile/multiplatform/features/news/presentation/components/NewsListComponent.kt` (agregar newsStrings, eliminar onNavigateToFullNews y onViewAllNewsClick)
4. **MODIFICAR**: `composeApp/src/commonMain/kotlin/cl/ipchile/multiplatform/features/home/presentation/HomeComponent.kt` (inyectar y pasar newsStrings, eliminar onNavigateToNews)
5. **MODIFICAR**: `composeApp/src/commonMain/kotlin/cl/ipchile/multiplatform/features/news/ui/NewsListContent.kt` (reemplazar 5 strings, eliminar botón Ver todas y su callback)
6. **MODIFICAR**: `CHANGELOG.md`

## Riesgos Mitigados

- **Único punto de creación**: NewsListComponent solo se crea en HomeComponent líneas 102-110
- **Sin otras dependencias**: No hay otros archivos que instancien NewsListComponent
- **Cambio controlado**: El cambio de constructor mantiene el mismo número de parámetros (4)
- **Eliminación segura**: El botón "Ver todas" no es crítico para la funcionalidad
- **Testing exhaustivo**: Plan incluye verificación de todos los estados posibles
- **Compilación temprana**: make fast-install detectará errores antes de testing

## Consideraciones Finales

- Los componentes DUI existentes (DuiNewsCard, DuiEmptyState) no requieren cambios
- SubTabsWithPager ya está refactorizado y no requiere cambios
- El sistema sigue el patrón establecido en HomeStrings
- No se introducen breaking changes externos
- La funcionalidad de navegación a detalle de noticia se mantiene intacta
- Se simplifica la UI eliminando un botón no utilizado

### To-dos

- [ ] Crear rama refactor/home-screen-dui desde main
- [ ] Agregar constantes de Bottom Navigation a ThemeDimens
- [ ] Agregar iconos de Bottom Navigation a IconsPlaceholders
- [ ] Crear HomeStrings interface e implementación española
- [ ] Crear componente DuiIconButton
- [ ] Crear componente DuiBottomNavigationBar con nuevo diseño
- [ ] Agregar DuiIconButton y DuiBottomNavigationBar al inicio del ShowRoom
- [ ] Testing de componentes en ShowRoom
- [ ] Refactorizar BottomNavigationTabs usando DuiBottomNavigationBar
- [ ] Refactorizar HomeContent a sistema DUI
- [ ] Actualizar AppContent para remover themeColors
- [ ] Agregar HomeStrings a módulo Koin
- [ ] Testing funcional completo de Home Screen
- [ ] Corregir errores de Detekt
- [ ] Actualizar CHANGELOG con nuevas funcionalidades
- [ ] Git add, commit, push y crear notas para PR