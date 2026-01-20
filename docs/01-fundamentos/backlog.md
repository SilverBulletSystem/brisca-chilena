# Backlog tecnico — Epica 01 Fundamentos (Historias tecnicas)

## HT-F1 Namespace y estructura
- Objetivo: confirmar namespace `cl.silverbullet.multiplatform.brisca` y crear estructura base.
- Entregables: arbol de carpetas, README de estructura, `.gitignore` base.
- Verificacion: `./gradlew :composeApp:assembleDebug`.

## HT-F2 Koin baseline
- Objetivo: wiring base de DI con `applicationModule`.
- Entregables: `applicationModule` + arranque Android/iOS.
- Verificacion: app arranca sin fallas en debug.

## HT-F3 Network base (URL unica temporal)
- Objetivo: cliente Ktor base con API publica de Rick and Morty para probar conectividad.
- Entregables: GET `/api/character` verificado.
- Verificacion: respuesta 200 y body no vacio.

## HT-F4 Ktor y ambientes (mock/dev/prod)
- Objetivo: definir ambientes, flavors Android y Mockoon CLI para pruebas.
- Entregables: contrato `NetworkEnvironment`, `mock/dev/prod`, puertos 3002/3003/3004, comandos make.
- Verificacion: `env-start|check|stop` operativos por ambiente.

## HT-F5 Detekt (spike reglas extra)
- Objetivo: portar detekt base + reglas custom (comentarios, .dp/.sp, strings).
- Entregables: `detekt.yml` ajustado + plan de reglas custom.
- Verificacion: `./gradlew detekt` pasa en estado base.

## HT-F6 i18n base ES/EN
- Objetivo: interfaces segregadas por pantalla y wiring base de idioma.
- Entregables: contratos ES/EN + ejemplo aplicado.
- Verificacion: cambio de locale refleja textos.

## HT-F7 Libs y config comunes
- Objetivo: alinear versiones y plantillas de config con base.
- Entregables: `libs.versions.toml`, `local.properties.example`, `Config.xcconfig`.
- Verificacion: assemble mock funciona con versiones fijadas.

## HT-F8 Esquemas iOS (spike)
- Objetivo: resolver 3 ambientes iOS con configs y schemes.
- Entregables: `MockDebug`, `DevDebug`, `ProdRelease` + xcconfig por env.
- Verificacion: cada esquema compila y expone variables.
