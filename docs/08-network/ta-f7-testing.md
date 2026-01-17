# TA-NET-F7 — Testing/observabilidad

## Rama
- `feat/ht-net-f7-testing`

## Objetivo
Definir plan de pruebas y observabilidad para la capa de red: config por env, interceptores, logging, retries, Firestore mínimo y smokes.

## Alcance
- Casos QA/Dev:
  - Config por env aplicada (baseUrl/headers/logging).
  - Interceptores activos: headers comunes, Toad tracing, sanitización de logs.
  - Timeouts/retries aplican; logging nivel según env.
  - Firestore lectura funciona o fallback controlado a local.
  - Smokes por env pasan o reportan error controlado.
- Observabilidad:
  - Logs estructurados a Toad con latencia/estado; no exponer PII ni credenciales.
  - Métricas sugeridas: tasa de error, latencia media, reintentos, fallas de config.

## Diseño (checklist)
- Verificar `net-smoke` y `net-info` comandos.
- Validar bloqueos/guard en prod release.
- Revisar que SDUI/flags sigan funcionando tras cambio de env (coordinación con selector).

## Entregables (documento)
- Checklist QA/Dev y métricas/logs sugeridos.

## Verificación futura
- Plan listo antes de implementar. 
