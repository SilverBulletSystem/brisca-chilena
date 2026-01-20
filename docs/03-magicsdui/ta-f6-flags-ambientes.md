# TA-SDUI-F6 — Flags/ambientes/inyección de datos

## Navegacion
- [Volver a Epica](03-magicsdui/epica.md)
- [Volver a Backlog](03-magicsdui/backlog.md)

## Rama
- `feat/ht-sdui-f6-flags`

## Objetivo
Documentar cómo SDUI consume flags y ambiente (mock/dev/prod), integra proveedores y maneja caching/resolución de datos.

## Alcance
- Resolución de flags: interfaz de dominio, múltiples proveedores (JSON local, Firestore u otros), prioridad y fallback.
- Ambiente: mock/dev/prod (seleccionado en cliente), cómo afecta base URL/endpoints SDUI.
- Caching/fetch: orden de lectura, invalidación, tiempos de vida (doc).
- Independencia de transporte: flags/ambiente no dependen del origen (HTTP/GraphQL); se resuelven en dominio antes de render.
- Offline/fixtures: permitir usar último schema válido o fixture local si el fetch falla.

## Diseño (doc)
1) Flags
   - Interfaz de dominio: `FlagsProvider` con métodos sync/async para obtener valores por key.
   - Prioridad: primero JSON local por flavor, luego remoto (Firestore u otro), fallback a default definido en contrato o app.
   - Comportamiento: si flag requerida falta o es false → nodo oculto; variantes opcionales aplican overrides si flag true.
   - Logging: cuando falte flag requerida, log warning.
2) Ambientes
   - Ambiente actual viene del cliente (selector de ambientes, fuera de SDUI). SDUI usa `env` para decidir si muestra nodos y para elegir base URL de fetch del JSON (Mockoon para mock; endpoints dev/prod para otros).
   - Documentar cómo se mapearía `FLAVOR` → base URL (mock→Mockoon, dev/prod→ futuros backends).
3) Datos/caching
   - Orden de resolución: cache en memoria → cache local (opcional) → fetch remoto según ambiente.
   - TTL sugerido y momento de invalidación (doc, sin implementar): p.ej. invalidar al cambiar de ambiente o cada lanzamiento.
   - Retry/backoff documentado para fetch remoto; límite de reintentos y luego fallback.
   - Si fetch falla: usar última versión cacheada o default seguro (layout mínimo o estado de error SDUI).
4) Integración con SDUI
   - Parser usa flags/ambiente para decidir visibilidad/variantes antes de render.
   - Renderer no vuelve a consultar flags; trabaja con árbol ya filtrado.
   - Contrato SDUI puede incluir `flags` y `env` por nodo; la app resuelve con este flujo.
   - Flags/ambiente se obtienen vía interfaces de dominio; el origen (HTTP/GraphQL) es transparente al parser.
   - En falla de red/origen, usar cache/último schema válido como fallback documentado.

## Entregables (documento)
- Flujo de resolución de flags y ambiente.
- Puntos de integración con proveedores y selector de ambientes.

## Verificación futura
- Claridad de cómo SDUI decide flags/ambiente y qué hacer si faltan datos.
- Prioridad de proveedores y fallback documentados.
- Impacto en base URL/endpoints según ambiente definido.

## No incluido
- Implementación de proveedores ni de caching.