# Epica 10 — Flujos del cliente

## Navegacion
- [Backlog tecnico](10-flujos/backlog.md)
- [Diseno detallado](10-flujos/diseno-detallado.md)
- Historias tecnicas:
  - [TA-FLUJOS-F0 Guards](10-flujos/ta-f0-guards.md)
  - [TA-FLUJOS-F1 Contratos](10-flujos/ta-f1-contratos.md)
  - [TA-FLUJOS-F2 Handlers](10-flujos/ta-f2-handlers.md)
  - [TA-FLUJOS-F3 Make](10-flujos/ta-f3-make.md)
  - [TA-FLUJOS-F4 Testing](10-flujos/ta-f4-testing.md)

## Contexto
Flujos MVP servidos por SDUI y renderizados con DS.

## Objetivo
Definir el modulo Flujos del cliente con enfoque DDD, integracion con el ecosistema y reglas de calidad.

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
