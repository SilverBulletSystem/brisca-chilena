# Diseno detallado - Epica 9 Storage (Nivel 4)

## Navegacion
- [Volver a Epica](09-storage/epica.md)
- [Volver a Backlog](09-storage/backlog.md)

## Contexto
Storage local con Settings MP y SQDelight.

## Alcance
- Contratos de prefs y DB.
- Wrapper Settings.
- Config DB por env.
- Factories y DI.

## TA-STORAGE-F0 Dominio
- PrefsKey/DbConfig y casos de uso.

## TA-STORAGE-F1 Settings wrapper
- Tipos seguros y namespaces.

## TA-STORAGE-F2 DB config
- Nombre, ruta, version inicial.

## TA-STORAGE-F3 Factories/DI
- Provide Settings y DB por env.

## TA-STORAGE-F4 Smokes
- Lectura/escritura basica.

## TA-STORAGE-F5 Make
- storage-smoke.

## TA-STORAGE-F6 Testing
- Checklist QA y observabilidad.