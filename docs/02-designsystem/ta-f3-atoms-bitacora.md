# TA-DS-F3 — Átomos Dui y bitácora

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f3-atoms`

## Objetivo
Definir y crear el catálogo completo de átomos Dui y su bitácora, con estados y validación visual. Sin implementación de código.

## Alcance
- Catálogo completo de átomos del sistema de diseño.
- Estados a mostrar por átomo.
- Diseño de pantalla “Bitácora de Átomos”: layout, agrupación, props a mostrar.
- Ruta y comando Make para abrir esta pantalla.

## Dependencias
- TA-DS-F2 (tokens y theming).
- TA-DS-F1 (rutas y comandos Make).

## Diseño (doc)
### 1) Catálogo completo de átomos (Dui)
- `DuiText` (Title/Subtitle/Body/Caption)
- `DuiIcon`
- `DuiButton` (filled/tonal/outlined/text + icon)
- `DuiFab`
- `DuiDivider`
- `DuiSpacer`
- `DuiChip`/`DuiTag`
- `DuiBadge` (incl. numérica)
- `DuiProgress` (linear/circular)
- `DuiRadio`
- `DuiSwitch`
- `DuiColorSwatch`

### 2) Estados y props por átomo (cerrado)
**DuiText**
- Variantes: `Title`, `Subtitle`, `Body`, `Caption`.
- Estados: `normal`, `disabled` (usar `onSurfaceVariant`).
- Tamaños: usar escala definida en TA-DS-F2.

**DuiIcon**
- Estados: `normal`, `disabled`.
- Tamaños: `ThemeDimens.ICON_SM`, `ThemeDimens.ICON_MD`, `ThemeDimens.ICON_LG`.
- Iconos se referencian por `DuiIconId` (ADR-0001).

**DuiButton**
- Variantes: filled, tonal, outlined, text.
- Estados: normal, disabled, loading.
- Con icono y sin icono.

**DuiFab**
- Estados: normal, disabled.
- Con icono obligatorio.

**DuiDivider**
- Mostrar un divider estándar con `DIVIDER_HEIGHT`.

**DuiSpacer**
- Mostrar tamaños: `SPACE_4`, `SPACE_8`, `SPACE_12`.

**DuiChip / DuiTag**
- Estados: normal, selected, disabled.

**DuiBadge**
- Estados: normal.
- Variante numérica y punto.

**DuiProgress**
- Linear y circular.
- Estados: normal e indeterminado.

**DuiRadio**
- Estados: unchecked, checked, disabled.

**DuiSwitch**
- Estados: off, on, disabled.

**DuiColorSwatch**
- Mostrar 6 swatches: `primary`, `secondary`, `tertiary`, `surface`, `background`, `error`.
- Tamaño: `ThemeDimens.SWATCH_MD`.
- Borde: `outline` con `BORDER_WIDTH`.

### 3) Bitácora de Átomos (pantalla)
- **Título:** `DuiText.Title` → “Bitácora de átomos”.
- **Sección Tipografía:**
  - `DuiText` en Title/Subtitle/Body/Caption (normal y disabled).
- **Sección Iconografía:**
  - `DuiIcon` en 16/24/32 (normal y disabled).
- **Sección Botones:**
  - `DuiButton` en 4 variantes + estados (normal/disabled/loading) con y sin icono.
  - `DuiFab` normal/disabled.
- **Sección Layout:**
  - `DuiSpacer` 4/8/12.
  - `DuiDivider`.
- **Sección Chips/Badges:**
  - `DuiChip`/`DuiTag` normal/selected/disabled.
  - `DuiBadge` punto y numérica.
- **Sección Progreso:**
  - `DuiProgress` linear/circular (normal/indeterminado).
- **Sección Inputs básicos:**
  - `DuiRadio` unchecked/checked/disabled.
  - `DuiSwitch` off/on/disabled.
- **Sección Colores:**
  - Fila `DuiColorSwatch` con los 6 colores del tema.

### 4) Navegación/Make
- Ruta: `app://bitacora/atoms`.
- Comando: `make run-bitacora-atoms`.

## Wireframe (servilleta)
```
Bitácora de átomos
────────────────────────────────────────
[Sección: Tipografía]
Title / Subtitle / Body / Caption (normal + disabled)

[Sección: Iconografía]
Icon 16 / 24 / 32 (normal + disabled)

[Sección: Botones]
Button filled/tonal/outlined/text (normal/disabled/loading)
Fab (normal/disabled)

[Sección: Layout]
Spacer 4 / 8 / 12
Divider

[Sección: Chips/Badges]
Chip/Tag (normal/selected/disabled)
Badge (dot/number)

[Sección: Progreso]
Progress linear/circular (normal/indeterminado)

[Sección: Inputs básicos]
Radio (unchecked/checked/disabled)
Switch (off/on/disabled)

[Sección: Colores]
[●][●][●][●][●][●]
```

## Entregables (documento)
- Estados/props por átomo.
- Diseño de la pantalla de bitácora de átomos.
- Ruta y comando Make esperado.

## Verificación futura
- Estados cubren todos los átomos definidos en esta historia.
- La pantalla respeta el wireframe definido.

## Criterios de aceptacion
- La bitácora muestra todas las secciones definidas.
- Se visualizan los estados por átomo según esta historia.
- Se visualiza la fila de 6 swatches.
- `run-bitacora-atoms` abre la pantalla correcta.

## No incluido
- Implementación en código ni creación real de la pantalla.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ds-f3): bitacora atomos (#2)`

**Incluye:**
- Actualización de `docs/02-designsystem/ta-f3-atoms-bitacora.md`.
- Entrada en `CHANGELOG.md` bajo `[Unreleased]`.

**Checklist:**
- [ ] Solo documentación (sin código).
- [ ] Enlace a la épica `docs/02-designsystem/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.