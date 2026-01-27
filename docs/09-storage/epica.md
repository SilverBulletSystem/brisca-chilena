# Epica 9 — Storage

## Navegacion
- [Backlog tecnico](09-storage/backlog.md)
- [Diseno detallado](09-storage/diseno-detallado.md)
- Historias tecnicas:
  - [TA-STORAGE-F0 Dominio](09-storage/ta-f0-domain.md)
  - [TA-STORAGE-F1 Settings](09-storage/ta-f1-settings.md)
  - [TA-STORAGE-F2 DB config](09-storage/ta-f2-dbconfig.md)
  - [TA-STORAGE-F3 Factories/DI](09-storage/ta-f3-di.md)
  - [TA-STORAGE-F4 Smokes](09-storage/ta-f4-smokes.md)
  - [TA-STORAGE-F5 Make](09-storage/ta-f5-make.md)
  - [TA-STORAGE-F6 Testing](09-storage/ta-f6-testing.md)

## Contexto
Settings MP y SQDelight para persistencia local.

## Objetivo
Definir el modulo Storage con enfoque DDD, integracion con el ecosistema y reglas de calidad.

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
