# TA-AUDIO-F1 — Catálogo ES/EN + empaquetado

## Navegacion
- [Volver a Epica](11-audio/epica.md)
- [Volver a Backlog](11-audio/backlog.md)

## Rama
- `feat/ht-audio-f1-catalog`

## Objetivo
Definir el catálogo de eventos sonoros por flujo (ES/EN), su mapeo a archivos empaquetados en el artefacto inicial y la selección por locale con fallback.

## Alcance
- Eventos (ejemplos, extender según flujos):
  - Navegación/feedback: `nav_click`, `success`, `error`, `notif`.
  - Juego: `shuffle`, `deal`, `play_card`, `renuncio`, `cobrar_renuncio`, `vale`, `cobrar_vale`, `turn_start`, `win`, `lose`, `draw`, `capote`, `achievement`.
- Mapeo a archivos:
  - Pack ES y pack EN; nombrado consistente (`event_locale.ext` o carpetas por locale).
- Selección por locale:
  - Usar `AudioRouter`; si falta asset en locale actual, fallback a pack ES o silencio.
- Tamaño/peso:
  - Documentar estimación y límites para el artefacto; priorizar compresión/modo corto.

## Entregables (documento)
- Lista de eventos con archivo ES/EN asociado y reglas de naming.
- Reglas de selección/fallback por locale.

## Verificación futura
- Catálogo completo para empaquetar audio inicial. 