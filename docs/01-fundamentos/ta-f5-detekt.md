# TA-F5 — Detekt (reglas Cursor)

## Objetivo
Diseñar la adopción de Detekt usando la configuración del proyecto base, adaptada al namespace actual y las reglas del usuario (Cursor). Sin ejecución de la herramienta.

## Rama
- Crear desde `main`: `feat/ht-f5-detekt`.

## Alcance
- Portar `detekt.yml` del proyecto base, ajustar rutas/namespace.
- Integrar tareas `detekt` y `detekt-fix` al Makefile (documentado).

## Diseño
1) Configuración:
   - Copiar `detekt.yml` base; ajustar `build`/`src` paths a `cl.silverbullet.multiplatform.brisca`.
   - Incluir reglas clave:
     - No strings hardcode en UI (forzar uso de interfaces de strings).
     - Nombres de casos de uso sin sufijo `UseCase`.
     - Uso de `ThemeDimens`/tokens (no valores hardcode de dp).
     - Atomic Design: evitar mezclar capas/patrones.
     - Capas limpias: presentación no depende de data/infrastructure directa.
   - Baseline: decidir si se genera o se evita (documentar preferencia; ideal evitar baseline inicial).
2) Build integration (doc):
   - Tarea Gradle `detekt` ya existe por plugin; añadir comando en Makefile:
     - `make detekt -> ./gradlew detekt`
     - `make detekt-fix -> ./gradlew detekt --auto-correct`
3) Reportes:
   - Output en `build/reports/detekt/detekt.html` (documentar ruta).

## Entregables (doc)
- Pasos para portar `detekt.yml`.
- Lista de reglas críticas a mantener.
- Comando Makefile previsto.
- Checklist futura:
  - [ ] `detekt.yml` copiado y ajustado.
  - [ ] Makefile tiene target `detekt`.
  - [ ] Makefile tiene target `detekt-fix`.
  - [ ] Sin baseline o baseline documentado.

## No incluido
- Ejecutar Detekt o corregir findings.
