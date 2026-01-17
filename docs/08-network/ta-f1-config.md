# TA-NET-F1 — Configuración por ambiente

## Rama
- `feat/ht-net-f1-config`

## Objetivo
Definir la tabla de configuración de red por ambiente (mock/dev/prod) para Ktor/Firestore: endpoints, timeouts, retries, logging level y notas de certificados/pinning (doc).

## Alcance
- Tabla `NetworkConfig` por env:
  - `baseUrl` para Ktor (mockserver/dev/prod).
  - `serviceEndpoints`: flags, sdui, otros (si aplica).
  - `timeouts`: connect/read/write (sugerido 10s/20s/20s mock-dev, 10s/30s/30s prod).
  - `retryPolicy`: reintentos y backoff (p.ej. 3 intentos, exponencial, jitter).
  - `loggingLevel`: verbose en mock, headers en dev, minimal en prod.
  - Cert/pinning: documentar si se usa; en MVP solo doc, no implementar.
- Integración con `EnvValue`/selector de ambientes.
- Versionar la tabla; sin edición en runtime.

## Entregables (documento)
- Tabla de config por env y reglas de tiempo/retry/logging.

## Verificación futura
- Config lista para usar en fábrica de cliente y reconfig al cambiar env. 
