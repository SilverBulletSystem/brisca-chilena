# TA-TOAD-F0 — Dominio/contratos External Record (DDD)

## Rama
- `feat/ht-toad-f0-domain`

## Objetivo
Definir modelo de dominio, servicios, repositorios y casos de uso para External Record (Toad), desacoplado de transporte. En MVP se usa HTTP, pero el contrato debe permitir otros canales (BD/Bluetooth) a futuro.

## Alcance
- Entidades/VO: `RequestId`, `ResponseId`, `SessionId?`, `CorrelationId?`, `Timestamp`, `Channel` (http|bluetooth|other_future), `Method/Operation`, `Endpoint` (url/path/topic/device), `StatusCode/Result`, `DurationMs`, `PayloadHash`, `HeadersHash`, `ErrorType`.
- Servicios de dominio:
  - `PayloadSanitizer`: redacción PII/tokens por canal; remueve headers sensibles (Authorization/Cookie); truncado y hash.
  - `PayloadLimiter`: aplica tamaños máximos por cuerpo (request/response) y por trace según canal.
  - `CorrelationService`: une con Inkribbon via `CorrelationId`.
  - `ClockService`: tiempo consistente.
- Repositorios (interfaces):
  - `ExternalRecordWriter`: escribe request/response/error sanitizados.
  - `ExternalRecordReader`: consulta trazas y detalles.
- Casos de uso:
  - `RecordRequest(sessionId?, correlationId?, channel, method/operation, endpoint, headers, body?)`
  - `RecordResponse(requestId, status/result, durationMs, headers, body?, errorType?)`
  - `ListTraces(filter)` -> resumen
  - `GetTraceDetail(traceId)` -> detalle
  - `PurgeOldTraces()`
- Reglas
  - Prohibido persistir payload crudo completo: truncar a límite y almacenar hash opcional.
  - URL normalizada (sin query sensible en claro; hash/mascar).
  - Status/errores categorizados para filtros (2xx/4xx/5xx, timeout, network).

## Diseño (doc)
1) Diagrama lógico (texto)
  - `RequestTrace` { id, sessionId?, correlationId?, channel, method/operation, endpoint, headersHash, payloadHash?, startedAt }
  - `ResponseTrace` { id, requestId, status/result, durationMs, payloadHash?, endedAt, errorType? }
2) Interacción
   - Interceptor → sanitización/limit → caso de uso `RecordRequest`/`RecordResponse` → writer/pipeline.
3) Manejo de errores
   - Si falta requestId/correlation crítico → descartar con warning.
   - Si excede tamaño → truncar y warning, preservar hash.

## Entregables (documento)
- Definición de entidades/VO/servicios/repositorios/casos de uso.
- Reglas de sanitización, límites y categorización de errores.
- Manejo de errores y logging.

## Verificación futura
- Dominio listo para que infra implemente interceptor/persistencia sin ambigüedad.

## No incluido
- Implementación en código ni wiring. 
