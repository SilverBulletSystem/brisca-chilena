# Diseno detallado - Epica 13 CI/CD + Calidad (Nivel 4)

## Contexto
Pipelines CI/CD multi-target con quality gates y detekt.

## Alcance
- Workflows GH Actions.
- Targets Make para CI.
- Detekt y smokes.
- Publicacion interna.

## TA-CICD-F0 Workflows base
- Android/iOS/Web con stages separados.

## TA-CICD-F1 Make targets
- assemble rapido y tests separados.

## TA-CICD-F2 Detekt
- Reglas del proyecto en CI.

## TA-CICD-F3 Smokes
- Arranque basico y SDUI sample.

## TA-CICD-F4 Publicacion
- Play Internal, TestFlight, web mock.

## TA-CICD-F5 Gates
- Checklist PR y merge.
