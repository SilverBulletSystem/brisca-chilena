# TA-ENV-F5 — Testing/observabilidad

## Rama
- `feat/ht-env-f5-testing`

## Objetivo
Definir plan de pruebas y observabilidad para el selector de ambientes: persistencia, reconfiguración, guard en release, UI e integración con servicios.

## Alcance
- Casos QA/Dev:
  - Cambio de env se persiste y se carga en restart.
  - Reconfigura network/flags/SDUI, invalida caches y refresca.
  - Guard en release prod: selector bloqueado/oculto.
  - UI accesible/i18n; deeplink/make funcionan.
  - Logs en Toad/Inkribbon sobre cambios de env.
- Observabilidad:
  - Logs estructurados: env anterior/nuevo, resultado (success/fallback), motivo de bloqueos.
  - Métricas sugeridas: cambios de env, fallas de reconfig, tiempo de reconfig.

## Diseño (checklist)
- Fixtures de configs por env; pruebas con cambios mock↔dev↔prod.
- Validar rollback si falla reconfig.
- Validar empty/error states en UI.

## Entregables (documento)
- Plan de pruebas y checklist QA/Dev.
- Logs/métricas sugeridos.

## Verificación futura
- Plan listo antes de implementar. 
