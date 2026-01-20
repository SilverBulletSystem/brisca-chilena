# TA-DS-F5 — Accesibilidad preparada

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f6-accessibility`

## Objetivo
Definir lineamientos de accesibilidad para el DS: contraste, estados, tamaños mínimos, reduce motion, y estructura de temas daltónicos. Sin implementación.

## Alcance
- Contraste y roles de color por tema (incluye variantes daltónicas).
- Estados: focus/hover/pressed/disabled, outlines, feedback.
- Tamaños mínimos: touch >=48dp, tipografía legible, line-height.
- Motion: bandera “reduce motion” y duraciones recomendadas.

## Diseño (doc)
1) Contraste
   - Requisitos de ratio para texto/íconos en fondos claros/osc./surface.
   - Variantes daltónicas: ajustar tokens para mantener contraste.
2) Estados
   - Tokens para focus/hover/pressed/disabled/selected/error.
   - Outlines y opacidades por estado.
3) Tamaños/espacios
   - Hit areas >=48dp; paddings mínimos; tamaños de íconos; min heights de inputs/dropdowns.
4) Motion
   - Reducir/omitir animaciones cuando la preferencia de sistema “reduce motion” esté activa (documentado).
5) Checklist por componente
   - Botones, inputs, listas/cards, dialogs, nav, juego (acciones), chat (lectura), flags/monitores.

## Entregables (documento)
- Guía de accesibilidad y mapeo de tokens de estado.
- Checklist aplicable a átomos/moléculas/organismos.

## Verificación futura
- Tokens cubren estados y contrastes requeridos.
- Checklist disponible para QA/dev.

## No incluido
- Cambios en código ni validación con herramientas automáticas.