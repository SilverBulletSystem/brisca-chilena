# TA-INK-F5 — Replay básico

## Navegacion
- [Volver a Epica](04-inkribbon/epica.md)
- [Volver a Backlog](04-inkribbon/backlog.md)

## Rama
- `feat/ht-ink-f5-replay`

## Objetivo
Diseñar el flujo de replay básico de sesiones grabadas: reconstruir secuencia de pantallas/eventos para QA/dev usando datos locales (sin backend).

## Alcance
- Caso de uso `BuildReplay(sessionId)`: toma timeline y produce una lista reproducible de pasos.
- Datos de entrada: eventos navigation/render/action + propsSummary (ya sanitizada).
- SDUI: si existe `schemaId`, re-render usando schema local/cache; si no, usar placeholder/mock de datos.
- Controles: play/pausa/next/prev; límite de pasos para evitar loops (ej. máx N eventos).
- Seguridad: no cargar payload crudo; solo propsSummary y schemas locales.
- Timebase: reproducir con intervalos configurables o manual (step-by-step).
- Límite sugerido: reproducir hasta 300 eventos por sesión; si se excede, truncar y advertir.

## Diseño (doc)
1) Estado del replay
   - `ReplayState`: { currentIndex, total, status (playing/paused/stopped), errors? }
   - `ReplayStep`: { type, screenId, schemaId?, actionId?, propsSummary, timestamp }
2) Políticas
   - Máx eventos reproducibles por sesión; descartar tipos no soportados.
   - Si falta schema para un render, mostrar placeholder y warning.
3) UI mínima (referencia con DS)
   - Controles (play/pause/next/prev) como moléculas/organismos existentes (buttons + progress).
   - Indicador de paso actual/total.

## Entregables (documento)
- Flujo del caso de uso, estructura de `ReplayStep/State`, políticas de límites y manejo de faltantes.

## Verificación futura
- Replay definido sin acoplar a backend; compatible con SDUI cache/local.

## No incluido
- Implementación de reproducción real ni fetching de schemas remotos. 