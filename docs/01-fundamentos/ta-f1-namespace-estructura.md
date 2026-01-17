# TA-F1 — Namespace y estructura de módulos

## Objetivo
Validar el namespace actual y definir la estructura de módulos/carpetas base sin implementación de lógica.

## Rama
- Crear desde `main`: `feat/ht-f1-namespace-structure`.

## Alcance
- Confirmar namespace `cl.silverbullet.multiplatform.brisca` (no renombrar si ya es correcto).
- Estructura esperada: `core`, `commons`, `features`, `designsystem`, `magicsdui`, `inkribbon`, `toad`, `flags`, `network`, `storage`.
- README breve en `composeApp` con el árbol objetivo.
- `.gitignore` alineado al proyecto base (excluir build/, .idea/, *.iml, .DS_Store, outputs Gradle/Xcode).

## Diseño (pasos documentados)
1) Verificar namespace en archivos `build.gradle.kts` y paquetes base.
2) Crear carpetas de los módulos listados (placeholders sin lógica).
3) Añadir README corto con el árbol de carpetas esperado.
4) Primer commit (si aplica): `chore(init): initialize repository structure` incluyendo docs y estructura, sin binarios.

## Verificación futura
- Antes de flavors (F2): `./gradlew :composeApp:assembleDebug` debe compilar.
- Tras definir flavors (F2): `assembleMockDebug` debe compilar.

## No incluido
- Wiring de DI, lógica de features o creación de módulos Gradle separados.
