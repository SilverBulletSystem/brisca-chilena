# Guia de estilo de documentacion

## Objetivo
Elevar la calidad y consistencia de la documentacion para que cualquier equipo pueda implementar sin ambiguedades.

## Principios
- Claridad primero: frases cortas, una idea por linea.
- Trazabilidad: toda decision debe apuntar a su epica/TA/ADR.
- No suposiciones: explicitar dependencias y limites.
- Verificable: cada documento incluye criterios de verificacion.

## Lenguaje y tono
- Español neutro, tecnico y directo.
- Evitar jerga innecesaria.
- Usar infinitivo para objetivos: "Definir", "Diseñar", "Validar".

## Estructura recomendada por tipo
- Vision/alto nivel: problema, principios, alcance, restricciones.
- Epica: objetivo, alcance, entregables, fuera de alcance, dependencias, riesgos.
- Backlog: lista de TA con objetivo, entregables, verificacion.
- TA: diseño detallado, pasos, criterios de aceptacion y PR checklist.

## Enlaces y navegacion
- Enlaces relativos desde `docs/index.md`.
- Evitar rutas absolutas del sistema de archivos.
- Mantener nombres de archivo estables para no romper links.

## Diagramas y tablas
- Usar Mermaid cuando aporte claridad.
- Tablas solo para comparar opciones o versionado.
- No duplicar info que ya existe en otro doc; enlazar.

## Convenciones de nombres
- Epicas: `docs/NN-nombre/epica.md`
- Backlog: `docs/NN-nombre/backlog.md`
- Diseño detallado: `docs/NN-nombre/diseno-detallado.md`
- TAs: `docs/NN-nombre/ta-fX-<tema>.md`

## Checklist minimo por documento
- Objetivo claro.
- Alcance y fuera de alcance.
- Dependencias y riesgos.
- Verificacion/criterios de aceptacion.
- Enlaces a documentos relacionados.
