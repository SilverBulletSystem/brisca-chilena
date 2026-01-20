# TA-DS-F2 — Navegación/atajos Make para bitácoras

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f2-make-shortcuts`

## Objetivo
Definir rutas/deeplinks y comandos Make para abrir las bitácoras sin interacción manual (patrón `make run-bitacora` del proyecto).

## Alcance
- Rutas internas para bitácoras de átomos, moléculas y organismos (general, juego, chat, flags).
- Targets Make que lancen la app en cada ruta.

## Diseño (doc)
1) Rutas propuestas:
   - Átomos: `app://bitacora/atoms`
   - Moléculas: `app://bitacora/molecules`
   - Organismos:
     - General: `app://bitacora/organisms/general`
     - Juego: `app://bitacora/organisms/game`
     - Chat/Soporte: `app://bitacora/organisms/chat`
     - Flags/Monitores: `app://bitacora/organisms/flags`
2) Targets Make propuestos (explícitos):
   - `make run-bitacora-atoms`
   - `make run-bitacora-molecules`
   - `make run-bitacora-organisms-general`
   - `make run-bitacora-organisms-game`
   - `make run-bitacora-organisms-chat`
   - `make run-bitacora-organisms-flags`
3) Notas:
   - Mantener el patrón del proyecto (`make run-...`) para consistencia.
   - Documentar variables opcionales (ej. `FLAVOR=mock`) si se requiere.

## Validación Dev (rol QA)
- [ ] Ejecutar cada comando Make y verificar que abre la ruta correspondiente sin interacción manual.
- [ ] Confirmar carga de la pantalla destino (aunque esté vacía inicialmente) y que la ruta se refleje en logs/arranque.

## Entregables (documento)
- Lista de rutas y targets Make.
- Instrucciones de uso esperadas (sin implementación).

## Verificación futura
- Comandos y rutas claros para uso por dev/QA al implementar.

## No incluido
- Implementación de Make ni wiring de deeplinks.