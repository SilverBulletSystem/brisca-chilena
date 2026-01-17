# Épica 4 — State Record (Inkribbon)

## Objetivo
Registrar pantallas/props/acciones por sesión de usuario, persistirlas (SQDelight) y habilitar replay básico para QA/dev. Debe ser desacoplado de transporte y de SDUI (consume eventos de dominio), con políticas de retención y protección de datos.

## Entregables
- Modelo de dominio de eventos de estado (navigation, screen render, action, error) con value objects y servicios de dominio.
- Esquema SQDelight y política de retención (tamaño/TTL) para sesiones y eventos.
- Hooks de captura en navegación Decompose/SDUI (sin acoplar a JSON; usa IDs de dominio).
- Writer pipeline (buffer + persistencia + redacción de datos sensibles).
- Visor in-app (lista de sesiones, detalle de eventos) usando componentes Dui.
- Replay mínimo: reconstrucción de secuencia de pantallas para QA/dev (mock de datos y SDUI si aplica).
- Comandos Make/atajos para abrir visor/replay sin clicks manuales.
- Plan de pruebas y observabilidad (logs hacia Toad/State Record mismo).

## Alcance
- Plataformas: Android/iOS (cliente). Web no prioritaria en este MVP.
- Persistencia local: SQDelight (ya en proyecto base).
- Integración con SDUI: recibe eventos de dominio (id de screen/schema, props resumidas) para no acoplar a JSON.
- Seguridad: redacción de PII/bearer tokens; límites de tamaño y TTL.

## Fuera de alcance
- Envío a backend remoto (solo preparar contrato/cola local opcional).
- Replay con datos reales del servidor (se usará mock/local data para QA).

## Orden sugerido
1) F0 Dominio/contratos → 2) F1 Schema DB + retención → 3) F2 Hooks de captura → 4) F3 Writer pipeline → 5) F4 Visor in-app → 6) F5 Replay básico → 7) F6 Make/atajos → 8) F7 Testing/observabilidad.
