# ADR-0003: Renderer SDUI con mapa de estrategias

## Navegacion
- [Volver al listado](architecture/decisions/README.md)

## Estado
Aceptado

## Contexto
El renderer SDUI debe mapear cada `NodeType` a un componente Dui. Un `when` gigante con todos los tipos no escala: crece indefinidamente, es dificil de mantener y viola el principio de responsabilidad unica.

## Decision
Se usa un **mapa de renderers** (strategy pattern) donde cada tipo tiene su propio renderer pequeno y testeable:

```kotlin
interface NodeRenderer {
  @Composable fun render(node: Node): Unit
}

class SduiRenderer(
  private val renderers: Map<NodeType, NodeRenderer>
) {
  fun render(node: Node): @Composable () -> Unit {
    val renderer = renderers[node.type] ?: DefaultRenderer
    return { renderer.render(node) }
  }
}
```

## Reglas
1) Cada `NodeType` tiene su propio `NodeRenderer` implementado.
2) Los renderers se registran en un mapa al inicializar `SduiRenderer`.
3) Si un tipo no tiene renderer, se usa `DefaultRenderer` (muestra placeholder y log warning).
4) Cada renderer es testeable de forma independiente.

## Alcance
- Aplica al renderer SDUI completo.
- Cada nuevo tipo requiere su propio renderer, no modificar el when central.

## Consecuencias
✅ Escalable: agregar tipos no modifica codigo existente.
✅ Testeable: cada renderer se prueba por separado.
✅ Mantenible: responsabilidades separadas.
✅ Extensible: facil agregar nuevos tipos sin tocar el core.

## Checklist de implementacion
- [ ] Definir interfaz `NodeRenderer`.
- [ ] Implementar renderer por tipo (atom/molecule/organism).
- [ ] Registrar renderers en mapa al inicializar `SduiRenderer`.
- [ ] Implementar `DefaultRenderer` para tipos desconocidos.
