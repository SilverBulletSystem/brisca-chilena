# TA-SDUI-F0 — Modelo de dominio SDUI (DDD)

## Navegacion
- [Volver a Epica](03-magicsdui/epica.md)
- [Volver a Backlog](03-magicsdui/backlog.md)

## Rama
- `feat/ht-sdui-f0-domain`

## Objetivo
Definir el modelo de dominio SDUI (entidades/agregados, value objects, repositorio y casos de uso) independiente del transporte (HTTP/GraphQL/mock). El contrato externo (DTO) siempre se traduce a este modelo antes de renderizar.

## Alcance
- Entidades/agregados: `Schema` (root), `Node` (organismo/molécula/átomo), `Props`, `Style`, `Action`, `NodeState`.
- Value Objects: `SchemaVersion`, `NodeId`, `NodeType`, `ActionTarget` (deeplink/navigate/open_url/intent), `EnvValue` (mock/dev/prod), `FlagKey`.
- Repositorio SDUI (interfaz): `fetchSchema(schemaId, env): SchemaDto` (o resultado) abstrae origen (HTTP/GraphQL/local fixture).
- Casos de uso:
  - `FetchSchema`: orquesta la obtención de DTO desde el repositorio (capa datos/infra).
  - `ParseSchema`: traduce DTO externo → modelo de dominio `Schema`/`Node` aplicando validaciones/defaults (alineado a TA-F2).
  - `RenderSchema` (descrito en TA-F3): consume modelo de dominio para construir árbol Dui.
- Reglas:
  - Dominio no conoce transporte ni infra; solo interfaces.
  - Flags/ambientes se resuelven vía servicios de dominio (`FlagsProvider`, `EnvProvider`) antes del render.
  - i18n: textos son keys, no literales; dominio no resuelve traducciones, solo garantiza keys presentes.

## Diseño (doc)
1) Modelo de dominio (data classes, campos exactos)
Package sugerido: `composeApp/src/commonMain/kotlin/cl/silverbullet/multiplatform/brisca/magicsdui/domain/model`

2) `Node` (estructura base del arbol SDUI)
Responsabilidad: representa un nodo del arbol con su tipo, nivel, props tipadas, hijos, estilo y restricciones (flags/env).

```kotlin
@JvmInline value class SchemaId(val value: String)
@JvmInline value class SchemaVersion(val value: String)
@JvmInline value class ScreenId(val value: String)
@JvmInline value class NodeId(val value: String)
@JvmInline value class FlagKey(val value: String)

enum class EnvValue { Mock, Dev, Prod }

sealed class NodeType {
  sealed class Organism : NodeType() {
    object AppScaffold : Organism()
    object TopBar : Organism()
    object BottomNav : Organism()
    object NavRail : Organism()
    object Drawer : Organism()
    object SideMenu : Organism()
    object List : Organism()
    object Section : Organism()
    object Grid : Organism()
    object SettingsList : Organism()
    object LogoutDialog : Organism()
    object SoundSetting : Organism()
    object EmptyState : Organism()
    object ErrorState : Organism()
    object LoadingState : Organism()
    object CardFace : Organism()
    object CardDeck : Organism()
    object DealControl : Organism()
    object CardHand : Organism()
    object Renuncio : Organism()
    object CobrarRenuncio : Organism()
    object Vale : Organism()
    object CobrarVale : Organism()
    object AcceptPlay : Organism()
    object RejectPlay : Organism()
    object Ready : Organism()
    object StartMatch : Organism()
    object LeaveMatch : Organism()
    object TurnState : Organism()
    object Timer : Organism()
    object Scoreboard1v1 : Organism()
    object Scoreboard2v2 : Organism()
    object ScoreTable1v1 : Organism()
    object ScoreTable2v2 : Organism()
    object TeamPanel : Organism()
    object MatchInviteCard : Organism()
    object LobbyList : Organism()
    object PauseOverlay : Organism()
    object MatchHistoryList : Organism()
    object AchievementItem : Organism()
    object CapoteBanner : Organism()
    object MessageBubble : Organism()
    object MessageInput : Organism()
    object MessageList : Organism()
    object TypingIndicator : Organism()
    object AttachmentAction : Organism()
    object SupportForm : Organism()
    object FlagList : Organism()
    object Table : Organism()
    object LogList : Organism()
    object Timeline : Organism()
    object FilterChips : Organism()
    object FilterSelect : Organism()
    object Carousel : Organism()
  }

  sealed class Molecule : NodeType() {
    object IconButton : Molecule()
    object TextField : Molecule()
    object Checkbox : Molecule()
    object Toggle : Molecule()
    object ListItem : Molecule()
    object SettingsItem : Molecule()
    object Card : Molecule()
    object Banner : Molecule()
    object Snackbar : Molecule()
    object Dialog : Molecule()
    object BottomSheet : Molecule()
    object Dropdown : Molecule()
    object Select : Molecule()
    object Tabs : Molecule()
    object Accordion : Molecule()
    object Avatar : Molecule()
    object ItemCard : Molecule()
    object MessageStatus : Molecule()
    object LoginButton : Molecule()
    object RegisterButton : Molecule()
    object RecoverButton : Molecule()
    object VerifyAccountButton : Molecule()
    object LogoutButton : Molecule()
    object LanguageSelector : Molecule()
    object LanguageOption : Molecule()
    object SoundToggle : Molecule()
  }

  sealed class Atom : NodeType() {
    object Text : Atom()
    object Icon : Atom()
    object Button : Atom()
    object Fab : Atom()
    object Divider : Atom()
    object Spacer : Atom()
    object Chip : Atom()
    object Tag : Atom()
    object Badge : Atom()
    object Progress : Atom()
    object Radio : Atom()
    object SwitchSimple : Atom()
  }
}

data class Schema(
  val id: SchemaId,
  val version: SchemaVersion,
  val screenId: ScreenId,
  val root: Node
)

data class Node(
  val id: NodeId,
  val type: NodeType,
  val level: NodeLevel,
  val props: NodeProps,
  val children: List<Node> = emptyList(),
  val style: NodeStyle? = null,
  val flags: List<FlagKey> = emptyList(),
  val env: List<EnvValue> = emptyList(),
  val state: NodeState = NodeState.Enabled,
  val actions: List<NodeAction> = emptyList()
)

sealed class NodeProps

data class TextProps(
  val textKey: String
) : NodeProps()

data class TextFieldProps(
  val labelKey: String,
  val placeholderKey: String? = null,
  val state: FieldState = FieldState.Default
) : NodeProps()

data class ButtonProps(
  val labelKey: String,
  val style: ButtonStyle = ButtonStyle.Filled
) : NodeProps()

data class NodeStyle(
  val colorRole: ColorRole? = null,
  val typographyRole: TypographyRole? = null,
  val spacingRole: SpacingRole? = null,
  val shapeRole: ShapeRole? = null
)

data class NodeAction(
  val type: ActionType,
  val target: ActionTarget,
  val extras: Map<String, String> = emptyMap()
)

enum class NodeLevel { ATOM, MOLECULE, ORGANISM }
enum class NodeState { Enabled, Disabled, Focus, Pressed, Error }
enum class ActionType { Deeplink, Navigate, OpenUrl, DispatchIntent }
```

