# TA-SDUI-F5 — Mockoon samples + casos borde

## Rama
- `feat/ht-sdui-f5-samples`

## Objetivo
Catalogar JSONs de Mockoon para SDUI: escenarios felices y casos borde (layout inválido, flags, versiones), para uso con comandos Make.

## Alcance
- Lista de samples con descripción y propósito.
- Cobertura de niveles (organismo/molécula/átomo) y flags/ambientes.

## Diseño (doc)
1) Lista de samples y cobertura
   - `sdui-home.json`: home básica (organismos generales, listas/cards, estados vacíos).
   - `sdui-auth.json`: login/crear cuenta/recover (textFields, botones identitarios, dialogs error).
   - `sdui-errors.json`: pantallas de error/empty/loading.
   - `sdui-flags.json`: componentes condicionados por flags y ambientes.
   - `sdui-invalid.json`: layout inválido (tipo desconocido, props faltantes) para validar fallback.
   - `sdui-version-unsupported.json`: `schemaVersion` no soportada.
   - `sdui-game.json`: flujos 1v1/2v2 (naipe, hand, score, acciones renuncio/vale, lobby, pause, history, capote).
   - `sdui-chat.json`: chat/soporte (bubbles, input, typing, attachments, support form).
   - `sdui-flags-monitors.json`: flags/monitores (flag list, logs, timeline, filtros).
2) Para cada sample documentar:
   - Componentes y nivel (átomo/molécula/organismo) cubiertos.
   - Flags/ambiente usados (`env`: mock/dev/prod).
   - Ruta/Make sugerida (TA-F4): ej. `make run-sdui-sample-home` → `app://sdui/sample/home`.
   - Resultado esperado (caso feliz o borde) y qué fallback se debe ver si falla.

## Entregables (documento)
- Catálogo de archivos JSON y su propósito.
- Mapeo a rutas/commands Make.

## Verificación futura
- Samples cubren casos felices y borde definidos en el contrato (incluye juego, chat, flags/monitores).
- Cada sample tiene ruta/Make asociada y resultado esperado documentado.

## No incluido
- Implementación de Mockoon o archivos reales.
