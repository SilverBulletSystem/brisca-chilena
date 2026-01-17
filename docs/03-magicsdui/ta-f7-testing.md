# TA-SDUI-F7 — Plan de pruebas SDUI (QA/Dev)

## Rama
- `feat/ht-sdui-f7-testing`

## Objetivo
Definir checklist de validación SDUI (parseo, render, navegación, fallback, flags/ambientes) usando los comandos Make y samples Mockoon. Sin implementación.

## Alcance
- Casos felices y borde.
- Uso de rutas/Make (TA-F4) y samples (TA-F5).

## Diseño (doc)
1) Casos felices (usar TA-F4 Make + TA-F5 samples)
   - Home (`run-sdui-sample-home` → `sdui-home.json`)
   - Auth (`run-sdui-sample-auth` → `sdui-auth.json`)
   - Juego (`run-sdui-sample-game` → `sdui-game.json`) 1v1/2v2 con acciones/score
   - Chat (`run-sdui-sample-chat` → `sdui-chat.json`)
   - Flags/Monitores (`run-sdui-sample-flags` → `sdui-flags.json`)
2) Casos borde
   - Layout inválido (`run-sdui-sample-invalid` → `sdui-invalid.json`)
   - Versión no soportada (`run-sdui-sample-version` → `sdui-version-unsupported.json`)
   - Flags faltantes/ambiente no coincidente (`run-sdui-sample-flags` con flags off)
   - Props ausentes en nodos críticos (ejemplo en `sdui-invalid.json`)
3) Verificaciones
   - Parseo: no crashes; AST se genera o cae en fallback.
   - Render: componentes correctos o pantalla de error SDUI si corresponde.
   - Navegación: comandos Make abren la pantalla sin clicks.
   - Flags/ambiente: nodos visibles solo si cumplen; variantes aplicadas.
   - Juego: acciones y score renderizados; timer/turno visibles.
   - Chat: bubbles, typing, input presentes.
   - Flags/monitores: listas/filtros/timeline visibles.
4) Logs (Toad/State Record)
   - Registrar errores/warnings de parser (tipo/id/razón).
   - Registrar rutas SDUI abiertas y sample usado.

## Entregables (documento)
- Checklist de QA/Dev con comandos Make y samples a usar.

## Verificación futura
- Plan listo para ejecutar tests manuales/automatizables, con comandos y samples asociados.

## No incluido
- Ejecución real de pruebas.
