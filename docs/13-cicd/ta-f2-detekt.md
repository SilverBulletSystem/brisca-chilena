# TA-CICD-F2 — Detekt config

## Rama
- `feat/ht-cicd-f2-detekt`

## Objetivo
Portar y configurar Detekt con las reglas del proyecto (naming, i18n, no hardcode strings, atomic design) y alinearlo con CI/Make.

## Alcance
- Importar `detekt.yml` del proyecto base; adaptar rutas/namespaces.
- Reglas clave: strings no hardcode en UI, naming conventions, temas/dimens centralizados, atomic design, prohibir prefijos/sufijos indebidos.
- Integración:
  - Comando `make detekt`.
  - Job en GH Actions (lint stage).
- Reportes: path de reportes (xml/html) definido.

## Entregables (documento)
- Config Detekt, reglas portadas y cómo se ejecuta en CI/Make.

## Verificación futura
- Config lista para implementación. 
