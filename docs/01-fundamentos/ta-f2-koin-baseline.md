# TA-F2 — DI baseline con Koin

## Objetivo
Diseñar el wiring inicial de Koin usando **solo** el `applicationModule` (sin placeholders por capa), con detalle de implementación esperado.

## Rama
- Crear desde `main`: `feat/ht-f2-koin-baseline`.

## Alcance
- Un único `applicationModule` con definiciones reales mínimas.
- Hook de arranque en Android/iOS documentado.

## Diseño
1) Módulo Koin único:
   - Definir `applicationModule` con dependencias reales mínimas para arrancar:
     - `HttpClient` Ktor configurado.
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
  - [ ] `applicationModule` declara dependencias reales mínimas.

## No incluido
- Módulos por capa.
