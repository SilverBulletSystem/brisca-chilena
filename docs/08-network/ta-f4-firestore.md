# TA-NET-F4 — Firestore mínimo (lectura)

## Rama
- `feat/ht-net-f4-firestore`

## Objetivo
Documentar el cliente Firestore mínimo (lectura-only) para flags/config, configurado por ambiente. Debe alinearse a dominio (no acoplar UI) y respetar timeouts/retry/sanitización.

## Alcance
- Config por env: proyecto/colecciones, credenciales, base URL si aplica.
- Operaciones: lectura de documentos/colecciones específicas (flags/config); sin escritura.
- Timeouts/retry/backoff alineados a `NetworkConfig`.
- Cache de red (si SDK soporta) y manejo de errores (fallo → usar proveedor local/TTL).
- Seguridad: sin PII; no exponer credenciales en logs; sanitizar errores.

## Diseño (doc)
1) Inicialización
   - Recibe `EnvValue` y config derivada de `EnvConfig`/`NetworkConfig`.
2) Errores
   - Network/auth: log warning, fallback a local/default.
3) Integración
   - Consumido por proveedor remoto de flags; no expone detalles a UI/SDUI.

## Entregables (documento)
- Configuración requerida por env y reglas de operación.

## Verificación futura
- Cliente definido para implementación mínima. 
