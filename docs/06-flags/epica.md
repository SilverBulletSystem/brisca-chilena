# Épica 6 — Feature Flags + Monitor (Flags)

## Objetivo
Diseñar la capa de feature flags en dominio, con proveedores múltiples (JSON local por flavor, Firestore remoto), cache/refresh, integración con SDUI/DS y un monitor in-app. Debe ser desacoplado del origen de datos y del transporte, preparado para agregar otros proveedores en el futuro.

## Entregables
- Modelo de dominio de flags (keys, valores tipados, fuente, timestamps).
- Interfaces de proveedores y casos de uso de resolución/refresh.
- Cache local con TTL/ETag y estrategia de prioridad (local→remoto→default).
- Proveedores: JSON local por flavor; Firestore remoto (mobile) configurado por ambiente.
- Monitor UI (lectura/inspección) con filtros, usando componentes Dui e i18n.
- Integración con SDUI/DS para visibilidad/variantes; hooks de logging.
- Comandos Make/atajos para abrir monitor y forzar refresh sin interacción manual.
- Plan de pruebas/observabilidad.

## Alcance
- Plataformas: Android/iOS; Web sólo consume local/mock en este MVP.
- Ambientes: mock/dev/prod; configuración por flavor y selector de ambientes.
- Seguridad: sin PII, redacción de tokens; límites de tamaño/TTL.
- DDD: dominio conoce interfaces y reglas; infra provee proveedores concretos.

## Fuera de alcance
- Escritura de flags en Firestore desde cliente (solo lectura en MVP).
- Proveedores adicionales (consignarlos como extensibles, no implementados).

## Orden sugerido
1) F0 Dominio/contratos → 2) F1 Proveedores (local/Firestore) → 3) F2 Cache/refresh → 4) F3 Monitor UI → 5) F4 Integración SDUI/DS → 6) F5 Make/atajos → 7) F6 Testing/observabilidad.
