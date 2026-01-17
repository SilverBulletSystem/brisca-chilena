# TA-F8 — Libs y config comunes

## Objetivo
Diseñar la alineación de versiones y configuraciones comunes basadas en el proyecto base. Sin ejecución ni cambios de código.

## Rama
- Crear desde `main`: `feat/ht-f8-libs-config`.

## Alcance
- `libs.versions.toml` alineado al proyecto base (Kotlin, Compose, Ktor, Koin, etc.).
- Plantillas de config locales (`local.properties.example`) y xcconfig.

## Diseño
1) `libs.versions.toml`:
   - Tomar versiones del proyecto base y listarlas (sin aplicar aún en el Gradle actual).
   - Notar posibles upgrades futuros, pero documentar que se mantienen las del base para consistencia inicial.
2) Config plantillas:
   - `local.properties.example`: claves esperadas (ej. sdk.dir, api keys placeholders, endpoints locales opcionales).
   - `Config.xcconfig` plantilla: `BASE_URL`, `LOG_LEVEL`, `FLAVOR_NAME` placeholders alineados con F3.
3) Orden de aplicación:
   - Primero consolidar las versiones en doc; luego, cuando se implemente, reemplazar el toml actual.

## Entregables (doc)
- Lista de versiones objetivo (copiadas del base).
- Plantillas de configuración descritas.
- Checklist futura:
  - [ ] libs.versions.toml actualizado con versiones del base.
  - [ ] local.properties.example y Config.xcconfig plantillas creadas.

## No incluido
- Cambios efectivos en Gradle ni creación de archivos reales.
