# TA-AUDIO-F2 — Toggle/UX + flags

## Navegacion
- [Volver a Epica](11-audio/epica.md)
- [Volver a Backlog](11-audio/backlog.md)

## Rama
- `feat/ht-audio-f2-toggle`

## Objetivo
Diseñar el toggle de sonido (UI + flag) y su persistencia, asegurando que la reproducción respete policy/flags y que el estado sea consistente entre sesiones.

## Alcance
- UI:
  - Componente DS `DuiSoundToggle` (molécula/organismo en settings).
  - i18n/accesibilidad; touch target >=48dp.
- Persistencia:
  - Guardar `AudioToggleState` en Settings (namespaced).
  - Inicializar estado en app start; default ON si no se especifica otra policy.
- Flags:
  - Flag de habilitación de audio; si está off, deshabilitar toggle y reproducir silencio.
- Integración con `AudioPolicy`:
  - Policy consulta flag + toggle antes de reproducir.

## Entregables (documento)
- UI, persistencia, integración con policy y reglas de estado.

## Verificación futura
- Toggle definido, persistente y respetado por reproducción. 