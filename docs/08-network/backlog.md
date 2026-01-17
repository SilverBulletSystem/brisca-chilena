# Backlog técnico — Épica 8 Network

Referencia: `08-network/epica.md`. Solo documentación, sin implementación.

## F0 – Dominio/contratos
- Entidades/VO: `BaseUrl`, `Timeouts`, `RetryPolicy`, `Headers` (safe), `EnvValue` (mock/dev/prod), `ServiceEndpoint` (flags/sdui/others).
- Servicios de dominio: `NetworkConfigProvider`, `NetworkClientFactory` (interfaz), `SerializerConfig`.
- Casos de uso: `GetNetworkConfig(env)`, `BuildHttpClient(config)`.
- Rama: `feat/ht-net-f0-domain`.

## F1 – Config por ambiente
- Tabla de configuración por env: base URL mock/dev/prod, timeouts, retries, logging level, cert/pinning (doc).
- Integración con selector de ambientes: usa `EnvValue` y `EnvConfig`.
- Rama: `feat/ht-net-f1-config`.

## F2 – Cliente Ktor base
- JSON (kotlinx) config (lenient=false, ignoreUnknownKeys=true?), timeouts, engine multiplataforma.
- Inyección vía Koin; factory que recibe `NetworkConfig`.
- Rama: `feat/ht-net-f2-client`.

## F3 – Interceptores/logging/tracing
- Interceptor de headers comunes (User-Agent, locale, appVersion).
- Interceptor Toad: logging de request/response sanitizado y correlación (CorrelationId).
- Logging level por build type/env (verbose en mock, minimal en prod).
- Rama: `feat/ht-net-f3-interceptors`.

## F4 – Firestore mínimo (lectura)
- Cliente lector (auth/config/flags) configurado por env; sólo lectura.
- Config credenciales/endpoints por env; backoff y timeouts.
- Rama: `feat/ht-net-f4-firestore`.

## F5 – Smokes de conectividad
- Smokes por env/flavor: reachability mock/dev, ping endpoints clave (flags, sdui, firestore).
- Sin tests pesados; solo verificación rápida.
- Rama: `feat/ht-net-f5-smokes`.

## F6 – Make/atajos
- Comandos: `make net-smoke FLAVOR=mock|dev|prod` (ejecuta smokes), `make net-info FLAVOR=...` (imprime config actual).
- Rama: `feat/ht-net-f6-make`.

## F7 – Testing/observabilidad
- Checklist: configs correctas por env, interceptores activos, logging nivel correcto, backoff/retry, Firestore lectura, smokes.
- Logs a Toad; métricas sugeridas (latencia, tasa de error).
- Rama: `feat/ht-net-f7-testing`.
