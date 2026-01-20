# TA-DS-F5-D — Organismos de flags/monitores y bitácora

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f5-d-organisms-flags`

## Objetivo
Definir bitácora para organismos de flags/monitores usando el catálogo ya definido en TA-DS-F1. Sin implementación.

## Alcance
- Bitácora: escenarios de filtrado, listas largas, timeline con eventos de State/External Record.
- Navegación/Make: comando para abrir la bitácora de flags/monitores sin interacción manual.

## Diseño (doc)
1) Props/estados: toggles on/off, filtros aplicados, niveles de log, timeline con eventos correlacionados.
2) Pantalla “Bitácora Organismos Flags/Monitores”: secciones por lista de flags, logs, timeline; controles de filtro.
3) Ruta interna propuesta: `app://bitacora/organisms/flags`.
4) Target Make propuesto: `make run-bitacora-organisms-flags`.

## Entregables (documento)
- Props/estados de organismos de flags/monitores.
- Diseño de pantalla de bitácora de flags/monitores.
- Ruta y comando Make definidos.

## Verificación futura
- Props/estados cubren organismos definidos en TA-DS-F1.
- Comando/ruta documentados para QA/dev.

## No incluido
- Implementación en código.

## Validación Dev (rol QA)
- [ ] Ejecutar `make run-bitacora-organisms-flags` y verificar apertura sin clicks manuales.
- [ ] Validar escenarios: toggles on/off, filtros aplicados, lista de logs, timeline con eventos, visibles aunque sean placeholders.