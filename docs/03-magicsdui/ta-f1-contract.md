# TA-SDUI-F1 — Contrato SDUI + versionado

## Navegacion
- [Volver a Epica](03-magicsdui/epica.md)
- [Volver a Backlog](03-magicsdui/backlog.md)

## Rama
- `feat/ht-sdui-f1-contract`

## Objetivo
Definir el contrato SDUI (entrada externa) con versionado, granularidad (organismo/molécula/átomo), flags/ambientes, estilos y navegación. El contrato es independiente del transporte (HTTP/GraphQL/mock) y no depende de JSON; se modela como DTO externo y se traduce a un modelo de dominio SDUI antes del render. Debe ser implementable sin ambigüedad.

## Alcance
- Esquema base: nodos, layout, props, estilos, eventos, navegación.
- Versionado: `schemaVersion` global y fallback.
- Flags/ambientes: visibilidad/variantes según flag y `env` (mock/dev/prod).
- Niveles: organismo, molécula, átomo (prefijo Dui).
- Independiente del transporte: puede provenir de HTTP/GraphQL/mock; siempre se trata como DTO externo y se mapea a un modelo de dominio SDUI.
- Telemetría: campos de tagging/analytics opcionales por nodo.
- Guardrails: límites de tamaño/profundidad y assets permitidos (solo iconos/tokens locales en MVP; sin media remota).

## Diseño (doc)
1) Interfaces del contrato (DTO externo)
Nota DDD: el contrato externo es DTO y vive fuera de dominio. En dominio solo van Entity/VO.
Package sugerido (data/contract): `composeApp/src/commonMain/kotlin/cl/silverbullet/multiplatform/brisca/magicsdui/data/dto`

```kotlin
data class SduiScreenDto(
  val schemaVersion: String,
  val screenId: String,
  val root: SduiNodeDto
)

data class SduiNodeDto(
  val id: String,
  val type: String,
  val props: Map<String, Any?>,
  val children: List<SduiNodeDto> = emptyList(),
  val flags: List<String> = emptyList(),
  val env: List<String> = emptyList(),
  val style: Map<String, String> = emptyMap(),
  val state: String? = null,
  val actions: List<SduiActionDto> = emptyList()
)

data class SduiActionDto(
  val type: String,
  val target: String,
  val extras: Map<String, String> = emptyMap()
)
```

Responsabilidades:
- `SduiScreenDto`: contrato de una pantalla completa (version + screenId + nodo raiz).
- `SduiNodeDto`: nodo generico sin tipado fuerte; se valida y transforma en dominio.
- `SduiActionDto`: accion declarada por el contrato (allowlist en parser).

1.1) Mapping DTO -> Entity (paso a paso)
- `SduiScreenDto.schemaVersion` -> `SchemaVersion`
- `SduiScreenDto.screenId` -> `ScreenId`
- `SduiScreenDto.root` -> `Node` (via parser)
- `SduiNodeDto.id` -> `NodeId`
- `SduiNodeDto.type` -> `NodeType` (segregado: Organism/Molecule/Atom)
- `SduiNodeDto.props` -> `NodeProps` tipado (TextProps/TextFieldProps/ButtonProps)
- `SduiNodeDto.children` -> `List<Node>`
- `SduiNodeDto.flags` -> `List<FlagKey>`
- `SduiNodeDto.env` -> `List<EnvValue>`
- `SduiNodeDto.style` -> `NodeStyle` (roles)
- `SduiNodeDto.actions` -> `List<NodeAction>`

2) Campos obligatorios por nodo
- `id`: string único en el árbol.
- `type`: enum según nivel.
- `version`: opcional por nodo (para futuras migraciones).
- `env`: opcional array `["mock","dev","prod"]`; si no aplica, mostrar.
- `flags`: opcional array de flags requeridas; si faltan, ocultar nodo.
- `props`: objeto según tipo.
- `children`: array cuando aplique (layouts, listas, containers).
- `style`: opcional; roles de color/tipografía/dimens; resolver via tokens Dui.
- `analytics`: opcional; `{ "tag": "stringKey|eventName", "attributes": { ... } }` para trazabilidad.

3) Versionado
- `schemaVersion` global (ej. `"1.0.0"`). Si no es soportada → fallback a pantalla clásica equivalente.
- `version` por nodo opcional para migraciones; si no soportada, omitir nodo o fallback seguro (error state).
- Política sugerida: aceptar misma major (1.x.x), warning en minor superior, rechazar major superior hasta soporte.

