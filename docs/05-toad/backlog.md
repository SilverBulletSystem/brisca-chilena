# Backlog técnico — Épica 5 Toad (External Record)

Referencia: `05-toad/epica.md`. Solo documentación, sin implementación.

## F0 – Dominio/contratos
- Entidades/VO: `RequestId`, `ResponseId`, `SessionId`, `CorrelationId`, `Timestamp`, `Channel` (http|bluetooth|other_future), `Method/Operation`, `Endpoint` (url/path/topic/device), `StatusCode/Result`, `PayloadHash`, `HeadersHash`, `DurationMs`, `ErrorType`.
- Servicios de dominio: `PayloadSanitizer` (redacción PII/tokens por canal), `PayloadLimiter` (tamaño), `CorrelationService` (con Inkribbon), `ClockService`.
- Repositorios (interfaces): `ExternalRecordWriter/Reader`.
- Casos de uso: `RecordRequest`, `RecordResponse`, `ListTraces`, `GetTraceDetail`, `PurgeOldTraces`.
- Rama: `feat/ht-toad-f0-domain`.

## F1 – Schema DB + retención
- Tablas SQDelight: `requests` (id, sessionId?, correlationId?, channel, method/operation, endpoint, headersHash, payloadHash?, startedAt), `responses` (id, requestId FK, status/result, durationMs, payloadHash?, endedAt, errorType?).
- Índices: `requests(startedAt)`, `responses(requestId)`, `requests(correlationId)`.
- Límites sugeridos: TTL 14 días, máx 500 trazas, máx 10 MB totales, payload truncado a 8 KB por cuerpo (request/response) con hash del completo.
- Purge: TTL → LRU por traza → tamaño; loggear purgas.
- Rama: `feat/ht-toad-f1-schema`.

## F2 – Interceptor HTTP (Ktor)
- Interceptor multiplataforma (Ktor client) para capturar request/response/error.
- Sanitizar headers sensibles (Authorization, Cookie) y truncar cuerpos a 8 KB; almacenar hash del cuerpo completo opcionalmente.
- Adjuntar `SessionId/CorrelationId` si está disponible desde Inkribbon; si no, generar uno efímero.
- Rama: `feat/ht-toad-f2-interceptor`.

## F2b – Adaptador Bluetooth/otros (contrato futuro)
- Campos mínimos: channel=bluetooth, operation, peer/deviceId, payload hash/truncado; status/result si aplica.
- Rama: `feat/ht-toad-f2b-bt`.

## F3 – Writer pipeline
- Buffer + batch persistente (similar a Inkribbon): sanitización → límites → cola → flush por tamaño/tiempo.
- Política de flush sugerida: cada 50 eventos o 5 s.
- Manejo de fallos de DB: reintentos limitados, luego drop con warning.
- Rama: `feat/ht-toad-f3-writer`.

## F4 – Correlación con Inkribbon
- Caso de uso para unir trazas externas con sesiones/eventos de State Record via `CorrelationId`.
- Estrategia: crear/propagar `CorrelationId` al iniciar request; enlazar con sessionId activo.
- Rama: `feat/ht-toad-f4-correlation`.

## F5 – Visor/monitor in-app
- UI Dui para listar trazas: método, url (host + path), estado/duración, severidad.
- Filtros: status class (2xx/4xx/5xx), método, rango de tiempo, ambiente.
- Detalle: headers sanitizados, payload truncado (8 KB), hash para referencia; mostrar `CorrelationId` y link a sesión en Inkribbon.
- Rutas/deeplinks: `app://toad/monitor`, `app://toad/monitor/{traceId}`.
- Rama: `feat/ht-toad-f5-viewer`.

## F6 – Make/atajos
- Comandos Make para abrir monitor y traza específica: `make run-toad-monitor FLAVOR=...`, `make run-toad-trace TRACE_ID=... FLAVOR=...`.
- Compatible con rutas anteriores; sin pasos manuales.
- Rama: `feat/ht-toad-f6-make`.

## F7 – Testing/observabilidad
- Checklist QA/Dev: captura request/response/error; truncado y hash; límites/TTL; purga; correlación con sesión; monitor UI; falla de DB.
- Logs estructurados a Toad/State Record.
- Rama: `feat/ht-toad-f7-testing`.
