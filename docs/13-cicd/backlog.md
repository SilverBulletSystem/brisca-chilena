# Backlog técnico — Épica 13 CI/CD + Calidad

Referencia: `13-cicd/epica.md`. Solo documentación, sin implementación.

## F0 – Workflows base
- GH Actions: jobs Android/iOS/Web (matrices si aplica).
- Separar assemble rápido (sin tests) de stage de tests/lint.
- Cache de deps (Gradle/npm/cocoapods) documentada.
- Rama: `feat/ht-cicd-f0-workflows`.

## F1 – Make targets CI
- Alinear Make con CI: `assembleMockDebug`, `assembleDevDebug`, `assembleProdRelease`, `detekt`, `test` (stage), `web-build-mock`.
- Documentar variables (FLAVOR, BUILD_TYPE).
- Rama: `feat/ht-cicd-f1-make`.

## F2 – Detekt config
- Portar `detekt.yml` del proyecto base; reglas de naming/i18n/strings no hardcodeadas.
- Integrar en CI y Make; reportes.
- Rama: `feat/ht-cicd-f2-detekt`.

## F3 – Smokes CI
- Definir smokes mínimos por plataforma (mock): app arranca, SDUI sample carga, red reachability.
- Ejecutar en job separado post-assemble.
- Rama: `feat/ht-cicd-f3-smokes`.

## F4 – Publicación interna
- Jobs para Play Internal (mock/dev) y TestFlight (mock/dev); artefactos firmados.
- Web mock deploy (static) si aplica.
- Rama: `feat/ht-cicd-f4-publish`.

## F5 – Gates de calidad/checklist
- Checklist PR/CI: detekt/lint ok, smokes ok, artefactos generados.
- Política de aprobación/merge.
- Rama: `feat/ht-cicd-f5-gates`. 
