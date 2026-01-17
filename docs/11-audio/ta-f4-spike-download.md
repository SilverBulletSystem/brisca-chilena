# TA-AUDIO-F4 — Spike descarga opcional

## Rama
- `feat/ht-audio-f4-spike-download`

## Objetivo
Evaluar la viabilidad de descargar packs de audio ES/EN en runtime (opcional). Si es complejo, mantener solo el empaquetado inicial.

## Alcance
- Analizar origen/hosting (mockserver u otro) y formato de paquetes.
- Límites: tamaño máximo permitido, caching local, TTL.
- Fallback: si falla descarga, seguir usando assets empaquetados.
- Seguridad: validar integridad (hash) y evitar ejecución de payload no confiable.

## Entregables (documento)
- Hallazgos del spike, riesgos y decisión (go/no-go).

## Verificación futura
- Conclusiones claras; si no se implementa, dejar explícito. 
