# TA-FLAGS-F3 — Monitor UI (read-only)

## Rama
- `feat/ht-flags-f3-monitor`

## Objetivo
Diseñar la UI in-app de monitor de flags (lectura/inspección), con filtros y detalles de fuente/ambiente, usando componentes Dui e i18n estricto.

## Alcance
- Lista de flags: key, valor, tipo, fuente (local/remote/default), timestamp, env.
- Filtros: por fuente, por ambiente, búsqueda por key (case-insensitive).
- Orden: por key asc o timestamp desc (toggle).
- Detalle: muestra valor y metadata; sin edición en MVP.
- Componentes Dui: listas, chips, badges, selectores, estados vacíos/errores (todos prefijo Dui).
- Accesibilidad: focus/roles/contraste; touch target >=48dp; soporte screen reader (labels/roles).

## Diseño (doc)
1) Rutas/deeplinks
   - `app://flags/monitor`
2) Layout sugerido
   - Contenedor: `DuiAppScaffold` con `DuiTopBar` (título/i18n) y contenido scroll.
   - Barra de filtros:
     - Filtro fuente: `DuiFilterChips` con opciones local/remote/default.
     - Filtro ambiente: `DuiSelect` (mock/dev/prod) — client-side, no SDUI.
     - Búsqueda: `DuiTextField` (debounce) para key.
     - Orden: `DuiTabs` o `DuiFilterChips` (key asc / timestamp desc).
   - Lista: `DuiList` de `DuiListItem`:
     - Título: key.
     - Subtitle: tipo y fuente (badge `DuiBadge` con color por fuente).
     - Trailing: valor resumido (bool → `DuiToggle` disabled; num/text → texto truncado).
     - Metadata inline: timestamp relativo (`hace 5m`) y env.
   - Empty/Error: organismos DS `DuiEmptyState`/`DuiErrorState` con CTA retry (llama a refresh).
3) Datos mostrados
   - Valor renderizado según tipo:
     - bool → `DuiToggle` disabled con label de fuente.
     - num/text → `DuiText` con truncado (p.ej. 24 chars) y tooltip/long-press opcional.
   - Fuente: badge con color role (local: info, remote: primary, default: neutral).
   - Timestamps: mostrar relativo y absolute en tooltip/secondary text.
   - Env actual visible (mock/dev/prod).
4) Interacción
   - Filtros aplican client-side sobre snapshot ya resuelto (sin llamadas nuevas).
   - Botón de refresh (en `DuiTopBar` o `DuiIconButton`) dispara `refreshFlags`.
   - Tap en item abre sheet/modal `DuiBottomSheet` con detalle (key, tipo, valor completo, fuente, fetchedAt, ttl, env).
5) Estados
   - Loading inicial: `DuiLoading` inline.
   - Empty: sin flags o filtro sin resultados.
   - Error: fallo al cargar snapshot (mostrar mensaje i18n, botón retry).
6) Observabilidad
   - Loggear en Toad: acciones de refresh, errores de carga, filtros aplicados, flags faltantes.
7) i18n y accesibilidad
   - Todas las strings via interfaz; sin literales.
   - Labels accesibles en filtros y toggles; focus visible; contraste roles DS.
8) Seguridad
   - No exponer contenido sensible (solo keys/valores de flags); sin PII; valores truncados si son largos (p.ej. >64 chars).
3) Datos mostrados
   - Valor renderizado según tipo (bool → toggle disabled, numérico/texto).
   - Timestamps (relative) y fuente.

## Entregables (documento)
- Flujos, rutas, componentes DS y requisitos de accesibilidad/i18n.

## Verificación futura
- Monitor claro, read-only, sin hardcode de strings. 
