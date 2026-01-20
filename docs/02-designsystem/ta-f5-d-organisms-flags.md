# TA-DS-F5-D — Organismos de flags/monitores y bitácora

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f5-d-organisms-flags`

## Objetivo
Definir el diseño de bitácora para organismos de flags/monitores (layout, props, estados y navegación), usando el catálogo ya definido en TA-DS-F2. Sin implementación de código.

## Alcance
- Catálogo completo de organismos flags/monitores del DS.
- Props/estados a mostrar por organismo.
- Diseño de pantalla “Bitácora Organismos Flags/Monitores”.
- Ruta y comando Make para abrir la bitácora.

## Dependencias
- TA-DS-F2 (tokens y theming, catálogo de organismos).
- TA-DS-F1 (rutas y comandos Make).
- TA-DS-F4 (bitácora de moléculas).

## Modelo de dominio
- **OrganismSpec**: definición de props/estados de un organismo Dui.
- **FlagsBitacoraSection**: agrupación por secciones (Flags, Logs, Timeline, Filtros).

## Contratos y datos
- Los organismos se definen como composables con parámetros claros.
- Todos los tamaños usan `ThemeDimens` (sin `.dp` hardcodeado).

## Diseño (doc)
### 1) Catálogo completo de organismos flags/monitores (Dui)
- `DuiFlagList` (usa `DuiToggle` por ítem)
- `DuiTable` (tabla simple 2 columnas)
- `DuiLogList`
- `DuiTimeline`
- Filtros: `DuiFilterChips` / `DuiSelect`

### 2) Props y estados por organismo (cerrado)
**DuiFlagList**
- Props: `flags: List<FlagItem>`, `onToggle: (FlagItem) -> Unit`.
- Estados: 3 flags (2 on / 1 off), lista vacía.

**DuiTable**
- Props: `rows: List<Pair<String, String>>`, `header: String?`.
- Estados: con header y 4 filas; sin header.

**DuiLogList**
- Props: `logs: List<LogItem>`.
- Estados: 5 logs con niveles `INFO/WARN/ERROR`, estado vacío.

**DuiTimeline**
- Props: `events: List<TimelineEvent>`.
- Estados: 4 eventos correlacionados (State/External Record), estado vacío.

**DuiFilterChips**
- Props: `items: List<String>`, `selectedIndex: Int`.
- Estados: 3 chips, 1 seleccionado.

**DuiSelect**
- Props: `label: String`, `items: List<String>`, `selectedItem: String?`.
- Estados: selección “All”, selección “Errors”.

### 3) Bitácora de Flags/Monitores (pantalla)
- **Título:** `DuiText.Title` → “Bitácora de flags/monitores”.
- **Sección Filtros:**
  - `DuiFilterChips` (All / Active / Disabled).
  - `DuiSelect` (Level: All / Warn / Error).
- **Sección Flags:**
  - `DuiFlagList` con 3 flags (2 on, 1 off).
- **Sección Logs:**
  - `DuiLogList` con niveles mixtos (INFO/WARN/ERROR).
- **Sección Timeline:**
  - `DuiTimeline` con eventos State/External Record.
- **Sección Tabla:**
  - `DuiTable` con 4 filas (key/value).

### 4) Navegación/Make
- Ruta: `app://bitacora/organisms/flags`.
- Comando: `make run-bitacora-organism-flags`.

## Wireframe (servilleta)
```
Bitácora de flags/monitores
────────────────────────────────────────
[Sección: Filtros]
FilterChips (All / Active / Disabled)
Select (Level: All / Warn / Error)

[Sección: Flags]
FlagList (3 items, 2 on / 1 off)

[Sección: Logs]
LogList (INFO / WARN / ERROR)

[Sección: Timeline]
Timeline (State / External Record)

[Sección: Tabla]
Table (key/value x4)
```

## UI y UX
- Listado vertical por secciones, con `LazyColumn`.
- Cada sección debe tener título con `DuiText.Subtitle`.

## Flujos y secuencias
1) Ejecutar `make run-bitacora-organism-flags`.
2) La app navega a `BitacoraFlagsScreen`.
3) Se visualizan filtros, flags, logs, timeline y tabla.

## Seguridad y privacidad
- No aplica.

## Performance
- `LazyColumn` para listas y secciones.

## Observabilidad
- Log de navegación al abrir la ruta.

## Testing
- Verificar visualmente estados vacíos y no vacíos.
- Confirmar que los filtros se renderizan en estado seleccionado.

## Criterios de aceptacion
- Catálogo de organismos flags/monitores documentado.
- Bitácora muestra todas las secciones y estados definidos.
- `make run-bitacora-organism-flags` abre la pantalla correcta.
- La pantalla respeta el wireframe.

## Entregables (documento)
- Props/estados por organismo.
- Diseño de la pantalla de bitácora.
- Ruta y comando Make definidos.

## Verificación futura
- Props/estados cubren organismos definidos en TA-DS-F2.
- Ruta y comando documentados para QA/dev.

## No incluido
- Implementación en código.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ds-f5d): bitacora flags/monitores (#2)`

**Incluye:**
- Actualización de `docs/02-designsystem/ta-f5-d-organisms-flags.md`.
- Entrada en `CHANGELOG.md` bajo `[Unreleased]`.

**Checklist:**
- [ ] Solo documentación (sin código).
- [ ] Enlace a la épica `docs/02-designsystem/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.