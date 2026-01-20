# Epica 1 — Fundamentos del proyecto

## Navegacion
- [Backlog tecnico](01-fundamentos/backlog.md)
- [Diseno detallado](01-fundamentos/diseno-detallado.md)
- Historias tecnicas:
  - [TA-F1 Namespace y estructura](01-fundamentos/ta-f1-namespace-estructura.md)
  - [TA-F2 Koin baseline](01-fundamentos/ta-f2-koin-baseline.md)
  - [TA-F3 Network base](01-fundamentos/ta-f3-network-base.md)
  - [TA-F4 Ktor y ambientes](01-fundamentos/ta-f4-ktor-ambientes.md)
  - [TA-F5 Detekt](01-fundamentos/ta-f5-detekt.md)
  - [TA-F6 i18n base](01-fundamentos/ta-f6-i18n-base.md)
  - [TA-F7 Libs y config](01-fundamentos/ta-f7-libs-config.md)
  - [TA-F8 Flavors iOS](01-fundamentos/ta-f8-flavors-ios.md)

## Contexto
Base tecnica para estructura, build, DI, i18n y calidad inicial.

## Objetivo
Definir el modulo Fundamentos del proyecto con enfoque DDD, integracion con el ecosistema y reglas de calidad.

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
