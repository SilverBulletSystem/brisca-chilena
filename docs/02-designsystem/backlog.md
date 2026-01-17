# Backlog técnico — Épica 2 Design System

Referencia: `02-designsystem/epica.md`. Solo documentación de tareas, sin implementación.

## F1 – Tokens y theming base
- Definir tokens de color (roles), tipografía, `ThemeDimens`, shapes.
- Estructura de temas: default + variantes daltónicas (estructura, sin UI completa).
- Integración esperada con `ThemeManager` (doc).
- Verificación futura: tokens cubren roles de todos los componentes Dui.
- Rama: `feat/ht-ds-f1-tokens`.

## F2 – Navegación/atajos Make para bitácoras
- Definir rutas/deeplinks para bitácoras: átomos (`app://bitacora/atoms`), moléculas (`app://bitacora/molecules`), organismos (general/juego/chat/flags).
- Definir comandos Make: `run-bitacora-atoms`, `run-bitacora-molecules`, `run-bitacora-organisms-*` para abrir sin interacción manual.
- Verificación futura: comandos documentados y rutas claras.
- Rama: `feat/ht-ds-f2-make-shortcuts`.

## F3 – Átomos Dui + bitácora
- Lista completa de átomos `Dui*` y sus estados (ej. enabled/disabled/hover/focus/pressed/error).
- Diseño de pantalla “Bitácora de Átomos” (qué muestra, cómo se navega).
- Navegación directa: depende de F2 (comando Make ya definido).
- Verificación futura: bitácora lista para implementar; todos los átomos listados.
- Rama: `feat/ht-ds-f2-atoms`.

## F4 – Moléculas Dui + bitácora
- Lista completa de moléculas `Dui*` y variantes.
- Diseño de “Bitácora de Moléculas” con estados/props relevantes.
- Navegación directa: depende de F2 (comando Make ya definido).
- Verificación futura: catálogo de moléculas y estados definido.
- Rama: `feat/ht-ds-f3-molecules`.

## F5 – Organismos Dui (dividido en 4 TAs para evitar PRs grandes)
- F5-A Organismos generales: layout/nav/estados/config (scaffold, top/bottom bars, nav rail/drawer/side menu, settings list, environment selector, loading/error/empty, theme selector, logout dialog, sound setting).
  - Navegación directa: comando Make para bitácora general.
  - Rama: `feat/ht-ds-f5-a-organisms-general`.
- F5-B Organismos de juego: naipe/deck/shuffle/deal, hand/play list/card hand, acciones renuncio/vale/accept/reject/ready/start/leave, turn state, timer, scoreboards/tables 1v1/2v2, team panel/player status, lobby/invite/pause/history/logros/capote.
  - Navegación directa: comando Make para bitácora de juego.
  - Rama: `feat/ht-ds-f5-b-organisms-game`.
- F5-C Organismos chat/soporte: message bubble, message input, message list, typing indicator, attachment action, support form.
  - Navegación directa: comando Make para bitácora de chat/soporte.
  - Rama: `feat/ht-ds-f5-c-organisms-chat`.
- F5-D Organismos flags/monitores: flag list con toggle, table/two-column, log list, timeline, filtros chips/select.
  - Navegación directa: comando Make para bitácora de flags/monitores.
  - Rama: `feat/ht-ds-f5-d-organisms-flags`.

## F5 – Navegación/atajos Make para bitácoras
- Definir rutas/pantallas de bitácora y targets Make (ej. `run-bitacora-atoms`, `run-bitacora-molecules`, `run-bitacora-organisms`), similar a `make run-bitacora` del proyecto base.
- Verificación futura: comandos documentados y mapeo de rutas listo para implementación.
- Rama: `feat/ht-ds-f5-make-shortcuts`.

## F6 – Accesibilidad preparada
- Reglas de contraste, focus/hover/pressed/disabled, tamaños mínimos, reduce motion, hit areas.
- Mapeo de tokens de estado; lineamientos de temas daltónicos.
- Verificación futura: checklist de accesibilidad para DS.
- Rama: `feat/ht-ds-f6-accessibility`.

## F7 – i18n estricto en DS
- Pauta para evitar strings hardcode en componentes DS.
- Ejemplo de consumo de interfaces de strings desde DS.
- Verificación futura: checklist i18n para DS.
- Rama: `feat/ht-ds-f7-i18n`.
