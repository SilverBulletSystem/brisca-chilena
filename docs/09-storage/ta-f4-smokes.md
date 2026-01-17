# TA-STORAGE-F4 — Smokes de almacenamiento

## Rama
- `feat/ht-storage-f4-smokes`

## Objetivo
Definir smokes rápidos para validar Settings y DB: lectura/escritura básica y apertura de SQDelight.

## Alcance
- Settings: set/get de claves de prueba en namespaces; verificar defaults.
- DB: abrir base, ejecutar query simple (ej. PRAGMA user_version o count en tabla conocida) sin afectar datos.
- Por env/flavor: usar ruta/nombre correspondiente.

## Diseño (doc)
1) Checklist
   - Settings: escribir/leer bool/int/string; limpiar clave de prueba.
   - DB: abrir y leer versión; opcional inserción/lectura en tabla de test si existe.
2) Reporte
   - Salida legible (para Make) con estado ok/fallo y logs de error.

## Entregables (documento)
- Lista de smokes y expectativas.

## Verificación futura
- Smokes claros, sin implementación. 
