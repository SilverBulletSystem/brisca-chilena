# Épica 8 — Network (Ktor + Firestore mínimo)

## Objetivo
Diseñar la capa de red con enfoque DDD: contratos de dominio, configuración por ambiente (mock/dev/prod), cliente Ktor con timeouts/logging/retries, y cliente Firestore mínimo (lectura) para mobile. Debe ser desacoplada de transporte y preparada para DI (Koin).

## Entregables
- Contratos de dominio de red y configuración por ambiente.
- Config base Ktor (timeouts, logging, JSON, certificados, retries backoff).
- Interceptores (Toad, headers comunes, tracing/correlation).
- Cliente Firestore mínimo (lectura flags/config) con configuración por env.
- Smoke tests de conectividad por ambiente/flavor.
- Comandos Make para ejecutar smokes sin pasos manuales.
- Plan de pruebas y observabilidad.

## Alcance
- Plataformas: Android/iOS; Web usa mockserver en MVP.
- Ambientes: mock/dev/prod; config de endpoints y credenciales separadas.
- Seguridad: manejo de headers sensibles, certificados (pinning opcional doc), timeouts y backoff.
- DDD: dominio define contratos; infra provee Ktor/Firestore.

## Fuera de alcance
- Escritura a Firestore desde cliente.
- Implementar backend real; se usa mockserver para web en MVP.

## Orden sugerido
1) F0 Dominio/contratos → 2) F1 Config por ambiente → 3) F2 Cliente Ktor base → 4) F3 Interceptores/logging/tracing → 5) F4 Firestore mínimo (lectura) → 6) F5 Smokes → 7) F6 Make/atajos → 8) F7 Testing/observabilidad. 
