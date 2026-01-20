# TA-DS-F4 — Moléculas Dui y bitácora

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f4-molecules`

## Objetivo
Definir y crear el catálogo completo de moléculas Dui y su bitácora, con estados/variantes cerrados y validación visual. Sin implementación de código.

## Alcance
- Catálogo completo de moléculas del sistema de diseño.
- Variantes/estados por molécula.
- Diseño de pantalla “Bitácora de Moléculas”.
- Ruta y comando Make para abrir esta pantalla.

## Dependencias
- TA-DS-F2 (tokens y theming).
- TA-DS-F1 (rutas y comandos Make).

## Diseño (doc)
### 1) Catálogo completo de moléculas (Dui)
- `DuiIconButton`
- `DuiRemoteImage` (carga desde URL)
- `DuiTextField` (single/password, con iconos/label)
- `DuiCheckbox`
- `DuiToggle` (switch con label/estado)
- `DuiListItem` (leading/trailing)
- `DuiSettingsItem`
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
- Selectores: `DuiLanguageSelector`/`DuiLanguageOption`, `DuiSoundToggle`

### 2) Estados y props por molécula (cerrado)
**DuiIconButton**
- Estados: normal, disabled, pressed.
- Tamaño: usar `ThemeDimens.ICON_MD`.

**DuiRemoteImage**
- Props obligatorias:
  - `url: String`
  - `contentDescription: String`
  - `size: Dp` (usar `ThemeDimens.IMAGE_SM` y `ThemeDimens.IMAGE_MD`)
- Estados:
  - `loading` (placeholder)
  - `success` (imagen)
  - `error` (fallback)
- Regla: siempre mostrar placeholder mientras carga; fallback si falla.

**Biblioteca obligatoria (KMP):**
- Coil KMP (`io.coil-kt:coil-compose` y engine multiplataforma).

**URL de prueba (obligatoria):**
- `https://rickandmortyapi.com/api/character/avatar/15.jpeg`

**Código de referencia (KMP):**
```kotlin
@Composable
fun DuiRemoteImage(
    url: String,
    contentDescription: String,
    size: Dp
) {
    AsyncImage(
        model = url,
        contentDescription = contentDescription,
        modifier = Modifier.size(size)
    )
}
```

**DuiTextField**
- Variantes: single / password.
- Estados: normal, focused, error, disabled.
- Icono leading opcional; trailing visible en password.

**DuiCheckbox**
- Estados: unchecked, checked, disabled.

**DuiToggle**
- Estados: off, on, disabled.
- Label obligatorio.

**DuiListItem**
- Estados: normal, disabled.
- Leading icon opcional; trailing value opcional.

**DuiSettingsItem**
- Estados: normal, disabled.
- Leading icon obligatorio; trailing value opcional.

**DuiCard**
- Estados: normal, outlined.

**DuiBanner**
- Estados: info, warning, error.
- Acción opcional.

**DuiSnackbar**
- Estados: info, error.
- Acción opcional.

**DuiDialog / DuiBottomSheet**
- Estados: abierto (visible) con título + cuerpo + CTA primaria.

**DuiDropdown / DuiSelect**
- Estados: normal, focused, disabled.
- Lista con 3 ítems de ejemplo.

**DuiTabs / DuiFilterChips**
- Estados: selected / unselected.
- 3 tabs/chips.

**DuiAccordion**
- Estados: collapsed / expanded.

**DuiAvatar**
- Estados: image / initials / placeholder.
- Tamaño: usar `ThemeDimens.ICON_LG`.

**DuiItemCard**
- Estados: normal, selected.

**DuiTypingIndicator**
- Estados: on / off.

**DuiMessageStatus**
- Estados: sent / delivered / read.

**Botones con identidad**
- Estados: normal, disabled.

**DuiLanguageSelector / DuiLanguageOption**
- Estados: selected / unselected.
- Dos opciones: ES / EN.

**DuiSoundToggle**
- Estados: on / off.

**DuiColorSwatchRow**
- Renderiza 6 swatches en orden fijo.

### 3) Bitácora de Moléculas (pantalla)
- **Título:** `DuiText.Title` → “Bitácora de moléculas”.
- **Sección Inputs:**
  - `DuiTextField` (normal/focused/error/disabled).
  - `DuiCheckbox`, `DuiToggle`, `DuiDropdown`, `DuiSelect`.
- **Sección Listas/Cards:**
  - `DuiListItem`, `DuiSettingsItem`, `DuiItemCard`.
- **Sección Media:**
  - `DuiRemoteImage` en `IMAGE_SM` y `IMAGE_MD` (loading/success/error).
- **Sección Feedback:**
  - `DuiBanner`, `DuiSnackbar`, `DuiDialog`, `DuiBottomSheet`.
- **Sección Navegación secundaria:**
  - `DuiTabs`, `DuiFilterChips`, `DuiAccordion`.
- **Sección Identitarios:**
  - `DuiLoginButton`, `DuiRegisterButton`, `DuiRecoverButton`, `DuiVerifyAccountButton`, `DuiLogoutButton`.
- **Sección Chat/Estado:**
  - `DuiTypingIndicator`, `DuiMessageStatus`, `DuiAvatar`.
- **Sección Tema:**
  - (No aplica en esta historia).

### 4) Navegación/Make
- Ruta: `app://bitacora/molecules`.
- Comando: `make run-bitacora-molecule`.

## Wireframe (servilleta)
```
Bitácora de moléculas
────────────────────────────────────────
[Sección: Inputs]
TextField (normal/focus/error/disabled)
Checkbox / Toggle / Dropdown / Select

[Sección: Listas/Cards]
ListItem / SettingsItem
ItemCard

[Sección: Media]
RemoteImage SM/MD (loading/success/error)

[Sección: Feedback]
Banner / Snackbar
Dialog / BottomSheet

[Sección: Navegación secundaria]
Tabs / FilterChips / Accordion

[Sección: Identitarios]
Login / Register / Recover / Verify / Logout

[Sección: Chat/Estado]
TypingIndicator / MessageStatus / Avatar

```

## Entregables (documento)
- Estados/variantes por molécula.
- Diseño de la pantalla de bitácora de moléculas.
- Ruta y comando Make esperado.

## Verificación futura
- Estados cubren todas las moléculas definidas en esta historia.
- La pantalla respeta el wireframe definido.

## Criterios de aceptacion
- La bitácora muestra todas las secciones definidas.
- Se visualizan los estados por molécula según esta historia.
- `run-bitacora-molecule` abre la pantalla correcta.

## No incluido
- Implementación en código.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ds-f4): bitacora moleculas (#2)`

**Incluye:**
- Actualización de `docs/02-designsystem/ta-f4-molecules-bitacora.md`.
- Entrada en `CHANGELOG.md` bajo `[Unreleased]`.

**Checklist:**
- [ ] Solo documentación (sin código).
- [ ] Enlace a la épica `docs/02-designsystem/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.