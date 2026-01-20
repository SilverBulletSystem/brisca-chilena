# TA-TOAD-F1 — Schema SQDelight + retención

## Navegacion
- [Volver a Epica](05-toad/epica.md)
- [Volver a Backlog](05-toad/backlog.md)

## Rama
- `feat/ht-toad-f1-schema`

## Objetivo
Diseñar el esquema SQDelight para trazas externas (HTTP en MVP) con índices y políticas de retención (TTL/tamaño/cantidad).

## Alcance
- Tablas:
  - `requests(request_id PK, session_id?, correlation_id?, channel, method_operation, endpoint, headers_hash, payload_hash?, started_at)`
  - `responses(response_id PK, request_id FK, status_result, duration_ms, payload_hash?, ended_at, error_type?)`
- Índices: `requests(started_at)`, `responses(request_id)`, `requests(correlation_id)`.
- Límites sugeridos: TTL 14 días, máx 500 trazas, 10 MB totales, payload truncado a 8 KB por cuerpo (request/response) con hash del completo opcional.
- Retención: purge TTL → LRU por traza → tamaño; log de purga.

## Diseño (doc)
1) DDL propuesta (texto)
   - Tipos multiplataforma (STRING/LONG/INT).
   - `channel` enum: `http|bluetooth|other_future`.
   - `error_type` enum: `none|timeout|network|serialization|unknown`.
2) Políticas
   - `maxTraces`=500, `maxStorageMb`=10, `ttlDays`=14.
   - Purga en este orden: TTL, luego LRU, luego por tamaño; registrar conteos purgados.
3) Migraciones
   - Versión inicial 1; cambios futuros via ALTER + migraciones documentadas.

## Entregables (documento)
- DDL textual, índices y políticas de retención.

## Verificación futura
- Esquema soporta las trazas y políticas de límite; listo para pipeline (F3).

## No incluido
- Implementación SQDelight ni migraciones reales. 