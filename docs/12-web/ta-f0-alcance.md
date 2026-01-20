# TA-WEB-F0 — Alcance/contratos web admin

## Navegacion
- [Volver a Epica](12-web/epica.md)
- [Volver a Backlog](12-web/backlog.md)

## Rama
- `feat/ht-web-f0-alcance`

## Objetivo
Definir el alcance funcional mínimo de la web admin en el MVP, los contratos de datos y las vistas soportadas (solo mockserver, sin Firestore ni juego web).

## Alcance
- Vistas mínimas:
  - Monitor de flags (read-only) consumiendo mockserver.
  - Selector de ambiente (cliente) para apuntar a mock/dev/prod endpoints web (mockserver en MVP).
  - Bitácora/SDUI samples: navegar y renderizar muestras SDUI para validar DS en web.
- Contratos de datos:
  - API mockserver para flags y SDUI samples (JSON).
  - Sin Firestore en web; no requiere auth en MVP.
- Reuso:
  - DS/SDUI existentes; ajustar hover/focus web.
  - Make/deeplinks específicos para web (ver F4).

## Entregables (documento)
- Lista de vistas y datos que consumen.
- Claridad de exclusiones: sin juego web, sin chat web, sin Firestore.

## Verificación futura
- Alcance cerrado y alineado a mockserver. 