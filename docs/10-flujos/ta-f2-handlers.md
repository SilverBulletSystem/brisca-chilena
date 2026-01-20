# TA-FLUJOS-F2 — Handlers/intents y navegación

## Navegacion
- [Volver a Epica](10-flujos/epica.md)
- [Volver a Backlog](10-flujos/backlog.md)

## Rama
- `feat/ht-flujos-f2-handlers`

## Objetivo
Documentar los handlers de intents (deeplinks/actions) y las rutas Decompose por flujo, asegurando compatibilidad con SDUI y Make/deeplinks.

## Alcance
- Tabla de rutas Decompose por flujo/pantalla; parámetros requeridos (ids de partida, usuario, etc.).
- Mapeo de `action` SDUI → intent (deeplink/navigate/event) → handler en cliente.
- Compatibilidad con Make: deeplinks que abren cada flujo directamente.
- Considerar auth/session: ciertas rutas requieren sesión válida; si no, redirigir a login.

## Diseño (doc)
1) Rutas principales y parámetros.
2) Guards de acceso (sesión, flags).
3) Comportamiento en deeplink invalido: fallback a home/login con log warning.

## Entregables (documento)
- Tabla de rutas/intents y reglas de acceso.

## Verificación futura
- Handlers claros para implementar navegación. 