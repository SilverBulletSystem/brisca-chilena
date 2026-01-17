# TA-TOAD-F3 — Writer pipeline (buffer + persistencia)

## Rama
- `feat/ht-toad-f3-writer`

## Objetivo
Diseñar el pipeline de escritura para External Record: buffer en memoria, sanitización/limitado de payloads, aplicación de límites y persistencia batch a SQDelight, con tolerancia a fallos.

## Alcance
- Buffer: cola in-memory con tope de eventos; flush por tamaño o intervalo.
- Sanitización: usar `PayloadSanitizer` (headers sensibles, truncado 8 KB, hash opcional); `PayloadLimiter` aplica tamaños por cuerpo/traza.
- Límites: `maxTraces`=500, `maxStorageMb`=10, TTL=14 días (coherente con F1); drop con warning si se excede.
- Persistencia: writer usa `ExternalRecordWriter` (interfaz) para batch insert; confirmar éxito o error.
- Falla de DB: reintentos limitados; luego descartar batch con warning.
- Logging: a Toad/State Record con conteo de descartes y causa.

## Diseño (doc)
1) Flujo
   - Recibir evento → sanitizar/limitar → encolar → flush (50 eventos o 5 s) → persistir.
2) Errores
   - DB down: reintentos N veces; luego drop y warning.
   - Evento sin requestId/correlation: descartar con warning.

## Entregables (documento)
- Flujo del writer, parámetros sugeridos y políticas de error/descartes.

## Verificación futura
- Pipeline alineado a límites de F1 y dominio de F0.

## No incluido
- Implementación real ni acceso a DB. 
