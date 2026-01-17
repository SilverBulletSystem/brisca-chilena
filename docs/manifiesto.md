# Manifiesto de estado (Brisca 2026)

## Contexto
- Proyecto KMP con Compose Multiplatform, Clean Architecture, DDD.
- Namespace/base: `cl.silverbullet.multiplatform.brisca`.
- Reglas transversales:
  - Git/branches/commits: `docs/git-workflow.md`.
  - PR + changelog (Keep a Changelog + SemVer): `docs/pr-template.md` y `CHANGELOG.md` bajo `[Unreleased]`.
- Índice general: `docs/index.md` (enlaces a todas las épicas, backlogs y TAs).
- Módulos (nombres sin guiones): `designsystem`, `magicsdui` (SDUI), `inkribbon` (State Record), `toad` (External Record), `flags`, `network`, `storage`, `commons`, `features`, `core`.

## Estado actual (documentación, sin implementación)
- Plan general: `docs/plan-mvp.md`.
- Épica 1 Fundamentos: `docs/01-fundamentos/epica.md` y `backlog.md`; TAs F1–F8 (namespace/estructura, flavors Android/iOS, Makefile, Koin, Detekt, i18n base, libs/config). Ramas sugeridas `feat/ht-fX-...`. Primer commit documentado; `assembleDebug` hasta flavors; luego `assembleMockDebug`.
- Épica 2 Design System: `docs/02-designsystem/epica.md` y `backlog.md`; TAs:
  - F1 tokens/theming
  - F2 make shortcuts (rutas/Make para bitácoras)
  - F3 átomos + bitácora
  - F4 moléculas + bitácora
  - F5-A/B/C/D organismos (general, juego, chat/soporte, flags/monitores) + bitácoras
  - F6 accesibilidad
  - F7 i18n DS
  Con navegación Make documentada para abrir bitácoras sin clicks.

## Convenciones clave
- Rama por TA/feature desde `main`, prefijos `feat/`, `docs/`, etc.; scopes HT `ht-XX`.
- Commits: `<tipo>(<scope>): desc` en minúsculas; PR con plantilla y entrada en `CHANGELOG.md` cuando aplica.
- No strings hardcode; i18n por interfaces; Atomic Design con prefijo `Dui`.

## Próximos pasos sugeridos
- Seguir con la siguiente épica del plan (MagicsDui/inkribbon/toad/flags según `plan-mvp.md`).
- Mantener backlog/TA por épica, ramas `feat/ht-...`.
- Al implementar, usar Make targets de assemble por flavor; separar tests del assemble.

## Épicas pendientes de refinar (mantener contexto)
- MagicsDui (SDUI core): contrato JSON Mockoon, parser/renderer, granularidad organismo/molécula/átomo, navegación Decompose, pruebas con atajos Make.
- Inkribbon (State Record): captura de pantallas/props, persistencia SQDelight, visor/replay, correlación con navegación; bitácora/monitor UI.
- Toad (External Record): interceptores HTTP (y futuro BT/BD), persistencia SQDelight, correlación con Inkribbon, visor con filtros.
- Flags: interfaz de dominio con proveedores múltiples (JSON local, Firestore), monitor de flags, selector de ambientes (separado), caché Settings, integración SDUI.
- Web admin (mockserver) y Audio ES/EN (si aplica en orden posterior según `plan-mvp.md`).

## Notas
- Todo es diseño/documentación; no se ha implementado código.
- Para navegación inmediata de docs: usar `docs/index.md`.
