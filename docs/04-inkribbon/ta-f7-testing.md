# TA-INK-F7 — Testing/observabilidad

## Rama
- `feat/ht-ink-f7-testing`

## Objetivo
Definir plan de pruebas y observabilidad para Inkribbon: captura, retención, sanitización, fallos de DB, límites y replay.

## Alcance
- Casos QA/Dev:
  - Captura de navegación/render/acción/error se registra y se puede leer en visor.
  - Retención: purga tras TTL y al superar límites (eventos/MB) con logging.
  - Sanitización: no se persisten tokens/PII; props truncadas/hash.
  - Fallos de DB: writer cae a cola en memoria y descarta con warning al llenarse.
  - Replay: reproduce secuencia hasta el límite; maneja faltantes de schema con placeholder.
- Observabilidad:
  - Logs estructurados a Toad con severidad y conteos de descartes/purge.
  - Métricas sugeridas (doc): eventos escritos, eventos descartados, tiempo de flush, tamaño cola.
- No-code: se valida con hooks simulados y datos mock.

## Diseño (checklist)
- Preparar fixtures de sesiones/eventos (mock) para pruebas manuales/automatizadas.
- Checklist de validación por entorno (mock/dev/prod debug): captura → visor → replay → purga.
- Verificar accesibilidad/i18n en visor (textos por interfaz de strings).

## Entregables (documento)
- Plan de pruebas y checklist QA/Dev.
- Métricas/logs sugeridos para monitoreo.

## Verificación futura
- Plan listo para ejecutar antes de implementar. 
