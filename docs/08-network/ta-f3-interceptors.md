# TA-NET-F3 — Interceptores/logging/tracing

## Navegacion
- [Volver a Epica](08-network/epica.md)
- [Volver a Backlog](08-network/backlog.md)

## Rama
- `feat/ht-net-f3-interceptors`

## Objetivo
Diseñar los interceptores de red: headers comunes, logging por ambiente y trazas/correlación con Toad, manteniendo sanitización y niveles según build.

## Alcance
- Headers comunes: User-Agent, locale, appVersion, CorrelationId (si disponible), env actual.
- Logging: nivel según env/build type (verbose body en mock, headers en dev, minimal en prod).
- Toad integration: emitir eventos sanitizados (sin PII, truncado 8 KB) usando adaptador de canal HTTP.
- Retry/backoff: respetar `RetryPolicy` (ya definido en config).
- Seguridad: remover/ocultar headers sensibles en logs (Authorization, Cookie).

## Diseño (doc)
1) Orden de interceptores
   - Headers comunes → tracing/Toad → logging → retry (si aplica).
2) Configuración por env
   - Logging level derivado de `NetworkConfig.loggingLevel`.
   - Correlación con Inkribbon via `CorrelationId` si existe.
3) Errores
   - Si falla Toad/trace, no bloquear request; log warning.

## Entregables (documento)
- Lista y orden de interceptores, políticas de logging y sanitización.

## Verificación futura
- Interceptores definidos y listos para implementar en Ktor base. 