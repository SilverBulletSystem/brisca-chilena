# Épica 3: MagicsDui (SDUI core)

Objetivo: diseñar el SDUI end-to-end (contrato Mockoon, parser, renderer a niveles átomo/molécula/organismo, navegación Decompose, flags/ambientes) con navegación rápida via Make. Sin implementación de código.

## Historias técnicas propuestas

### F0 – Modelo de dominio SDUI
- Objetivo: definir entidades, value objects, repositorios y casos de uso (sin transporte) para SDUI; DTO externo se traduce a modelo de dominio.
- Entregables: contrato de dominio (Schema, Node, Flags, Env), interfaces de repositorio y casos de uso.

### F1 – Contrato SDUI + versionado
- Objetivo: definir esquema JSON (Mockoon) con versionado, soporte de flags/ambientes y granularidad (organismo/molécula/átomo).
- Entregables: contrato estructurado, convenciones de versionado, manejo de defaults/fallback.

### F2 – Parser/validador SDUI
- Objetivo: definir cómo se parsea/valida el contrato (tipos, layout, props), detección de errores y fallback seguro.
- Entregables: reglas de validación y mapping intermedio.

### F3 – Renderer SDUI → Dui (átomos/moléculas/organismos)
- Objetivo: mapear nodos SDUI a componentes Dui con soporte de estilos/tokens y estados.
- Entregables: tabla de mapeo por tipo y nivel (organismo/molécula/átomo).

### F4 – Navegación/atajos Make (SDUI)
- Objetivo: definir rutas/deeplinks y comandos Make para abrir pantallas SDUI específicas (ej. `make run-sdui-sample-*`) sin interacción manual.
- Entregables: rutas y targets Make documentados.

### F5 – Mockoon samples + casos borde
- Objetivo: preparar catálogo de muestras JSON para escenarios felices y borde (layouts inválidos, flags, versiones).
- Entregables: lista de samples y qué cubre cada uno.

### F6 – Flags/ambientes/inyección de datos
- Objetivo: documentar cómo SDUI consume flags y ambiente (mock/dev/prod) y resuelve datos/caching.
- Entregables: flujo de resolución de flags/ambiente; puntos de integración con providers.

### F7 – Plan de pruebas SDUI (QA/Dev)
- Objetivo: checklist de validación SDUI (parseo, render, navegación, fallback) para QA/Dev.
- Entregables: casos a ejecutar con los Make targets y samples.

## Orden sugerido
1) F0 Dominio (modelo, repositorio, casos de uso) → 2) F1 Contrato → 3) F2 Parser → 4) F3 Renderer → 5) F4 Make/Deeplinks → 6) F5 Samples → 7) F6 Flags/Ambientes → 8) F7 Plan de pruebas.
