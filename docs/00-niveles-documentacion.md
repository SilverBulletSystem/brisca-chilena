# Niveles de documentacion (alto a detallado)

## Navegacion
- [Volver al Inicio](README.md)

## Objetivo
Definir un proceso unico para documentar todo el proyecto desde alto nivel hasta el diseño mas detallado, de forma consistente y trazable.

## Niveles
**Nivel 0 - Vision y alcance**
- Proposito del producto, principios, alcance MVP y no-MVP.
- Metas de negocio y restricciones no funcionales.

**Nivel 1 - Arquitectura general**
- Capas (Clean Architecture + DDD), modulos y responsabilidades.
- Flujos principales y dependencias entre modulos.
- Decisiones transversales (i18n, theming, SDUI, accesibilidad, seguridad).

**Nivel 2 - Epicas**
- Objetivo de la epica, alcance, entregables y dependencias.
- Riesgos, fuera de alcance, criterios de aceptacion.

**Nivel 3 - Tareas tecnicas (TA)**
- Trabajo atomico para implementar la epica.
- Criterios de verificacion y pasos claros.

**Nivel 4 - Diseño detallado**
- Contratos, modelos de dominio, DTOs, diagramas de flujo, UI detallada.
- Casos borde, errores, performance, observabilidad y tests.

## Regla de avance
1) Documentar Nivel 0 y Nivel 1 una sola vez (global).
2) Para cada epica, completar Nivel 2 (si falta).
3) Para cada TA, completar Nivel 3 (si falta).
4) Para cada TA, producir el Nivel 4 (diseno detallado).

## Resultado esperado
- Cada epica debe tener trazabilidad: Vision -> Arquitectura -> Epica -> TA -> Diseño detallado.
- Ninguna implementacion sin Nivel 4 aprobado.