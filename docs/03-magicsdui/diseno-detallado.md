# Diseno detallado - Epica 3 MagicsDui (SDUI) (Nivel 4)

## Navegacion
- [Volver a Epica](03-magicsdui/epica.md)
- [Volver a Backlog](03-magicsdui/backlog.md)

## Contexto
MagicsDui interpreta contratos SDUI y renderiza componentes Dui con validacion y fallback seguro.

## Alcance
- Contrato JSON versionado.
- Parser y validador.
- Renderer SDUI -> Dui.
- Flags y ambientes.
- Samples y plan de pruebas.

## TA-SDUI-F0 Dominio
- Entidades Schema/Node/Props/Style/Action.
- Servicios de dominio para validacion y visibilidad.

## TA-SDUI-F1 Contrato
- Esquema JSON con versionado y granularidad.
- Props tipadas y acciones allowlist.

## TA-SDUI-F2 Parser
- Reglas de validacion y defaults.
- Guardrails de tamanos y fallback.

## TA-SDUI-F3 Renderer
- Mapeo por nivel: atomos, moleculas, organismos.
- Estilos via tokens; i18n por keys.

## TA-SDUI-F4 Make/Deeplinks
- Rutas y targets Make para samples SDUI.

## TA-SDUI-F5 Samples Mockoon
- Catalogo de JSONs felices y borde.

## TA-SDUI-F6 Flags y ambientes
- Resolucion de flags/env en dominio.

## TA-SDUI-F7 Testing
- Checklist de validacion end-to-end.