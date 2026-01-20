# TA-CICD-F5 — Gates de calidad/checklist

## Navegacion
- [Volver a Epica](13-cicd/epica.md)
- [Volver a Backlog](13-cicd/backlog.md)

## Rama
- `feat/ht-cicd-f5-gates`

## Objetivo
Definir los gates de calidad para PR/CI: qué checks deben pasar (detekt, smokes, build) antes de merge, y la checklist mínima en PR.

## Alcance
- Gates CI:
  - Detekt/lint ok.
  - Assemble ok (mock/dev).
  - Smokes CI ok (mock).
  - (Opcional) Tests unitarios stage separados.
- Checklist PR:
  - Tipo de cambio (feat/fix/chore), pruebas realizadas, impacto, actualización de docs.
  - Changelog/PR template alineado (ver `pr-template.md`).

## Entregables (documento)
- Lista de gates y checklist PR a exigir.

## Verificación futura
- Criterios claros para merge. 