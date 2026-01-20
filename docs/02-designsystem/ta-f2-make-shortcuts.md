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

### 3) Comandos Make (existentes y obligatorios)
- `make run-bitacora-theme`
- `make run-bitacora-atoms`
- `make run-bitacora-molecule`
- `make run-bitacora-organism`

### 4) Reglas
- Las rutas deben mapear 1:1 a cada comando.
- Tema **no** es placeholder: debe renderizar todos los temas definidos en TA-DS-F1.
- Átomos/moléculas/organismos pueden ser placeholders hasta sus historias.

## Validación Dev (rol QA)
- [ ] Ejecutar cada comando Make y verificar apertura de pantalla correspondiente.
- [ ] `run-bitacora-theme` renderiza lista completa de temas.
- [ ] `run-bitacora-atoms|molecule|organism` renderiza título y placeholder.

## Entregables (documento)
- Lista de rutas y targets Make.
- Wireframe textual de cada pantalla mínima.

## Verificación futura
- Comandos y rutas claros para uso por dev/QA al implementar.

## No incluido
- Catálogos completos de componentes (van en TA-DS-F2B/F3/F5).