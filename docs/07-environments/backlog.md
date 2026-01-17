# Backlog técnico — Épica 7 Selector de Ambientes

Referencia: `07-environments/epica.md`. Solo documentación, sin implementación.

## F0 – Dominio/contratos
- Entidades/VO: `EnvValue` (mock|dev|prod), `EnvConfig` (baseUrls, flagsEndpoint?, sduiEndpoint?, firestoreProject?), `EnvSource` (default|user).
- Servicios de dominio: `EnvResolver` (elige ambiente actual), `EnvGuard` (restricciones por build type), `EnvChangeCoordinator` (notifica dependencias), `ClockService`.
- Repositorios: `EnvRepository` (persistencia + carga).
- Casos de uso: `GetCurrentEnv`, `SetEnv(env)`, `ListEnvs()`.
- Rama: `feat/ht-env-f0-domain`.

## F1 – Persistencia/config
- Persistir elección en Settings MP; cargar en startup.
- Definir tabla/config estática de `EnvConfig` por env; sin edición en runtime.
- Guard para release prod: bloquear cambio o esconder UI.
- Rama: `feat/ht-env-f1-persistence`.

## F2 – UI selector (organismo cliente)
- Organismo Dui `DuiEnvironmentSelector` (no SDUI): lista de ambientes, selección actual, confirmación si cambia.
- i18n/accesibilidad; estados: actual, deshabilitado (si policy).
- Rama: `feat/ht-env-f2-ui`.

## F3 – Reconfiguración de servicios
- Al cambiar env: actualizar base URL de Ktor, apuntar proveedores de flags (local/remote), endpoint SDUI/mockserver.
- Invalidar caches (flags, SDUI schemas) y refrescar.
- Notificar a Toad/Inkribbon de cambio de env para correlación.
- Rama: `feat/ht-env-f3-reconfigure`.

## F4 – Make/deeplinks
- Comandos: `make run-env-selector FLAVOR=...` abre pantalla; `make set-env ENV=mock|dev|prod FLAVOR=...` (opcional si aplica).
- Deeplink: `app://env/selector`.
- Rama: `feat/ht-env-f4-make`.

## F5 – Testing/observabilidad
- Checklist: cambio de env persiste; reconfigura endpoints; invalida caches; bloqueos en release prod; UI i18n/a11y; logs a Toad.
- Rama: `feat/ht-env-f5-testing`.