3) `NodeProps` (contrato de props tipadas)
Responsabilidad: define el tipo base para props por componente; evita props sueltas o dinamicas.

4) `TextProps`
Responsabilidad: props de texto simple; requiere `textKey` (i18n).

5) `TextFieldProps`
Responsabilidad: props de input; define label, placeholder opcional y estado visual.

6) `ButtonProps`
Responsabilidad: props de boton; define label y estilo.

Notas:
- `SchemaId`, `SchemaVersion`, `ScreenId`, `NodeId` y `FlagKey` son value objects sobre `String`.
- `NodeType` define el componente Dui a renderizar; se amplia con el catalogo completo.
- `flags` y `env` viven en el nodo porque la visibilidad puede variar por seccion dentro de una pantalla.

Responsabilidades por clase/VO:
- `Schema`: raiz del contrato SDUI ya normalizado; agrupa version, screenId y nodo raiz.
- `SchemaId`: identifica de forma unica el contrato; no es el `screenId`.
- `SchemaVersion`: permite validar compatibilidad del contrato (SemVer).
- `ScreenId`: identifica la pantalla (ej. `home`, `login`) y debe coincidir con el equivalente clasico.
- `Node`: unidad renderizable del arbol; define props, estilo, acciones y visibilidad.
- `NodeId`: identificador unico dentro del arbol para trazabilidad y logs.
- `NodeType`: define el componente Dui exacto a renderizar; esta segregado en `Organism`, `Molecule` y `Atom`.
- `NodeLevel`: nivel atomico del componente; se deriva del subtipo de `NodeType` (no se define manualmente).
- `NodeProps`: contrato base para props tipadas por `NodeType`.
- `TextProps`: props minimas para texto; solo acepta key i18n.
- `TextFieldProps`: props de input; label obligatorio y placeholder opcional.
- `ButtonProps`: props de boton; define label y estilo visual.
- `NodeStyle`: roles de estilo; nunca contiene valores crudos.
- `NodeAction`: accion asociada a un nodo interactivo.
- `ActionType`: allowlist de tipos de accion aceptados.
- `NodeState`: estado visual del nodo (enabled/disabled/focus/pressed/error).
- `EnvValue`: ambiente valido para visibilidad del nodo.
- `FlagKey`: flag requerida para habilitar el nodo.

Reglas:
- `Node.type` debe mapear 1:1 a un componente Dui.
- `Node.level` se deriva por `Node.type` y no se define manualmente en render.
- `NodeProps` es tipado por `Node.type`; props desconocidas se descartan con warning.
  
