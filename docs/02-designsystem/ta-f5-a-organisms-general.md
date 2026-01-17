# TA-DS-F5-A — Organismos generales y bitácora

## Rama
- `feat/ht-ds-f5-a-organisms-general`

## Objetivo
Definir catálogo y bitácora para organismos generales (layout, navegación, estados, configuración) con navegación Make. Sin implementación.

## Alcance
- Organismos generales: `DuiAppScaffold`, `DuiTopBar`, `DuiBottomNav`/`DuiBottomBar`, `DuiNavigationRail`/`DuiDrawer`/`DuiSideMenu`, `DuiList`/`DuiSection`/`DuiGrid`, `DuiSettingsList`, `DuiThemeSelector`, `DuiEnvironmentSelector`/`DuiEnvironmentItem`, `DuiLogoutDialog`, `DuiSoundSetting`, estados `DuiEmptyState`/`DuiErrorState`/`DuiLoading`.
- Bitácora de organismos generales: secciones y props a mostrar.
- Navegación/Make: comando para abrir la bitácora sin interacción manual.

## Diseño (doc)
1) Catálogo de organismos generales y props relevantes (títulos, acciones, estados).
2) Pantalla “Bitácora Organismos Generales”: agrupación por categoría (nav, settings, estados).
3) Ruta interna propuesta: `app://bitacora/organisms/general`.
4) Target Make propuesto: `make run-bitacora-organisms-general`.

## Entregables (documento)
- Catálogo y props.
- Diseño de pantalla de bitácora general.
- Ruta y comando Make definidos.

## Verificación futura
- Cobertura de organismos generales completa.
- Comando/ruta documentados para QA/dev.

## Validación Dev (rol QA)
- [ ] Ejecutar `make run-bitacora-organisms-general` y verificar que abre la pantalla de bitácora sin clicks manuales.
- [ ] Confirmar que se visualizan las secciones definidas (nav, settings, estados) aunque sean placeholders.

## No incluido
- Implementación en código.
