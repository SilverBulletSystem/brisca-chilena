# TA-DS-F3 — Moléculas Dui y bitácora

## Rama
- `feat/ht-ds-f3-molecules`

## Objetivo
Definir el catálogo de moléculas `Dui*` y la bitácora de moléculas con estados/variantes, incluyendo navegación directa vía comando Make. Sin implementación de código.

## Alcance
- Lista de moléculas y sus variantes/estados.
- Diseño de pantalla “Bitácora de Moléculas”.
- Comando Make y ruta de navegación.

## Diseño (doc)
1) **Catálogo de moléculas `Dui*`**
   - `DuiIconButton`
   - `DuiTextField` (single/password, con iconos/label)
   - `DuiCheckbox`
   - `DuiToggle` (switch con label/estado)
   - `DuiListItem` (leading/trailing)
   - `DuiSettingsItem`
   - `DuiCard`
   - `DuiBanner`
   - `DuiSnackbar`
   - `DuiDialog`/`DuiBottomSheet`
   - `DuiDropdown`/`DuiSelect`
   - `DuiTabs`/`DuiFilterChips`
   - `DuiAccordion`
   - `DuiAvatar`
   - `DuiItemCard`
   - `DuiTypingIndicator`
   - `DuiMessageStatus`
   - Botones con identidad: `DuiLoginButton`, `DuiRegisterButton`, `DuiRecoverButton`, `DuiVerifyAccountButton`, `DuiLogoutButton`
   - Selectores: `DuiLanguageSelector`/`DuiLanguageOption`, `DuiSoundToggle` (molecular)
2) **Estados/props**
- enabled/disabled, focus/pressed (touch down), error; hover solo Web/Desktop; tamaños (S/M/L) cuando aplique, íconos opcionales, conteos/badges.
3) **Bitácora de Moléculas (pantalla)**
   - Secciones por tipo (inputs, listas/cards, feedback/dialogs, navegación secundaria, identitarios).
   - Para cada molécula: ejemplos de estados/props relevantes.
4) **Navegación/Make**
   - Depende de F2 (atajos Make). Confirmar uso de la ruta `app://bitacora/molecules` y target `make run-bitacora-molecules` definidos en F2.

## Entregables (documento)
- Catálogo de moléculas y estados.
- Diseño de la pantalla de bitácora de moléculas.
- Definición de ruta y comando Make.

## Verificación futura
- Catálogo cubre todas las moléculas utilizadas por organismos.
- Ruta y comando documentados.

## No incluido
- Implementación en código.
