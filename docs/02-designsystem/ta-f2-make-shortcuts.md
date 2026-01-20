# TA-DS-F2A — Bitácoras mínimas + atajos Make

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f2-make-shortcuts`

## Objetivo
Definir y crear bitácoras mínimas (incluida la de temas), además de rutas/deeplinks y comandos Make concretos para abrirlas sin interacción manual.

## Alcance
- Bitácoras mínimas reales (pantallas destino) para temas, átomos, moléculas y organismos.
- Ruta interna por bitácora.
- Comandos Make concretos para abrir cada bitácora.

## Diseño (doc)
### 1) Pantallas mínimas (layout cerrado)
- **ThemeCatalogScreen**:
  - Título: `DuiText.Title` con texto “Bitácora de temas”.
  - Lista vertical de cards (una por tema).
  - Cada card: título, descripción y fila de 6 swatches (definida en TA-DS-F1).
- **AtomsBitacoraScreen**:
  - Título: `DuiText.Title` con texto “Bitácora de átomos”.
  - Lista mínima de átomos ya usados en `ThemeCatalogScreen`:
    - `DuiText` (Title/Body).
    - `DuiColorSwatch`.
    - `DuiSpacer`.
    - `DuiDivider`.
- **MoleculesBitacoraScreen**:
  - Título: `DuiText.Title` con texto “Bitácora de moléculas”.
  - Lista mínima de moléculas ya usadas en `ThemeCatalogScreen`:
    - `DuiCard`.
    - `DuiColorSwatchRow`.
- **OrganismsBitacoraScreen**:
  - Título: `DuiText.Title` con texto “Bitácora de organismos”.
  - Contenedor vacío con texto “Pendiente catálogo de estados”.

### 2) Rutas internas
- Temas: `app://bitacora/theme`
- Átomos: `app://bitacora/atoms`
- Moléculas: `app://bitacora/molecules`
- Organismos: `app://bitacora/organisms`

### 3) Navegación Decompose (obligatoria)
- Definir `sealed class BitacoraRoute` con:
  - `Theme`, `Atoms`, `Molecules`, `Organisms`, `OrganismsGame`, `OrganismsChat`, `OrganismsFlags`.
- Resolver URI → ruta:
  - `app://bitacora/theme` → `BitacoraRoute.Theme`
  - `app://bitacora/atoms` → `BitacoraRoute.Atoms`
  - `app://bitacora/molecules` → `BitacoraRoute.Molecules`
  - `app://bitacora/organisms` → `BitacoraRoute.Organisms`
  - `app://bitacora/organisms/game` → `BitacoraRoute.OrganismsGame`
  - `app://bitacora/organisms/chat` → `BitacoraRoute.OrganismsChat`
  - `app://bitacora/organisms/flags` → `BitacoraRoute.OrganismsFlags`
- Configurar `childStack` con rutas anteriores y mapear a pantallas:
  - `ThemeCatalogScreen`, `AtomsBitacoraScreen`, `MoleculesBitacoraScreen`,
    `OrganismsBitacoraScreen`, `OrganismsGameScreen`, `OrganismsChatScreen`, `OrganismsFlagsScreen`.
- Si URI no coincide, navegar a `ThemeCatalogScreen` por defecto.

### 4) Comandos Make (existentes y obligatorios)
- `make run-bitacora-theme`
- `make run-bitacora-atoms`
- `make run-bitacora-molecule`
- `make run-bitacora-organism`

### 5) Reglas
- Las rutas deben mapear 1:1 a cada comando.
- Tema **no** es placeholder: debe renderizar todos los temas definidos en TA-DS-F1.
- Átomos/moléculas/organismos pueden ser placeholders hasta sus historias.

## Validación Dev (rol QA)
- [ ] Ejecutar cada comando Make y verificar apertura de pantalla correspondiente.
- [ ] `run-bitacora-theme` renderiza lista completa de temas.
- [ ] `run-bitacora-atoms|molecule|organism` renderiza la estructura de servilleta definida.

## Entregables (documento)
- Lista de rutas y targets Make.
- Wireframe textual de cada pantalla mínima.

## Wireframes (servilleta)
```
Bitácora de átomos
────────────────────────────────────────
[Sección: Texto]
- DuiText.Title
- DuiText.Body

[Sección: Colores]
- DuiColorSwatch (primary)
- DuiColorSwatch (secondary)

[Sección: Layout]
- DuiSpacer
- DuiDivider
```

```
Bitácora de moléculas
────────────────────────────────────────
[Card] DuiCard
  Title: "Tema ejemplo"
  Body:  "Descripción de ejemplo"
  [●][●][●][●][●][●]  (DuiColorSwatchRow)

[Row] DuiColorSwatchRow (solo colores)
[●][●][●][●][●][●]
```

```
Bitácora de organismos
────────────────────────────────────────
[Sección: Placeholder]
- Texto: "Pendiente catálogo de organismos"
```

## Verificación futura
- Comandos y rutas claros para uso por dev/QA al implementar.

## No incluido
- Catálogos completos de componentes (van en TA-DS-F2B/F3/F5).

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ds-f2a): bitacoras minimas y make (#2)`

**Incluye:**
- Actualización de `docs/02-designsystem/ta-f2-make-shortcuts.md`.
- Entrada en `CHANGELOG.md` bajo `[Unreleased]`.

**Checklist:**
- [ ] Solo documentación (sin código).
- [ ] Enlace a la épica `docs/02-designsystem/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.