# Diseno detallado - Epica 14 Analytics (Nivel 4)

## Contexto
Analytics requiere un estandar de tagging transversal desde el DS para medir uso sin acoplar features.

## Alcance
- Taxonomia de eventos.
- Pipeline de captura.
- Providers (Firebase u otro).
- Debug/monitor local.

## TA-AN-F0 Dominio
- EventName, Params, Scope, ScreenId.

## TA-AN-F1 Taxonomia
- Naming y atributos obligatorios.

## TA-AN-F2 Collector
- API unica para registrar eventos.

## TA-AN-F3 Providers
- Adapter a proveedor externo.

## TA-AN-F4 Monitor
- Vista local o logs estructurados.

## TA-AN-F5 Testing
- Checklist QA y validacion de payloads.
