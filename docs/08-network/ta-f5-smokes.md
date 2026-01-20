# TA-NET-F5 — Smokes de conectividad

## Navegacion
- [Volver a Epica](08-network/epica.md)
- [Volver a Backlog](08-network/backlog.md)

## Rama
- `feat/ht-net-f5-smokes`

## Objetivo
Definir smokes rápidos de conectividad por ambiente/flavor para validar base URL, endpoints clave (flags, sdui, firestore) y logging/interceptores activos.

## Alcance
- Smokes por env (mock/dev/prod): HEAD/GET liviano a base URL y endpoint de flags/SDUI.
- Validar timeouts/retry básicos y logging nivel esperado según env.
- Para Firestore (mobile): lectura mínima de doc/collection configurada (si credenciales presentes).
- No correr tests pesados ni unit tests; solo reachability y configuración correcta.

## Diseño (doc)
1) Checklist
   - Resolución de `EnvValue` actual.
   - Cliente usa config correcta (baseUrl, headers, logging level).
   - Respuesta 200/ok o error controlado si endpoint mock no disponible (log warning).
2) Reporte
   - Salida legible (para Make) con resultado por endpoint.

## Entregables (documento)
- Lista de smokes y expectativas por env.

## Verificación futura
- Smokes claros y reproducibles; sin implementación. 