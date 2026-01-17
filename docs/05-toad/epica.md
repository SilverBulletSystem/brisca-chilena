# Épica 5 — External Record (Toad)

## Objetivo
Registrar requests/responses externos (en MVP: HTTP Ktor) con correlación a sesiones de State Record (Inkribbon), persistirlos en SQDelight y ofrecer un visor/monitor en-app. Debe ser desacoplado de transporte (preparado para futuros canales como BD/Bluetooth) y aplicar límites/sanitización.

## Entregables
- Modelo de dominio de eventos externos (request/response/error) con value objects y servicios de dominio.
- Esquema SQDelight y política de retención (TTL/tamaño/límite de eventos).
- Interceptor Ktor (HTTP) que emite eventos sanitizados + correlación con Inkribbon.
- Pipeline de escritura (buffer + persistencia) con backpressure y sanitización de PII/tokens.
- Visor/monitor in-app (lista y detalle con filtros) usando componentes Dui.
- Integración de correlación con State Record (CorrelationId compartido).
- Comandos Make/atajos para abrir el monitor sin interacción manual.
- Plan de pruebas y observabilidad (logs hacia Toad/State Record).

## Alcance
- Canales: diseño agnóstico de transporte con adaptadores por canal (HTTP en MVP; contratos listos para futuros canales como Bluetooth/otros).
- Persistencia local: SQDelight.
- Seguridad: redacción de PII/bearer; límites de tamaño por payload.
- Correlación: unir request/response a sesión/CorrelationId de Inkribbon.

## Fuera de alcance
- Envío a backend remoto (solo preparación opcional de cola).
- Implementación de adaptadores no HTTP (GraphQL/Bluetooth) en MVP; se deja contrato listo. 

## Orden sugerido
1) F0 Dominio/contratos (agnóstico de canal) → 2) F1 Schema DB + retención → 3) F2 Interceptor HTTP (adaptador canal HTTP) → 4) (opcional futuro) adaptador Bluetooth/otros → 5) F3 Writer pipeline → 6) F4 Correlación con Inkribbon → 7) F5 Visor/monitor → 8) F6 Make/atajos → 9) F7 Testing/observabilidad.
