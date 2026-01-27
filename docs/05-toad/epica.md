# Epica 5 — Toad (External Record)

## Navegacion
- [Backlog tecnico](05-toad/backlog.md)
- [Diseno detallado](05-toad/diseno-detallado.md)
- Historias tecnicas:
  - [TA-TOAD-F0 Dominio](05-toad/ta-f0-domain.md)
  - [TA-TOAD-F1 Schema DB](05-toad/ta-f1-schema.md)
  - [TA-TOAD-F2 Interceptor](05-toad/ta-f2-interceptor.md)
  - [TA-TOAD-F3 Writer](05-toad/ta-f3-writer.md)
  - [TA-TOAD-F4 Correlacion](05-toad/ta-f4-correlation.md)
  - [TA-TOAD-F5 Viewer](05-toad/ta-f5-viewer.md)
  - [TA-TOAD-F6 Make](05-toad/ta-f6-make.md)
  - [TA-TOAD-F7 Testing](05-toad/ta-f7-testing.md)

## Contexto
Registro de requests externos y correlacion con sesiones.

## Objetivo
Definir el modulo Toad (External Record) con enfoque DDD, integracion con el ecosistema y reglas de calidad.

## Alcance
- Contratos de dominio y casos de uso principales.
- Integracion con flags/ambientes si aplica.
- UI o herramientas asociadas (si aplica).
- Observabilidad y testing.

## Entregables
- Modelo de dominio y contratos.
- Flujos principales y casos borde.
- Plan de pruebas y observabilidad.

## Fuera de alcance
- Implementacion productiva completa si no corresponde al MVP.

## Dependencias
- Design System (Dui) para cualquier UI.
- Network/Storage si requiere persistencia o fetch.
- Flags/Ambientes si requiere control dinamico.

## Riesgos
- Integraciones externas o permisos pueden bloquear entregas.
- Carga de datos sensibles requiere sanitizacion estricta.
