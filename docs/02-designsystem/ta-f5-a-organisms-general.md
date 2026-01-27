# TA-DS-F5-A — Organismos generales y bitácora

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f5-a-organisms-general`

## Objetivo
Definir y crear el catálogo de organismos generales y su bitácora, con estados/props cerrados y validación visual. Sin implementación de código.

## Alcance
- Catálogo completo de organismos generales del sistema de diseño.
- Bitácora de organismos generales: secciones y props a mostrar.
- Ruta y comando Make para abrir esta pantalla.

## Dependencias
- TA-DS-F2 (tokens y theming).
- TA-DS-F1 (rutas y comandos Make).

## Diseño (doc)
### 1) Catálogo completo de organismos generales (Dui)
- `DuiAppScaffold`
- `DuiTopBar`
- `DuiBottomNav` / `DuiBottomBar`
- `DuiNavigationRail` / `DuiDrawer` / `DuiSideMenu`
- `DuiList` / `DuiSection` / `DuiGrid`
- `DuiCarousel`
- `DuiSettingsList`
- `DuiEmptyState` / `DuiErrorState` / `DuiLoading`
- `DuiThemeCatalog` (bitácora de temas)
- `DuiEnvironmentSelector` / `DuiEnvironmentItem`
- `DuiLogoutDialog`
- `DuiSoundSetting`

### 2) Estados y props por organismo (cerrado)
**DuiAppScaffold**
- Estados: normal.
- Slots obligatorios: top bar, content.
- Slots opcionales: bottom bar, drawer.

**DuiTopBar**
- Variantes: center title / start title.
- Acciones: 0, 1 o 2 iconos.

**DuiBottomNav / DuiBottomBar**
- Estados: 3 ítems con 1 seleccionado.

**DuiNavigationRail / DuiDrawer / DuiSideMenu**
- Estados: collapsed/expanded (solo Drawer).
- 3 ítems + 1 seleccionado.

**DuiList / DuiSection / DuiGrid**
- List: 5 ítems.
- Section: título + 3 ítems.
- Grid: 2 columnas, 4 ítems.

**DuiCarousel**
- Estados: 3 items, 1 seleccionado.
- Uso: carrusel horizontal con snapping por item.

**DuiSettingsList**
- 5 items con toggle/valor.

**DuiEmptyState / DuiErrorState / DuiLoading**
- Empty: icono + título + descripción.
- Error: icono + título + botón retry.
- Loading: spinner + texto.

**DuiThemeCatalog**
- Lista de temas (de TA-DS-F2).

**DuiEnvironmentSelector / DuiEnvironmentItem**
- 3 ambientes: mock/dev/prod, 1 seleccionado.

**DuiLogoutDialog**
- Título + cuerpo + CTA primaria + CTA secundaria.

**DuiSoundSetting**
- Toggle on/off + label.

### 3) Bitácora de Organismos Generales (pantalla)
- **Título:** `DuiText.Title` → “Bitácora de organismos generales”.
- **Sección Navegación:**
  - `DuiTopBar`, `DuiBottomNav`, `DuiNavigationRail`, `DuiDrawer`, `DuiSideMenu`.
- **Sección Layout:**
  - `DuiAppScaffold`, `DuiList`, `DuiSection`, `DuiGrid`, `DuiCarousel`.
- **Sección Estados:**
  - `DuiEmptyState`, `DuiErrorState`, `DuiLoading`.
- **Sección Settings:**
  - `DuiSettingsList`, `DuiEnvironmentSelector`, `DuiSoundSetting`.
- **Sección Diálogos:**
  - `DuiLogoutDialog`.
- **Sección Temas:**
  - `DuiThemeCatalog`.

### 4) Navegación/Make
- Ruta: `app://bitacora/organisms`.
- Comando: `make run-bitacora-organism`.

## Wireframe (servilleta)
```
Bitácora de organismos generales
────────────────────────────────────────
[Sección: Navegación]
TopBar / BottomNav / NavigationRail / Drawer / SideMenu

[Sección: Layout]
AppScaffold
List / Section / Grid

[Sección: Estados]
EmptyState / ErrorState / Loading

[Sección: Settings]
SettingsList / EnvironmentSelector / SoundSetting

[Sección: Diálogos]
LogoutDialog

[Sección: Temas]
ThemeCatalog
```

## Entregables (documento)
- Props/estados por organismo.
- Diseño de pantalla de bitácora general.
- Ruta y comando Make definidos.

## Verificación futura
- Props/estados cubren organismos definidos en TA-DS-F2.
- La pantalla respeta el wireframe definido.

## Validación Dev (rol QA)
- [ ] Ejecutar `make run-bitacora-organism` y verificar que abre la pantalla de bitácora sin clicks manuales.
- [ ] Confirmar que se visualizan todas las secciones definidas.

## No incluido
- Implementación en código.

## Criterios de aceptacion
- La bitácora muestra todas las secciones definidas.
- Se visualizan los estados por organismo según esta historia.
- `run-bitacora-organism` abre la pantalla correcta.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ds-f5a): bitacora organismos generales (#2)`

**Incluye:**
- Actualización de `docs/02-designsystem/ta-f5-a-organisms-general.md`.
- Entrada en `CHANGELOG.md` bajo `[Unreleased]`.

**Checklist:**
- [ ] Solo documentación (sin código).
- [ ] Enlace a la épica `docs/02-designsystem/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.