# TA-TOAD-F7 — Testing/observabilidad

## Rama
- `feat/ht-toad-f7-testing`

## Objetivo
Definir plan de pruebas y observabilidad para External Record: captura HTTP, truncado/sanitización, retención, correlación y visor.

## Alcance
- Casos QA/Dev:
  - Captura de request/response/error con headers sensibles redactados y payload truncado (8 KB).
  - Límites/TTL: purge tras 14 días, tope 500 trazas/10 MB.
  - Correlación: `CorrelationId`/`SessionId` visible y linkeable con Inkribbon.
  - Fallo de DB: writer cae a cola y descarta con warning al llenarse.
  - Monitor UI: filtros, detalle, hashes visibles; empty/error states.
- Observabilidad:
  - Logs estructurados a Toad/State Record con severidad y conteos de purga/descartes.
  - Métricas sugeridas: trazas escritas, truncadas, descartadas, duración media, tamaño medio de payload, reintentos de DB.
- No-code: validación con interceptores simulados/mocks.

## Diseño (checklist)
- Fixtures de request/response (2xx/4xx/5xx, timeout).
- Verificación de truncado/hash y headers redactados.
- Pruebas de purga por TTL y por tamaño.
- Navegación entre monitor Toad y sesión Inkribbon.

## Entregables (documento)
- Plan de pruebas y checklist QA/Dev.
- Métricas/logs sugeridos.

## Verificación futura
- Plan listo para ejecutar antes de implementar. 
