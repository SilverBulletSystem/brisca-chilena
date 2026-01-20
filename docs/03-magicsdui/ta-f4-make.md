# TA-SDUI-F4 — Navegación/atajos Make (SDUI)

## Navegacion
- [Volver a Epica](03-magicsdui/epica.md)
- [Volver a Backlog](03-magicsdui/backlog.md)

## Rama
- `feat/ht-sdui-f4-make`

## Objetivo
Definir rutas/deeplinks y comandos Make para abrir pantallas SDUI de prueba sin interacción manual.

## Alcance
- Rutas internas para samples SDUI.
- Targets Make que lancen la app en cada ruta.

## Diseño (doc)
1) Rutas propuestas:
   - Felices: `app://sdui/sample/home`, `app://sdui/sample/auth`, `app://sdui/sample/errors`, `app://sdui/sample/game`, `app://sdui/sample/chat`, `app://sdui/sample/flags`
   - Casos borde: `app://sdui/sample/invalid`, `app://sdui/sample/version`
2) Targets Make propuestos (explícitos, alineados con samples TA-F5):
   - `make run-sdui-sample-home`
   - `make run-sdui-sample-auth`
   - `make run-sdui-sample-errors`
   - `make run-sdui-sample-game`
   - `make run-sdui-sample-chat`
   - `make run-sdui-sample-flags`
   - `make run-sdui-sample-invalid`
   - `make run-sdui-sample-version`
3) Notas:
   - Usar patrón `make run-...` (consistente con bitácoras DS).
   - Permitir variable `FLAVOR=mock|dev|prod` si se requiere.

## Entregables (documento)
- Lista de rutas y targets Make.
- Instrucciones de uso esperadas (sin implementación).

## Validación Dev (rol QA)
- [ ] Ejecutar cada comando Make y verificar que abre la ruta SDUI correspondiente sin clicks manuales.
- [ ] Confirmar carga de la pantalla (aunque sea placeholder) y logging de la ruta.

## No incluido
- Implementación de Make ni wiring de deeplinks.