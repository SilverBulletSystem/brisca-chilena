# TA-NET-F2 — Cliente Ktor base

## Rama
- `feat/ht-net-f2-client`

## Objetivo
Documentar la construcción del cliente Ktor base multiplataforma usando `NetworkConfig`: JSON (kotlinx) seguro, timeouts, retries y DI con Koin. Debe ser extensible a interceptores de Toad y headers comunes.

## Alcance
- JSON: `ignoreUnknownKeys=true`, `isLenient=false`, `encodeDefaults=true`.
- Timeouts: usar valores de `NetworkConfig`.
- Retries/backoff: aplicar política de `NetworkConfig` (doc).
- Logging: nivel según env/build type.
- DI: fábrica Koin `provideHttpClient(config)`.
- Engines: multiplataforma; sin implementación específica, solo doc de elección (OkHttp/ Darwin/Js).

## Diseño (doc)
1) Constructor
   - Recibe `NetworkConfig`, aplica timeouts/retry/logging/JSON.
2) Headers comunes
   - Prepared para inyectar en F3 (User-Agent, locale, appVersion, CorrelationId opcional).
3) Errores
   - Si config inválida, fallback a mock config y log crítico.

## Entregables (documento)
- Pasos para construir el cliente base y parámetros esperados.

## Verificación futura
- Cliente base diseñado, listo para añadir interceptores (F3). 