4) Granularidad y tipos (alineado a Dui Brisca)
- Organismos (nombres lógicos, mapean a Dui*): `appScaffold`, `topBar`, `bottomNav`, `navRail`, `drawer`, `sideMenu`, `list`, `section`, `grid`, `settingsList`, `logoutDialog`, `soundSetting`, `emptyState`, `errorState`, `loadingState`; juego: `cardFace`/`naipe`, `cardDeck`/`shuffleControl`, `dealControl`, `cardHand`/`hand`/`playList`, `renuncio`, `cobrarRenuncio`, `vale`, `cobrarVale`, `acceptPlay`, `rejectPlay`, `ready`, `startMatch`, `leaveMatch`, `turnState`, `timer`/`countdown`, `scoreboard1v1`, `scoreboard2v2`, `scoreTable1v1`, `scoreTable2v2`, `teamPanel`/`playerStatus`, `matchInviteCard`, `lobbyList`/`playerSeat`, `pauseOverlay`/`resumeButton`, `matchHistoryList`, `achievementItem`, `capoteBanner`; chat/soporte: `messageBubble`, `messageInput`, `messageList`, `typingIndicator`, `attachmentAction`, `supportForm`; flags/monitores: `flagList`, `table`, `logList`, `timeline`, `filterChips`/`filterSelect`. **Selectores de ambiente/tema son organismos del cliente y quedan fuera del contrato SDUI (no provienen del backend).**
- Moléculas: `iconButton`, `textField`, `checkbox`, `toggle`, `listItem`, `settingsItem`, `card`, `banner`, `snackbar`, `dialog`/`bottomSheet`, `dropdown`/`select`, `tabs`/`filterChips`, `accordion`, `avatar`, `itemCard`, `typingIndicator`, `messageStatus`, botones identitarios (`loginButton`, `registerButton`, `recoverButton`, `verifyAccountButton`, `logoutButton`), selectores (`languageSelector`/`languageOption`, `soundToggle`).
- Átomos: `text`, `icon`, `button`, `fab`, `divider`, `spacer`, `chip`/`tag`, `badge`, `progress`, `radio`, `switchSimple`.
Cada `type` debe mapearse 1:1 a un componente Dui con prefijo al renderizar (ver TA F3 renderer).

5) Props (ejemplos por nivel, alineados a Dui; ejemplos en JSON solo ilustrativos)
- `button` (DuiButton): `{ "label": "stringKey", "icon": "iconName?", "style": "filled|tonal|outlined|text", "size": "s|m|l", "action": { "type": "deeplink", "target": "app://..." } }`
- `textField` (DuiTextField): `{ "label": "stringKey", "placeholder": "stringKey", "leadingIcon": "iconName?", "state": "default|error", "helperText": "stringKey?" }`
- `card/listItem` (DuiCard/DuiListItem): `{ "title": "stringKey", "subtitle": "stringKey?", "icon": "iconName?", "trailing": "stringKey?|badge?", "action": { ... } }`
- `list/section/grid` (DuiList/DuiSection/DuiGrid): `{ "orientation": "vertical|horizontal"?, "items": [ ...children... ], "spacingRole": "md|lg" }`
- `toggle/checkbox` (DuiToggle/DuiCheckbox): `{ "label": "stringKey", "checked": true|false, "helperText": "stringKey?" }`
- `tabs/filterChips` (DuiTabs/DuiFilterChips): `{ "options": [ { "id": "...", "label": "stringKey", "selected": true|false } ], "multiSelect": true|false }`
- Juegos:
  - `scoreboard1v1`: `{ "teamA": {"name":"stringKey","score":0}, "teamB": {...}, "set":"stringKey?" }`
  - `scoreboard2v2`: `{ "teamA": {"players":[...],"score":0}, "teamB": {...} }`
  - `scoreTable1v1|2v2`: `{ "rows":[{"label":"stringKey","value":"stringKey|number"}] }`
  - `cardFace`: `{ "suit":"oros|copas|espadas|bastos", "rank":"as|2|...|rey", "faceDown": false }`
  - `cardHand`: `{ "cards":[{...cardFace...}], "owner":"stringKey?" }`
  - `shuffleControl`: `{ "label":"stringKey","action":{"type":"event","target":"shuffle"} }`
  - `dealControl`: `{ "label":"stringKey","action":{"type":"event","target":"deal"} }`
  - `actionRenuncio|CobrarRenuncio|Vale|CobrarVale|AcceptPlay|RejectPlay|Ready|StartMatch|LeaveMatch`: `{ "label":"stringKey","action":{"type":"event","target":"renuncio" | ...} }`
  - `turnState`: `{ "current":"playerId","team":"teamId?","phase":"stringKey?" }`
  - `timer`: `{ "seconds":30, "countDirection":"down" }`
  - `lobbyList/playerSeat`: `{ "players":[{"id":"...","name":"stringKey","status":"ready|waiting"}] }`
  - `matchInviteCard`: `{ "host":"stringKey","mode":"1v1|2v2","actions":{"accept":{...},"reject":{...}} }`
  - `matchHistoryList`: `{ "items":[{"rival":"stringKey","result":"win|lose|draw","score":"stringKey","date":"string"}] }`
  - `achievementItem`: `{ "title":"stringKey","description":"stringKey","badge":"stringKey?" }`
  - `capoteBanner`: `{ "label":"stringKey","highlight":"stringKey?" }`
