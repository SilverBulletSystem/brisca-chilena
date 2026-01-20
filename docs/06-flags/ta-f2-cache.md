# TA-FLAGS-F2 — Cache/refresh de flags

## Navegacion
- [Volver a Epica](06-flags/epica.md)
- [Volver a Backlog](06-flags/backlog.md)

## Rama
- `feat/ht-flags-f2-cache`

## Objetivo
Diseñar la estrategia de cache y refresh de flags usando Settings MP (persistencia ligera) y cache en memoria, con TTL/ETag y fallback offline.

## Alcance
- Cache en Settings MP:
  - Guarda `FlagSnapshotMap` con `fetchedAt`, `ttl`, `etag/version`.
  - Tamaño esperado <100 KB.
- Cache en memoria:
  - Para lecturas rápidas; invalida por TTL/ETag.
- Estrategia de refresh:
  - En app start si cache stale; manual desde monitor; auto cada 24h (sugerido).
  - Orden de fetch: local → remote; merge por prioridad.
- Fallback:
  - Si falla remote, usar cache previa válida; si no hay, defaults.
- Concurrencia:
  - Evitar fetch duplicado; usar in-flight guard.
- Logs:
  - Cache hit/miss, expiración, errores de fetch.

## Entregables (documento)
- Flujo de cache/refresh, TTL/ETag, orden de resolución y fallbacks.

## Verificación futura
- Cache y refresh definidos sin ambigüedad. 