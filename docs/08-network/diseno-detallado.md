# Diseno detallado - Epica 8 Network (Nivel 4)

## Contexto
Capa de red con Ktor y Firestore minimo, configurada por ambiente.

## Alcance
- Contratos de dominio.
- Config por env.
- Cliente Ktor base.
- Interceptores y logging.
- Smokes de red.

## TA-NET-F0 Dominio
- NetworkConfig y factory.

## TA-NET-F1 Config por ambiente
- BaseUrl, timeouts, retry y logging.

## TA-NET-F2 Cliente Ktor
- JSON seguro y DI.

## TA-NET-F3 Interceptores
- Headers comunes y Toad tracing.

## TA-NET-F4 Firestore minimo
- Lectura-only por env.

## TA-NET-F5 Smokes
- Reachability por env.

## TA-NET-F6 Make
- net-smoke y net-info.

## TA-NET-F7 Testing
- Checklist QA y observabilidad.
