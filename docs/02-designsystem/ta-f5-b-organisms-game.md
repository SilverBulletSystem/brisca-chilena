# TA-DS-F5-B — Organismos de juego y bitácora

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f5-b-organisms-game`

## Objetivo
Definir catálogo y bitácora para organismos de juego (naipe, score, lobby, pausa, historial, logros) con navegación Make. Sin implementación.

## Alcance
- Organismos de juego: `DuiCardFace`/`DuiNaipe`, `DuiCardDeck`/`DuiShuffleControl`, `DuiDealControl`, `DuiHand`/`DuiPlayList`/`DuiCardHand`, acciones `DuiRenuncio`, `DuiCobrarRenuncio`, `DuiVale`, `DuiCobrarVale`, `DuiAcceptPlay`, `DuiRejectPlay`, `DuiReady`, `DuiStartMatch`, `DuiLeaveMatch`, estado/turno `DuiTurnState`, `DuiTimer`/`DuiCountdown`, score `DuiScoreboard1v1`/`DuiScoreboard2v2`/`DuiScoreTable1v1`/`DuiScoreTable2v2`, `DuiTeamPanel`/`DuiPlayerStatus`, lobby/partida `DuiMatchInviteCard`, `DuiLobbyList`/`DuiPlayerSeat`, `DuiPauseOverlay`/`DuiResumeButton`, `DuiMatchHistoryList`, logros `DuiAchievementItem`, `DuiCapoteBanner`.
- Bitácora de juego: secciones y escenarios (1v1/2v2, pausa, invitación, historial, capote).
- Navegación/Make: comando para abrir la bitácora de juego sin interacción manual.

## Diseño (doc)
1) Catálogo de organismos de juego con props y estados (turno, timers, score, acciones).
2) Pantalla “Bitácora Organismos Juego”: secciones por flujo (invitación/lobby, barajar/repartir, acciones, score, pausa/historial/logros).
3) Ruta interna propuesta: `app://bitacora/organisms/game`.
4) Target Make propuesto: `make run-bitacora-organisms-game`.

## Entregables (documento)
- Catálogo de organismos de juego y props.
- Diseño de pantalla de bitácora de juego.
- Ruta y comando Make definidos.

## Verificación futura
- Cobertura de organismos de juego completa.
- Comando/ruta documentados para QA/dev.

## No incluido
- Implementación en código.

## Validación Dev (rol QA)
- [ ] Ejecutar `make run-bitacora-organisms-game` y verificar apertura sin clicks manuales.
- [ ] Validar secciones: invitación/lobby, barajar/repartir, acciones (renuncio/vale/etc.), score 1v1/2v2, pausa/historial/logros/capote, visibles aunque sean placeholders.