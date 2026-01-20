# TA-F8 — Esquemas/Flavors iOS (spike)

## Navegacion
- [Volver a Epica](01-fundamentos/epica.md)
- [Volver a Backlog](01-fundamentos/backlog.md)

## Objetivo
Diseñar la configuración de esquemas y xcconfig por ambiente en iOS, alineado al estándar del proyecto: **debug** para mock/dev y **release** para prod.

## Rama
- Crear desde `main`: `feat/ht-f9-flavors-ios`.

## Alcance
- Esquemas: `MockDebug`, `DevDebug`, `ProdRelease`.
- `MockDebug` y `DevDebug` usan build configuration **Debug** con sufijo `.dev`.
- `ProdRelease` usa build configuration **Release** sin sufijo (tienda).
- xcconfig por ambiente con variables: `BASE_URL`, `LOG_LEVEL`, `FLAVOR_NAME`.
- Guía de ejecución en Xcode.

## Diseño
1) Esquemas y configuraciones:
   - `MockDebug` → Debug + sufijo `.dev`.
   - `DevDebug` → Debug + sufijo `.dev`.
   - `ProdRelease` → Release + sin sufijo.
2) xcconfig por ambiente:
   - Definir claves: `BASE_URL`, `LOG_LEVEL`, `FLAVOR_NAME`.
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

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ht-f9): spike esquemas ios (#F9)`

**Incluye:**
- Actualizacion de `docs/01-fundamentos/ta-f9-flavors-ios.md` (este documento).
- Actualizacion de `docs/01-fundamentos/backlog.md` con el orden final.
- ADR si el spike define una decision de estructura iOS.

**Checklist:**
- [ ] Solo documentacion (sin codigo).
- [ ] Enlace a la epica `docs/01-fundamentos/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.