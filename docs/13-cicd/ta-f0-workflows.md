# TA-CICD-F0 — Workflows base (GH Actions)

## Navegacion
- [Volver a Epica](13-cicd/epica.md)
- [Volver a Backlog](13-cicd/backlog.md)

## Rama
- `feat/ht-cicd-f0-workflows`

## Objetivo
Diseñar los workflows de GitHub Actions base para Android/iOS/Web, separando assemble rápido de tests/lint, con cache de dependencias y matriz por env/build cuando aplique.

## Alcance
- Jobs:
  - Android: assemble mock/dev, sin tests; stage separado para tests/lint.
  - iOS: build mock/dev (si procede), stage separado para tests.
  - Web: build mock (sin backend real).
- Cache: Gradle, npm, cocoapods (doc), keyed por lockfiles.
- Estrategia: matrix por FLAVOR/BUILD_TYPE donde aporte; evitar builds pesados innecesarios.
- Triggers: PR y push a main.

## Entregables (documento)
- Definición de jobs, triggers, cache y separación de stages.

## Verificación futura
- Workflows claros para implementación YAML. 