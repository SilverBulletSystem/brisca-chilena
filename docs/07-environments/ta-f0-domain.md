# TA-ENV-F0 — Dominio/contratos del Selector de Ambientes (DDD)

## Navegacion
- [Volver a Epica](07-environments/epica.md)
- [Volver a Backlog](07-environments/backlog.md)

## Rama
- `feat/ht-env-f0-domain`

## Objetivo
Definir el modelo de dominio, servicios y repositorios para gestionar ambiente actual (mock/dev/prod) de forma desacoplada de la UI y de los proveedores (network/flags/SDUI).

## Alcance
- Entidades/VO:
  - `EnvValue`: `mock|dev|prod`.
  - `EnvConfig`: `{ baseUrlKtor, flagsEndpoint?, sduiEndpoint?, firestoreProject?, mockBase? }`.
  - `EnvSource`: `default|user`.
- Servicios:
  - `EnvResolver`: obtiene ambiente actual, aplica restricciones por build type.
  - `EnvGuard`: bloquea cambio en release prod (o pide confirmación).
  - `EnvChangeCoordinator`: notifica a dependencias (network, flags, SDUI) para reconfiguración.
  - `ClockService`.
- Repositorios:
  - `EnvRepository`: persiste y lee ambiente seleccionado (Settings MP).
- Casos de uso:
  - `GetCurrentEnv()`.
  - `SetEnv(env, source)` -> aplica guard, persiste, coordina notificaciones.
  - `ListEnvs()` -> devuelve configs disponibles.

## Reglas
- La lista de ambientes es estática y versionada; no se edita en runtime.
- No se expone en SDUI; es organismo cliente.
- Cambio de env debe invalidar caches (flags, SDUI schemas) y refrescar fuentes.

## Entregables (documento)
- Entidades/servicios/repositorios/casos de uso y reglas de cambio.

## Verificación futura
- Dominio claro para implementar persistencia, UI y reconfiguración sin ambigüedad.

## No incluido
- Implementación en código ni wiring DI. 