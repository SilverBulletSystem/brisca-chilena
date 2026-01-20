# TA-STORAGE-F3 — Factories/DI

## Navegacion
- [Volver a Epica](09-storage/epica.md)
- [Volver a Backlog](09-storage/backlog.md)

## Rama
- `feat/ht-storage-f3-di`

## Objetivo
Diseñar las fábricas multiplataforma y el wiring DI (Koin) para Settings y SQDelight, consumiendo `DbConfig` y namespaces definidos.

## Alcance
- Factories:
  - `provideSettings(namespace)` -> instancia Settings MP.
  - `provideDatabase(config: DbConfig)` -> instancia SQDelight con driver por plataforma.
- Módulos Koin:
  - Módulo storage que expone `PrefsProvider` y `DatabaseProvider`.
- Integración con env:
  - Seleccionar config por env (mock/dev/prod) si aplica ruta/nombre distinta.

## Entregables (documento)
- Diagrama de módulos y factories; parámetros requeridos.

## Verificación futura
- DI definido sin ambigüedad. 