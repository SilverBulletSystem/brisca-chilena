# TA-DS-F1 — Tokens y theming base

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f1-tokens`

## Objetivo
Diseñar la definición completa de tokens (colores, tipografía, `ThemeDimens`, shapes) y la estructura de temas (default + variantes daltónicas), con especificación suficiente para implementar sin decisiones adicionales.

## Alcance
- Roles de color alineados a Material3 + necesidades Dui.
- Tipografía (familia, pesos, tamaños) y mapeo a `MaterialTheme.typography`.
- `ThemeDimens`: espaciados, tamaños de ítems touch (>=48dp), alturas de inputs/dropdowns, radios.
- Shapes: esquinas por jerarquía (card, dialog, chip).
- Estructura de temas: default + variantes daltónicos (Deuteranopia, Tritanopia, Achromatopsia, High Contrast) definidas en tokens.
- Contrato de `ThemeManager` y selección de variante/tema.
- Menú mínimo de selección de tema para validación manual.
- Comandos Make para probar variantes y modos.

## Dependencias
- Epica 1: reglas de `ThemeDimens`, `MaterialTheme` y i18n base.
- ADR-0017 (Atomic Design), ADR-0019 (i18n), ADR-0029 (ThemeDimens).

## Modelo de dominio
- **Token**: valor atómico reutilizable (color, tipografía, dimensión, shape).
- **Theme**: conjunto de tokens agrupados por rol.
- **ThemeVariant**: variante visual (default, daltónicos).
- **ThemeMode**: modo de color (light/dark/system).

## Contratos y datos
- Los tokens se definen como constantes tipadas (Color, TextStyle, Dp).
- Los nombres de roles deben coincidir con `MaterialTheme.colorScheme` y `MaterialTheme.typography`.
- `ThemeManager` expone selección actual y setters para variante y modo.

## Diseño (doc)
### 1) Estructura de archivos esperada
- `magicsdui/theme/ColorTokens.kt`
- `magicsdui/theme/TypographyTokens.kt`
- `magicsdui/theme/ThemeDimens.kt`
- `magicsdui/theme/Shapes.kt`
- `magicsdui/theme/ThemeManager.kt`
- `magicsdui/theme/DuiTheme.kt` (wrapper de `MaterialTheme`)

### 2) Tokens de color (roles + valores)
**Roles obligatorios (alineados a MaterialTheme.colorScheme):**
- `primary`, `onPrimary`, `primaryContainer`, `onPrimaryContainer`
- `secondary`, `onSecondary`, `secondaryContainer`, `onSecondaryContainer`
- `tertiary`, `onTertiary`, `tertiaryContainer`, `onTertiaryContainer`
- `background`, `onBackground`
- `surface`, `onSurface`, `surfaceVariant`, `onSurfaceVariant`
- `error`, `onError`, `errorContainer`, `onErrorContainer`
- `outline`, `outlineVariant`
- `scrim`, `inverseSurface`, `inverseOnSurface`, `inversePrimary`

**Paleta default (light) — valores obligatorios (morado + amarillo):**
- `primary`: `#6A1B9A`
- `onPrimary`: `#FFFFFF`
- `primaryContainer`: `#E9D5FF`
- `onPrimaryContainer`: `#2D004F`
- `secondary`: `#FBC02D`
- `onSecondary`: `#3B2E00`
- `secondaryContainer`: `#FFF4C2`
- `onSecondaryContainer`: `#3A2E00`
- `tertiary`: `#7D5260`
- `onTertiary`: `#FFFFFF`
- `tertiaryContainer`: `#FFD8E4`
- `onTertiaryContainer`: `#31111D`
- `background`: `#FFFBFE`
- `onBackground`: `#1C1B1F`
- `surface`: `#FFFBFE`
- `onSurface`: `#1C1B1F`
- `surfaceVariant`: `#E7E0EC`
- `onSurfaceVariant`: `#49454F`
- `error`: `#B3261E`
- `onError`: `#FFFFFF`
- `errorContainer`: `#F9DEDC`
- `onErrorContainer`: `#410E0B`
- `outline`: `#79747E`
- `outlineVariant`: `#CAC4D0`
- `scrim`: `#000000`
- `inverseSurface`: `#313033`
- `inverseOnSurface`: `#F4EFF4`
- `inversePrimary`: `#D0BCFF`

**Paleta default (dark) — valores obligatorios (morado + amarillo):**
- `primary`: `#D7B8FF`
- `onPrimary`: `#3A0B59`
- `primaryContainer`: `#4B1D72`
- `onPrimaryContainer`: `#F2E7FF`
- `secondary`: `#FFD54F`
- `onSecondary`: `#3A2E00`
- `secondaryContainer`: `#5C4A00`
- `onSecondaryContainer`: `#FFE7A0`
- `tertiary`: `#EFB8C8`
- `onTertiary`: `#492532`
- `tertiaryContainer`: `#633B48`
- `onTertiaryContainer`: `#FFD8E4`
- `background`: `#1C1B1F`
- `onBackground`: `#E6E1E5`
- `surface`: `#1C1B1F`
- `onSurface`: `#E6E1E5`
- `surfaceVariant`: `#49454F`
- `onSurfaceVariant`: `#CAC4D0`
- `error`: `#F2B8B5`
- `onError`: `#601410`
- `errorContainer`: `#8C1D18`
- `onErrorContainer`: `#F9DEDC`
- `outline`: `#938F99`
- `outlineVariant`: `#49454F`
- `scrim`: `#000000`
- `inverseSurface`: `#E6E1E5`
- `inverseOnSurface`: `#313033`
- `inversePrimary`: `#6750A4`

