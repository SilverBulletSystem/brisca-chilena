# TA-DS-F2 — Átomos Dui y bitácora

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f2-atoms`

## Objetivo
Definir el diseño de la bitácora de átomos y sus estados, usando el catálogo ya definido en TA-DS-F1. Sin implementación de código.

## Alcance
- Estados a mostrar por átomo (solo los definidos en esta historia).
- Diseño de pantalla “Bitácora de Átomos”: layout, agrupación, props a mostrar.
- Ruta y comando Make para abrir esta pantalla.

## Dependencias
- TA-DS-F1 (catálogo mínimo y tokens).
- TA-DS-F2A (rutas y comandos Make).

## Diseño (doc)
### 1) Catálogo mínimo de átomos (de TA-DS-F1)
- `DuiText` (Title/Body)
- `DuiColorSwatch`
- `DuiSpacer`
- `DuiDivider`

### 2) Estados y props por átomo (cerrado)
**DuiText**
- Variantes: `Title`, `Body`.
- Estados: `normal`, `disabled` (usar `onSurfaceVariant`).
- Tamaños: usar escala definida en TA-DS-F1.

**DuiColorSwatch**
- Mostrar 6 swatches: `primary`, `secondary`, `tertiary`, `surface`, `background`, `error`.
- Tamaño: `24.dp`.
- Borde: `outline` con `BORDER_WIDTH`.

**DuiSpacer**
- Mostrar tamaños: `SPACE_4`, `SPACE_8`, `SPACE_12`.

**DuiDivider**
- Mostrar un divider estándar con `DIVIDER_HEIGHT`.

### 3) Bitácora de Átomos (pantalla)
- **Título:** `DuiText.Title` → “Bitácora de átomos”.
- **Sección Tipografía:**
  - `DuiText.Title` normal y disabled.
  - `DuiText.Body` normal y disabled.
- **Sección Colores:**
  - Fila `DuiColorSwatch` con los 6 colores del tema.
- **Sección Layout:**
  - `DuiSpacer` con etiquetas de tamaño (4/8/12).
  - `DuiDivider` estándar.

### 4) Navegación/Make
- Ruta: `app://bitacora/atoms`.
- Comando: `make run-bitacora-atoms`.

## Wireframe (servilleta)
```
Bitácora de átomos
────────────────────────────────────────
[Sección: Tipografía]
Title (normal)   Title (disabled)
Body (normal)    Body (disabled)

[Sección: Colores]
[●][●][●][●][●][●]  (primary, secondary, tertiary, surface, background, error)

[Sección: Layout]
Spacer 4   Spacer 8   Spacer 12
Divider ───────────────────────
```

## Entregables (documento)
- Estados/props por átomo.
- Diseño de la pantalla de bitácora de átomos.
- Ruta y comando Make esperado.

## Verificación futura
- Estados cubren los átomos definidos en TA-DS-F1.
- La pantalla respeta el wireframe definido.

## Criterios de aceptacion
- La bitácora muestra las 3 secciones con los átomos definidos.
- Se visualizan estados normal/disabled para `DuiText`.
- Se visualiza la fila de 6 swatches.
- `run-bitacora-atoms` abre la pantalla correcta.

## No incluido
- Implementación en código ni creación real de la pantalla.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ds-f2b): bitacora atomos (#2)`

**Incluye:**
- Actualización de `docs/02-designsystem/ta-f2-atoms-bitacora.md`.
- Entrada en `CHANGELOG.md` bajo `[Unreleased]`.

**Checklist:**
- [ ] Solo documentación (sin código).
- [ ] Enlace a la épica `docs/02-designsystem/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.