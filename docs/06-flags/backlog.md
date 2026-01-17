# Backlog técnico — Épica 6 Flags

Referencia: `06-flags/epica.md`. Solo documentación, sin implementación.

## F0 – Dominio/contratos
- Entidades/VO: `FlagKey`, `FlagValue` (bool|int|double|string), `FlagSource` (local|remote|default), `EnvValue` (mock|dev|prod), `Timestamp`.
- Servicios de dominio: `FlagResolver` (prioridad local→remote→default), `FlagValidator` (tipo/parse), `FlagCachePolicy` (TTL/ETag), `ClockService`.
- Repositorios (interfaces): `FlagsProvider` (pull), `FlagsRepository` (cache + merge).
- Casos de uso: `GetFlag(key)`, `GetFlags(keys)`, `RefreshFlags(env)`, `ResolveForNode(flagsNeeded)`.
- Rama: `feat/ht-flags-f0-domain`.

## F1 – Proveedores (local/Firestore)
- Proveedor local JSON por flavor/ambiente; esquema de archivo; ubicación y carga segura.
- Proveedor remoto Firestore (lectura-only) configurado por env; manejo de errores y backoff.
- Estrategia de merge y prioridad (local > remote > default).
- Rama: `feat/ht-flags-f1-providers`.

## F2 – Cache/refresh
- Cache en Settings MP con TTL (sugerido 24h) y ETag/versionado.
- Estrategia de refresh: on app start, on manual refresh (monitor), y por TTL.
- Fallback offline: usar último snapshot válido; logging de expiración.
- Rama: `feat/ht-flags-f2-cache`.

## F3 – Monitor UI (read-only)
- Pantalla para listar flags, ver valor/fuente/ambiente; filtros por ambiente y fuente.
- Read-only en MVP; sin toggle de escritura.
- Componentes Dui, i18n estricto, accesibilidad.
- Rama: `feat/ht-flags-f3-monitor`.

## F4 – Integración SDUI/DS
- Documentar cómo SDUI/renderer consumen flags resueltos; prefetch de set mínimo.
- Hooks de logging cuando un flag requerido falta; fallback a default.
- Rama: `feat/ht-flags-f4-sdui`.

## F5 – Make/atajos
- Comandos Make para abrir monitor y para refrescar flags sin clicks:
  - `make run-flags-monitor FLAVOR=...`
  - `make refresh-flags FLAVOR=...`
- Rama: `feat/ht-flags-f5-make`.

## F6 – Testing/observabilidad
- Checklist QA/Dev: prioridad, TTL, offline, errores remoto, monitor UI, integración SDUI.
- Logs estructurados a Toad/State Record; métricas sugeridas.
- Rama: `feat/ht-flags-f6-testing`.
