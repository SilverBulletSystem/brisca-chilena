# TA-DS-F2A — Bitácoras mínimas + atajos Make

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f2-make-shortcuts`

## Objetivo
Definir y crear bitácoras mínimas (incluida la de temas), además de rutas/deeplinks y comandos Make para abrirlas sin interacción manual (patrón `make run-bitacora`).

## Alcance
- Bitácoras mínimas reales (pantallas destino) para temas, átomos, moléculas y organismos.
- Rutas internas para bitácoras de temas, átomos, moléculas y organismos (general, juego, chat, flags).
- Targets Make que lancen la app en cada ruta.

## Diseño (doc)
1) Bitácoras mínimas:
   - Crear pantallas destino con layout básico.
   - Deben ser navegables y renderizar sin fallas.
   - La bitácora de temas **no** es placeholder: debe renderizar la lista definida en TA-DS-F1.
2) Rutas propuestas:
   - Temas: `app://bitacora/theme`
   - Átomos: `app://bitacora/atoms`
   - Moléculas: `app://bitacora/molecules`
   - Organismos:
     - General: `app://bitacora/organisms/general`
     - Juego: `app://bitacora/organisms/game`
     - Chat/Soporte: `app://bitacora/organisms/chat`
     - Flags/Monitores: `app://bitacora/organisms/flags`
3) Targets Make propuestos (explícitos):
   - `make run-bitacora-theme`
   - `make run-bitacora-atoms`
   - `make run-bitacora-molecules`
   - `make run-bitacora-organisms-general`
   - `make run-bitacora-organisms-game`
   - `make run-bitacora-organisms-chat`
   - `make run-bitacora-organisms-flags`
4) Notas:
   - Mantener el patrón del proyecto (`make run-...`) para consistencia.
   - Documentar variables opcionales (ej. `FLAVOR=mock`) si se requiere.

## Validación Dev (rol QA)
- [ ] Ejecutar cada comando Make y verificar que abre la ruta correspondiente sin interacción manual.
- [ ] Confirmar carga de la pantalla destino (aunque esté vacía inicialmente) y que la ruta se refleje en logs/arranque.

## Entregables (documento)
- Lista de rutas y targets Make.
- Descripción de bitácoras mínimas creadas.

## Verificación futura
- Comandos y rutas claros para uso por dev/QA al implementar.

## No incluido
- Catálogos completos de componentes (van en TA-DS-F2B/F3/F5).