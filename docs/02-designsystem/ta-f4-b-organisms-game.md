# TA-DS-F4-B — Organismos de juego y bitácora

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f4-b-organisms-game`

## Objetivo
Definir y crear el catálogo de organismos de juego y su bitácora, con estados/props cerrados y validación visual. Sin implementación de código.

## Alcance
- Catálogo completo de organismos de juego del sistema de diseño.
- Bitácora de juego: secciones y escenarios (1v1/2v2, pausa, invitación, historial, capote).
- Ruta y comando Make para abrir esta pantalla.

## Dependencias
- TA-DS-F1 (tokens y theming).
- TA-DS-F2A (rutas y comandos Make).

## Diseño (doc)
### 1) Catálogo completo de organismos de juego (Dui)
- `DuiCardFace` / `DuiNaipe`
- `DuiCardDeck` / `DuiShuffleControl`
- `DuiDealControl`
- `DuiHand` / `DuiPlayList` / `DuiCardHand`
- Acciones: `DuiRenuncio`, `DuiCobrarRenuncio`, `DuiVale`, `DuiCobrarVale`, `DuiAcceptPlay`, `DuiRejectPlay`, `DuiReady`, `DuiStartMatch`, `DuiLeaveMatch`
- Estado/turno: `DuiTurnState`, `DuiTimer` / `DuiCountdown`
- Score: `DuiScoreboard1v1`, `DuiScoreboard2v2`, `DuiScoreTable1v1`, `DuiScoreTable2v2`
- `DuiTeamPanel` / `DuiPlayerStatus`
- Match/session: `DuiMatchInviteCard`, `DuiLobbyList` / `DuiPlayerSeat`
- `DuiPauseOverlay` / `DuiResumeButton`
- `DuiMatchHistoryList`
- `DuiAchievementItem`, `DuiCapoteBanner`

### 2) Estados y props por organismo (cerrado)
**DuiCardFace / DuiNaipe**
- Estado: visible / hidden.
- Tamaño: `ThemeDimens.IMAGE_SM`.

**DuiCardDeck / DuiShuffleControl**
- Estados: idle / shuffling.

**DuiDealControl**
- Estados: enabled / disabled.

**DuiHand / DuiPlayList / DuiCardHand**
- Estados: 3 cartas visibles / 0 cartas (empty).

**Acciones (Renuncio/Vale/etc.)**
- Estados: enabled / disabled.

**DuiTurnState**
- Estados: myTurn / otherTurn.

**DuiTimer / DuiCountdown**
- Estados: running / stopped.

**Scoreboards / ScoreTables**
- Estados: 1v1 y 2v2 con marcador.

**DuiTeamPanel / DuiPlayerStatus**
- Estados: active / inactive.

**DuiMatchInviteCard**
- Estados: pending / accepted / rejected.

**DuiLobbyList / DuiPlayerSeat**
- Estados: seats 2/4 ocupados.

**DuiPauseOverlay / DuiResumeButton**
- Estados: visible / hidden.

**DuiMatchHistoryList**
- Estados: 3 items con resultado.

**DuiAchievementItem / DuiCapoteBanner**
- Estados: locked / unlocked.

### 3) Bitácora de Organismos Juego (pantalla)
- **Título:** `DuiText.Title` → “Bitácora de organismos juego”.
- **Sección Cartas:**
  - `DuiCardFace`, `DuiCardDeck`, `DuiHand`.
- **Sección Acciones:**
  - `DuiRenuncio`, `DuiVale`, `DuiAcceptPlay`, `DuiRejectPlay`.
- **Sección Turno y tiempo:**
  - `DuiTurnState`, `DuiTimer`, `DuiCountdown`.
- **Sección Score:**
  - `DuiScoreboard1v1`, `DuiScoreboard2v2`, `DuiScoreTable1v1`, `DuiScoreTable2v2`.
- **Sección Lobby/Match:**
  - `DuiMatchInviteCard`, `DuiLobbyList`, `DuiPlayerSeat`.
- **Sección Pausa/Historial:**
  - `DuiPauseOverlay`, `DuiResumeButton`, `DuiMatchHistoryList`.
- **Sección Logros/Capote:**
  - `DuiAchievementItem`, `DuiCapoteBanner`.

### 4) Navegación/Make
- Ruta: `app://bitacora/organisms/game`.
- Comando: `make run-bitacora-organism-game`.

## Wireframe (servilleta)
```
Bitácora de organismos juego
────────────────────────────────────────
[Sección: Cartas]
CardFace / CardDeck / Hand

[Sección: Acciones]
Renuncio / Vale / Accept / Reject

[Sección: Turno y tiempo]
TurnState / Timer / Countdown

[Sección: Score]
Scoreboard1v1 / Scoreboard2v2
ScoreTable1v1 / ScoreTable2v2

[Sección: Lobby/Match]
MatchInvite / LobbyList / PlayerSeat

[Sección: Pausa/Historial]
PauseOverlay / ResumeButton / MatchHistoryList

[Sección: Logros/Capote]
AchievementItem / CapoteBanner
```

## Entregables (documento)
- Props/estados por organismo de juego.
- Diseño de pantalla de bitácora de juego.
- Ruta y comando Make definidos.

## Verificación futura
- Props/estados cubren organismos definidos en TA-DS-F1.
- La pantalla respeta el wireframe definido.

## No incluido
- Implementación en código.

## Validación Dev (rol QA)
- [ ] Ejecutar `make run-bitacora-organism-game` y verificar apertura sin clicks manuales.
- [ ] Validar secciones: cartas, acciones, turno/tiempo, score, lobby/match, pausa/historial, logros/capote.

## Criterios de aceptacion
- La bitácora muestra todas las secciones definidas.
- Se visualizan los estados por organismo según esta historia.
- `run-bitacora-organism-game` abre la pantalla correcta.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ds-f4b): bitacora organismos juego (#2)`

**Incluye:**
- Actualización de `docs/02-designsystem/ta-f4-b-organisms-game.md`.
- Entrada en `CHANGELOG.md` bajo `[Unreleased]`.

**Checklist:**
- [ ] Solo documentación (sin código).
- [ ] Enlace a la épica `docs/02-designsystem/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.