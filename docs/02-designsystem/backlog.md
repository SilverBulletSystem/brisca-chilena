# Backlog tecnico — Epica 02 Design System (Historias tecnicas)

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Diseno detallado](02-designsystem/diseno-detallado.md)

## TA-DS-F1 Tokens y theming
- Objetivo: definir tokens, roles y ThemeDimens con variantes de tema.
- Entregables: listado de tokens, estructura de temas, reglas de naming.
- Verificacion: tokens y mapping documentados.
- Dependencias: Epica 1 (i18n base y ThemeDimens).

## TA-DS-F2A Make shortcuts bitacoras
- Objetivo: definir rutas internas y comandos Make para abrir bitácoras.
- Entregables: rutas/deeplinks y targets Make.
- Verificacion: comandos `make run-bitacora-*` documentados.
- Dependencias: estructura de navegación definida.

## TA-DS-F2B Atomos y bitacora
- Objetivo: definir catálogo de átomos Dui y estados.
- Entregables: inventario de átomos, estados y bitácora por categoría.
- Verificacion: bitácora de átomos documentada.
- Dependencias: TA-DS-F1.

## TA-DS-F3 Moleculas y bitacora
- Objetivo: definir catálogo de moléculas Dui y variantes.
- Entregables: inventario de moléculas, estados y bitácora.
- Verificacion: bitácora de moléculas documentada.
- Dependencias: TA-DS-F2B.

## TA-DS-F5A Organismos generales
- Objetivo: definir organismos base de layout y navegación.
- Entregables: listado de organismos generales con estados.
- Verificacion: bitácora de organismos generales documentada.
- Dependencias: TA-DS-F3.

## TA-DS-F5B Organismos juego
- Objetivo: definir organismos específicos de juego.
- Entregables: listado de organismos de juego con estados.
- Verificacion: bitácora de organismos de juego documentada.
- Dependencias: TA-DS-F3.

## TA-DS-F5C Organismos chat/soporte
- Objetivo: definir organismos de chat y soporte.
- Entregables: listado de organismos de chat/soporte con estados.
- Verificacion: bitácora de organismos de chat/soporte documentada.
- Dependencias: TA-DS-F3.

## TA-DS-F5D Organismos flags/monitores
- Objetivo: definir organismos para flags/monitores.
- Entregables: listado de organismos de flags/monitores con estados.
- Verificacion: bitácora de organismos de flags/monitores documentada.
- Dependencias: TA-DS-F3.

## TA-DS-F6 Accesibilidad
- Objetivo: checklist de accesibilidad y reglas de contraste.
- Entregables: guía de accesibilidad para Dui.
- Verificacion: checklist aplicada a cada componente.
- Dependencias: TA-DS-F1 a TA-DS-F5.

## TA-DS-F7 i18n estricto
- Objetivo: reglas de i18n en DS sin strings literales.
- Entregables: interfaces de strings por bitácora o pantalla DS.
- Verificacion: checklist i18n en componentes Dui.
- Dependencias: Epica 1 (i18n base).

## Notas
- Orden sugerido: F1 → F2A → F2B → F3 → F5A/B/C/D → F6 → F7.
