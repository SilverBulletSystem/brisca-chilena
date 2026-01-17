# TA-FLAGS-F6 — Testing/observabilidad

## Rama
- `feat/ht-flags-f6-testing`

## Objetivo
Definir plan de pruebas y observabilidad para flags: prioridad, TTL, fallback, errores remotos, monitor UI e integración con SDUI.

## Alcance
- Casos QA/Dev:
  - Prioridad local > remoto > default se respeta.
  - TTL/ETag: cache expira y se refresca; offline usa snapshot previa.
  - Errores remoto: app sigue con local/default; warning log.
  - Monitor UI: muestra valores/fuentes, filtros, estados empty/error; i18n/accesibilidad.
  - Integración SDUI: flag faltante usa default y log; tipo incorrecto se descarta.
- Observabilidad:
  - Logs estructurados a Toad/State Record: cache hit/miss, expiración, fallas de fetch, defaults aplicados.
  - Métricas sugeridas: tasa de cache hit, tiempo de fetch, conteo de faltantes, porcentaje de defaults usados.
- No-code: validación con proveedores mock (local/remote) y toggles de ambiente/flavor.

## Diseño (checklist)
- Preparar fixtures JSON locales y simulaciones de Firestore (mock) para QA.
- Verificar comandos Make (`run-flags-monitor`, `refresh-flags`).
- Validar accesibilidad/i18n en monitor.

## Entregables (documento)
- Plan de pruebas y checklist QA/Dev.
- Métricas/logs sugeridos.

## Verificación futura
- Plan listo para ejecutar antes de implementación. 
