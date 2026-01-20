# TA-DS-F4-A — Organismos generales y bitácora

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f5-a-organisms-general`

## Objetivo
Definir bitácora para organismos generales (layout, navegación, estados, configuración) usando el catálogo ya definido en TA-DS-F1. Sin implementación.

## Alcance
- Bitácora de organismos generales: secciones y props a mostrar.
- Navegación/Make: comando para abrir la bitácora sin interacción manual.

## Diseño (doc)
1) Props y estados relevantes por organismo (títulos, acciones, estados).
2) Pantalla “Bitácora Organismos Generales”: agrupación por categoría (nav, settings, estados).
3) Ruta interna propuesta: `app://bitacora/organisms/general`.
4) Target Make propuesto: `make run-bitacora-organisms-general`.

## Entregables (documento)
- Props/estados por organismo.
- Diseño de pantalla de bitácora general.
- Ruta y comando Make definidos.

## Verificación futura
- Props/estados cubren organismos definidos en TA-DS-F1.
- Comando/ruta documentados para QA/dev.

## Validación Dev (rol QA)
- [ ] Ejecutar `make run-bitacora-organisms-general` y verificar que abre la pantalla de bitácora sin clicks manuales.
- [ ] Confirmar que se visualizan las secciones definidas (nav, settings, estados) aunque sean placeholders.

## No incluido
- Implementación en código.