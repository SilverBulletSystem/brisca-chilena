# TA-CICD-F4 — Publicación interna

## Navegacion
- [Volver a Epica](13-cicd/epica.md)
- [Volver a Backlog](13-cicd/backlog.md)

## Rama
- `feat/ht-cicd-f4-publish`

## Objetivo
Documentar los jobs de publicación interna: Play Internal y TestFlight para mock/dev, y deploy web mock, usando los artefactos generados en CI.

## Alcance
- Android: subida a Play Internal (mock/dev) con firma adecuada.
- iOS: build y upload a TestFlight (mock/dev) con credenciales.
- Web: deploy static bundle mock a hosting (doc de destino; puede ser artefacto descargable si no hay hosting).
- Dependencias: jobs posteriores a smokes y lint/detekt.

## Entregables (documento)
- Flujo de publicación por plataforma, requerimientos de credenciales y orden en pipeline.

## Verificación futura
- Publicaciones definidas sin ambigüedad para implementar. 