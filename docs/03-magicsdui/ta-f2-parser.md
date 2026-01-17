# TA-SDUI-F2 — Parser/validador SDUI

## Rama
- `feat/ht-sdui-f2-parser`

## Objetivo
Diseñar el parseo y validación del contrato SDUI, con reglas concretas de tipado, defaults, resolución de flags/ambientes, logging y fallback seguro. Debe ser lo suficientemente detallado para que un dev implemente sin ambigüedad. El parser convierte DTO externos (independientes de transporte HTTP/GraphQL/mock) a un modelo de dominio SDUI (AST) que consume el renderer.

## Alcance
- Reglas de tipado/estructura/props/layout por nivel (organismo/molécula/átomo) alineado al DS Dui.
- Validación de `schemaVersion`, `type`, `props`, `children`, `env`, `flags`, `style`.
- Defaults de props (texto, botones, listas) y estados.
- Resolución de flags/ambiente para visibilidad/variantes.
- Estrategia de fallback y logging (para Toad/State Record).
- Independencia de transporte: el parser no depende de cómo llegue el DTO; recibe el payload ya deserializado (HTTP/GraphQL/mock) y lo mapea a dominio.
- Guardrails: límites de tamaño/profundidad del árbol, número de nodos y peso de payload; política ante exceso (fallback a pantalla de error SDUI). Sugerencia: profundidad ≤ 25, nodos ≤ 500, payload ≤ 200 KB.
- Validación de acciones: allowlist de `action.type` y validación de dominios para `open_url` (via NavigationGuard).
- i18n: manejo de claves faltantes (mostrar key y log warning) y ausencia de traducción de locale.
- Accesibilidad mínima: roles/estilos deben mapear a tokens con contraste/touch target definidos en DS; log warning si faltan roles.
- Caching/offline: soporte a fixture/local payload como fuente (sin mockserver) y reutilización del último schema válido si falla el fetch.

## Diseño (doc)
1) Versionado
   - Rechazar `schemaVersion` no soportada → devolver pantalla SDUI de error estándar.
   - Aceptar `schemaVersion` conocida (ej. 1.x). `version` por nodo opcional: si no soportada, omitir nodo y loggear warning.

2) Tipos permitidos (alineado a Dui Brisca)
   - Organismos: `appScaffold`, `topBar`, `bottomNav`, `navRail`, `drawer`, `sideMenu`, `list`, `section`, `grid`, `settingsList`, `logoutDialog`, `soundSetting`, `emptyState`, `errorState`, `loadingState`, juego (deck/shuffle/deal/hand/actions/score/turn/lobby/history/logros/capote), chat/soporte, flags/monitores (ver TA F1).
   - Moléculas: `iconButton`, `textField`, `checkbox`, `toggle`, `listItem`, `settingsItem`, `card`, `banner`, `snackbar`, `dialog`/`bottomSheet`, `dropdown`/`select`, `tabs`/`filterChips`, `accordion`, `avatar`, `itemCard`, `typingIndicator`, `messageStatus`, botones identitarios, selectores idioma/sonido.
   - Átomos: `text`, `icon`, `button`, `fab`, `divider`, `spacer`, `chip`/`tag`, `badge`, `progress`, `radio`, `switchSimple`.

3) Validación de props (ejemplos)
   - `button`: requiere `label`, opcional `icon`, `style` ∈ {filled, tonal, outlined, text}, `size` ∈ {s,m,l}, `action`.
   - `textField`: requiere `label`; opcional `placeholder`, `leadingIcon`, `state` ∈ {default,error}, `helperText`.
   - `list/section/grid`: `items` no vacío, `orientation` si aplica, `spacingRole`.
   - Juego: `cardFace` requiere `suit` y `rank`; `scoreboard1v1` requiere teams; acciones (`renuncio`, etc.) requieren `label` y `action`.
   - Chat: `messageBubble` requiere `author` ∈ {self,other}, `text`, `status` ∈ {sent,read}.

4) Defaults
   - Textos: si falta `label` → error crítico (no se puede renderizar sin i18n).
   - Estilos: si falta `style`, usar rol por defecto del DS (p.ej. button filled).
   - Layout: `spacingRole` por defecto `md`; orientación por defecto vertical.
   - Estados: por defecto `enabled`.

5) Flags/ambientes
   - Si `env` no incluye el ambiente actual → omitir nodo.
   - Si `flags` incluye alguna no activa → omitir nodo.
   - Variantes por flag/ambiente (si se modelan) → aplicar override, si falla, usar base.

6) Errores y fallback
   - Críticos (rompen render completo): `schemaVersion` no soportada, tipo desconocido en raíz/organismo, props obligatorias ausentes en nodos de layout principales → mostrar pantalla de error SDUI.
   - Recuperables: tipo desconocido en hijos, props faltantes no críticas → omitir nodo/prop y loggear warning.
   - Logging estructurado (Toad/State Record): incluir ruta/nodo/id/tipo/razón/error; categorizar en `error` o `warning`.

7) AST/DTO intermedio
   - Normalizar nodos a un AST con:
     - `type`, `id`, `resolvedProps` (defaults aplicados), `children` validados, `styleResolved` (roles/tokens), `visibility` (true/false tras flags/env), `state`.
   - Este AST lo consume el renderer (TA F3).

8) i18n y estilos
   - Validar que campos de texto sean keys (no literales).
   - `style` solo acepta roles/tokens, no valores crudos; si llegan valores crudos, ignorar y loggear warning.
   - Preservar `analytics` tag/attributes si vienen; si faltan, continuar sin bloquear.

9) Navegación
   - Validar `action` con tipos permitidos: `deeplink|navigate|open_url|dispatch_intent`; `target` no vacío.
   - Las rutas deben ser compatibles con los atajos Make documentados (bitácoras SDUI/DS).
10) Guardrails cuantitativos (doc)
    - Límites sugeridos: profundidad máxima 25, máximo 500 nodos, tamaño de payload ≤ 200 KB.
    - Si se exceden, marcar AST como truncado y devolver pantalla de error SDUI; loggear error crítico.

## Entregables (documento)
- Lista de validaciones y defaults.
- Estrategia de errores/fallback y logging.
- Estructura de salida intermedia.

## Verificación futura
- Parser diseñado para casos felices y borde del contrato.

## No incluido
- Implementación de parser en código.
