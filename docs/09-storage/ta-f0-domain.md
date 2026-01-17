# TA-STORAGE-F0 — Dominio/contratos Storage (DDD)

## Rama
- `feat/ht-storage-f0-domain`

## Objetivo
Definir contratos de dominio para preferencias (Settings MP) y base de datos (SQDelight) sin acoplar a las implementaciones. Servirá de base para wrappers, config y DI.

## Alcance
- Entidades/VO:
  - `PrefsKey`, `PrefsValue` (bool/int/double/string), `StoragePath`, `DbName`, `EnvValue`.
  - `DbConfig`: nombre, ruta, versión, migración mínima.
- Servicios (interfaces):
  - `PrefsProvider`: get/set con typing seguro y namespaces por módulo.
  - `DatabaseProvider`: abre instancia de DB según `DbConfig`.
  - `MigrationPolicy`: define versión inicial y manejo de upgrades.
- Casos de uso:
  - `GetPref(key, default)` / `SetPref(key, value)`.
  - `OpenDatabase(config)` -> instancia de DB (infra concreta).
- Reglas
  - Dominio no conoce Settings ni SQL; solo contratos.
  - Claves de prefs centralizadas; evitar colisiones entre módulos.

## Entregables (documento)
- Definición de VO/servicios/casos de uso y reglas de namespacing/migración.

## Verificación futura
- Contratos listos para wrappers e inicialización multiplataforma.

## No incluido
- Implementación de Settings/SQDelight. 