**Variantes daltónicas — equivalentes para morado/amarillo:**
Regla: solo se reemplazan roles **primary/secondary** y sus containers/on*; el resto de roles usa la paleta default.

**Deuteranopia**
- Light:
  - `primary`: `#0072B2`, `onPrimary`: `#FFFFFF`, `primaryContainer`: `#D6ECFF`, `onPrimaryContainer`: `#001D36`
  - `secondary`: `#E69F00`, `onSecondary`: `#2B1F00`, `secondaryContainer`: `#FFE4B5`, `onSecondaryContainer`: `#2B1F00`
- Dark:
  - `primary`: `#9CCEFF`, `onPrimary`: `#003352`, `primaryContainer`: `#00507A`, `onPrimaryContainer`: `#D6ECFF`
  - `secondary`: `#FFC857`, `onSecondary`: `#3A2E00`, `secondaryContainer`: `#7A5A00`, `onSecondaryContainer`: `#FFE4B5`

**Tritanopia**
- Light:
  - `primary`: `#009E73`, `onPrimary`: `#FFFFFF`, `primaryContainer`: `#C8F4E3`, `onPrimaryContainer`: `#003525`
  - `secondary`: `#CC79A7`, `onSecondary`: `#3A1A2B`, `secondaryContainer`: `#F8D7E6`, `onSecondaryContainer`: `#3A1A2B`
- Dark:
  - `primary`: `#76D7B5`, `onPrimary`: `#003A2A`, `primaryContainer`: `#006B4A`, `onPrimaryContainer`: `#C8F4E3`
  - `secondary`: `#E6A6C7`, `onSecondary`: `#4A2236`, `secondaryContainer`: `#6B3A53`, `onSecondaryContainer`: `#F8D7E6`

**Achromatopsia**
- Light:
  - `primary`: `#616161`, `onPrimary`: `#FFFFFF`, `primaryContainer`: `#E0E0E0`, `onPrimaryContainer`: `#1B1B1B`
  - `secondary`: `#9E9E9E`, `onSecondary`: `#1B1B1B`, `secondaryContainer`: `#F0F0F0`, `onSecondaryContainer`: `#1B1B1B`
- Dark:
  - `primary`: `#CFCFCF`, `onPrimary`: `#1B1B1B`, `primaryContainer`: `#7A7A7A`, `onPrimaryContainer`: `#F2F2F2`
  - `secondary`: `#B0B0B0`, `onSecondary`: `#1B1B1B`, `secondaryContainer`: `#5C5C5C`, `onSecondaryContainer`: `#F2F2F2`

**High Contrast**
- Light:
  - `primary`: `#000000`, `onPrimary`: `#FFFFFF`, `primaryContainer`: `#FFFFFF`, `onPrimaryContainer`: `#000000`
  - `secondary`: `#4D4D4D`, `onSecondary`: `#FFFFFF`, `secondaryContainer`: `#E6E6E6`, `onSecondaryContainer`: `#000000`
- Dark:
  - `primary`: `#FFFFFF`, `onPrimary`: `#000000`, `primaryContainer`: `#B3B3B3`, `onPrimaryContainer`: `#000000`
  - `secondary`: `#E6E6E6`, `onSecondary`: `#000000`, `secondaryContainer`: `#4D4D4D`, `onSecondaryContainer`: `#FFFFFF`

### 3) Tipografía (tokens + mapeo)
**Familia base:** `Default/System` (sin fuente custom en esta historia).

**Escala y tamaños (Material 3):**
- Display: `displayLarge 57sp`, `displayMedium 45sp`, `displaySmall 36sp`
- Headline: `headlineLarge 32sp`, `headlineMedium 28sp`, `headlineSmall 24sp`
- Title: `titleLarge 22sp`, `titleMedium 16sp`, `titleSmall 14sp`
- Body: `bodyLarge 16sp`, `bodyMedium 14sp`, `bodySmall 12sp`
- Label: `labelLarge 14sp`, `labelMedium 12sp`, `labelSmall 11sp`

**Pesos:**
- `display/headline/title`: `SemiBold`
- `body`: `Normal`
- `label`: `Medium`

**Mapeo obligatorio a `MaterialTheme.typography`:**
- Usar la escala anterior en cada campo homónimo.

