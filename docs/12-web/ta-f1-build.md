# TA-WEB-F1 — Config build/deploy web

## Navegacion
- [Volver a Epica](12-web/epica.md)
- [Volver a Backlog](12-web/backlog.md)

## Rama
- `feat/ht-web-f1-build`

## Objetivo
Documentar la configuración de build y deploy web (Compose Multiplatform) para el admin mock, reutilizando scripts base y asegurando consumo de mockserver.

## Alcance
- Target web en Gradle (ya en proyecto base): confirmar ajustes de bundling/minificación.
- Build scripts:
  - Make: `make web-run-mock`, `make web-build-mock`.
  - GH Actions: job para build web mock (sin Firestore).
- Config endpoints: apuntar a mockserver; permitir override via env vars.
- Salida: bundle/static files listos para servir (sin backend propio).

## Entregables (documento)
- Pasos de build/deploy, comandos y variables necesarias.

## Verificación futura
- Build reproducible y configurado para mockserver. 