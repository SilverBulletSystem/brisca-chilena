# TA-F6 — i18n base ES/EN

## Navegacion
- [Volver a Epica](01-fundamentos/epica.md)
- [Volver a Backlog](01-fundamentos/backlog.md)

## Objetivo
Diseñar la primera implementación mínima de i18n: un string de nombre en ES/EN y un Splash vacío que lo muestre según idioma seleccionado.

## Rama
- Crear desde `main`: `feat/ht-f6-i18n-base`.

## Alcance
- Un único string de nombre con soporte ES/EN.
- Splash vacío que muestra el string según idioma seleccionado.
- Comandos Make para probar ES/EN.

## Diseño
1) String mínimo:
   - Definir `AppStrings` (o equivalente) con `appName`.
   - Implementaciones ES/EN con el nombre real.
2) Splash mínimo:
   - Crear `SplashScreen` vacío que solo renderiza `appName` según idioma.
3) Selección de idioma:
   - Permitir seleccionar `es` o `en` para la prueba (vía parámetro en Make).
4) Proveedor centralizado de strings:
   - Crear un `StringsProvider` (o `LanguageProvider`) en memoria con idioma actual.
   - Expone `currentLanguage` y `getStrings(): AppStrings`.
   - Se registra en DI (Koin) para consumo desde UI:
     - `single { StringsProvider(initialLanguage = AppLanguage.DEFAULT) }`
   - `AppLanguage` es un `enum` centralizado:
     - Ubicación: `core/language/AppLanguage.kt`.
     - Paquete: `cl.silverbullet.multiplatform.brisca.core.language`.
     - Valores: `ES`, `EN`.
     - `DEFAULT = ES` (sin strings hardcodeados en DI).
   - Persistencia se agrega más adelante (fuera de esta historia).
5) Comandos Make:
   - `make test-splash-es` → levanta Splash en español.
   - `make test-splash-en` → levanta Splash en inglés.
   - El último parámetro indica el idioma seleccionado.

## Entregables (doc)
- Definición de `AppStrings` con `appName`.
- Splash vacío con render del nombre.
- Comandos `test-splash-es` y `test-splash-en`.
- Checklist futura:
  - [ ] `appName` definido en ES/EN.
  - [ ] Splash muestra el texto según idioma.
  - [ ] `StringsProvider` centralizado en memoria y registrado en Koin.
  - [ ] Makefile incluye `test-splash-es` y `test-splash-en`.
  - [ ] `make detekt` pasa sin findings nuevos.

## No incluido
- Flujos completos de i18n por pantalla.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ht-f6): i18n base splash es/en (#F6)`

**Incluye:**
- Actualizacion de `docs/01-fundamentos/ta-f6-i18n-base.md` (este documento).
- Actualizacion de `docs/01-fundamentos/backlog.md` si se ajusta el orden.

**Checklist:**
- [ ] Solo documentacion (sin codigo).
- [ ] Enlace a la epica `docs/01-fundamentos/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.