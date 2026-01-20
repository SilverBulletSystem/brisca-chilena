# Diseno detallado - Epica 1 Fundamentos (Nivel 4)

## 1. Contexto y objetivo
La Epica 1 define la base tecnica del proyecto Brisca para garantizar consistencia arquitectonica, builds reproducibles y reglas de calidad. Esta epica debe alinearse con el proyecto base `ipchile-multiplatform` y sus ADRs.

## 2. Referencias del proyecto base
- Estructura: `ipchile-multiplatform/docs/architecture/project-structure.md`
- Clean Architecture + DDD + MVI: `docs/architecture/decisions/0001-clean-architecture-ddd.md`
- Atomic Design: `docs/architecture/decisions/0017-atomic-design-system.md`
- i18n (interfaces por pantalla): `docs/architecture/decisions/0019-internationalization-language-handling.md`
- ThemeDimens (dimensiones centralizadas): `docs/architecture/decisions/0029-responsive-dimensions-system.md`
- Koin: `docs/architecture/decisions/0004-dependency-injection-koin.md`
- Detekt: `docs/architecture/decisions/0005-static-code-analysis-detekt.md`
- Flavors/env strategy: `docs/architecture/decisions/0026-environment-configuration-strategy.md`
- Build reference: `ipchile-multiplatform/composeApp/build.gradle.kts`
- Makefile reference: `ipchile-multiplatform/Makefile`
- Versions: `ipchile-multiplatform/gradle/libs.versions.toml`

## 3. Alcance
- Namespace y estructura de carpetas.
- Koin baseline (applicationModule) y arranque.
- Network base con Ktor (API pública de prueba).
- Ambientes Android mock/dev/prod con Mockoon CLI y comandos make.
- Esquemas iOS (spike) alineados a debug mock/dev y release prod.
- Detekt con reglas del proyecto.
- i18n mínima ES/EN (Splash + provider en memoria).
- Alineacion de versiones y plantillas de config.

## 4. Estructura de carpetas y paquetes
**Namespace base**: `cl.silverbullet.multiplatform.brisca`

**Estructura logica (alineada al base, sin modulos Gradle separados):**
```
composeApp/src/commonMain/kotlin/cl/silverbullet/multiplatform/brisca/
├── core/
│   ├── platform/
│   ├── di/
│   ├── network/
│   ├── storage/
│   ├── utils/
│   └── ui/
├── commons/
│   ├── domain/
│   ├── data/
│   └── presentation/
├── features/
│   ├── splash/
│   ├── auth/
│   ├── home/
│   └── ...
├── designsystem/
├── magicsdui/
├── inkribbon/
├── toad/
├── flags/
├── network/
└── storage/
```

**Reglas clave:**
- **core**: todo lo necesario para que cualquier app funcione (DI, network, security, storage, platform).
- **commons**: capacidades reutilizables que no son base (cache, onboarding, version, notificaciones, etc.).
- **features**: funcionalidades especificas de Brisca (ej: mesa).
- Clean Architecture por feature (domain/data/presentation).
- Presentacion con MVI (state/intent/effect/reducer/viewmodel).
- Codigo fuente en ingles.
- Nombres camelCase y sin sufijo `UseCase` en casos de uso.

**Ejemplos esperados (alto nivel):**
- `core/network`, `core/security`, `core/di`
- `commons/onboarding`, `commons/cache`, `commons/version`, `commons/notifications`
- `features/mesa`

## 5. Flavors Android (mock/dev/prod)
**Objetivo:** replicar la estrategia del base, agregando `mock` para Mockoon.

**Gradle - reglas minimas:**
- `flavorDimensions += "env"`
- `productFlavors`: `mock`, `dev`, `prod`.
- `applicationIdSuffix`: `.mock`, `.dev`, prod sin sufijo.
- `versionNameSuffix`: `-mock`, `-dev`, prod sin sufijo.
- `buildConfigField` por flavor:
  - `ENVIRONMENT`: `MOCK|DEVELOPMENT|PRODUCTION`
  - `DEV_BASE_URL`, `PROD_BASE_URL`, `MOCK_BASE_URL`
  - `LOCAL_IP`, `MOCKOON_PORT`, `MOCKOON_BASE_URL`
  - `APP_KEY` (placeholder)

