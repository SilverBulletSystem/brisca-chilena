# Diseno detallado - Epica 7 Selector de Ambientes (Nivel 4)

## Navegacion
- [Volver a Epica](07-environments/epica.md)
- [Volver a Backlog](07-environments/backlog.md)

## Contexto
Permite cambiar ambiente en cliente y reconfigurar servicios.

## Alcance
- Dominio EnvValue/EnvConfig.
- Persistencia en Settings.
- UI selector.
- Reconfiguracion de servicios.

## TA-ENV-F0 Dominio
- EnvResolver, EnvGuard, EnvChangeCoordinator.

## TA-ENV-F1 Persistencia
- Settings MP con env actual y config estatica.

## TA-ENV-F2 UI Selector
- Organismo Dui, i18n y accesibilidad.

## TA-ENV-F3 Reconfiguracion
- Network, flags, SDUI y caches.

## TA-ENV-F4 Make
- Deeplinks para abrir selector.

## TA-ENV-F5 Testing
- Checklist QA y observabilidad.