# Diseno detallado - Epica 4 Inkribbon (State Record) (Nivel 4)

## Navegacion
- [Volver a Epica](04-inkribbon/epica.md)
- [Volver a Backlog](04-inkribbon/backlog.md)

## Contexto
State Record registra estados y acciones para reproducir sesiones y depurar errores.

## Alcance
- Dominio de eventos.
- Persistencia SQDelight.
- Hooks de captura.
- Writer pipeline.
- Visor in-app y replay.

## TA-INK-F0 Dominio
- Entidades Session/Event y servicios de sanitizacion.

## TA-INK-F1 Schema DB
- Tablas sessions/events con indices y retencion.

## TA-INK-F2 Hooks
- Navegacion, render SDUI, acciones, errores.

## TA-INK-F3 Writer
- Buffer, sanitizacion, limites y flush.

## TA-INK-F4 Viewer
- UI lista y detalle con timeline.

## TA-INK-F5 Replay
- Reconstruccion de pasos con limites.

## TA-INK-F6 Make
- Deeplinks para abrir visor/replay.

## TA-INK-F7 Testing
- Checklist QA y observabilidad.