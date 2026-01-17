# Épica 9 — Storage (Settings MP + SQDelight)

## Objetivo
Definir la capa de almacenamiento local con enfoque DDD: Settings Multiplatform para preferencias ligeras y SQDelight para persistencia estructurada (State/External Record y futuros esquemas). Debe ser configurable por ambiente, con inicialización multiplataforma y DI (Koin).

## Entregables
- Contratos de dominio para storage (prefs y DB) y configuración por ambiente.
- Wrapper Settings MP tipado y seguro.
- Configuración e inicialización de SQDelight multiplataforma.
- Factories/DI para proveedores de DB y prefs.
- Smokes básicos de lectura/escritura.
- Comandos Make para ejecutar smokes.
- Plan de pruebas y observabilidad.

## Alcance
- Plataformas: Android/iOS; Web no prioritaria en MVP.
- Ambientes: mock/dev/prod; rutas/archivos separados cuando aplique.
- Seguridad: sin PII en claro; doc de cifrado futuro (no implementar en MVP).
- DDD: dominio define interfaces; infra provee Settings/SQDelight.

## Fuera de alcance
- Cifrado avanzado (solo documentado).
- Migraciones complejas más allá de las tablas necesarias (State/External Record).

## Orden sugerido
1) F0 Dominio/contratos → 2) F1 Settings wrapper → 3) F2 Config DB/SQDelight → 4) F3 Factories/DI → 5) F4 Smokes → 6) F5 Make → 7) F6 Testing/observabilidad. 
