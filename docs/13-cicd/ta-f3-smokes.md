# TA-CICD-F3 — Smokes CI

## Rama
- `feat/ht-cicd-f3-smokes`

## Objetivo
Definir smokes mínimos ejecutables en CI (mock flavor) para validar que la app arranca, la red responde y SDUI básico carga, sin tests pesados.

## Alcance
- Android/iOS: lanzar app en modo headless/instrumented mínimo o equivalente que verifique arranque y ping a mockserver.
- Web: build y carga básica (sin errores JS) + fetch a mockserver.
- SDUI sample: carga un JSON de ejemplo y renderiza (smoke).
- Tiempo acotado: debe ser rápido y estable.

## Entregables (documento)
- Lista de smokes y expectativas, con criterios de ok/fail.

## Verificación futura
- Smokes claros para codificar en workflows. 
