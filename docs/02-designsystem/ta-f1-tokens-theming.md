# TA-DS-F1 — Tokens y theming base

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f1-tokens`

## Objetivo
Diseñar la definición de tokens (colores, tipografía, `ThemeDimens`, shapes) y la estructura de temas (default + variantes daltónicas, sin UI completa), integrables con `ThemeManager`.

## Alcance
- Roles de color alineados a Material3 + necesidades Dui.
- Tipografía (familia, pesos, tamaños) y mapeo a `MaterialTheme.typography`.
- `ThemeDimens`: espaciados, tamaños de ítems touch (>=48dp), alturas de inputs/dropdowns, radios.
- Shapes: esquinas por jerarquía (card, dialog, chip).
- Estructura de temas: default + variantes daltónicos (Deuteranopia, Tritanopia, Achromatopsia, High Contrast) definidas en tokens.

## Diseño (doc)
1) **Colores**
   - Definir roles: primary, onPrimary, primaryContainer, secondary, onSecondary, secondaryContainer, tertiary, background, surface, surfaceVariant, error, outline, scrim, inverseSurface, etc.
   - Paletas para: default, deuteranopia, tritanopia, achromatopsia, high-contrast (solo tokens; sin implementarlas en UI).
2) **Tipografía**
   - Definir escalas: Display, Headline, Title, Body, Label con pesos y tamaños; mapear a `MaterialTheme.typography`.
3) **Dimensiones**
   - `ThemeDimens`: spacing XS/S/M/L/XL, input heights (incl. dropdown 2-line), touch target >=48dp, paddings estándar, border widths.
4) **Shapes**
   - Radios para card, sheet/dialog, chip/pill; borde para focus/outline.
5) **ThemeManager**
   - Documentar cómo se conectarían tokens a `ThemeManager` (sin código): selector de tema, persistencia, fallback.

## Entregables (documento)
- Lista de tokens por categoría (colores, tipografía, dimens, shapes).
- Tabla/resumen de temas y roles.
- Pauta de integración con `ThemeManager`.

## Verificación futura
- Tokens cubren todos los roles usados por átomos/moléculas/organismos Dui.
- Temas daltónicos definidos a nivel de tokens.

## No incluido
- Implementación de temas en código ni UI.