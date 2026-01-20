# TA-ENV-F1 — Persistencia/config de ambientes

## Navegacion
- [Volver a Epica](07-environments/epica.md)
- [Volver a Backlog](07-environments/backlog.md)

## Rama
- `feat/ht-env-f1-persistence`

## Objetivo
Diseñar la persistencia del ambiente seleccionado y la configuración estática por env (mock/dev/prod) usando Settings MP y carga temprana.

## Alcance
- Persistencia:
  - Guardar `EnvValue` y `EnvSource` en Settings MP.
  - Cargar en startup y entregar a `EnvResolver`.
- Config estática:
  - Tabla de `EnvConfig` por env (baseUrlKtor, endpoints flags/SDUI, firestoreProject si aplica).
  - Versionar la tabla; sin edición en runtime.
- Guard para release prod:
  - En builds prod release, bloquear cambios o esconder UI de selector.

## Entregables (documento)
- Estructura de storage, claves Settings, tabla de configs por env y reglas de carga.

## Verificación futura
- Persistencia clara, configs definidas y política de guard documentada. 