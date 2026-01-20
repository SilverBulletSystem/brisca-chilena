# TA-INK-F2 — Hooks de captura (navegación/SDUI/acciones)

## Navegacion
- [Volver a Epica](04-inkribbon/epica.md)
- [Volver a Backlog](04-inkribbon/backlog.md)

## Rama
- `feat/ht-ink-f2-hooks`

## Objetivo
Documentar los puntos de inyección para capturar eventos de navegación, render SDUI y acciones/errores, produciendo eventos de dominio para Inkribbon sin acoplar a JSON ni a transporte.

## Alcance
- Navegación (Decompose): hook en cambios de screen/stack, emite `NavigationEvent(screenId, route, timestamp)`.
- Render SDUI: desde renderer (TA-F3 MagicsDui) emitir `RenderEvent(schemaId, screenId, propsSummary, timestamp)`.
- Acciones de usuario: botones/acciones SDUI/DS reportan `ActionEvent(actionId, screenId, schemaId?)`.
- Errores: render/navegación fallida → `ErrorEvent(kind, messageRedacted, screenId?, schemaId?)`.
- Correlación: adjuntar `CorrelationId` si viene de External Record (opcional).
- Sanitización: usar `EventSanitizer` antes de entregar al caso de uso `RecordEvent`.

## Diseño (doc)
1) Interfaces de hook
   - `NavigationTracker.onScreenChanged(screenId, route, timestamp)`
   - `RenderTracker.onRendered(schemaId, screenId, propsSummary, timestamp)`
   - `ActionTracker.onAction(actionId, screenId, schemaId?, timestamp)`
   - `ErrorTracker.onError(kind, messageRedacted, screenId?, schemaId?, timestamp)`
2) PropsSummary
   - Reducir props a claves/ids; prohibido payload completo ni PII; tamaño máximo 2 KB tras sanitización (truncar y hash si excede).
3) Flujo
   - Hook → sanitización → EventLimiter → caso de uso `RecordEvent`.
   - Si excede límite, descartar con warning.

## Entregables (documento)
- Lista de hooks, forma de uso y datos mínimos capturados.
- Reglas de sanitización y límites de tamaño por evento.

## Verificación futura
- Hooks cubren navegación, render y acciones/errores.
- Sin acoplar a JSON ni a transporte.

## No incluido
- Implementación de hooks ni wiring con Decompose/renderer. 