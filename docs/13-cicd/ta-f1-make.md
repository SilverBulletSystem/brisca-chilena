# TA-CICD-F1 — Make targets alineados a CI

## Rama
- `feat/ht-cicd-f1-make`

## Objetivo
Documentar los comandos Make necesarios para CI/CD, alineados con los jobs de GH Actions y con los flavors/build types del proyecto.

## Alcance
- Comandos clave:
  - `make assemble-mock-debug`, `make assemble-dev-debug`, `make assemble-prod-release`.
  - `make detekt` (lint estático).
  - `make test` (unit tests stage, no en assemble local).
  - `make web-build-mock`.
- Variables: `FLAVOR`, `BUILD_TYPE`, `CI=true` si aplica.
- Reglas: assemble no ejecuta tests; tests van en stage dedicado.

## Entregables (documento)
- Lista de comandos, variables y su propósito en CI.

## Verificación futura
- Make definido para usarse en workflows. 
