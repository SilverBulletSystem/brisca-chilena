# TA-DS-F5-B — Organismos de juego y bitácora

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f5-b-organisms-game`

## Objetivo
Definir bitácora para organismos de juego (naipe, score, lobby, pausa, historial, logros) usando el catálogo ya definido en TA-DS-F1. Sin implementación.

## Alcance
- Bitácora de juego: secciones y escenarios (1v1/2v2, pausa, invitación, historial, capote).
- Navegación/Make: comando para abrir la bitácora de juego sin interacción manual.

## Diseño (doc)
1) Props y estados relevantes (turno, timers, score, acciones).
2) Pantalla “Bitácora Organismos Juego”: secciones por flujo (invitación/lobby, barajar/repartir, acciones, score, pausa/historial/logros).
3) Ruta interna propuesta: `app://bitacora/organisms/game`.
4) Target Make propuesto: `make run-bitacora-organisms-game`.

## Entregables (documento)
- Props/estados por organismo de juego.
- Diseño de pantalla de bitácora de juego.
- Ruta y comando Make definidos.

## Verificación futura
- Props/estados cubren organismos definidos en TA-DS-F1.
- Comando/ruta documentados para QA/dev.

## No incluido
- Implementación en código.

## Validación Dev (rol QA)
- [ ] Ejecutar `make run-bitacora-organisms-game` y verificar apertura sin clicks manuales.
- [ ] Validar secciones: invitación/lobby, barajar/repartir, acciones (renuncio/vale/etc.), score 1v1/2v2, pausa/historial/logros/capote, visibles aunque sean placeholders.