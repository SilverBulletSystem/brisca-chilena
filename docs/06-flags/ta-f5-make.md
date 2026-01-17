# TA-FLAGS-F5 — Make/atajos de Flags

## Rama
- `feat/ht-flags-f5-make`

## Objetivo
Definir comandos Make/deeplinks para abrir el monitor de flags y forzar refresh sin pasos manuales.

## Alcance
- Comandos Make sugeridos:
  - `make run-flags-monitor FLAVOR=mock|dev|prod` → abre monitor.
  - `make refresh-flags FLAVOR=mock|dev|prod` → fuerza fetch/merge y muestra resultado en logs.
- Deeplinks/rutas:
  - `app://flags/monitor`
- Variables: `FLAVOR` reutiliza navegación rápida del proyecto base; validar que la app esté instalada en ese flavor.
- Comportamiento sin datos: monitor debe mostrar empty state con mensaje i18n.

## Entregables (documento)
- Lista de comandos Make/deeplinks y parámetros requeridos.

## Verificación futura
- Atajos reproducibles sin interacción manual. 
