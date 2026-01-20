# TA-WEB-F4 — Make/atajos Web

## Navegacion
- [Volver a Epica](12-web/epica.md)
- [Volver a Backlog](12-web/backlog.md)

## Rama
- `feat/ht-web-f4-make`

## Objetivo
Definir comandos Make para correr y probar la web admin (mock) sin pasos manuales.

## Alcance
- Comandos:
  - `make web-run-mock` → levanta web apuntando a mockserver.
  - `make web-smoke` → ejecuta smokes web (TA-F3).
- Variables: permitir override de endpoint mock (`MOCK_BASE_URL`) si aplica.
- Validaciones: fallar con mensaje claro si mockserver no está disponible.

## Entregables (documento)
- Comandos, variables y comportamiento esperado.

## Verificación futura
- Atajos reproducibles para QA/Dev web. 