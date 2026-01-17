# TA-DS-F5-D — Organismos de flags/monitores y bitácora

## Rama
- `feat/ht-ds-f5-d-organisms-flags`

## Objetivo
Definir catálogo y bitácora para organismos de flags/monitores con navegación Make. Sin implementación.

## Alcance
- Organismos flags/monitores: `DuiFlagList` (con `DuiToggle`), `DuiTable`/two-column item, `DuiLogList`, `DuiTimeline`, filtros (`DuiFilterChips`/`DuiSelect`).
- Bitácora: escenarios de filtrado, listas largas, timeline con eventos de State/External Record.
- Navegación/Make: comando para abrir la bitácora de flags/monitores sin interacción manual.

## Diseño (doc)
1) Catálogo con props/estados: toggles on/off, filtros aplicados, niveles de log, timeline con eventos correlacionados.
2) Pantalla “Bitácora Organismos Flags/Monitores”: secciones por lista de flags, logs, timeline; controles de filtro.
3) Ruta interna propuesta: `app://bitacora/organisms/flags`.
4) Target Make propuesto: `make run-bitacora-organisms-flags`.

## Entregables (documento)
- Catálogo y props de organismos de flags/monitores.
- Diseño de pantalla de bitácora de flags/monitores.
- Ruta y comando Make definidos.

## Verificación futura
- Cobertura de organismos de flags/monitores completa.
- Comando/ruta documentados para QA/dev.

## No incluido
- Implementación en código.

## Validación Dev (rol QA)
- [ ] Ejecutar `make run-bitacora-organisms-flags` y verificar apertura sin clicks manuales.
- [ ] Validar escenarios: toggles on/off, filtros aplicados, lista de logs, timeline con eventos, visibles aunque sean placeholders.
