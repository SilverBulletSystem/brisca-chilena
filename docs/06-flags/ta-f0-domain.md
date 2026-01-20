# TA-FLAGS-F0 — Dominio/contratos de Feature Flags (DDD)

## Navegacion
- [Volver a Epica](06-flags/epica.md)
- [Volver a Backlog](06-flags/backlog.md)

## Rama
- `feat/ht-flags-f0-domain`

## Objetivo
Definir el modelo de dominio, servicios, repositorios e interfaces para Feature Flags, desacoplado de proveedores concretos (local/Firestore). Servirá de base para cache, monitor y consumo por SDUI/DS.

## Alcance
- Entidades/VO:
  - `FlagKey` (string), `FlagValue` (bool|int|double|string), `FlagSource` (local|remote|default), `EnvValue` (mock|dev|prod), `Timestamp`.
  - `FlagSnapshot` { key, value, source, fetchedAt, ttl? }.
- Servicios de dominio:
  - `FlagResolver`: aplica prioridad local → remote → default; valida tipo esperado.
  - `FlagValidator`: parse/typing; rechaza valores incompatibles con el contrato.
  - `FlagCachePolicy`: TTL/ETag y expiración.
  - `ClockService`.
- Repositorios (interfaces):
  - `FlagsProvider`: obtiene snapshot desde una fuente (local/remote).
  - `FlagsRepository`: combina proveedores, aplica resolución/TTL y expone consultas.
- Casos de uso:
  - `GetFlag(key, expectedType, env)` -> valor tipado o default.
  - `GetFlags(keys, env)` -> batch.
  - `RefreshFlags(env)` -> merge local/remote, respeta prioridad/TTL.
  - `ResolveForNode(flagsNeeded)` -> subset para SDUI/DS.
- Reglas
  - Dominio no conoce Firestore ni JSON; solo interfaces.
  - Debe ser determinista por env y prioridad; sin PII ni strings hardcode en UI (solo keys).

## Diseño (doc)
1) Diagrama lógico (texto)
   - `FlagSnapshot` con source/ttl/fetchedAt.
   - `FlagsRepository` mantiene cache en memoria (opcional) y en Settings (infra).
2) Errores
   - Flag faltante: usar default definido y log warning.
   - Tipo inesperado: descartar valor y usar default; log.
   - TTL expirado: marcar stale y disparar refresh si procede.

## Entregables (documento)
- Definición de entidades/servicios/repositorios/casos de uso.
- Reglas de prioridad/TTL/errores.

## Verificación futura
- Dominio claro para implementar proveedores, cache y monitor sin ambigüedad.

## No incluido
- Implementación en código ni wiring de DI. 