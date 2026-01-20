# ADR-0001: Estandar de iconos centralizados

## Navegacion
- [Volver al listado](architecture/decisions/README.md)

## Estado
Aceptado

## Contexto
El sistema de diseño requiere iconos consistentes y reemplazables sin modificar cada componente. Sin un estándar, los iconos terminan acoplados a cada UI, dificultando cambios globales.

## Decision
Se define un **estándar de iconos centralizados**:
- Los componentes **no** reciben assets directos (vector/png) ni rutas hardcodeadas.
- Los componentes usan un **identificador de icono** (`DuiIconId`) y un **resolver central** (`DuiIconRegistry`).
- Si un icono no está disponible, se muestra **placeholder** único (`DuiIconPlaceholder`) definido en el registro.

## Reglas
1) **Prohibido** usar assets directos en componentes (`R.drawable.*`, `ImageVector` directo, rutas).
2) **Obligatorio** usar `DuiIconId` para cualquier icono en UI.
3) El mapping de `DuiIconId` → asset vive en **un solo lugar** (`DuiIconRegistry`).
4) El placeholder es único y centralizado.

## Alcance
- Aplica a todos los componentes Dui (átomos, moléculas, organismos) y a bitácoras.

## Consecuencias
✅ Cambio global de iconos sin tocar cada componente.  
✅ Consistencia visual en toda la app.  
✅ Facilita theme packs futuros.

## Checklist de implementación
- [ ] Definir `DuiIconId` (lista cerrada).
- [ ] Implementar `DuiIconRegistry` con mapping central.
- [ ] Definir `DuiIconPlaceholder` para casos faltantes.

