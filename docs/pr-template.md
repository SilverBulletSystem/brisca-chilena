# Plantilla y estándar de Pull Request (cross-épica)

## Título
- Formato sugerido (alineado a commits): `<tipo>(<scope>): <descripción breve>`
  - Ej: `feat(ui): add login screen`
  - Tipos: feat, fix, docs, style, refactor, test, chore, ci, build, other.
  - Scope para historias técnicas: `ht-XX` (ej. `feat(ht-02): ...`).

## Secciones obligatorias
1) **Descripción**
   - Qué se cambió e intención del cambio.
2) **Tipo de cambio**
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
3) **Pruebas realizadas**
   - Comandos ejecutados o “no aplica” (justificar).
4) **Checklist**
   - [ ] Cumple `docs/git-workflow.md` (ramas/commits)
   - [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]` (Added/Changed/Fixed) o justificación de no entrada
   - [ ] Sin binarios/artefactos generados
   - [ ] Docs actualizadas si aplica
   - [ ] Pruebas declaradas
5) **Impacto**
   - Riesgos/regresiones potenciales
   - Flags/ambientes tocados (mock/dev/prod)
   - i18n/tema/accesibilidad si aplica
6) **Evidencia**
   - Capturas/logs/links a pruebas si aplica

## Changelog (Keep a Changelog + SemVer)
- Cada PR con impacto funcional agrega entrada en `CHANGELOG.md` → `[Unreleased]` → sección adecuada:
  - Added: nuevas funcionalidades/componentes/pantallas.
  - Changed: cambios de comportamiento, refactors visibles, config.
  - Fixed: bugs corregidos.
- Formato de bullet conciso y técnico.
- Versionado: las entradas permanecen en `[Unreleased]` hasta corte de release.
- Si no aplica entrada (docs/housekeeping), explicitarlo en la sección de Checklist.

## Rama y merge
- Rama desde `main` con prefijo correcto (ver `docs/git-workflow.md`).
- Revisor: al menos 1.
- Estrategia: “Squash and merge”.

## Ejemplos rápidos
- Título: `feat(ht-03): add environment selector`
- Changelog (Unreleased > Added):
  - `- Selector de ambientes: menú para mock/dev/prod con persistencia local`
- Changelog (Unreleased > Fixed):
  - `- Splash: corrige detección de sesión activa al volver de background`