**IP dinamica:**
- Usar `localIp` por Gradle property, como en el base.
- Script sugerido: `scripts/get-local-ip.sh`.

**Build types:**
- `debug`: `isMinifyEnabled=false`, `buildConfigField("boolean","DEBUG","true")`.
- `release`: `isMinifyEnabled=true`, `buildConfigField("boolean","DEBUG","false")`.

## 6. Esquemas iOS (MockDebug/DevDebug/ProdRelease) + spike
**Estado:** spike requerido para crear 3 ambientes en iOS con paridad Android.

**Contexto actual informado**
- Debug con sufijo dev para mock/dev y Release para tienda.
- No hay claridad completa para 3 ambientes en iOS, se requiere spike.

**Hipotesis de implementacion (spike)**
1) Crear 3 configuraciones xcconfig:
   - `MockDebug.xcconfig`
   - `DevDebug.xcconfig`
   - `ProdRelease.xcconfig`
2) Cada xcconfig define:
   - `BASE_URL`
   - `LOG_LEVEL`
   - `FLAVOR_NAME`
   - `PRODUCT_BUNDLE_IDENTIFIER`
3) Esquemas Xcode:
   - `MockDebug` -> Build configuration `MockDebug`
   - `DevDebug` -> Build configuration `DevDebug`
   - `ProdRelease` -> Build configuration `ProdRelease`
4) BundleId:
   - `mock` y `dev` usan sufijo `.dev` (ej: `cl.silverbullet.multiplatform.brisca.dev`)
   - `prod` sin sufijo (tienda)
5) Validacion:
   - Build de cada esquema sin errores.
   - Variables visibles en Swift (Bundle.infoDictionary).

**Decision pendiente (spike)**
- Confirmar si iOS permite 3 ambientes sin multiplicar targets o si basta con 3 build configurations + schemes.
- Definir convencion final de bundleId y sufijos por ambiente.

**Plantilla base:** `iosApp/Configuration/Config.xcconfig`.

## 7. DI baseline con Koin
**Base:** ADR-0004.

**Modulo unico:**
- `applicationModule` con `HttpClient` real.

**Arranque Android:** `startKoin` en `Application`.
**Arranque iOS:** `initKoin()` expuesto a Swift.

## 8. Detekt
**Base:** `ipchile-multiplatform/composeApp/config/detekt/detekt.yml`.

**Decision:** agregar reglas extra (spike) ademas del base.

**Reglas obligatorias:**
- Sin strings hardcode en UI.
- Sin dimensiones hardcode (`.dp`/`.sp` prohibidos en UI; usar `ThemeDimens`).
- Comentarios prohibidos (definir lista en spike).
- Casos de uso sin sufijo `UseCase`.
- Separacion de capas y atomic design.

**Spike detekt adicional:**
- Definir reglas custom para detectar:
  - Comentarios prohibidos (lista concreta).
  - Uso de `.dp` y `.sp` fuera de `ThemeDimens`.
  - Strings literales en UI.
- Evaluar si se implementa con reglas custom o `detekt-formatting` + reglas propias.

**Integracion:**
- Plugin detekt en Gradle.
- Target `make detekt`.

## 9. i18n base ES/EN
**Base:** ADR-0019.

**Reglas:**
- `AppLanguage` enum en `core/language`.
- `StringsProvider` en memoria + DI.
- Splash mínimo renderiza `appName` según idioma.

## 10. Alineacion de versiones
**Base:** `ipchile-multiplatform/gradle/libs.versions.toml`.

**Claves a alinear:**
- Kotlin, Compose Multiplatform, Ktor, Koin, Firebase, Detekt, Kover.
- Nota Ktor: usar 3.3.3 (validar D8).

## 11. Verificacion
- `./gradlew :composeApp:assembleDebug` antes de flavors.
- `assembleMockDebug`, `assembleDevDebug`, `assembleProdRelease` tras flavors.
- `./gradlew detekt` pasa en estado base.
- Cambio de locale refleja ES/EN en pantalla ejemplo.

## 12. Riesgos
- Desalineacion con base en versiones y estructura.
- Errores en flavors/xcconfig que bloquean build.
- Falta de reglas detekt que permitan strings hardcode.

## 13. Dudas detectadas
1) Definir el resultado del spike iOS: 3 ambientes con build configurations + schemes o multiples targets.
2) Definir lista de comentarios prohibidos para detekt custom.
