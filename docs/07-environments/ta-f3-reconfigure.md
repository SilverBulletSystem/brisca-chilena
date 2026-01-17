# TA-ENV-F3 — Reconfiguración de servicios al cambiar ambiente

## Rama
- `feat/ht-env-f3-reconfigure`

## Objetivo
Documentar el flujo para reconfigurar servicios (network, flags, SDUI) al cambiar de ambiente, invalidando caches y notificando a monitores.

## Alcance
- Network (Ktor): actualizar base URL/endpoints según `EnvConfig`; reinicializar cliente si es necesario.
- Flags: apuntar proveedor local/remote del nuevo env; invalidar cache y hacer `RefreshFlags`.
- SDUI: actualizar endpoint mockserver/remote y limpiar schemas cacheados; opcional prefetch mínimo.
- Monitores (Toad/Inkribbon): registrar evento de cambio de env para correlación.
- Orden de operaciones y manejo de errores (fallback al env previo si reconfig falla).

## Diseño (doc)
1) Flujo
   - `SetEnv` -> EnvChangeCoordinator -> (network update) -> (flags refresh) -> (sdui reset/prefetch) -> notificar monitores.
2) Caches
   - Limpiar caches de flags y SDUI; mantener versión previa como rollback.
3) Errores
   - Si falla reconfig network/flags/sdui: revertir al env anterior y log error; mostrar mensaje i18n.

## Entregables (documento)
- Secuencia de reconfiguración, dependencias y manejo de errores/fallback.

## Verificación futura
- Reconfig definida y sin acople a UI/SDUI. 
