# TA-DS-F2 — Átomos Dui y bitácora

## Rama
- `feat/ht-ds-f2-atoms`

## Objetivo
Definir el catálogo de átomos `Dui*` (Atomic) y el diseño de la bitácora de átomos con todos sus estados, incluyendo navegación directa vía comando Make (similar a `make run-bitacora` del proyecto base). Sin implementación de código.

## Alcance
- Lista completa de átomos y estados (enabled/disabled/hover/focus/pressed/error).
- Diseño de pantalla “Bitácora de Átomos”: layout, agrupación, props a mostrar.
- Definir comando Make para abrir esta pantalla.

## Diseño (doc)
1) **Catálogo de átomos `Dui*`**
   - `DuiText` (Title/Subtitle/Body/Caption), `DuiIcon`
   - `DuiButton` (filled/tonal/outlined/text + icon), `DuiFab`
   - `DuiDivider`, `DuiSpacer`
   - `DuiChip`/`DuiTag`
   - `DuiBadge` (incl. numérica)
   - `DuiProgress` (linear/circular)
   - `DuiRadio`, `DuiSwitch` simple
2) **Estados/props a documentar**
- enabled/disabled, focus/pressed (touch down), error; hover solo aplica a Web/Desktop (no mobile); loading donde aplique; tamaños (S/M/L), ícono opcional.
3) **Bitácora de Átomos (pantalla)**
   - Secciones por categoría (tipografía, acciones, indicadores, inputs básicos).
   - Para cada átomo: muestra de estados y props relevantes.
   - Links de navegación de retorno a menú de bitácoras.
4) **Navegación/Make**
   - Depende de F2 (atajos Make). Confirmar uso de la ruta `app://bitacora/atoms` y target `make run-bitacora-atoms` ya definido en F2.

## Entregables (documento)
- Catálogo de átomos y estados.
- Diseño de la pantalla de bitácora de átomos.
- Definición de ruta y comando Make esperado.

## Verificación futura
- Catálogo cubre todos los átomos usados por moléculas/organismos.
- Ruta y comando documentados para navegación directa.

## No incluido
- Implementación en código ni creación real de la pantalla.
