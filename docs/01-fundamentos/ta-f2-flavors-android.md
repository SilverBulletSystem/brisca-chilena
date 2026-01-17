# TA-F2 — Flavors Android (mock/dev/prod)

## Objetivo
Diseñar la configuración de flavors/buildTypes en Android: IDs, suffixes, endpoints/logging placeholders, y comandos de assemble, sin implementar código.

## Rama
- Crear desde `main`: `feat/ht-f2-flavors-android`.

## Alcance
- Flavors: `mock`, `dev`, `prod` (cada uno con `debug`/`release`).
- `applicationIdSuffix` y `versionNameSuffix` por flavor.
- `BuildConfig`/resources por flavor con placeholders de endpoint y logging level.
- Comandos Makefile de assemble por flavor (sin tests).

## Diseño
1) `build.gradle.kts` (Android):
   - Definir productFlavors `mock`, `dev`, `prod`.
   - `applicationIdSuffix`: `.mock`, `.dev`, (prod sin sufijo).
   - `versionNameSuffix`: `-mock`, `-dev`, (prod sin sufijo).
   - `buildTypes`: `debug` (minify false), `release` (minify según política futura).
   - `buildConfigField` por flavor: `BASE_URL`, `LOG_LEVEL` (`DEBUG` para mock/dev, `NONE` para prod).
2) Resources por flavor (opcional):
   - `strings.xml` con `environment_name` y `api_base_url` placeholders.
3) Makefile:
   - `assemble-mock-debug`, `assemble-dev-debug`, `assemble-prod-release` mapeando a gradle tasks.
   - Sin tests en assemble.
4) Logging:
   - Mock/Dev: logging detallado; Prod: deshabilitado.
5) Endpoints:
   - Mock: placeholder (Mockoon o local) — documentar que aún no existe mock server.
   - Dev/Prod: placeholders apuntando a futuros backends.

## Entregables (doc)
- Instrucciones detalladas (este TA) + ejemplo de bloques Gradle (texto) + comandos Makefile esperados.
- Checklist de validación futura:
  - [ ] `assembleMockDebug` corre.
  - [ ] `assembleDevDebug` corre.
  - [ ] `assembleProdRelease` corre.
  - [ ] `BuildConfig` tiene `BASE_URL` y `LOG_LEVEL` correctos por flavor.

## No incluido
- Implementación real en Gradle/Makefile.
- Mock server o endpoints reales.
