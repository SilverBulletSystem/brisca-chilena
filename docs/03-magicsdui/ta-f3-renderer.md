# TA-SDUI-F3 — Renderer SDUI → Dui

## Navegacion
- [Volver a Epica](03-magicsdui/epica.md)
- [Volver a Backlog](03-magicsdui/backlog.md)

## Rama
- `feat/ht-sdui-f3-renderer`

## Objetivo
Diseñar el mapeo de nodos SDUI al árbol de componentes Dui (átomos/moléculas/organismos), especificando qué componente Dui se usa por tipo, cómo se aplican estilos/tokens, estados, acciones y cómo se trata el i18n y accesibilidad. Debe ser ejecutable por un dev sin ambigüedad.

## Alcance
- Tabla de mapeo SDUI type → componente Dui (átomo/molécula/organismo), incluyendo juego/chat/flags.
- Resolución de estilos via roles/tokens (colores, tipografía, `ThemeDimens`, shapes).
- Estados (focus/pressed/disabled/error; hover solo Web/Desktop).
- Acciones/navegación y binding de i18n.
- Usa el AST de dominio (no JSON crudo), con acciones validadas por NavigationGuard y estilos por StyleResolution.

## Diseño (doc)
1) Mapeo SDUI type → Dui
   - Átomos: `text`→DuiText (role/title/body/etc.), `icon`→DuiIcon, `button`→DuiButton, `fab`→DuiFab, `divider`→DuiDivider, `spacer`→DuiSpacer, `chip/tag`→DuiChip, `badge`→DuiBadge, `progress`→DuiProgress, `radio`→DuiRadio, `switchSimple`→DuiSwitch.
   - Moléculas: `iconButton`→DuiIconButton, `textField`→DuiTextField, `checkbox`→DuiCheckbox, `toggle`→DuiToggle, `listItem`→DuiListItem, `settingsItem`→DuiSettingsItem, `card`→DuiCard, `banner`→DuiBanner, `snackbar`→DuiSnackbar, `dialog/bottomSheet`→DuiDialog/DuiBottomSheet, `dropdown/select`→DuiDropdown/DuiSelect, `tabs/filterChips`→DuiTabs/DuiFilterChips, `accordion`→DuiAccordion, `avatar`→DuiAvatar, `itemCard`→DuiItemCard, `typingIndicator`→DuiTypingIndicator, `messageStatus`→DuiMessageStatus, botones identitarios → wrappers de DuiButton, selectores idioma/sonido → wrappers de DuiToggle/Select.
   - Organismos generales: `appScaffold`→DuiAppScaffold, `topBar`→DuiTopBar, `bottomNav`→DuiBottomNav, `navRail/drawer/sideMenu`→DuiNavigationRail/Drawer/SideMenu, `list/section/grid`→DuiList/DuiSection/DuiGrid, `settingsList`→DuiSettingsList, `logoutDialog`→DuiLogoutDialog, `soundSetting`→DuiSoundSetting, `emptyState/errorState/loadingState`→ DuiEmpty/Error/Loading.
   - Organismos juego: `cardFace`/`naipe`→DuiCardFace, `cardDeck/shuffleControl`→DuiShuffleControl, `dealControl`→DuiDealControl, `cardHand/hand/playList`→DuiCardHand/DuiHand, acciones `renuncio/cobrarRenuncio/vale/cobrarVale/acceptPlay/rejectPlay/ready/startMatch/leaveMatch`→botones DuiAction*, `turnState`→DuiTurnState, `timer/countdown`→DuiTimer, `scoreboard*`→DuiScoreboard*, `scoreTable*`→DuiScoreTable*, `teamPanel/playerStatus`→DuiTeamPanel/DuiPlayerStatus, `matchInviteCard`→DuiMatchInviteCard, `lobbyList/playerSeat`→DuiLobbyList/PlayerSeat, `pauseOverlay/resumeButton`→DuiPauseOverlay/Resume, `matchHistoryList`→DuiMatchHistoryList, `achievementItem`→DuiAchievementItem, `capoteBanner`→DuiCapoteBanner.
   - Organismos chat/soporte: `messageBubble`→DuiMessageBubble, `messageInput`→DuiMessageInput, `messageList`→DuiMessageList, `typingIndicator`→DuiTypingIndicator, `attachmentAction`→DuiAttachmentAction, `supportForm`→DuiSupportForm.
   - Organismos flags/monitores: `flagList`→DuiFlagList, `table`→DuiTable/two-column, `logList`→DuiLogList, `timeline`→DuiTimeline, `filterChips/filterSelect`→DuiFilterChips/DuiSelect.

2) Estilos y tokens
   - Resolver `style` en roles: `colorRole`, `typographyRole`, `spacingRole`, `shapeRole`; mapear a tokens de `designsystem`.
   - Defaults si faltan roles: usar roles por defecto del DS para cada tipo (p.ej. button filled, text body).
   - Prohibir valores crudos; si llegan, ignorar y loggear warning.
   - Si falta role requerido, aplicar fallback del DS y registrar warning (Toad/State Record).

3) Estados
   - Soportar `state`: `enabled|disabled|focus|pressed|error`; hover solo Web/Desktop (ignorar en mobile).
   - Aplicar estilos de estado desde tokens (outline, opacidad, colores de estado).
   - Si el estado no es reconocido, usar `enabled` y warning.

4) Acciones/navegación
   - Mapear `action` a handlers: `deeplink|navigate|open_url|dispatch_intent`; validar target no vacío.
   - Hooks a Decompose/nav; compatible con atajos Make (bitácoras/samples).
   - `open_url` debe pasar por allowlist de dominios; si falla validación, deshabilitar acción y warning.
   - Si falta acción en componente interactivo, deshabilitar componente y warning.

5) i18n y accesibilidad
   - Todos los textos vienen como keys; resolver contra interfaces de strings; fallback muestra clave si falta (log warning).
   - Garantizar hit targets y spacing mínimos (48dp) según `ThemeDimens`; respetar focus/outline para accesibilidad.
   - Si falta label accesible en un interactivo, usar la key como label y warning.

6) Fallback y logging
   - Si un nodo no se puede renderizar (tipo no soportado/prop crítica faltante): omitir y loggear warning; si es nodo raíz crítico, render de error SDUI.
   - Logging estructurado (para Toad/State Record): nodo/type/id, motivo, severidad (warning/error).
7) Guardrails de carga/render
   - Respetar límites definidos por parser (tamaño/profundidad/payload). Si el AST viene marcado como truncado, mostrar fallback de error en lugar de render parcial.

## Entregables (documento)
- Tabla de mapeo SDUI → Dui por tipo.
- Reglas de estilos/estados, acciones, i18n y accesibilidad.
- Manejo de fallback y logging.

## Verificación futura
- Cobertura de todos los tipos definidos en el contrato.

## No incluido
- Implementación del renderer en código.