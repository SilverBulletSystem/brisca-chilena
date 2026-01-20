# TA-DS-F3 — Moléculas Dui y bitácora

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f3-molecules`

## Objetivo
Definir la bitácora de moléculas con estados/variantes, usando el catálogo ya definido en TA-DS-F1. Sin implementación de código.

## Alcance
- Variantes/estados por molécula.
- Diseño de pantalla “Bitácora de Moléculas”.
- Comando Make y ruta de navegación.

## Diseño (doc)
1) **Estados/props**
- enabled/disabled, focus/pressed (touch down), error; hover solo Web/Desktop; tamaños (S/M/L) cuando aplique, íconos opcionales, conteos/badges.
2) **Bitácora de Moléculas (pantalla)**
   - Secciones por tipo (inputs, listas/cards, feedback/dialogs, navegación secundaria, identitarios).
   - Para cada molécula: ejemplos de estados/props relevantes.
3) **Navegación/Make**
   - Depende de F2 (atajos Make). Confirmar uso de la ruta `app://bitacora/molecules` y target `make run-bitacora-molecules` definidos en F2.

## Entregables (documento)
- Estados/variantes por molécula.
- Diseño de la pantalla de bitácora de moléculas.
- Definición de ruta y comando Make.

## Verificación futura
- Estados cubren las moléculas definidas en TA-DS-F1.
- Ruta y comando documentados.

## No incluido
- Implementación en código.