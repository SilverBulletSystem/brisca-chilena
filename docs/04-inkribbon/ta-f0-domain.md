# TA-INK-F0 — Dominio/contratos Inkribbon (DDD)

## Navegacion
- [Volver a Epica](04-inkribbon/epica.md)
- [Volver a Backlog](04-inkribbon/backlog.md)

## Rama
- `feat/ht-ink-f0-domain`

## Objetivo
Definir modelo de dominio, servicios, repositorios e interfaces para State Record (Inkribbon), desacoplado de transporte/infra. Sirve como base para hooks, persistencia, visor y replay.

## Alcance
- Entidades/VO: `SessionId`, `EventId`, `ScreenId`, `SchemaId`, `Timestamp`, `PayloadHash`, `PropsSummary`, `EventType` (navigation/render/action/error), `CorrelationId` (para External Record), `UserId?` (anon opcional).
- Servicios de dominio:
  - `EventSanitizer`: redacción PII/tokens; límites de longitud; hashing de props grandes.
  - `EventLimiter`: aplica límites (eventos por sesión, tamaño por payload, TTL).
  - `CorrelationService`: genera/une `CorrelationId` con External Record.
  - `ClockService`: fuente de tiempo para ordenar eventos.
- Repositorios (interfaces):
  - `StateRecordWriter`: persiste sesiones/eventos (sin conocer DB).
  - `StateRecordReader`: lee sesiones/eventos, soporta filtros por rango/ids.
- Casos de uso:
  - `StartSession(appInfo, deviceInfo)` -> `SessionId`
  - `RecordEvent(sessionId, event)` -> resultado (success/warning/error)
  - `ListSessions(filter)` -> lista resumida
  - `GetSessionDetail(sessionId)` -> timeline
  - `PurgeOldSessions()` -> resultado de limpieza
  - `BuildReplay(sessionId)` -> secuencia reproducible (ver TA-F5)
- Reglas
  - Dominio no conoce SQDelight ni SDUI JSON; recibe DTOs de eventos ya sanitizados o usa `EventSanitizer`.
  - Sin PII: prohibido persistir tokens, passwords, raw payloads; usar resumen/hashes.

## Diseño (doc)
1) Diagrama lógico (texto)
   - `Session` { id, startedAt, device/app info (reducido), tags }
   - `Event` { id, sessionId, type, screenId?, schemaId?, actionId?, timestamp, propsSummary, payloadHash?, correlationId?, severity? }
2) Interacción
   - Hooks llaman a casos de uso → servicios de dominio → repositorio.
   - Writer pipeline validará límites (EventLimiter) antes de persistir.
3) Manejo de errores
   - Críticos: sin sessionId o clock → rechazar evento.
   - Recuperables: props grandes → truncar/hash y warning; exceso de eventos → drop con warning.
   - Logging estructurado (apunta a Toad/State Record): id/tipo/motivo/severidad.

## Entregables (documento)
- Definición de entidades/VO/servicios/repositorios/casos de uso.
- Reglas de sanitización y límites.
- Manejo de errores y logging.

## Verificación futura
- Dominio listo para que infra implemente repositorios y casos de uso sin ambigüedad.

## No incluido
- Implementación en código ni wiring de DI.