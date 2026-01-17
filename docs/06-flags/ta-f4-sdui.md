# TA-FLAGS-F4 — Integración con SDUI/DS

## Rama
- `feat/ht-flags-f4-sdui`

## Objetivo
Documentar cómo las flags se consumen en SDUI/DS: prefetch, resolución por nodo, fallbacks y logging, manteniendo desac acoplamiento a proveedores.

## Alcance
- Prefetch inicial de set mínimo de flags al iniciar app/SDUI.
- Resolver flags por nodo en parser (MagicsDui) usando `FlagResolver`.
- Fallback: flag faltante → default; log warning.
- Cache consultada antes de ir a proveedores; sin llamadas por nodo (se usa snapshot resuelto).
- Hooks de logging hacia Toad/State Record cuando falta flag requerida o tipo no coincide.
- Ambiente: resolver según `EnvValue` actual (selector de ambientes cliente).

## Diseño (doc)
1) Flujo
   - App start: `RefreshFlags(env)` → snapshot en cache.
   - Parser SDUI recibe snapshot y decide visibilidad/variantes.
   - Renderer no vuelve a consultar flags.
2) Errores
   - Flag no definida: usar default; warning.
   - Tipo incorrecto: ignorar valor y usar default; warning.
3) No acople
   - SDUI no conoce Firestore/JSON; solo usa dominio y snapshot.

## Entregables (documento)
- Flujo de uso de flags en SDUI/DS y reglas de fallback/logging.

## Verificación futura
- Integración clara y sin acople a proveedores. 
