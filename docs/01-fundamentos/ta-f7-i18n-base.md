# TA-F7 — i18n base ES/EN

## Objetivo
Diseñar el scaffolding de internacionalización con interfaces de strings segregadas por pantalla, soportando ES/EN. Sin implementación de código.

## Rama
- Crear desde `main`: `feat/ht-f7-i18n-base`.

## Alcance
- Contratos de strings por pantalla (interfaces por feature/pantalla).
- Ejemplo aplicado (documentado) en una pantalla para validar wiring futuro.

## Diseño
1) Convención:
   - Interfaces por pantalla/feature: `SplashStrings`, `LoginStrings`, `HomeStrings`, etc.
   - Cada interfaz expone keys tipadas (val properties).
   - Implementaciones ES/EN en resources/kotlin multiplatform (según patrón del base).
2) Inyección:
   - Documentar cómo proveer la instancia de strings vía DI o proveedor de locale.
3) Ejemplo objetivo:
   - Pantalla ejemplo: Splash o Login con las keys mínimas (title, cta, error).
   - Mostrar cómo se obtiene la implementación según locale.
4) Uso en UI:
   - UI no hardcodea textos; consume la interfaz.
5) Archivos/paquetes:
   - Ubicar interfaces en `commonMain` bajo el paquete de cada feature.

## Entregables (doc)
- Descripción de estructura de interfaces y cómo mapear ES/EN.
- Ejemplo objetivo con listado de keys.
- Checklist futura:
  - [ ] Interfaces creadas por pantalla.
  - [ ] Implementaciones ES/EN preparadas.
  - [ ] UI referenciará solo interfaces (sin strings literales).

## No incluido
- Implementación de los archivos de strings ni wiring real.
