# Épica 1: Fundamentos del proyecto

Namespace base: `cl.silverbullet.multiplatform.brisca` (applicationId/bundleId ya definido).

## Historias técnicas propuestas

### F1 – Namespace y estructura de módulos
- Objetivo: validar namespace ya definido y crear skeleton de módulos (`core`, `commons`, `features`, `designsystem`, `magicsdui`, `inkribbon`, `toad`, `flags`, `network`, `storage`).
- Entregables:
  - Confirmación de namespace `cl.silverbullet.multiplatform.brisca` (sin renombrar paquetes si ya está correcto).
  - Carpetas/módulos base creados (sin lógica aún) con un archivo placeholder por módulo.
  - README corto en `composeApp` con árbol actualizado.
- Criterios de aceptación:
  - `./gradlew :composeApp:assembleDebug` compila tras añadir la estructura; tras definir flavors (F2) compila `assembleMockDebug`.
- No incluido: wiring de DI, lógica de features.

### F2 – Flavors Android (mock/dev/prod)
- Objetivo: definir flavors y buildTypes con suffixes claros y config de endpoints/logging por ambiente.
- Entregables:
  - `applicationIdSuffix` y `versionNameSuffix` por flavor.
  - `BuildConfig`/resources por flavor con placeholders de endpoint y logging (mock aún sin mock server real; se documenta el placeholder).
  - Script de assemble rápido en Makefile referenciando flavors.
- Criterios de aceptación:
  - `assembleMockDebug`, `assembleDevDebug`, `assembleProdRelease` corren sin tests.
  - Logging nivel DEBUG solo en mock/dev.
- No incluido: contratos SDUI ni mock server implementado (se definirá en otra épica/tarea).

### F3 – Flavors/Esquemas iOS (spike)
- Objetivo: crear esquemas `MockDebug`, `DevDebug`, `ProdRelease` con xcconfig por ambiente.
- Entregables:
  - xcconfig por ambiente con endpoints/logging placeholders.
  - Guía breve para lanzar cada esquema en Xcode.
- Criterios de aceptación:
  - Build de cada esquema sin romper el proyecto.
  - Variables de entorno disponibles en Swift (ej. endpoints).
- No incluido: firmar con team real; usar TeamID placeholder.

### F4 – Makefile seeds (assemble-first)
- Objetivo: comandos mínimos orientados a assemble rápido (no `build`).
- Entregables:
  - `make assemble-mock-debug`, `assemble-dev-debug`, `assemble-prod-release`.
  - `make test-unit`, `make detekt`, `make mockoon-*` placeholders.
  - `make help` actualizado.
- Criterios de aceptación:
  - Los comandos ejecutan sin pasos innecesarios (sin tests en assemble).
- No incluido: despliegues ni publicación.

### F5 – DI baseline con Koin
- Objetivo: wiring inicial sin lógica, para habilitar registro de módulos por capa.
- Entregables:
  - Módulos Koin vacíos/placeholder (core, network, storage, feature-flags, sdui).
  - Hook de arranque en entrypoints Android/iOS (App init).
- Criterios de aceptación:
  - App arranca con Koin sin fallar (assemble mock).
- No incluido: providers concretos de network/storage.

### F6 – Detekt (spike reglas Cursor)
- Objetivo: portar configuración de Detekt del proyecto base adaptada al namespace y reglas de Cursor.
- Entregables:
  - `detekt.yml` ajustado; task integrada al Makefile (`make detekt`).
- Reglas clave: no hardcode strings UI, naming de casos de uso, temas/dimens, atomicidad, capas limpias.
- Criterios de aceptación:
  - `./gradlew detekt` pasa en limpio en el estado actual del código.
- No incluido: fixes de findings futuros.

### F7 – i18n base ES/EN
- Objetivo: scaffolding de strings segregadas por pantalla (interfaces) con ES/EN.
- Entregables:
  - Contratos de strings por pantalla (ej. `SplashStrings`, `LoginStrings`).
  - Ejemplo aplicado en una pantalla (Splash/Login) para validar wiring.
- Criterios de aceptación:
  - Cambio de locale refleja textos ES/EN en la pantalla ejemplo.
- No incluido: traducción completa de todas las pantallas.

### F8 – Libs y config comunes
- Objetivo: alinear `libs.versions.toml` y configs Gradle con el proyecto base.
- Entregables:
  - Versions de Kotlin/Compose/Ktor/Koin sincronizadas.
  - `local.properties.example` y `Config.xcconfig` plantilla.
- Criterios de aceptación:
  - Assemble mock funciona con las versiones fijadas.
- No incluido: upgrades mayores fuera de las versiones del base sin validación.

## Orden sugerido de implementación
1) F1 Namespace/estructura → 2) F2 Flavors Android → 3) F3 Esquemas iOS → 4) F4 Makefile → 5) F8 Libs/config → 6) F5 Koin → 7) F6 Detekt → 8) F7 i18n base.
