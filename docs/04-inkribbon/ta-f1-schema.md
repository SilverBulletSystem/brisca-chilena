# TA-INK-F1 — Schema SQDelight + retención

## Navegacion
- [Volver a Epica](04-inkribbon/epica.md)
- [Volver a Backlog](04-inkribbon/backlog.md)

## Rama
- `feat/ht-ink-f1-schema`

## Objetivo
Diseñar el esquema SQDelight para sesiones y eventos de State Record, con índices y política de retención (TTL/tamaño/límite de eventos).

## Alcance
- Tablas:
  - `sessions(session_id PK, started_at, device_info?, app_version, locale, env, flags_snapshot?)`
  - `events(event_id PK, session_id FK, type, screen_id?, schema_id?, action_id?, timestamp, props_summary, payload_hash?, correlation_id?, severity?, extra?)`
- Índices: `events(session_id, timestamp)`, `sessions(started_at)`.
- Límites sugeridos: máx `2000` eventos por sesión, máx `200` sesiones, máx `20 MB` totales, TTL `14 días`.
- Retención: purge LRU por sesión y TTL; drop eventos cuando se exceda el tope con logging.
- Persistencia: UTF-8, sin blobs grandes; props_summary/hash como texto reducido.

## Diseño (doc)
1) DDL propuesta (texto)
   - Tipos compatibles multiplataforma (STRING/INTEGER/LONG).
   - `severity` enum (`info|warning|error`).
2) Políticas
   - `maxEventsPerSession`=2000, `maxSessions`=200, `maxStorageMb`=20, `ttlDays`=14.
   - Purge: primero TTL, luego LRU por sesiones más antiguas, luego drop por tamaño; loggear conteos purgados.
3) Migraciones
   - Estrategia de versión inicial 1; cambios futuros vía ALTER y migraciones documentadas.

## Entregables (documento)
- DDL textual, índices, políticas de retención y orden de purga.

## Verificación futura
- Esquema soporta los eventos definidos en dominio.
- Retención y límites documentados para implementar en F3.

## No incluido
- Implementación SQDelight ni migraciones reales. 