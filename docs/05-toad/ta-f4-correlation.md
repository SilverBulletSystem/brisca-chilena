# TA-TOAD-F4 — Correlación con Inkribbon

## Navegacion
- [Volver a Epica](05-toad/epica.md)
- [Volver a Backlog](05-toad/backlog.md)

## Rama
- `feat/ht-toad-f4-correlation`

## Objetivo
Diseñar la estrategia para correlacionar trazas de External Record con sesiones/eventos de State Record usando `CorrelationId` y `SessionId`.

## Alcance
- Generación/propagación de `CorrelationId` al iniciar request; reuso si ya existe en sesión activa.
- Asociación con `SessionId` actual (si existe), para enlazar trazas externas al timeline de Inkribbon.
- Resolución inversa: desde visor de Inkribbon, permitir navegar a trazas correlacionadas en Toad.
- Guardrails: si no hay sesión, se puede generar correlación efímera; log warning si falta.

## Diseño (doc)
1) Flujo
   - Navegación/acción inicia → produce `CorrelationId`.
   - Interceptor HTTP adjunta `CorrelationId` y `SessionId` a la traza.
   - Persistencia guarda ambos; visor permite saltar entre sesiones y trazas.
2) Datos mínimos
   - `CorrelationId` (UUID corto), `SessionId` opcional, timestamps coherentes.
3) Errores
   - Si no hay `CorrelationId`, generar uno; loggear advertencia.

## Entregables (documento)
- Flujo de correlación, datos mínimos y manejo de faltantes.

## Verificación futura
- Correlación definida para implementación en interceptor/visor. 