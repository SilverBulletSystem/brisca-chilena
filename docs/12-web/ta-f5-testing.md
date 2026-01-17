# TA-WEB-F5 — Testing/observabilidad

## Rama
- `feat/ht-web-f5-testing`

## Objetivo
Definir plan de pruebas y observabilidad para web admin: vistas mínimas, consumo de mockserver, DS/SDUI en web y fallos controlados (sin Firestore).

## Alcance
- Casos QA/Dev:
  - Build y carga web en mock.
  - Monitor de flags muestra datos mock; selector de ambiente visible (cliente).
  - Bitácora/SDUI samples renderizan sin errores; hover/focus en DS web.
  - Mockserver caído → mensajes de error controlados (sin crash).
- Observabilidad:
  - Logs de fetch/errores (sin PII); nivel adecuado.
  - Métricas sugeridas: tiempo de carga, tasas de error en fetch.

## Diseño (checklist)
- Probar con mockserver arriba/abajo.
- Validar navegabilidad a vistas admin y estados vacíos/error.

## Entregables (documento)
- Checklist QA/Dev, logs/métricas sugeridos.

## Verificación futura
- Plan listo antes de implementación. 
