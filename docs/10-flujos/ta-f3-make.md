# TA-FLUJOS-F3 — Make/deeplinks por flujo

## Rama
- `feat/ht-flujos-f3-make`

## Objetivo
Definir atajos Make y deeplinks para abrir flujos/pantallas rápidamente (mock flavor) sin interacción manual.

## Alcance
- Comandos sugeridos (mock por defecto, FLAVOR configurable):
  - `make run-splash`
  - `make run-login`
  - `make run-home`
  - `make run-game-1v1`
  - `make run-game-2v2`
  - `make run-chat`
  - `make run-flags-ui`
  - `make run-bitacora`
  - `make run-support`
  - `make run-settings`
- Deeplinks esperados: `app://<route>` correspondientes a cada comando.
- Validaciones: comandos deben fallar con mensaje claro si falta app/flavor.

## Entregables (documento)
- Lista de comandos/deeplinks y parámetros.

## Verificación futura
- Atajos claros para QA/Dev sin clicks. 
