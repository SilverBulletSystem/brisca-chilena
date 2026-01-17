# TA-SDUI-F1 — Contrato SDUI + versionado

## Rama
- `feat/ht-sdui-f1-contract`

## Objetivo
Definir el contrato SDUI (entrada externa) con versionado, granularidad (organismo/molécula/átomo), flags/ambientes, estilos y navegación. El contrato es independiente del transporte (HTTP/GraphQL/mock); se modela como DTO externo y se traduce a un modelo de dominio SDUI antes del render. Debe ser implementable sin ambigüedad.

## Alcance
- Esquema base: nodos, layout, props, estilos, eventos, navegación.
- Versionado: `schemaVersion` global y fallback.
- Flags/ambientes: visibilidad/variantes según flag y `env` (mock/dev/prod).
- Niveles: organismo, molécula, átomo (prefijo Dui).
- Independiente del transporte: puede provenir de HTTP/GraphQL/mock; siempre se trata como DTO externo y se mapea a un modelo de dominio SDUI.
- Telemetría: campos de tagging/analytics opcionales por nodo.
- Guardrails: límites de tamaño/profundidad y assets permitidos (solo iconos/tokens locales en MVP; sin media remota).

## Diseño (doc)
1) Estructura raíz
```json
{
  "schemaVersion": "1.0.0",
  "screen": {
    "id": "home",
    "type": "screen",
    "env": ["mock","dev","prod"],
    "flags": ["home_enabled"],
    "layout": { ... organismo ... }
  }
}
```

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
- `schemaVersion` global (ej. `"1.0.0"`). Si no es soportada → fallback a pantalla de error SDUI genérica.
- `version` por nodo opcional para migraciones; si no soportada, omitir nodo o fallback seguro (error state).
- Política sugerida: aceptar misma major (1.x.x), warning en minor superior, rechazar major superior hasta soporte.

-4) Granularidad y tipos (alineado a Dui Brisca)
- Organismos (nombres lógicos, mapean a Dui*): `appScaffold`, `topBar`, `bottomNav`, `navRail`, `drawer`, `sideMenu`, `list`, `section`, `grid`, `settingsList`, `logoutDialog`, `soundSetting`, `emptyState`, `errorState`, `loadingState`; juego: `cardFace`/`naipe`, `cardDeck`/`shuffleControl`, `dealControl`, `cardHand`/`hand`/`playList`, `renuncio`, `cobrarRenuncio`, `vale`, `cobrarVale`, `acceptPlay`, `rejectPlay`, `ready`, `startMatch`, `leaveMatch`, `turnState`, `timer`/`countdown`, `scoreboard1v1`, `scoreboard2v2`, `scoreTable1v1`, `scoreTable2v2`, `teamPanel`/`playerStatus`, `matchInviteCard`, `lobbyList`/`playerSeat`, `pauseOverlay`/`resumeButton`, `matchHistoryList`, `achievementItem`, `capoteBanner`; chat/soporte: `messageBubble`, `messageInput`, `messageList`, `typingIndicator`, `attachmentAction`, `supportForm`; flags/monitores: `flagList`, `table`, `logList`, `timeline`, `filterChips`/`filterSelect`. **Selectores de ambiente/tema son organismos del cliente y quedan fuera del contrato SDUI (no provienen del backend).**
- Moléculas: `iconButton`, `textField`, `checkbox`, `toggle`, `listItem`, `settingsItem`, `card`, `banner`, `snackbar`, `dialog`/`bottomSheet`, `dropdown`/`select`, `tabs`/`filterChips`, `accordion`, `avatar`, `itemCard`, `typingIndicator`, `messageStatus`, botones identitarios (`loginButton`, `registerButton`, `recoverButton`, `verifyAccountButton`, `logoutButton`), selectores (`languageSelector`/`languageOption`, `soundToggle`).
- Átomos: `text`, `icon`, `button`, `fab`, `divider`, `spacer`, `chip`/`tag`, `badge`, `progress`, `radio`, `switchSimple`.
Cada `type` debe mapearse 1:1 a un componente Dui con prefijo al renderizar (ver TA F3 renderer).

5) Props (ejemplos por nivel, alineados a Dui)
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

## Entregables (documento)
- Esquema con campos y reglas.
- Tabla de tipos por nivel con props esperadas.
- Reglas de versionado, flags/ambientes y navegación.
- Guardrails de seguridad (allowlist de acciones, dominios permitidos), límites de payload/árbol y notas de assets soportados.

## Verificación futura
- Contrato cubre todos los componentes Dui necesarios.
- Estrategia de fallback definida para versiones no soportadas y flags/ambiente faltantes.
- Asegura desac acoplamiento a transporte (HTTP/GraphQL): contrato se consume como DTO y se traduce a modelo de dominio antes de render.
- Incluye campos de analytics opcionales y restricciones de acciones/targets/domínios.

## Alineación con Design System (Brisca)
- Tipos SDUI deben mapear 1:1 a la taxonomía Dui definida en la épica DS:
  - Átomos (ej: `text`, `icon`, `button`, `chip`, `badge`, `progress`, `radio`, `switch`).
  - Moléculas (ej: `textField`, `checkbox`, `toggle`, `listItem`, `card`, `banner`, `snackbar`, `dialog`, `dropdown/select`, `tabs/chips`, `accordion`, `avatar`, `itemCard`, botones identitarios, selectores idioma/sonido).
  - Organismos (ej: scaffold/nav bars/rail/drawer, settings list, environment selector, empty/error/loading, theme selector, logout dialog, sound setting; juego: naipe/deck/shuffle/deal/hand/actions/score/lobby/pause/history/logros; chat/soporte; flags/monitores).
- Estilos: solo roles de tokens del DS (colores, tipografía, `ThemeDimens`, shapes). Sin valores crudos en el contrato.
- Estados: usar estados soportados en DS (focus/pressed/disabled/error; hover solo Web/Desktop).
- i18n: las `props` de texto deben referenciar keys de strings (interfaces por pantalla); nunca literales.
- Navegación: las rutas/acciones deben ser compatibles con los atajos Make de DS bitácoras y SDUI (run-bitacora-*, run-sdui-sample-*).

## Entregables (documento)
- Esquema propuesto con campos y reglas.
- Tabla de tipos por nivel y ejemplos de nodos.
- Reglas de versionado y fallback.

## Verificación futura
- Contrato cubre todos los componentes Dui necesarios.
- Estrategia de fallback definida para versiones no soportadas.

## No incluido
- Implementación de Mockoon ni código.
