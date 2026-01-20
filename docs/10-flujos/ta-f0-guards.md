# TA-FLUJOS-F0 — Dominio de navegación/guards

## Navegacion
- [Volver a Epica](10-flujos/epica.md)
- [Volver a Backlog](10-flujos/backlog.md)

## Rama
- `feat/ht-flujos-f0-guards`

## Objetivo
Definir casos de uso y guards de dominio para navegación: versión/mantenimiento/red/seguridad/sesión, reutilizables en Splash y en el router de flujos.

## Alcance
- Casos de uso:
  - `CheckVersionPolicy` (force/optional update).
  - `CheckMaintenance`.
  - `CheckConnectivity`.
  - `CheckSession` (activa/expirada).
  - `CheckSecurityBasic` (device/root básico).
- Resultado: enum/estado de navegación siguiente (update/maintenance/offline/login/home/etc).
- Integración: router usa estos checks antes de resolver SDUI.
- DDD: lógica en dominio; UI solo consume estado.

## Entregables (documento)
- Lista de guards, posibles estados de salida y prioridad de evaluación.

## Verificación futura
- Guards definidos sin ambigüedad para implementar en router. 