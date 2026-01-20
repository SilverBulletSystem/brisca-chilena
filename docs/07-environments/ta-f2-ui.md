# TA-ENV-F2 — UI Selector de Ambientes (organismo cliente)

## Navegacion
- [Volver a Epica](07-environments/epica.md)
- [Volver a Backlog](07-environments/backlog.md)

## Rama
- `feat/ht-env-f2-ui`

## Objetivo
Diseñar el organismo UI `DuiEnvironmentSelector` (cliente, no SDUI) para elegir mock/dev/prod, con i18n y accesibilidad. Debe integrarse con el caso de uso `SetEnv` y respetar guards de build.

## Alcance
- Layout y componentes Dui:
  - Contenedor `DuiAppScaffold` + `DuiTopBar` (título i18n).
  - Lista de opciones (mock/dev/prod) con `DuiList`/`DuiListItem`, radio/checkbox para selección.
  - Botón de confirmación `DuiButton` (si requiere confirmación).
  - `DuiBadge` o texto para mostrar endpoint/base URL resumido.
  - Estado actual resaltado; opciones deshabilitadas según guard.
- Interacción:
  - Al seleccionar, llamar `SetEnv(env, source=user)`; si guard bloquea, mostrar `DuiDialog` informativo.
  - Confirmación opcional cuando se cambia de prod→otro (o viceversa).
- Accesibilidad/i18n:
  - Todas las strings por contrato de strings; roles accesibles; focus visible; touch target >=48dp.
- Navegación:
  - Deeplink `app://env/selector` (coordinado con Make).

## Entregables (documento)
- Layout, componentes, flujos de interacción, estados (normal, bloqueado, confirmación).

## Verificación futura
- UI clara, sin hardcode de strings, lista para implementar con el caso de uso. 