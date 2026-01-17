# Backlog técnico — Épica 10 Flujos del cliente

Referencia: `10-flujos/epica.md`. Solo documentación, sin implementación.

## F0 – Dominio de navegación/guards
- Definir guards de versión/mantenimiento/sesión/red (ya usados en Splash) como casos de uso.
- Intents de dominio para auth (login/logout/register/recover/verify), juego (1v1/2v2), chat/soporte.
- Rama: `feat/ht-flujos-f0-guards`.

## F1 – Contratos SDUI por flujo
- Para cada flujo listado, mapear pantalla → organismo SDUI requerido (reutilizando catálogo MagicsDui/DS).
- Enumerar props/flags clave y dependencias (auth/session id, locale).
- Rama: `feat/ht-flujos-f1-contratos`.

## F2 – Handlers/intents y navegación
- Definir handlers de intents (deeplinks/actions) para cada flujo; compatibilidad con Make/deeplink.
- Tabla de rutas Decompose y parámetros requeridos (ids de partida, usuario, etc.).
- Rama: `feat/ht-flujos-f2-handlers`.

## F3 – Make/deeplinks por flujo
- Comandos Make/deeplinks para abrir cada flujo/pantalla de forma directa (mock flavor).
- Ejemplos: `make run-splash`, `run-login`, `run-home`, `run-game-1v1`, `run-game-2v2`, `run-chat`, `run-flags-ui`, `run-bitacora`.
- Rama: `feat/ht-flujos-f3-make`.

## F4 – Testing por flujo
- Checklist QA/Dev por flujo: SDUI carga, navegación, flags/env, estados error/offline, i18n/accesibilidad.
- Rama: `feat/ht-flujos-f4-testing`. 
