# TA-F4 — Makefile seeds (assemble-first)

## Objetivo
Diseñar los comandos mínimos de Make orientados a assemble rápido (sin tests), alineados a los flavors y buildTypes. Sin implementación.

## Rama
- Crear desde `main`: `feat/ht-f4-makefile-seeds`.

## Alcance
- Targets de assemble por flavor/buildType.
- Targets de calidad: tests unitarios, detekt, mockoon (placeholder), help.

## Diseño
1) Targets assemble:
   - `assemble-mock-debug` → `./gradlew :composeApp:assembleMockDebug`
   - `assemble-dev-debug` → `./gradlew :composeApp:assembleDevDebug`
   - `assemble-prod-release` → `./gradlew :composeApp:assembleProdRelease`
   - Sin ejecución de tests en estos targets.
2) Calidad:
   - `test-unit` → `./gradlew :composeApp:commonTest`
   - `detekt` → `./gradlew detekt`
   - `mockoon-*` placeholders (listar pero no implementar; depende de épica SDUI/mock).
3) Help:
   - `make help` muestra descripción de cada target.
4) Orden de uso:
   - Primer paso tras clonar: `make assemble-mock-debug` (una vez flavors estén configurados).

## Entregables (doc)
- Este TA con la lista de targets y los comandos Gradle asociados.
- Checklist futura:
  - [ ] Targets assemble no lanzan tests.
  - [ ] `make help` describe los comandos.

## No incluido
- Implementación del Makefile ni integración CI/CD.
