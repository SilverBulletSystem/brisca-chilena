# TA-NET-F0 — Dominio/contratos Network (DDD)

## Navegacion
- [Volver a Epica](08-network/epica.md)
- [Volver a Backlog](08-network/backlog.md)

## Rama
- `feat/ht-net-f0-domain`

## Objetivo
Definir contratos de dominio para la capa de red (configuración y fábrica de cliente) sin acoplarse a Ktor/Firestore. Servirá de base para configurar clientes por ambiente y para DI.

## Alcance
- Entidades/VO:
  - `BaseUrl`, `Timeouts` (connect/read/write), `RetryPolicy` (reintentos, backoff), `LoggingLevel` (none|basic|headers|body), `EnvValue` (mock|dev|prod), `ServiceEndpoint` (flags|sdui|other), `Headers` (clave segura), `SerializerConfig`.
- Servicios:
  - `NetworkConfigProvider(env)` -> `NetworkConfig`.
  - `NetworkClientFactory` (interfaz) -> crea cliente HTTP a partir de `NetworkConfig`.
  - `SerializerConfigProvider`.
- Casos de uso:
  - `GetNetworkConfig(env)` -> config para Ktor/Firestore.
  - `BuildHttpClient(config)` -> cliente listo para inyectar (implementado en infra).
- Reglas
  - Dominio no conoce Ktor; solo define contratos/valores.
  - Config por env debe ser determinista y versionada.

## Diseño (doc)
1) `NetworkConfig` (texto)
   - `{ baseUrl, timeouts, retryPolicy, loggingLevel, defaultHeaders, serviceEndpoints }`
2) Errores
   - Env no soportado: fallback a mock con warning.
   - Config inválida (URL malformada): error crítico a log y fallback a mock seguro.

## Entregables (documento)
- Definición de entidades/servicios/casos de uso para la capa de red.

## Verificación futura
- Contratos claros para implementar Ktor/Firestore sin ambigüedad.

## No incluido
- Implementación de clientes ni wiring Koin. 