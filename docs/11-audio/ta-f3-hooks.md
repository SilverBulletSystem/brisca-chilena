# TA-AUDIO-F3 — Hooks de reproducción (UI/SDUI)

## Rama
- `feat/ht-audio-f3-hooks`

## Objetivo
Definir cómo los eventos de UI/SDUI disparan audio usando `AudioPlayer`/`AudioPolicy`, mapeando acciones/estados a `AudioEvent` y asegurando desac acoplamiento a la implementación.

## Alcance
- Mapeo de eventos:
  - Navegación: clicks, back, success/error feedback.
  - Juego: shuffle, deal, play_card, renuncio, cobrar_renuncio, vale, cobrar_vale, turn_start, win/lose/draw, capote, achievement.
  - Chat/Soporte: send message, receive message (opcional).
- Integración SDUI:
  - En renderer, mapear acciones SDUI a `AudioEvent` (sin hardcodear paths).
  - Respetar flags/toggle (policy) antes de reproducir.
- Fallback:
  - Si no hay asset o policy bloquea, silencio sin error.

## Entregables (documento)
- Tabla de acciones/estados → `AudioEvent`.
- Reglas de invocación y fallback.

## Verificación futura
- Hooks claros para implementación. 