1) Diagrama lógico (texto)
   - `Schema` { `id`, `version`: SchemaVersion, `root`: Node }
   - `Node` { `id`: NodeId, `type`: NodeType, `props`, `children`: [Node], `style`, `flags`: [FlagKey], `env`: [EnvValue], `state`: NodeState }
   - `Props` según tipo de Node (ver TA-F1 contract/TA-F2 parser para detalle de campos).
   - `Style` usa roles/tokens (no valores crudos).
   - `Action` { `type`, `target`: ActionTarget, `extras`? }
2) Interfaces
   - `SduiRepository` (infra): `suspend fun fetch(schemaId: SchemaId, env: EnvValue): SchemaDto`
   - `FlagsProvider`: obtiene flags activos (local/remoto) como VO; prioriza local → remoto → default.
   - `EnvProvider`: entrega ambiente actual (mock/dev/prod) elegido en cliente.
3) Casos de uso (firma sugerida)
   - `FetchSchema(schemaId, env)` -> `SchemaDto`
   - `ParseSchema(dto, flags, env)` -> `Schema` (dominio) + errores/warnings recolectados
   - `RenderSchema(schema)` -> árbol UI (TA-F3)
4) Servicios de dominio
   - `FlagResolutionService`: combina proveedores y entrega flags activas; resuelve defaults.
   - `EnvironmentResolutionService`: entrega `EnvValue` actual (mock/dev/prod).
   - `NodeVisibilityService`: decide inclusión del nodo según flags/env.
   - `SchemaValidationService`: valida tipos/props/estructura y versionado; clasifica errores (crítico vs recuperable).
   - `SchemaNormalizationService`: aplica defaults de props/estilos/estado a nodos válidos.
   - `StyleResolutionService`: mapea roles de estilo a tokens DS; ignora valores crudos.
   - `NavigationGuardService`: valida acciones (deeplink/navigate/open_url/intent) y produce `ActionTarget` seguro.
5) Manejo de errores (dominio)
   - Errores críticos: version incompatible, tipo raíz inválido → se hace fallback a pantalla clásica equivalente y se registra en Toad.
   - Errores recuperables: props faltantes no críticas → omitir nodo/hijo y acumular warning.
   - Acoplar con Toad/State Record: logging estructurado (id/tipo/razón/severidad).

## Implementacion esperada (nivel 4)
Checklist para cuando se implemente:
- Crear clases/VO exactamente como estan definidos en este documento.
- Implementar interfaces y firmas sin cambios de nombre ni parametros.
- Agregar tests unitarios (sin emulador) para:
  - Validacion de `SchemaVersion` incompatible -> rechazo del contrato.
  - Resolucion de `NodeType` -> `NodeLevel` correcta por tipo.
  - Filtrado de nodos por `flags` y `env` (visible/oculto).
  - Normalizacion de props y defaults por tipo (text/textField/button).
  - Rechazo de `ActionType` no permitido.
  - Ignorar estilos con valores crudos y mantener roles validos.
- Base de tests KMP:
  - Ubicacion: `composeApp/src/commonTest/kotlin/...`
  - Framework: `kotlin.test` + `kotlinx.coroutines.test`.
  - Comando: `./gradlew :composeApp:commonTest`
  - Agregar target `make test` que invoque ese comando.
  - Dependencias requeridas en `commonTest.dependencies`:
    - `libs.kotlin.test`
    - `libs.jetbrains.kotlinx.coroutines.test`
    - `libs.koin.test` si el test necesita DI

Tests unitarios obligatorios (nombres sugeridos):
1) `SchemaVersionRejectsIncompatible`:
   - Given: `SchemaVersion("2.0.0")` con version soportada `1.x`.
   - Expect: rechazo del contrato + error registrado.
2) `NodeTypeMapsToLevel`:
   - Given: `NodeType.Text`, `NodeType.TextField`, `NodeType.AppScaffold`.
   - Expect: `ATOM`, `MOLECULE`, `ORGANISM` respectivamente.
3) `NodeVisibilityByFlag`:
   - Given: nodo con `flags=[FlagKey("sdui_home_enabled")]` y flags activos vacios.
   - Expect: nodo oculto.
4) `NodeVisibilityByEnv`:
   - Given: nodo con `env=[EnvValue.Dev]` y env actual `Prod`.
   - Expect: nodo oculto.
5) `DefaultPropsApplied`:
   - Given: `TextFieldProps(labelKey="email")` sin `placeholderKey`.
   - Expect: placeholder nulo y `FieldState.Default`.
6) `ActionTypeRejectsUnknown`:
   - Given: `ActionType` fuera de allowlist.
   - Expect: rechazo de accion + warning.
7) `StyleRejectsRawValues`:
   - Given: estilo con valor crudo.
   - Expect: se ignora valor crudo y se mantiene roles validos.
## Pull Request (contenido esperado)
**Titulo sugerido:** `feat(ht-sdui-f0): modelo de dominio sdui (#XX)`

**Incluye:**
- Codigo de dominio (modelos/VO/servicios).
- Tests unitarios definidos en este documento.
- Actualizacion de docs si cambia el contrato.

**Checklist:**
- [ ] Enlace a la epica `docs/03-magicsdui/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.
- [ ] `./gradlew :composeApp:commonTest` ejecuta tests unitarios SDUI.
