# Épica 13 — CI/CD + Calidad

## Objetivo
Diseñar la estrategia de CI/CD y calidad: GitHub Actions multi-target (Android/iOS/Web), jobs separados para assemble rápido y para tests, uso de Make, Detekt con reglas del proyecto, caché de dependencias y smoke tests mínimos por flavor mock.

## Entregables
- Workflows GH Actions base (Android/iOS/Web) con matrices de flavor/build donde aplique.
- Make targets alineados a CI (assemble rápido, tests, lint/detekt, web build).
- Detekt configurado y portable desde proyecto base.
- Smoke tests mock por plataforma (rápidos, sin unit tests pesados en assemble local).
- Publicación interna: Play Internal, TestFlight, deploy web mock.
- Gates de calidad: checks de lint/detekt/smokes antes de merge.

## Alcance
- Repositorio GitHub Flow; branch main publica builds internos.
- Usar `assemble<Flavor><BuildType>` para rapidez; tests en stage separado.
- Cache de deps para acelerar pipelines.
- Artefactos: APK/AAB mock/dev, ipa TestFlight (mock/dev), bundle web mock.

## Fuera de alcance
- Release store productiva completa (fuera del MVP).
- Infra adicional (Sonar, SAST) más allá de Detekt/lints.

## Orden sugerido
1) F0 Workflows base → 2) F1 Make targets CI → 3) F2 Detekt config → 4) F3 Smokes CI → 5) F4 Publicación interna → 6) F5 Gates de calidad/checklist. 
