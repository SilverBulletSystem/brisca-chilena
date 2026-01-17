# TA-TOAD-F6 — Make/atajos External Record

## Rama
- `feat/ht-toad-f6-make`

## Objetivo
Definir atajos Make/deeplinks para abrir el monitor de Toad y trazas específicas sin interacción manual.

## Alcance
- Comandos Make sugeridos:
  - `make run-toad-monitor FLAVOR=mock|dev|prod` → abre monitor.
  - `make run-toad-trace TRACE_ID=<id> FLAVOR=...` → abre detalle de traza.
- Deeplinks/rutas:
  - `app://toad/monitor`
  - `app://toad/monitor/{traceId}`
- Variables: `FLAVOR` reutiliza las de navegación rápida; validar presencia de app y datos.
- Comportamiento sin datos: si no hay trazas, mostrar empty state.

## Entregables (documento)
- Lista de comandos Make/deeplinks y parámetros.

## Verificación futura
- Atajos reproducibles sin pasos manuales. 
