# TA-FLAGS-F1 — Proveedores (local JSON / Firestore)

## Navegacion
- [Volver a Epica](06-flags/epica.md)
- [Volver a Backlog](06-flags/backlog.md)

## Rama
- `feat/ht-flags-f1-providers`

## Objetivo
Documentar los proveedores de flags: local JSON por flavor/ambiente y remoto Firestore (lectura-only), ambos detrás de la interfaz de dominio `FlagsProvider`. Definir esquema, errores, prioridades y redacción de datos sensibles.

## Alcance
- Proveedor local:
  - Archivo JSON por flavor/ambiente (mock/dev/prod), ruta acordada en recursos.
  - Esquema (SemVer obligatorio): `{ "version": "1.0.0", "flags": { "flag_key": { "value": <typed>, "type": "bool|int|double|string" } } }`
  - El campo `version` sigue SemVer (major.minor.patch); versiones no compatibles deben ser rechazadas con warning y fallback.
  - Validación de tipos, manejo de ausentes; log warnings.
- Proveedor Firestore:
  - Colección/paths por ambiente; solo lectura.
  - Cache de red (si aplica) y backoff en errores.
  - Sanitización: no almacenar PII; solo keys/values.
- Prioridad y merge:
  - Orden: local → remote → default.
  - Si remote falla: usar local + defaults, log error.
- Límites sugeridos:
  - Tamaño máximo de payload flags: 100 KB.
  - TTL remoto: 24h; TTL local: hasta siguiente release o cambio de archivo.

## Diseño (doc)
1) Interfaces
   - `FlagsProvider.fetch(env): Result<FlagSnapshotMap>`
2) Errores
   - Network/Firestore: reintentos limitados; log; no bloquear app.
   - JSON inválido: descartar y usar defaults/local válido.
3) Configuración por ambiente
   - Base paths por flavor, configurable vía build config/Settings.

## Entregables (documento)
- Contrato de los proveedores, esquema JSON, paths por ambiente, manejo de errores y límites.

## Verificación futura
- Proveedores definidos y alineados con dominio; prioridad clara. 