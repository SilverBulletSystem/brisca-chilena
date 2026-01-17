# Backlog técnico — Épica 11 Audio ES/EN

Referencia: `11-audio/epica.md`. Solo documentación, sin implementación.

## F0 – Dominio/contratos de audio
- Entidades/VO: `AudioEvent` (id por flujo), `Locale` (es|en), `Volume`, `AudioToggleState` (on/off), `AudioPack` (es|en).
- Servicios: `AudioPlayer` (play/stop), `AudioRouter` (elige pack por locale), `AudioPolicy` (respeta toggle/flags).
- Casos de uso: `PlayEvent(eventId)`, `ToggleAudio(on/off)`, `SetLocale(locale)`.
- Rama: `feat/ht-audio-f0-domain`.

## F1 – Catálogo ES/EN + empaquetado
- Lista de eventos sonoros por flujo (juego, navegación, feedback).
- Mapeo a archivos ES/EN incluidos en el artefacto inicial.
- Estrategia de selección por locale y fallback.
- Rama: `feat/ht-audio-f1-catalog`.

## F2 – Toggle/UX + flags
- UI toggle sonido (DS: DuiSoundToggle) y flag que habilita audio.
- Estado persistente (Settings) y respetado por `AudioPolicy`.
- Rama: `feat/ht-audio-f2-toggle`.

## F3 – Hooks de reproducción (UI/SDUI)
- Definir qué eventos UI/SDUI disparan audio y cómo invocan `AudioPlayer`.
- Integración con MagicsDui: mapping de acción/estado a `AudioEvent`.
- Rama: `feat/ht-audio-f3-hooks`.

## F4 – Spike descarga opcional
- Evaluar descarga en runtime de packs ES/EN; definir tamaño/límites y fallback.
- Si se complica, mantener solo empaquetado inicial.
- Rama: `feat/ht-audio-f4-spike-download`.

## F5 – Testing/observabilidad
- Checklist QA/Dev: reproducción por flujo, respeto de toggle/locale, fallback silencioso, latencia.
- Logs: eventos de reproducción, errores de carga; métricas de latencia si aplica.
- Rama: `feat/ht-audio-f5-testing`. 
