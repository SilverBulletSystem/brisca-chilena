# TA-TOAD-F5 — Visor/monitor in-app

## Rama
- `feat/ht-toad-f5-viewer`

## Objetivo
Diseñar la UI in-app para monitorear trazas externas: lista y detalle, con filtros y correlación hacia Inkribbon, usando componentes Dui e i18n estricto.

## Alcance
- Lista de trazas: mostrar método, host+path, status/duración, severidad; orden por fecha desc.
- Filtros: por status class (2xx/4xx/5xx), método, rango de tiempo, ambiente.
- Detalle: headers sanitizados, payload truncado (8 KB) + hash, `CorrelationId`/`SessionId` (link a visor de Inkribbon).
- Componentes Dui: listas, chips de filtros, badges de status, timelines/loglist.
- Accesibilidad: focus/roles/contrast; touch target >=48dp; sin strings hardcode.

## Diseño (doc)
1) Rutas/deeplinks
   - `app://toad/monitor`
   - `app://toad/monitor/{traceId}`
2) Layout sugerido
   - Lista: `DuiList` con items (método badge, url corta, status badge, duración).
   - Detalle: secciones para request/response; muestra hashes y truncado; link a sesión si existe.
3) Estados vacíos/errores
   - Empty state cuando no hay trazas; error state si falla lectura.

## Entregables (documento)
- Flujos, rutas, componentes DS y requisitos de accesibilidad/i18n.

## Verificación futura
- Monitor cubre lista+detalle; rutas definidas; integra correlación visible. 
