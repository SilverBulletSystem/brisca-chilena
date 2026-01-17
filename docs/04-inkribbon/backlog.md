# Backlog técnico — Épica 4 Inkribbon (State Record)

Referencia: `04-inkribbon/epica.md`. Solo documentación, sin implementación.

## F0 – Dominio/contratos
- Entidades/VO: `SessionId`, `EventId`, `UserId?` (anon opcional), `ScreenId`, `SchemaId`, `Timestamp`, `PayloadHash`, `PropsSummary`, `NavigationEvent`, `ActionEvent`, `ErrorEvent`, `ReplaySnapshot`.
- Servicios de dominio: EventSanitizer (redacción PII/tokens), EventLimiter (límites de tamaño/TTL), CorrelationService (State ↔ External Record), ClockService.
- Repositorios (interfaces): `StateRecordRepository` (persistencia), `StateRecordReader`, `StateRecordWriter`.
- Casos de uso: `StartSession`, `RecordEvent`, `ListSessions`, `GetSessionDetail`, `PurgeOldSessions`, `BuildReplay`.
- Rama: `feat/ht-ink-f0-domain`.

## F1 – Schema DB + retención
- Definir tablas SQDelight: `sessions` (sessionId, startedAt, device/app info), `events` (eventId, sessionId, type, screenId/schemaId, timestamp, payloadSanitized, propsSummary, correlationId).
- Índices por `sessionId` y `timestamp`.
- Política de retención: TTL 14 días, tope 2000 eventos por sesión, 200 sesiones, 20 MB; estrategia de purge (LRU por sesión).
- Rama: `feat/ht-ink-f1-schema`.

## F2 – Hooks de captura (navegación/SDUI/acciones)
- Integración con Decompose/navigation: hook en cambios de screen.
- Integración con SDUI renderer: emitir evento de render con ids de screen/schema y props resumidas (no JSON crudo).
- Acciones/errores: capturar intents de usuario y errores render/navegación (sin payload sensible).
- Rama: `feat/ht-ink-f2-hooks`.

## F3 – Writer pipeline
- Buffer en memoria + persistencia batched hacia SQDelight; backpressure si excede límite.
- Sanitización: remover tokens/PII antes de persistir; hashing de props grandes.
- Estrategia de fallo: si DB no disponible, cola en memoria con tope y descartes con warning.
- Rama: `feat/ht-ink-f3-writer`.

## F4 – Visor in-app (lista/detalle)
- Pantalla lista de sesiones: fecha, duración, número de eventos; filtros básicos.
- Detalle de sesión: timeline con eventos (navegación, acciones, errores).
- Componentes Dui y strings i18n; atajo Make para abrir visor.
- Rama: `feat/ht-ink-f4-viewer`.

## F5 – Replay básico
- Caso de uso que reconstruye secuencia de pantallas a partir de eventos registrados.
- Mock de datos para props; integración con SDUI para re-render si hay schemaId.
- Controles: play/next/prev; límite de pasos para evitar loops.
- Rama: `feat/ht-ink-f5-replay`.

## F6 – Make/atajos
- Comandos Make para abrir visor y correr un replay específico (deeplink/route).
- Documentar rutas/flags necesarios; sin interacción manual.
- Rama: `feat/ht-ink-f6-make`.

## F7 – Testing/observabilidad
- Checklist QA/Dev: captura, retención, sanitización, fallos de DB, límites, replay.
- Logs estructurados hacia Toad; validación de redacción de PII.
- Rama: `feat/ht-ink-f7-testing`.
