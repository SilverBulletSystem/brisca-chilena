# TA-FLUJOS-F4 — Testing por flujo

## Rama
- `feat/ht-flujos-f4-testing`

## Objetivo
Definir checklist QA/Dev por flujo SDUI: carga, navegación, flags/env, estados de error/offline y i18n/accesibilidad.

## Alcance
- Para cada flujo/pantalla: Splash/Errores/Updates, Auth, Home, Perfil/Config, Reglas/Ranking/FAQ, Juego 1v1/2v2, Chat/Soporte, Monitores/Bitácora/Flags UI, Themas.
- Validar:
  - SDUI carga y renderiza organismos correctos con DS.
  - Navegación y deeplinks (Make).
  - Flags/ambiente aplican visibilidad/variantes.
  - Estados offline/error/version/maintenance.
  - i18n (ES/EN) y accesibilidad básica (focus/labels).
- Logs: registrar en Toad/Inkribbon fallas y flags faltantes.

## Entregables (documento)
- Checklist por flujo con criterios de aceptación.

## Verificación futura
- Plan listo para ejecución QA/Dev. 
