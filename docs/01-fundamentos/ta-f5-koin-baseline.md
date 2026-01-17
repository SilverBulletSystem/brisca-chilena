# TA-F5 — DI baseline con Koin

## Objetivo
Diseñar el wiring inicial de Koin (sin lógica), dejando módulos listos para registrar dependencias por capa.

## Rama
- Crear desde `main`: `feat/ht-f5-koin-baseline`.

## Alcance
- Módulos vacíos/placeholder por capa.
- Hook de arranque en Android/iOS documentado.

## Diseño
1) Módulos Koin (placeholders):
   - `coreModule`, `networkModule`, `storageModule`, `flagsModule`, `magicsduiModule`, `designsystemModule`.
   - Sin bindings reales; solo funciones `module { }` vacías documentadas.
2) Arranque:
   - Android: documentar `startKoin` en `Application`/entrypoint con lista de módulos.
   - iOS: documentar inicialización desde `initKoin()` expuesto a Swift (pattern del proyecto base).
3) Organización de paquetes:
   - Ubicar módulos en sus paquetes correspondientes (`network`, `storage`, etc.) bajo `cl.silverbullet.multiplatform.brisca`.
4) Extensión futura:
   - Documentar cómo agregar bindings reales cuando se implemente cada epic.

## Entregables (doc)
- Listado de módulos y funciones `module { }` esperadas.
- Pasos de arranque en cada plataforma (texto).
- Checklist futura:
  - [ ] startKoin declarado en Android.
  - [ ] initKoin documentado para iOS.
  - [ ] Módulos declarados sin dependencias reales.

## No incluido
- Implementación de dependencias reales ni wiring concreto.
