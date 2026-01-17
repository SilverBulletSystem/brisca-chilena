# TA-AUDIO-F0 — Dominio/contratos de Audio (DDD)

## Rama
- `feat/ht-audio-f0-domain`

## Objetivo
Definir el modelo de dominio para audio con dos idiomas (ES/EN), toggles y routing por locale, desacoplado de la implementación de reproducción y descarga.

## Alcance
- Entidades/VO:
  - `AudioEvent` (id por flujo: juego, navegación, feedback).
  - `Locale` (es|en).
  - `AudioToggleState` (on/off).
  - `AudioPack` (es|en).
- Servicios (interfaces):
  - `AudioPlayer`: `play(event: AudioEvent)`, `stopAll()`.
  - `AudioRouter`: elige `AudioPack` según locale; fallback si falta asset.
  - `AudioPolicy`: respeta toggle/flags (no reproducir si off o flag desactivado).
- Casos de uso:
  - `PlayEvent(eventId)` -> aplica policy, selecciona pack, invoca player.
  - `ToggleAudio(on/off)` -> actualiza estado (Settings) y policy.
  - `SetLocale(locale)` -> actualiza router; aplica en siguiente reproducción.
- Reglas
  - Dominio no conoce path de assets ni motor de audio; solo ids y packs.
  - Fallback silencioso si falta asset o policy bloquea.

## Entregables (documento)
- Definición de VO/servicios/casos de uso y reglas de policy.

## Verificación futura
- Contratos listos para catálogo, toggle y hooks. 
