# TA-F7 — Libs y config comunes

## Objetivo
Diseñar la alineación de versiones y configuraciones comunes basadas en el proyecto base. Sin ejecución ni cambios de código.

## Rama
- Crear desde `main`: `feat/ht-f7-libs-config`.

## Alcance
- `libs.versions.toml` alineado al proyecto base (Kotlin, Compose, Ktor, Koin, etc.).
- Plantillas de config locales (`local.properties.example`) y xcconfig.
- Definir librerías mínimas requeridas por el MVP técnico.

## Diseño
1) Librerías mínimas requeridas (MVP técnico):
   - **Imágenes**:
     - Android: Glide.
     - Multiplataforma: Coil KMP (según proyecto base).
   - **Settings**: Multiplatform Settings.
   - **DB**: SQLDelight:
     - Runtime.
     - Drivers: `android-driver` y `native-driver`.
   - **Firebase**:
     - KMP (GitLive).
     - BOM Android + artefactos Android requeridos.
   - **Resources**: Compose Multiplatform Resources.
   - **JSON**: Kotlinx Serialization.
   - **Coroutines**: Kotlinx Coroutines.
   - **Date/Time**: Kotlinx Datetime.
   - **Navegación/Componentes**: Decompose:
     - `decompose-core`
     - `decompose-compose`
   - **Permisos**: Moko Permissions.
   - **Seguridad**: AndroidX Security Crypto.
   - **Persistencia simple**: DataStore (Android).
   - **Testing básico**:
     - JUnit4
     - AndroidX test (runner, rules, espresso)
   - **Plugins asociados**:
     - Google Services (Firebase)
     - Crashlytics
     - Detekt
     - Kover
     - Skie (interop iOS)
2) Librerías del proyecto base a considerar:
   - **Coil KMP** (si se decide no usar Glide en Android, definir una única solución).
   - **Compose WebView** (si hay webviews en MVP).
   - **FontAwesome** (si hay íconos en MVP).
   - **Skie** (interop iOS).
3) `libs.versions.toml`:
   - Tomar versiones del proyecto base y listarlas (sin aplicar aún en el Gradle actual).
   - Notar posibles upgrades futuros, pero documentar que se mantienen las del base para consistencia inicial.
   - Versiones base (referencia, excepto Kotlin/Compose/Koin/Ktor ya configurados):
     - `kotlinx-serialization = 1.7.3`
     - `kotlinx-datetime = 0.6.0`
     - `multiplatform-settings = 1.3.0`
     - `datastore = 1.1.7`
     - `firebase-kmp = 2.1.0`
     - `firebase-android = 33.15.0`
     - `google-services = 4.4.2`
     - `firebase-crashlytics = 2.9.9`
     - `coil = 3.0.2`
     - `decompose = 3.3.0`
     - `moko-permissions = 0.20.1`
     - `androidx-security = 1.1.0-beta01`
     - `junit = 4.13.2`
     - `androidx-testExt = 1.2.1`
     - `androidx-espresso = 3.6.1`
     - `rules = 1.6.1`
     - `runner = 1.6.2`
     - `detekt = 1.23.8`
     - `detektFormatting = 1.23.8`
     - `kover = 0.9.1`
     - `skie = 0.10.6`
4) Config plantillas:
   - `local.properties.example`: claves esperadas (ej. `sdk.dir`, `apiKey`, `googleServices`).
   - `Config.xcconfig` plantilla: `BASE_URL`, `LOG_LEVEL`, `FLAVOR_NAME`.
5) Orden de aplicación:
   - Primero consolidar las versiones en doc; luego, cuando se implemente, reemplazar el toml actual.

6) Release notes a revisar (diferencias detectadas vs base):
   - **Kotlin**: 2.1.21 → 2.3.0 (wizard). Revisar compatibilidad.
   - **Compose Multiplatform**: 1.8.1 → 1.10.0 (wizard). Revisar compatibilidad.
   - **AGP**: 8.7.3 → 8.11.2 (wizard). Revisar compatibilidad.
   - **AndroidX (core/activity/lifecycle/test/espresso)**: versiones más nuevas en wizard. Revisar compatibilidad.
   - **compileSdk/targetSdk**: 35 → 36 (wizard). Revisar compatibilidad.

