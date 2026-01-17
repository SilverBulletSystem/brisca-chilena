# TA-ENV-F4 — Make/deeplinks del selector de ambientes

## Rama
- `feat/ht-env-f4-make`

## Objetivo
Definir atajos Make y deeplinks para abrir el selector de ambientes sin interacción manual y facilitar pruebas.

## Alcance
- Comandos Make sugeridos:
  - `make run-env-selector FLAVOR=mock|dev|prod` → abre `app://env/selector`.
  - (Opcional) `make set-env ENV=mock|dev|prod FLAVOR=...` si se permite setear directo en dev.
- Deeplink:
  - `app://env/selector`
- Validaciones:
  - En release prod, comando debe ser no-operativo o bloqueado según guard.
  - Logs claros si se bloquea.

## Entregables (documento)
- Lista de comandos y parámetros; política por build type.

## Verificación futura
- Atajos reproducibles y alineados con guard de ambientes. 
