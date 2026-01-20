# TA-WEB-F3 — Smokes web

## Navegacion
- [Volver a Epica](12-web/epica.md)
- [Volver a Backlog](12-web/backlog.md)

## Rama
- `feat/ht-web-f3-smokes`

## Objetivo
Definir smokes rápidos para el target web admin: carga, fetch a mockserver y render de vistas básicas (flags, bitácora/SDUI samples).

## Alcance
- Verificar que la app web cargue y alcance el mockserver.
- Render de monitor de flags (datos mock) y bitácora/samples SDUI.
- Sin Firestore; endpoints solo de mockserver.

## Diseño (doc)
1) Checklist
   - Build web ok.
   - HTTP a mockserver responde (flags/samples).
   - Renderiza lista de flags y sample SDUI sin errores JS.
2) Reporte
   - Salida simple para Make (`ok`/`fail` + mensaje).

## Entregables (documento)
- Lista de smokes y expectativas por entorno mock.

## Verificación futura
- Smokes claros para QA/Dev web. 