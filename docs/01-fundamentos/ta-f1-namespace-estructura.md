# TA-F1 — Namespace y estructura de módulos

## Navegacion
- [Volver a Epica](01-fundamentos/epica.md)
- [Volver a Backlog](01-fundamentos/backlog.md)

## Navegacion
- [Volver a Epica](01-fundamentos/epica.md)
- [Volver a Backlog](01-fundamentos/backlog.md)

## Objetivo
Validar el namespace actual y definir la estructura de módulos/carpetas base sin implementación de lógica.

## Rama
- Crear desde `main`: `feat/ht-f1-namespace-structure`.

## Alcance
- Confirmar namespace `cl.silverbullet.multiplatform.brisca` (no renombrar si ya es correcto).
- Estructura esperada:
  - `core/` (DI, network, security, storage, platform).
  - `commons/` (cache, onboarding, version, notificaciones y reutilizables no core).
  - `features/` (solo funcionalidades Brisca, ej: `mesa`).
  - `designsystem`, `magicsdui`, `inkribbon`, `toad`, `flags`.
- README breve en `composeApp` con el árbol objetivo y ejemplos.
- `.gitignore` alineado al proyecto (excluir build/, .idea/, *.iml, .DS_Store, outputs Gradle/Xcode).

## Diseño (pasos documentados)
1) Verificar namespace en archivos `build.gradle.kts` y paquetes base.
2) Crear carpetas de los módulos listados (placeholders sin lógica).
3) Añadir README corto con el árbol de carpetas esperado.
4) Primer commit (si aplica): `chore(init): initialize repository structure` incluyendo docs y estructura, sin binarios.

## Verificación futura
- Antes de ambientes (F3): `./gradlew :composeApp:assembleDebug` debe compilar.
- Tras definir ambientes (F3): `assembleMockDebug` debe compilar.

## No incluido
- Wiring de DI, lógica de features o creación de módulos Gradle separados.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ht-f1): namespace y estructura base (#XX)`

**Incluye:**
- Actualizacion de `docs/01-fundamentos/ta-f4-namespace-estructura.md` (este documento).
- Actualizacion de `docs/01-fundamentos/backlog.md` (si aplica).
- Nota de verificacion (resultado esperado de `assembleDebug`).

**Checklist:**
- [ ] Solo documentacion (sin codigo).
- [ ] Enlace a la epica `docs/01-fundamentos/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.