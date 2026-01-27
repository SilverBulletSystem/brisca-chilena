# Epica 3 — MagicsDui (SDUI)

## Navegacion
- [Backlog tecnico](03-magicsdui/backlog.md)
- [Diseno detallado](03-magicsdui/diseno-detallado.md)
- Historias tecnicas:
  - [TA-SDUI-F0 Dominio](03-magicsdui/ta-f0-domain.md)
  - [TA-SDUI-F1 Contrato](03-magicsdui/ta-f1-contract.md)
  - [TA-SDUI-F2 Parser](03-magicsdui/ta-f2-parser.md)
  - [TA-SDUI-F3 Renderer](03-magicsdui/ta-f3-renderer.md)
  - [TA-SDUI-F4 Make/Deeplinks](03-magicsdui/ta-f4-make.md)
  - [TA-SDUI-F5 Samples/fixtures](03-magicsdui/ta-f5-samples.md)
  - [TA-SDUI-F6 Flags y ambientes](03-magicsdui/ta-f6-flags-ambientes.md)
  - [TA-SDUI-F7 Login Screen](03-magicsdui/ta-f7-login-screen.md)
  - [TA-SDUI-F8 Splash Screen](03-magicsdui/ta-f8-splash-screen.md)
  - [TA-SDUI-F9 Home Screen](03-magicsdui/ta-f9-home-screen.md)
  - [TA-SDUI-F10 Integration Test](03-magicsdui/ta-f10-testing.md)

## Contexto
Motor SDUI para interpretar contratos y renderizar Dui. El transporte es agnostico: el contrato externo se modela como DTO y luego se traduce a dominio (no depende de JSON ni de Mockoon).

## Objetivo
Definir el modulo MagicsDui (SDUI) con enfoque DDD, integracion con el ecosistema y reglas de calidad.

## Alcance
- Contratos de dominio y casos de uso principales (agnosticos al transporte).
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
- Infra de mockserver (Mockoon) pertenece a Fundamentos; no es dependencia directa de MagicsDui.

## Riesgos
- Integraciones externas o permisos pueden bloquear entregas.
- Carga de datos sensibles requiere sanitizacion estricta.
