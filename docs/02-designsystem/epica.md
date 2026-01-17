# Épica 2: Design System + accesibilidad preparada

Objetivo: definir el Design System (Atomic con prefijo `Dui`), tokens de tema/accesibilidad, y bitácoras navegables de átomos, moléculas y organismos, con navegación rápida vía comandos Make (similar a `make run-bitacora` del proyecto base). Sin implementación de código.

## Historias técnicas propuestas

### F1 – Tokens y theming base
- Objetivo: documentar tokens (colores, tipografía, `ThemeDimens`, shapes) y estructura de temas (default + estructura daltónicos, sin UI completa).
- Entregables: mapa de tokens, roles de color, tipografías, dimensiones, y cómo integrarlos en `ThemeManager`.

### F2 – Navegación/atajos Make para bitácoras
- Objetivo: documentar comandos Make (p. ej. `make run-bitacora-atoms`, `run-bitacora-molecules`, `run-bitacora-organisms-*`) y rutas para abrir bitácoras sin interacción manual (similar a `make run-bitacora`/`run-login` del proyecto base).
- Entregables: definición de targets Make (solo doc) y rutas de navegación asociadas.

### F3 – Átomos Dui + bitácora de átomos
- Objetivo: definir lista de átomos `Dui*` (texto, icono, button, chip, badge, progress, etc.) y bitácora que muestre todos los estados.
- Entregables: catálogo de átomos con estados; diseño de pantalla “Bitácora de Átomos”; navegación directa por comando Make.

### F4 – Moléculas Dui + bitácora de moléculas
- Objetivo: definir moléculas `Dui*` (text field, checkbox, toggle, list item, card, banner, snackbar, dialog, dropdown, tabs, accordion, avatar, item card, message indicator, etc.) y bitácora con estados/variantes.
- Entregables: catálogo + pantalla “Bitácora de Moléculas”; navegación por Make.

### F5 – Organismos Dui + bitácoras (partido en 4)
- Objetivo: definir organismos en lotes pequeños para evitar PRs grandes y facilitar pruebas:
  - F5-A Organismos generales (layout/navegación/estados/config).
  - F5-B Organismos de juego (naipe/score/lobby/pausa/historial/logros).
  - F5-C Organismos de chat/soporte.
  - F5-D Organismos de flags/monitores.
- Entregables: catálogos y pantallas de bitácora por lote; navegación por Make en cada bitácora.

### F6 – Accesibilidad preparada
- Objetivo: definir tokens y lineamientos de accesibilidad (contraste, focus/hover/pressed/disabled, tamaños mínimos, reduce motion), temas daltónicos estructurados.
- Entregables: guía de accesibilidad del DS; mapeo de tokens para estados; requerimientos mínimos por componente.

### F7 – i18n estricto en DS
- Objetivo: reforzar que ningún componente UI del DS hardcodea strings; todos via interfaces/strings contractuales.
- Entregables: pauta y ejemplo de uso de strings en componentes DS.

## Orden sugerido
1) F1 Tokens/Theming
2) F2 Navegación/atajos Make (para probar sin clicks)
3) F6 Accesibilidad
4) F3 Átomos + bitácora
5) F4 Moléculas + bitácora
6) F5 Organismos + bitácoras (F5-A–F5-D)
7) F7 i18n en DS
