# TA-INK-F3 — Writer pipeline (buffer + persistencia)

## Navegacion
- [Volver a Epica](04-inkribbon/epica.md)
- [Volver a Backlog](04-inkribbon/backlog.md)

## Rama
- `feat/ht-ink-f3-writer`

## Objetivo
Diseñar el pipeline de escritura: buffer en memoria, sanitización/redacción, aplicación de límites y persistencia batch a SQDelight. Debe tolerar fallos de DB con descarte controlado y logging.

## Alcance
- Buffer: cola in-memory con tope de eventos; flush por tamaño o intervalo.
- Sanitización: usar `EventSanitizer` (remover PII/tokens, truncar y hash); `PropsSummary` limitado a 2 KB.
- Límites: `EventLimiter` aplica eventos por sesión, tamaño total, TTL; si excede → drop con warning.
- Persistencia: writer usa `StateRecordWriter` (interfaz) para insertar en lote; confirma éxito o error.
- Falla de DB: cola de fallback en memoria con límite; si se llena, descarta eventos menos recientes del batch.
- Logging: a Toad/State Record con severidad `warning|error`, incluyendo conteo de descartes.

## Diseño (doc)
1) Flujo
   - Recibir evento → sanitizar → validar límites → encolar → flush (intervalo o tamaño) → persistir batch.
2) Estrategia de flush
   - Parámetros sugeridos: flush cada 50 eventos o 5 segundos (lo que ocurra primero); documentar sin implementar.
3) Errores
   - DB down: reintentos limitados; luego descartar batch con warning.
   - Evento inválido tras sanitización: descartar y loggear motivo.

## Entregables (documento)
- Flujo detallado del writer, parámetros sugeridos y políticas de error/descartes.

## Verificación futura
- Writer puede operar con límites definidos en F1 y servicios de dominio de F0.

## No incluido
- Implementación real del buffer ni acceso a DB. 