- Chat/soporte:
  - `messageBubble`: `{ "author":"self|other","text":"stringKey","status":"sent|read","timestamp":"string" }`
  - `messageInput`: `{ "placeholder":"stringKey","sendAction":{"type":"event","target":"sendMessage"} }`
  - `messageList`: `{ "items":[...messageBubble...] }`
  - `typingIndicator`: `{ "visible":true|false }`
  - `attachmentAction`: `{ "label":"stringKey","action":{"type":"event","target":"addAttachment"} }`
  - `supportForm`: `{ "fields":[{"id":"...","label":"stringKey","type":"text|select","required":true|false}] }`

6) Estilos
- `style` usa roles: `colorRole`, `typographyRole`, `spacingRole`, `shapeRole`; no valores crudos. Se resuelve vía tokens del DS.
- Estados: `state` opcional (`enabled|disabled|focus|pressed|error`); hover solo Web/Desktop.

7) Flags y ambiente
- Nodo visible si `env` contiene el ambiente actual Y todas las `flags` están activas; de lo contrario se omite.
- Para variantes: permitir `variants` por flag/ambiente (opcional) documentado como lista de overrides.

8) Navegación
- `action`: `{ "type": "deeplink"|"navigate"|"open_url"|"dispatch_intent", "target": "app://route" }`.
- Rutas internas deben mapear a Decompose/nav; documentar que Make shortcuts pueden apuntar a estas rutas.
- Allowlist de `action.type`: solo `deeplink|navigate|open_url|dispatch_intent`; `open_url` debe validar dominio permitido (policy en NavigationGuard).

9) Ejemplos de nodos
- Botón con flag:
```json
{ "id":"cta-login","type":"button","flags":["login_enabled"],"props":{"label":"login_cta","style":"primary","action":{"type":"deeplink","target":"app://auth/login"}} }
```
- Layout con lista:
```json
{ "id":"list-news","type":"list","children":[ { "type":"card", "id":"news-1", "props":{ "title":"news_title_1", "action":{"type":"deeplink","target":"app://news/1"} } } ] }
```

## Alineación con Design System (Brisca)
- Tipos SDUI deben mapear 1:1 a la taxonomía Dui definida en la épica DS:
  - Átomos (ej: `text`, `icon`, `button`, `chip`, `badge`, `progress`, `radio`, `switch`).
  - Moléculas (ej: `textField`, `checkbox`, `toggle`, `listItem`, `card`, `banner`, `snackbar`, `dialog`, `dropdown/select`, `tabs/chips`, `accordion`, `avatar`, `itemCard`, botones identitarios, selectores idioma/sonido).
  - Organismos (ej: scaffold/nav bars/rail/drawer, settings list, environment selector, empty/error/loading, theme selector, logout dialog, sound setting; juego: naipe/deck/shuffle/deal/hand/actions/score/lobby/pause/history/logros; chat/soporte; flags/monitores).
- Estilos: solo roles de tokens del DS (colores, tipografía, `ThemeDimens`, shapes). Sin valores crudos en el contrato.
- Estados: usar estados soportados en DS (focus/pressed/disabled/error; hover solo Web/Desktop).
- i18n: las `props` de texto deben referenciar keys de strings (interfaces por pantalla); nunca literales.
- Navegación: las rutas/acciones deben ser compatibles con los atajos Make de DS bitácoras y SDUI (run-bitacora-*, run-sdui-screen-*).

## Implementacion esperada (nivel 4)
Checklist para cuando se implemente:
- Crear DTOs exactamente como estan definidos en este documento.
- Implementar el mapping DTO -> Entity sin cambios de nombres.

## Pull Request (contenido esperado)
**Titulo sugerido:** `feat(ht-sdui-f1): contrato sdui (#XX)`

**Incluye:**
- DTOs del contrato y validaciones de campos.
- Actualizacion de docs si cambia el contrato.

**Checklist:**
- [ ] Enlace a la epica `docs/03-magicsdui/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.
 - [ ] `./gradlew :composeApp:commonTest` ejecuta tests unitarios SDUI.