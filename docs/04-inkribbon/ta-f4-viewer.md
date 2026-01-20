# TA-INK-F4 — Visor in-app (lista/detalle)

## Navegacion
- [Volver a Epica](04-inkribbon/epica.md)
- [Volver a Backlog](04-inkribbon/backlog.md)

## Rama
- `feat/ht-ink-f4-viewer`

## Objetivo
Diseñar la UI in-app para explorar State Record: lista de sesiones y detalle con timeline de eventos, usando componentes Dui y i18n estricto.

## Alcance
- Lista de sesiones: orden por `startedAt` desc, mostrar fecha/hora, duración estimada, #eventos, ambiente.
- Filtros mínimos: por ambiente (mock/dev/prod) y por severidad (warning/error).
- Detalle de sesión: timeline con navegación/render/acciones/errores; permite tap para ver propsSummary/redactada.
- Componentes Dui: reutilizar moléculas/listas/badges; sin strings hardcode (usar interfaces de strings).
- Accesibilidad: focus/roles/contrast; touch target >=48dp.
- Performance: paginación o carga lazada si eventos > N.

## Diseño (doc)
1) Rutas y deeplinks
   - `app://inkribbon/viewer` abre lista.
   - `app://inkribbon/viewer/{sessionId}` abre detalle.
2) Layout sugerido
   - Lista: `DuiList` de tarjetas resumen; badges para severidad; chip de ambiente.
   - Detalle: `DuiTimeline`/`DuiLogList` (DS) con timestamp, tipo, screenId/schemaId, acción, severidad.
3) Interacción
   - Tap en evento muestra propsSummary redacted (modal/sheet).
   - Botón para copiar `CorrelationId` (si existe) para correlacionar con External Record.
4) Estados vacíos/errores
   - Empty state si no hay sesiones; error state si falla lectura (usando organismos DS).

## Entregables (documento)
- Flujos, rutas, componentes DS utilizados y requisitos de accesibilidad/i18n.

## Verificación futura
- UI cubre lista y detalle; rutas definidas; sin hardcode de strings; compatibilidad con Make (F6).

## No incluido
- Implementación ni wiring de datos reales. 