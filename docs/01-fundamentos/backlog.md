# Backlog técnico — Épica 1 Fundamentos

Referencia: `01-fundamentos/epica.md`. Solo documentación de tareas, sin implementación.

## F1 – Namespace y estructura de módulos
- Confirmar namespace `cl.silverbullet.multiplatform.brisca` (sin renombrar si ya es correcto).
- Definir árbol de carpetas/módulos lógicos: `core`, `commons`, `features`, `designsystem`, `magicsdui`, `inkribbon`, `toad`, `flags`, `network`, `storage`.
- Añadir README corto con el árbol esperado.
- Planear y ejecutar el **primer commit**:
  - `git init` si aún no existe repo local.
  - Commit inicial `chore(init): initialize repository structure` con la estructura base y docs (incluye `docs/git-workflow.md` y `docs/01-fundamentos/*`), sin código generado ni bins.
  - Respetar `docs/git-workflow.md` para naming de ramas/commits/PRs desde este punto.
  - Incluir `.gitignore` alineado al proyecto base (excluir build/, .idea/, *.iml, .DS_Store, outputs Gradle/Xcode); no commitear artefactos generados.
- Verificación: mientras no exista F2, usar `./gradlew :composeApp:assembleDebug`; cuando F2 defina flavors, validar con `assembleMockDebug`.
- No incluido: wiring de DI, lógica de features.
- Rama: `feat/ht-f1-namespace-structure`.

## F2 – Flavors Android (mock/dev/prod)
- Definir flavors con `applicationIdSuffix` y `versionNameSuffix`.
- Configurar `BuildConfig`/resources por flavor (endpoints placeholders, logging level).
- Actualizar Makefile con comandos assemble por flavor.
- Verificación: `assembleMockDebug`, `assembleDevDebug`, `assembleProdRelease` corren sin tests (reemplaza el uso provisional de `assembleDebug` en F1).
- Nota: mock server aún no existe; documentar endpoints placeholders y apuntar a la épica/tarea futura de mock server.
- Rama: `feat/ht-f2-flavors-android`.

## F3 – Esquemas/Flavors iOS (spike)
- Crear esquemas `MockDebug`, `DevDebug`, `ProdRelease`.
- xcconfig por ambiente con endpoints/logging placeholders.
- Mini guía de ejecución en Xcode.
- Verificación: cada esquema compila; variables accesibles en Swift.
- Nota: mock server no implementado; dejar endpoint placeholder.
- Rama: `feat/ht-f3-flavors-ios`.

## F4 – Makefile seeds (assemble-first)
- Comandos: `assemble-mock-debug`, `assemble-dev-debug`, `assemble-prod-release`.
- Comandos de calidad: `test-unit`, `detekt`, `mockoon-*` placeholders, `help`.
- Verificación: assemble no dispara tests; help lista comandos.
- Rama: `feat/ht-f4-makefile-seeds`.

## F5 – DI baseline con Koin
- Definir módulos vacíos/placeholder por capa (core, network, storage, feature-flags, sdui).
- Hook de arranque por plataforma (documentado).
- Verificación: arranque sin fallar en assemble mock.
- Rama: `feat/ht-f5-koin-baseline`.

## F6 – Detekt (reglas Cursor)
- Portar `detekt.yml` del proyecto base al nuevo namespace.
- Reglas clave: sin strings hardcode en UI, naming casos de uso, uso de `ThemeDimens`, Atomic, capas limpias.
- Integrar tarea `detekt` en Makefile.
- Verificación: `./gradlew detekt` pasa en limpio con el estado actual.
- Rama: `feat/ht-f6-detekt`.

## F7 – i18n base ES/EN
- Definir contratos de strings por pantalla (interfaces segregadas).
- Ejemplo aplicado en una pantalla (Splash/Login) para validar wiring.
- Verificación: cambio de locale muestra ES/EN en la pantalla ejemplo.
- Rama: `feat/ht-f7-i18n-base`.

## F8 – Libs y config comunes
- Alinear `libs.versions.toml` (Kotlin/Compose/Ktor/Koin) con el proyecto base.
- Plantillas `local.properties.example` y `Config.xcconfig`.
- Verificación: assemble mock funciona con las versiones fijadas.
- Rama: `feat/ht-f8-libs-config`.