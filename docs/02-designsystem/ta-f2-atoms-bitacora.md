# TA-DS-F2 — Átomos Dui y bitácora

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f2-atoms`

## Objetivo
Definir el diseño de la bitácora de átomos y sus estados, usando el catálogo ya definido en TA-DS-F1. Sin implementación de código.

## Alcance
- Estados a mostrar por átomo (enabled/disabled/hover/focus/pressed/error).
- Diseño de pantalla “Bitácora de Átomos”: layout, agrupación, props a mostrar.
- Definir comando Make para abrir esta pantalla.

## Diseño (doc)
1) **Estados/props a documentar**
- enabled/disabled, focus/pressed (touch down), error; hover solo aplica a Web/Desktop (no mobile); loading donde aplique; tamaños (S/M/L), ícono opcional.
2) **Bitácora de Átomos (pantalla)**
   - Secciones por categoría (tipografía, acciones, indicadores, inputs básicos).
   - Para cada átomo: muestra de estados y props relevantes.
   - Links de navegación de retorno a menú de bitácoras.
3) **Navegación/Make**
   - Depende de F2 (atajos Make). Confirmar uso de la ruta `app://bitacora/atoms` y target `make run-bitacora-atoms` ya definido en F2.

## Entregables (documento)
- Estados/props por átomo.
- Diseño de la pantalla de bitácora de átomos.
- Definición de ruta y comando Make esperado.

## Verificación futura
- Estados cubren los átomos definidos en TA-DS-F1.
- Ruta y comando documentados para navegación directa.

## No incluido
- Implementación en código ni creación real de la pantalla.