# Plantilla y estándar de Pull Request (cross-épica)

## 1) Título
- Formato obligatorio: `<tipo>(<scope>): <descripcion breve>`
- Tipos: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`, `ci`, `build`, `other`.
- Scope para historias técnicas: `ht-XX` (ej: `docs(ht-06): ...`).

## 2) Contexto y diseño
- Epic: `docs/<epica>/epica.md`
- Backlog: `docs/<epica>/backlog.md`
- TA(s): `docs/<epica>/ta-*.md`
- ADR(s): `docs/architecture/decisions/adr-*.md` (si aplica)

## 3) Descripción (obligatoria)
- Qué se cambió.
- Por qué se cambió.
- Qué queda fuera del alcance.

## 4) Lista de cambios
- Bullet list corta (3-7 puntos).
- Evitar duplicar el changelog.

## 5) Tipo de cambio (marcar uno)
- [ ] feat
- [ ] fix
- [ ] docs
- [ ] style
- [ ] refactor
- [ ] test
- [ ] chore
- [ ] ci
- [ ] build
- [ ] other (especificar)

## 6) Pruebas realizadas
- Comandos ejecutados o “no aplica” con justificación.

## 7) Changelog (obligatorio si hay impacto en el sistema)
- `CHANGELOG.md` → `[Unreleased]` → sección adecuada:
  - Added / Changed / Fixed
- Ejemplo:
  - `- DS: agrega bitácora de organismos flags/monitores`

## 8) Impacto y riesgos
- Riesgos/regresiones posibles.
- Flags/ambientes tocados (mock/dev/prod).
- i18n/tema/accesibilidad si aplica.

## 9) Evidencia
- Capturas/logs/links si aplica.

## 10) Checklist final
- [ ] Cumple `docs/git-workflow.md` (rama/commits).
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] Sin binarios/artefactos generados.
- [ ] Docs actualizadas si aplica.
- [ ] Pruebas declaradas.
- [ ] `make detekt` pasa sin findings nuevos.

## Ejemplos rápidos
- Título: `docs(ht-06): i18n base es/en`
- Changelog (Unreleased > Added):
  - `- i18n base: AppLanguage + StringsProvider + Splash`
