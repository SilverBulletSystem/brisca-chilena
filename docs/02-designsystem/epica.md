# Epica 2 — Design System

## Navegacion
- [Backlog tecnico](02-designsystem/backlog.md)
- [Diseno detallado](02-designsystem/diseno-detallado.md)
- Historias tecnicas:
  - [TA-DS-F1 Tokens y theming](02-designsystem/ta-f1-tokens-theming.md)
  - [TA-DS-F2A Make shortcuts bitacoras](02-designsystem/ta-f2-make-shortcuts.md)
  - [TA-DS-F2B Atomos y bitacora](02-designsystem/ta-f2-atoms-bitacora.md)
  - [TA-DS-F3 Moleculas y bitacora](02-designsystem/ta-f3-molecules-bitacora.md)
  - [TA-DS-F5A Organismos generales](02-designsystem/ta-f5-a-organisms-general.md)
  - [TA-DS-F5B Organismos juego](02-designsystem/ta-f5-b-organisms-game.md)
  - [TA-DS-F5C Organismos chat/soporte](02-designsystem/ta-f5-c-organisms-chat.md)
  - [TA-DS-F5D Organismos flags/monitores](02-designsystem/ta-f5-d-organisms-flags.md)
  - [TA-DS-F6 Accesibilidad](02-designsystem/ta-f6-accessibility.md)
  - [TA-DS-F7 i18n estricto](02-designsystem/ta-f7-i18n-ds.md)

## 1. Contexto
El Design System (Dui) define los tokens, reglas y componentes base que garantizan consistencia visual, accesibilidad e i18n en toda la suite.

## 2. Objetivo
Definir el sistema de diseño Dui con tokens, theming, catálogo de componentes y bitácoras navegables, con reglas claras de accesibilidad e i18n.

## 3. Alcance
Incluye:
- Tokens de color, tipografía, dimensiones y shapes.
- Theming con variantes (incluye tokens para daltonismo).
- Catálogo de átomos, moléculas y organismos Dui.
- Bitácoras navegables por categoría con atajos Make.
- Reglas de accesibilidad e i18n para todo el DS.

Excluye:
- Implementación de pantallas de negocio.
- SDUI y flujos del cliente.

## 4. Entregables
- Documentos de diseño por TA.
- Inventario de componentes con estados.
- Reglas de accesibilidad e i18n para Dui.
- Comandos Make para abrir bitácoras.

## 5. Dependencias
- Fundamentos (Epica 1): i18n base, estructura de módulos y reglas.
- Core UI: ThemeDimens, ThemeManager, MaterialTheme.

## 6. Riesgos
- Duplicación de componentes sin reglas claras.
- Inconsistencia de tokens entre temas.
- Accesibilidad incompleta por falta de checklist.

## 7. Criterios de aceptacion
- Existe catálogo de átomos, moléculas y organismos con estados.
- Tokens documentados y mapeados a theming.
- Bitácoras navegables con comandos Make.
- Reglas de i18n y accesibilidad documentadas.

## 8. Referencias
- ADR-0017 Atomic Design
- ADR-0019 i18n por pantalla
- ADR-0029 ThemeDimens
