# Epica 8 — Network

## Navegacion
- [Backlog tecnico](08-network/backlog.md)
- [Diseno detallado](08-network/diseno-detallado.md)
- Historias tecnicas:
  - [TA-NET-F0 Dominio](08-network/ta-f0-domain.md)
  - [TA-NET-F1 Config](08-network/ta-f1-config.md)
  - [TA-NET-F2 Cliente](08-network/ta-f2-client.md)
  - [TA-NET-F3 Interceptores](08-network/ta-f3-interceptors.md)
  - [TA-NET-F4 Firestore](08-network/ta-f4-firestore.md)
  - [TA-NET-F5 Smokes](08-network/ta-f5-smokes.md)
  - [TA-NET-F6 Make](08-network/ta-f6-make.md)
  - [TA-NET-F7 Testing](08-network/ta-f7-testing.md)

## Contexto
Ktor y Firestore minimo con config por ambiente.

## Objetivo
Definir el modulo Network con enfoque DDD, integracion con el ecosistema y reglas de calidad.

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