### 4) ThemeDimens (valores obligatorios)
- `SPACE_2 = 4.dp`
- `SPACE_4 = 8.dp`
- `SPACE_6 = 12.dp`
- `SPACE_8 = 16.dp`
- `SPACE_10 = 20.dp`
- `SPACE_12 = 24.dp`
- `SPACE_16 = 32.dp`
- `SPACE_20 = 40.dp`
- `SPACE_24 = 48.dp`
- `TOUCH_TARGET_MIN = 48.dp`
- `INPUT_HEIGHT_SINGLE = 48.dp`
- `INPUT_HEIGHT_MULTI = 56.dp`
- `DROPDOWN_HEIGHT = 56.dp`
- `BORDER_WIDTH = 1.dp`
- `DIVIDER_HEIGHT = 1.dp`

### 5) Shapes (radios obligatorios)
- `RADIUS_SM = 8.dp`
- `RADIUS_MD = 12.dp`
- `RADIUS_LG = 16.dp`
- `RADIUS_XL = 24.dp`
- `RADIUS_PILL = 999.dp`

### 6) ThemeManager (contrato esperado)
- `ThemeVariant`: `DEFAULT`, `DEUTERANOPIA`, `TRITANOPIA`, `ACHROMATOPSIA`, `HIGH_CONTRAST`
- `ThemeMode`: `LIGHT`, `DARK`, `SYSTEM`
- `ThemeSelection`:
  - `variant: ThemeVariant`
  - `mode: ThemeMode`
- `ThemeManager` expone:
  - `currentSelection` (estado observable)
  - `setVariant(ThemeVariant)`
  - `setMode(ThemeMode)`
- Persistencia **no** se implementa aquí (se documenta como futura).

## UI y UX
- El DS no define pantallas de negocio; solo temas y tokens.
- Se requiere **menú mínimo de temas** para validar variantes y modos.

**Menú de temas (alcance mínimo):**
- Pantalla simple con:
  - Selector de variante (`DEFAULT`, `DEUTERANOPIA`, `TRITANOPIA`, `ACHROMATOPSIA`, `HIGH_CONTRAST`).
  - Selector de modo (`LIGHT`, `DARK`, `SYSTEM`).
  - Vista previa con 2 bloques:
    - Botón primario + texto sobre `primary`.
    - Card/surface con `surface` y texto `onSurface`.
- La pantalla se expone vía ruta de bitácora: `app://bitacora/theme`.

**Make targets:**
- `make run-bitacora-theme-default-light`
- `make run-bitacora-theme-default-dark`
- `make run-bitacora-theme-deuteranopia-light`
- `make run-bitacora-theme-deuteranopia-dark`
- `make run-bitacora-theme-tritanopia-light`
- `make run-bitacora-theme-tritanopia-dark`
- `make run-bitacora-theme-achromatopsia-light`
- `make run-bitacora-theme-achromatopsia-dark`
- `make run-bitacora-theme-high-contrast-light`
- `make run-bitacora-theme-high-contrast-dark`

## Flujos y secuencias
1) Abrir bitácora de temas vía Make.
2) Selección de variante de tema.
3) Selección de modo (light/dark/system).
4) Aplicación de tokens en `DuiTheme`.

## Seguridad y privacidad
- No aplica (sin PII ni persistencia en esta historia).

## Performance
- Sin impacto relevante; tokens son constantes en memoria.

## Observabilidad
- No se requieren eventos ni logs en esta historia.

## Testing
- Checklist de consistencia: roles completos y mapeo correcto.
- Verificación manual de que `MaterialTheme` recibe todos los tokens.
- Validación visual de menú de temas para cada variante/modo.

## Criterios de aceptacion
- Tokens definidos con roles completos y valores explícitos.
- Tipografía mapeada a `MaterialTheme.typography` con tamaños/pesos definidos.
- `ThemeDimens` y shapes con valores definidos.
- `ThemeManager` con contrato de selección de variante/modo.
- Existe bitácora de temas con selección de variante/modo.
- Existen comandos Make para abrir cada variante/modo.

## Pendientes y riesgos
- Ajuste futuro de paletas daltónicas reales.
- Riesgo de inconsistencias si se agregan roles fuera del estándar.

## Entregables (documento)
- Lista de tokens por categoría (colores, tipografía, dimens, shapes).
- Tabla/resumen de temas y roles.
- Pauta de integración con `ThemeManager`.

## Verificación futura
- Tokens cubren todos los roles usados por átomos/moléculas/organismos Dui.
- Variantes daltónicas definen primary/secondary y containers según tabla.

## No incluido
- Implementación de temas en código ni UI.
 - UI de configuración persistente del tema (se define en historia futura).

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ds-f1): tokens y theming base (#2)`

**Incluye:**
- Actualización de `docs/02-designsystem/ta-f1-tokens-theming.md`.
- Actualización de `docs/02-designsystem/backlog.md` si aplica.
- Entrada en `CHANGELOG.md` bajo `[Unreleased]`.

**Checklist:**
- [ ] Solo documentación (sin código).
- [ ] Enlace a la épica `docs/02-designsystem/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.