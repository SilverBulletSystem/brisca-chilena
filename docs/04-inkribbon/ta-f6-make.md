# TA-INK-F6 — Make/atajos Inkribbon

## Rama
- `feat/ht-ink-f6-make`

## Objetivo
Definir atajos Make/deeplinks para abrir el visor de State Record y reproducir sesiones sin interacción manual.

## Alcance
- Comandos Make sugeridos:
  - `make run-inkribbon-viewer FLAVOR=mock|dev|prod` → abre lista de sesiones.
  - `make run-inkribbon-replay SESSION_ID=<id> FLAVOR=...` → abre replay de una sesión.
- Deeplinks/rutas:
  - `app://inkribbon/viewer`
  - `app://inkribbon/viewer/{sessionId}`
  - `app://inkribbon/replay/{sessionId}`
- Variables: `FLAVOR` reutiliza configuraciones de navegación rápida del proyecto base.
- Validación Dev: comandos ejecutan sin intervención humana; fallos deben loggear error claro.

## Diseño (doc)
1) Tabla de comandos y sus parámetros.
2) Dependencias: requiere que la app esté instalable en el flavor indicado y que exista al menos una sesión para el replay.
3) Comportamiento en ausencia de datos: si no hay sesiones, abrir visor con empty state.

## Entregables (documento)
- Lista de comandos Make/deeplinks y requisitos.

## Verificación futura
- Atajos claros y reproducibles; sin pasos manuales. 
