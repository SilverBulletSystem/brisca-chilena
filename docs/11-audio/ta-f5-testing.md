# TA-AUDIO-F5 — Testing/observabilidad

## Rama
- `feat/ht-audio-f5-testing`

## Objetivo
Definir plan de pruebas y observabilidad para audio ES/EN: reproducción por flujo, respeto de toggle/flags, fallback silencioso y latencia aceptable.

## Alcance
- Casos QA/Dev:
  - Reproducción de eventos clave (navegación, juego, feedback) en ES/EN según locale.
  - Toggle/flags: audio se silencia cuando corresponde y persiste estado.
  - Faltan assets: fallback a pack alterno o silencio sin crash.
  - Latencia: reproducción no bloquea UI; sonido llega a tiempo aceptable.
  - Spike descarga (si aplica): falla controlada, integridad hash.
- Observabilidad:
  - Logs de reproducción/fallos; no incluir paths sensibles.
  - Métricas sugeridas: tasa de éxito de play, latencia promedio, errores de carga.

## Diseño (checklist)
- Probar en ambos locales; validar naming de assets.
- Probar toggles y flags en mock/dev.
- Validar comportamiento offline (usa assets empaquetados).

## Entregables (documento)
- Checklist QA/Dev y métricas/logs sugeridos.

## Verificación futura
- Plan listo antes de implementación. 
