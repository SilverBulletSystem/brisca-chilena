# TA-WEB-F2 — Adaptaciones DS/SDUI para web

## Rama
- `feat/ht-web-f2-ds-sdui`

## Objetivo
Documentar las adaptaciones necesarias de DS y SDUI para web admin: soportar hover/focus/inputs y asegurar que el renderer MagicsDui funcione en web para vistas de admin (flags/bitácora/samples).

## Alcance
- DS en web:
  - Estados hover y focus visibles; cursores apropiados en interactivos.
  - Inputs (textField, select) compatibles con teclado/mouse.
  - Accesibilidad web (roles aria básicos).
- SDUI en web (admin):
  - Renderer soporta los organismos usados en admin (flags/bitácora/samples).
  - Performance: carga de JSON mock; sin juego.
- Reuso de Make/deeplinks para navegar a bitácoras/samples en web.

## Entregables (documento)
- Lista de ajustes DS/SDUI necesarios en web y organismos cubiertos.

## Verificación futura
- Adaptaciones claras para implementación. 
