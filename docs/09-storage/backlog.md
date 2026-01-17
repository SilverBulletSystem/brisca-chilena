# Backlog técnico — Épica 9 Storage

Referencia: `09-storage/epica.md`. Solo documentación, sin implementación.

## F0 – Dominio/contratos
- Entidades/VO: `StoragePath`, `DbName`, `EnvValue`, `PrefsKey`, `PrefsValue`, `DbConfig`.
- Servicios: `PrefsProvider` (interface), `DatabaseProvider` (interface), `MigrationPolicy`.
- Casos de uso: `GetPref/SetPref`, `OpenDatabase(env)`.
- Rama: `feat/ht-storage-f0-domain`.

## F1 – Settings wrapper
- Wrapper tipado sobre Settings MP (bool/int/double/string), con claves centralizadas.
- Soporte de namespaces por módulo (flags/env/etc).
- Rama: `feat/ht-storage-f1-settings`.

## F2 – Config DB/SQDelight
- Definir `DbConfig` por plataforma/env: nombre de DB, ruta, esquema inicial (State/External Record).
- Estrategia de migración mínima (versión inicial, cambios futuros documentados).
- Rama: `feat/ht-storage-f2-dbconfig`.

## F3 – Factories/DI
- Factories multiplataforma para Settings y SQDelight; wiring Koin.
- Separación por env si aplica (mock/dev/prod).
- Rama: `feat/ht-storage-f3-di`.

## F4 – Smokes
- Smokes de lectura/escritura básica en Settings y DB init.
- Sin lógica de negocio; solo reachability local.
- Rama: `feat/ht-storage-f4-smokes`.

## F5 – Make/atajos
- Comandos: `make storage-smoke FLAVOR=...` para correr smokes locales.
- Rama: `feat/ht-storage-f5-make`.

## F6 – Testing/observabilidad
- Checklist: prefs funcionan, DB se inicializa, migración mínima, logs de errores.
- Rama: `feat/ht-storage-f6-testing`. 
