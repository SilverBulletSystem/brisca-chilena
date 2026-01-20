# Backlog tecnico — Epica 02 Design System (Historias tecnicas)

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Diseno detallado](02-designsystem/diseno-detallado.md)

## TA-DS-F1 Navegación bitácoras
- Objetivo: definir rutas y wiring Decompose para bitácoras.
- Entregables: rutas, navegación y comandos Make.
- Verificacion: `make run-bitacora-*` abre pantalla correcta.
- Dependencias: estructura de navegación definida.

## TA-DS-F2 Tokens y theming
- Objetivo: definir tokens, roles y ThemeDimens con variantes de tema.
- Entregables: listado de tokens, estructura de temas, reglas de naming.
- Verificacion: tokens y mapping documentados.
- Dependencias: Epica 1 (i18n base y ThemeDimens), TA-DS-F1 (navegación).

## TA-DS-F3 Atomos y bitacora
- Objetivo: definir estados y bitácora de átomos (catálogo ya en TA-DS-F2).
- Entregables: estados y bitácora por categoría.
- Verificacion: bitácora de átomos documentada.
- Dependencias: TA-DS-F2.

## TA-DS-F4 Moleculas y bitacora
- Objetivo: definir estados/variantes y bitácora de moléculas (catálogo ya en TA-DS-F2).
- Entregables: estados/variantes y bitácora.
- Verificacion: bitácora de moléculas documentada.
- Dependencias: TA-DS-F3.

## TA-DS-F5A Organismos generales
- Objetivo: definir bitácora y estados de organismos base (catálogo ya en TA-DS-F2).
- Entregables: estados/props y bitácora de organismos generales.
- Verificacion: bitácora de organismos generales documentada.
- Dependencias: TA-DS-F4.

## TA-DS-F5B Organismos juego
- Objetivo: definir bitácora y estados de organismos de juego (catálogo ya en TA-DS-F2).
- Entregables: estados/props y bitácora de organismos de juego.
- Verificacion: bitácora de organismos de juego documentada.
- Dependencias: TA-DS-F4.

## TA-DS-F5C Organismos chat/soporte
- Objetivo: definir bitácora y estados de organismos de chat/soporte (catálogo ya en TA-DS-F2).
- Entregables: estados/props y bitácora de organismos de chat/soporte.
- Verificacion: bitácora de organismos de chat/soporte documentada.
- Dependencias: TA-DS-F4.

## TA-DS-F5D Organismos flags/monitores
- Objetivo: definir bitácora y estados de organismos flags/monitores (catálogo ya en TA-DS-F2).
- Entregables: estados/props y bitácora de organismos flags/monitores.
- Verificacion: bitácora de organismos flags/monitores documentada.
- Dependencias: TA-DS-F4.

## TA-DS-F6 Accesibilidad
- Objetivo: checklist de accesibilidad y reglas de contraste.
- Entregables: guía de accesibilidad para Dui.
- Verificacion: checklist aplicada a cada componente.
- Dependencias: TA-DS-F2 a TA-DS-F5.

## TA-DS-F7 i18n estricto
- Objetivo: reglas de i18n en DS sin strings literales.
- Entregables: interfaces de strings por bitácora o pantalla DS.
- Verificacion: checklist i18n en componentes Dui.
- Dependencias: Epica 1 (i18n base).

## Notas
- Orden sugerido: F1 → F2 → F3 → F4 → F5A/B/C/D → F6 → F7.
