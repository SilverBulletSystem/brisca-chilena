# TA-TOAD-F2 — Interceptor HTTP (Ktor)

## Rama
- `feat/ht-toad-f2-interceptor`

## Objetivo
Documentar el diseño del interceptor Ktor multiplataforma para capturar request/response/error, sanitizar y emitir eventos de dominio para External Record (Toad) con correlación a Inkribbon. Dejar explícito cómo se adaptaría el patrón a otros canales (p. ej., Bluetooth) mediante adaptadores.

## Alcance
- Captura en Ktor client (Android/iOS/Web si aplica) como adaptador de canal HTTP.
- Sanitización:
  - Headers: eliminar/sustituir `Authorization`, `Cookie`, `Set-Cookie`, `X-Api-Key`, etc.
  - Body: truncar a 8 KB; calcular hash del cuerpo completo opcional.
  - URL: normalizar; ocultar query sensible (mask/hash).
- Correlación:
  - Adjuntar `SessionId` (si hay sesión activa) y `CorrelationId` (generado o provisto por Inkribbon).
- Errores: capturar timeout, network, status >=400; categorizar en `error_type`.
- Extensibilidad:
  - Bluetooth u otros: mapear `operation`, `peer/deviceId`, `payload` truncado/hash, `channel=bluetooth`; definir `error_type` acorde (timeout/disconnect).

## Diseño (doc)
1) Flujo
   - Antes de enviar: sanitizar request → `RecordRequest`.
   - Al recibir respuesta o error: sanitizar → `RecordResponse` con status/duración/errorType.
2) Lógica de tamaño
   - Payload body máximo 8 KB almacenado; resto descartado; hash opcional del completo para trazabilidad.
3) Compatibilidad
   - No bloquear request si falla el recorder; debe ser no intrusivo.
4) Logging
   - Warnings si no se puede correlacionar o si se recorta payload; errores si falla persistencia (sin romper request).

## Entregables (documento)
- Flujo detallado de captura/sanitización/correlación.
- Reglas de truncado y headers sensibles.
- Mapeo de errores Ktor a `error_type`.

## Verificación futura
- Interceptor definido sin ambigüedad; listo para implementar. 
