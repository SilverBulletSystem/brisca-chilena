# TA-F3 — Esquemas/Flavors iOS (spike)

## Objetivo
Diseñar la configuración de esquemas y xcconfig por ambiente en iOS: MockDebug, DevDebug, ProdRelease, con endpoints/logging placeholders. Sin implementación de código.

## Rama
- Crear desde `main`: `feat/ht-f3-flavors-ios`.

## Alcance
- Esquemas: `MockDebug`, `DevDebug`, `ProdRelease`.
- xcconfig por ambiente con variables: `BASE_URL`, `LOG_LEVEL`, `FLAVOR_NAME`.
- Guía de ejecución en Xcode.

## Diseño
1) Esquemas:
   - `MockDebug`: logging verbose, endpoint mock (placeholder).
   - `DevDebug`: logging info, endpoint dev (placeholder).
   - `ProdRelease`: logging none, endpoint prod (placeholder).
2) xcconfig por ambiente:
   - Definir claves: `BASE_URL=<placeholder>`, `LOG_LEVEL=<DEBUG|INFO|NONE>`, `FLAVOR_NAME=<mock|dev|prod>`.
   - Incluir en build settings de cada esquema.
3) Acceso en Swift:
   - Documentar cómo leer variables de Info.plist/xcconfig (ej. `Bundle.main.infoDictionary` o `#if DEBUG` + flags).
4) Guía de ejecución:
   - Abrir `iosApp.xcodeproj`, seleccionar esquema y correr en simulador/dispositivo.
5) Signing:
   - Usar TeamID placeholder; no se configura firma real.

## Entregables (doc)
- Este TA con pasos y ejemplos de claves xcconfig.
- Checklist futura:
  - [ ] Cada esquema compila (cuando se implemente).
  - [ ] Variables accesibles en Swift.

## No incluido
- Implementación real en Xcode/xcconfig.
- Configuración de firma o perfiles reales.
