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
1) Interfaces y firmas (nivel 4)
Package sugerido (presentation/renderer): `composeApp/src/commonMain/kotlin/cl/silverbullet/multiplatform/brisca/magicsdui/presentation/renderer`

```kotlin
interface NodeRenderer {
  @Composable fun render(node: Node): Unit
}

class SduiRenderer(
  private val renderers: Map<NodeType, NodeRenderer>
) {
  fun render(node: Node): @Composable () -> Unit {
    val renderer = renderers[node.type] ?: DefaultRenderer
    return { renderer.render(node) }
  }
}

interface SduiActionHandler {
  fun handle(action: NodeAction)
}
```

Responsabilidades:
- `SduiRenderer`: coordina el render usando mapa de estrategias (ver ADR-0003).
- `NodeRenderer`: contrato para renderizar un tipo especifico de nodo.
- Los tamanos se resuelven desde `Node.style.spacingRole` (mapeado a `ThemeDimens`) o defaults del DS por tipo de componente.
- `SduiActionHandler`: traduce acciones SDUI a intents/rutas de Decompose.

Referencia: [ADR-0003 Renderer SDUI con mapa de estrategias](../architecture/decisions/adr-0003-renderer-strategy.md)

Ejemplo de render (paso a paso, usando mapa de renderers):
```kotlin
// 1) Renderer especifico para Button (atom)
class ButtonRenderer(
  private val stringsProvider: StringsProvider,
  private val actionHandler: SduiActionHandler
) : NodeRenderer {
  @Composable
  override fun render(node: Node) {
    val props = node.props as ButtonProps
    val spacing = node.style?.spacingRole?.toThemeDimens() ?: ThemeDimens.SPACE_8
    val text = stringsProvider.resolve(props.labelKey)
    val color = resolveColor(node.style?.colorRole)
    
    DuiButton(
      text = text,
      onClick = { actionHandler.handle(node.actions.first()) },
      modifier = Modifier.padding(spacing),
      colors = ButtonDefaults.buttonColors(containerColor = color),
      enabled = node.state == NodeState.Enabled
    )
  }
}

// 2) Registro de renderers (al inicializar SduiRenderer)
val renderers = mapOf(
  NodeType.Atom.Button to ButtonRenderer(stringsProvider, actionHandler),
  NodeType.Atom.Text to TextRenderer(stringsProvider),
  NodeType.Molecule.TextField to TextFieldRenderer(stringsProvider),
  // ... resto de tipos
)

// 3) Uso del renderer coordinador
val renderer = SduiRenderer(renderers)
renderer.render(node) // busca en mapa, no when gigante
```

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
   - Si un nodo no se puede renderizar (tipo no soportado/prop crítica faltante): omitir y loggear warning; si es nodo raíz crítico, hacer fallback a pantalla clásica equivalente y registrar en Toad.
   - Logging estructurado (para Toad/State Record): nodo/type/id, motivo, severidad (warning/error).
7) Guardrails de carga/render
   - Respetar límites definidos por parser (tamaño/profundidad/payload). Si el AST viene marcado como truncado, hacer fallback a pantalla clásica equivalente y registrar en Toad.

## Implementacion esperada (nivel 4)
Checklist para cuando se implemente:
- Implementar `SduiRenderer` y `SduiActionHandler` en presentation.
- Asegurar mapeo 1:1 entre `NodeType` y componentes Dui.
- Usar `SduiLogger` para warnings de render.
- Registrar eventos de render en `SduiInkribbonRecorder`.

## Tests unitarios obligatorios (nivel 4)

**Ubicación:** `composeApp/src/commonTest/kotlin/.../sdui/renderer/`

### Test: RenderNode_TextNode_RendersDuiText

**Given:**
- `Node` con `type == NodeType.Atom.Text`
- `Node.props == TextProps(textKey = "login_title")`
- `SduiRenderer` implementado
- Mock de `StringsProvider`

**Expect:**
- Renderer retorna composable que renderiza `DuiText`
- Texto resuelto desde `StringsProvider` usando `textKey`
- Sin errores de render

### Test: RenderNode_ButtonNode_RendersDuiButton

**Given:**
- `Node` con `type == NodeType.Atom.Button`
- `Node.props == ButtonProps(labelKey = "login_cta")`
- `Node.actions == [NodeAction(type = DispatchIntent, target = "login")]`
- `SduiRenderer` implementado
- Mock de `StringsProvider` y `SduiActionHandler`

**Expect:**
- Renderer retorna composable que renderiza `DuiButton`
- Texto resuelto desde `StringsProvider` usando `labelKey`
- Acción configurada correctamente
- Sin errores de render

### Test: RenderNode_TextFieldNode_RendersDuiTextField

**Given:**
- `Node` con `type == NodeType.Molecule.TextField`
- `Node.props == TextFieldProps(labelKey = "login_email", placeholderKey = "login_email_hint")`
- `SduiRenderer` implementado
- Mock de `StringsProvider`

**Expect:**
- Renderer retorna composable que renderiza `DuiTextField`
- Label y placeholder resueltos desde `StringsProvider`
- Sin errores de render

### Test: RenderNode_UnknownType_LogsWarning

**Given:**
- `Node` con `type` no soportado
- `SduiRenderer` implementado

**Expect:**
- Renderer omite el nodo
- Warning registrado en `SduiToadRecorder`
- No se lanza excepción

### Test: RenderNode_MissingProps_LogsWarning

**Given:**
- `Node` con `type == NodeType.Atom.Text` pero sin `props.textKey`
- `SduiRenderer` implementado

**Expect:**
- Renderer omite el nodo
- Warning registrado en `SduiToadRecorder`
- No se lanza excepción

Base de tests KMP:
- Ubicacion: `composeApp/src/commonTest/kotlin/...`
- Framework: `kotlin.test` + `kotlinx.coroutines.test`
- Comando: `./gradlew :composeApp:commonTest`
- Agregar target `make test` que invoque ese comando.

## Pull Request (contenido esperado)
**Titulo sugerido:** `feat(ht-sdui-f3): renderer sdui (#XX)`

**Incluye:**
- Renderer y handler de acciones.
- Tests unitarios obligatorios.
- Actualizacion de docs si cambia el contrato.

**Checklist:**
- [ ] Enlace a la epica `docs/03-magicsdui/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.
- [ ] `./gradlew :composeApp:commonTest` ejecuta tests unitarios SDUI.