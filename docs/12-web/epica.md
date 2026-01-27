# Epica 12 — Web admin

## Navegacion
- [Backlog tecnico](12-web/backlog.md)
- [Diseno detallado](12-web/diseno-detallado.md)
- Historias tecnicas:
  - [TA-WEB-F0 Alcance](12-web/ta-f0-alcance.md)
  - [TA-WEB-F1 Build/Deploy](12-web/ta-f1-build.md)
  - [TA-WEB-F2 Adaptaciones DS/SDUI](12-web/ta-f2-ds-sdui.md)
  - [TA-WEB-F3 Smokes](12-web/ta-f3-smokes.md)
  - [TA-WEB-F4 Make](12-web/ta-f4-make.md)
  - [TA-WEB-F5 Testing](12-web/ta-f5-testing.md)

## Contexto
Target web admin minimo con mockserver.

## Objetivo
Definir el modulo Web admin con enfoque DDD, integracion con el ecosistema y reglas de calidad.

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
