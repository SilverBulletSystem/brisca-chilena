# TA-STORAGE-F6 — Testing/observabilidad

## Rama
- `feat/ht-storage-f6-testing`

## Objetivo
Definir plan de pruebas y observabilidad para storage: prefs, DB init, migración mínima, smokes y logging.

## Alcance
- Casos QA/Dev:
  - Settings: set/get en namespaces; defaults aplican; claves de prueba no afectan prod.
  - DB: apertura correcta, versión esperada, tablas presentes (State/External Record).
  - Migración: versión inicial 1; verificar manejo seguro si versión difiere (fallback/log).
  - Smokes: `storage-smoke` pasa por env/flavor.
- Observabilidad:
  - Logs de errores en apertura/lectura; no exponer rutas sensibles.
  - Métricas sugeridas: tiempo de apertura de DB, tasa de error en get/set prefs.

## Diseño (checklist)
- Preparar claves de prueba y limpiar tras smokes.
- Validar en mock/dev/prod debug; en prod release evitar escrituras duraderas.

## Entregables (documento)
- Checklist QA/Dev y logs/métricas sugeridos.

## Verificación futura
- Plan listo antes de implementación. 