7) Release notes por librería (verificar si hay versiones superiores a la base):
   - kotlinx-serialization: https://github.com/Kotlin/kotlinx.serialization/releases
   - kotlinx-datetime: https://github.com/Kotlin/kotlinx-datetime/releases
   - multiplatform-settings: https://github.com/russhwolf/multiplatform-settings/releases
   - DataStore (AndroidX): https://developer.android.com/jetpack/androidx/releases/datastore
   - Firebase KMP (GitLive): https://github.com/GitLiveApp/firebase-kotlin-sdk/releases
   - Firebase Android BOM: https://firebase.google.com/support/release-notes/android
   - Google Services plugin: https://github.com/google/play-services-plugins/releases
   - Firebase Crashlytics plugin: https://firebase.google.com/support/release-notes/android
   - Coil: https://github.com/coil-kt/coil/releases
   - Decompose: https://github.com/arkivanov/Decompose/releases
   - Moko Permissions: https://github.com/icerockdev/moko-permissions/releases
   - AndroidX Security: https://developer.android.com/jetpack/androidx/releases/security
   - JUnit4: https://github.com/junit-team/junit4/releases
   - AndroidX Test: https://developer.android.com/jetpack/androidx/releases/test
   - Detekt: https://github.com/detekt/detekt/releases
   - Detekt Formatting: https://github.com/detekt/detekt/releases
   - Kover: https://github.com/Kotlin/kotlinx-kover/releases
   - Skie: https://skie.touchlab.co/changelog/

8) Resultado de revisión (base vs última):
   - **kotlinx-serialization**: base 1.7.3 → latest 1.10.0-RC (RC, validar).
   - **kotlinx-datetime**: base 0.6.0 → latest 0.7.1.
   - **multiplatform-settings**: base 1.3.0 → latest 1.3.0 (sin cambio).
   - **datastore**: base 1.1.7 → latest 1.3.0-alpha04 (alpha, validar).
   - **firebase-kmp**: base 2.1.0 → latest 2.4.0.
   - **firebase-android (BOM)**: base 33.15.0 → latest 34.8.0.
   - **google-services**: base 4.4.2 → latest 4.4.4.
   - **firebase-crashlytics**: base 2.9.9 → latest 3.0.6.
   - **coil**: base 3.0.2 → latest 3.3.0.
   - **decompose**: base 3.3.0 → latest 3.4.0.
   - **moko-permissions**: base 0.20.1 → latest 0.20.1 (sin cambio).
   - **androidx-security**: base 1.1.0-beta01 → latest 1.1.0.
   - **junit**: base 4.13.2 → latest 4.13.2 (sin cambio).
   - **androidx-testExt**: base 1.2.1 → latest 1.3.0.
   - **androidx-espresso**: base 3.6.1 → latest 3.7.0.
   - **rules**: base 1.6.1 → latest 1.7.0.
   - **runner**: base 1.6.2 → latest 1.7.0.
   - **detekt**: base 1.23.8 → latest 1.23.8 (sin cambio).
   - **detektFormatting**: base 1.23.8 → latest 1.23.8 (sin cambio).
   - **kover**: base 0.9.1 → latest 0.9.4.
   - **skie**: base 0.10.6 → latest 0.10.9.

## Entregables (doc)
- Lista de versiones objetivo (copiadas del base) y listado de librerías mínimas.
- Plantillas de configuración descritas.
- Checklist futura:
  - [ ] libs.versions.toml actualizado con versiones del base.
  - [ ] local.properties.example y Config.xcconfig plantillas creadas.

## No incluido
- Cambios efectivos en Gradle ni creación de archivos reales.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ht-f7): libs config base (#F7)`

**Incluye:**
- Actualizacion de `docs/01-fundamentos/ta-f7-libs-config.md` (este documento).
- Actualizacion de `docs/01-fundamentos/backlog.md` si se ajusta el orden.

**Checklist:**
- [ ] Solo documentacion (sin codigo).
- [ ] Enlace a la epica `docs/01-fundamentos/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.