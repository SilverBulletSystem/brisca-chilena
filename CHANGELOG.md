# Changelog

All notable changes to this project will be documented in this file.

The format is based on Keep a Changelog, and this project adheres to SemVer.

## [Unreleased]
### Added
- Refinamiento nivel 4 de Epica 03 MagicsDui (SDUI): diseño detallado completo con interfaces, estructuras y tests explícitos.
- TA-SDUI-F7 Login Screen: pantalla Login en variantes clásica y SDUI con wireframe, i18n, tests y registro en logs.
- TA-SDUI-F8 Splash Screen: pantalla Splash en variantes clásica y SDUI con validaciones, navegación y registro en logs.
- TA-SDUI-F9 Home Screen: pantalla Home en variantes clásica y SDUI con carrusel, tabs y registro en logs.
- TA-SDUI-F10 Integration Test: tests de integración para flujo completo SDUI, fallback y comandos Make.
- Wireframes para Login, Splash y Home screens.
- ADR-0003 Renderer SDUI con mapa de estrategias.
- Sample `flags.json` movido a TA-F6 (Flags y ambientes).
- DTOs para Login en `data/model/` con `ErrorResponseDto` en módulo `error`.
- Tests unitarios movidos a sus respectivas historias técnicas (TA-F2, TA-F3, TA-F6).

### Changed
- Documentacion base: glosario, plantillas y guia de estilo.
- Guia de GitHub Pages para publicar la documentacion.
- Docsify con sidebar, navbar y cover para GitHub Pages.
- ADR-0001 para estandar de iconos.
- Indice de ADRs.
- README con sección ADRs para navegación.
- ADR index con link absoluto al ADR-0001.
- ADR-0001 agrega volver al listado.
- Sidebar: quitar “Listado de ADRs”.
- README: quitar “Listado de ADRs”.

### Changed
- Epica 03 MagicsDui: refinamiento completo a nivel 4 (implementación) con diseño detallado, interfaces, estructuras y tests explícitos.
- TA-SDUI-F0 Domain: transformado a nivel 4 con data classes, value objects, responsabilidades detalladas y tests unitarios explícitos.
- TA-SDUI-F1 Contract: clarificado separación DTO/Entity, agregado mapping DTO -> Entity y tests unitarios explícitos.
- TA-SDUI-F2 Parser: transformado a nivel 4 con interfaces, reglas de validación detalladas y 7 tests unitarios explícitos.
- TA-SDUI-F3 Renderer: transformado a nivel 4 con interfaces, ejemplo de render y 5 tests unitarios explícitos.
- TA-SDUI-F4 Make/Deeplinks: renombrado `SduiSampleConfig` a `SduiScreenConfig` y actualizado a nivel 4 con estructura Makefile y tests.
- TA-SDUI-F5 Samples: detallado con JSON completos para login, home, errors, flags, invalid y version-unsupported.
- TA-SDUI-F6 Flags y ambientes: agregado sample `flags.json` y 4 tests unitarios explícitos.
- Rutas de samples cambiadas de `sdui/samples/` a `sdui/screen/`.
- Comandos Make renombrados de `run-sdui-sample-*` a `run-sdui-screen-*`.
- Fallback siempre a pantalla clásica equivalente (eliminadas referencias a "pantalla SDUI de error").
- Tests unitarios movidos desde TA-F10 a sus respectivas historias (TA-F2, TA-F3, TA-F6).
- TA-F10 renombrado de "Testing" a "Integration Test" y enfocado solo en tests de integración, logs y Make.
- Reorganización de TAs: Testing (ahora Integration Test) movido al final como TA-F10.
- Renumeración de pantallas: Login (F7), Splash (F8), Home (F9), Integration Test (F10).
- Refinamiento de Epica 01 Fundamentos y sus historias tecnicas.
- Refinamiento de Epica 02 Design System (epica, backlog, diseno).
- Docsify: deshabilitar cache para reflejar cambios al instante.
- Sidebar: ocultar TOC de la pagina en menu lateral.
- TA-DS-F1 ahora incluye bitácoras mínimas para validar navegación.
- TA-DS-F2 definido con tokens, paletas y ThemeManager sin decisiones.
- TA-DS-F2 completado con dependencias, testing y aceptación.
- TA-DS-F2 movido a estructura magicsdui/theme.
- TA-DS-F2 define semillas de color para variantes daltónicas.
- TA-DS-F2 usa tema morado/amarillo y equivalentes daltónicos explícitos.
- TA-DS-F2 agrega sección de Pull Request.
- TA-DS-F2 agrega menú mínimo de temas y comandos Make de prueba.
- TA-DS-F2 redefine la bitácora de temas como listado de variantes.
- TA-DS-F2 reemplaza DuiIcon por DuiColorSwatch en la bitácora.
- TA-DS-F2 define contratos mínimos para DuiColorSwatch y fila.
- TA-DS-F2 elimina pendiente de paletas daltónicas.
- TA-DS-F2 agrega checklist con detekt.
- TA-DS-F2 agrega wireframe de bitácora de temas.
- TA-DS-F1 incluye bitácora de temas y comando Make.
- TA-DS-F1 detalla pantallas mínimas y comandos Make reales.
- TA-DS-F2 nombra la pantalla ThemeCatalogScreen.
- TA-DS-F1 lista átomos y moléculas mínimas usadas en tema.
- TA-DS-F1 agrega sección de Pull Request.
- TA-DS-F1 agrega wireframes de bitácoras mínimas.
- TA-DS-F1 define wiring Decompose para rutas de bitácoras.
- TA-DS-F3 define bitácora de átomos con wireframe y criterios.
- TA-DS-F3 incluye catálogo completo de átomos y estados.
- TA-DS-F3 reemplaza tamaños hardcode por ThemeDimens.
- TA-DS-F3 mueve tamaño de swatch a ThemeDimens.
- TA-DS-F2 define ICON_* y SWATCH_MD en ThemeDimens.
- TA-DS-F3 referencia ADR-0001 para iconos.
- TA-DS-F4 define bitácora de moléculas con catálogo y wireframe.
- TA-DS-F4 excluye moléculas ya creadas (DuiCard y swatch row).
- TA-DS-F4 agrega DuiRemoteImage y tamaños IMAGE_*.
- TA-DS-F4 define biblioteca y código para imágenes remotas.
- Epica 02: renumeración de TA para eliminar salto (F5A-D, F6, F7).
- TA-DS-F5A define bitácora de organismos generales con wireframe.
- TA-DS-F5B define bitácora de organismos juego con wireframe.
- TA-DS-F5C define bitácora de chat/soporte con wireframe.
- TA-DS-F5D define bitácora de flags/monitores con wireframe.
- TA-DS-F6 define guía de accesibilidad con reglas numéricas y checklist.
- TA-DS-F7 define reglas i18n estrictas y contratos de bitácoras.
- ADR-0002 define estrategia de idiomas escalable.
- Mejora de plantilla de Pull Request y template en .github.
- Epica 02: orden de ejecución actualizado (F1 antes de F2).
- TA-DS-F2 elimina referencias a ADRs inexistentes.
- Indice de documentacion con enlaces completos.
- Mejora de navegacion del indice para GitHub Pages.
- Ajuste de enlaces internos en epica de Fundamentos.
- Navegacion de retorno en backlog y TAs de Fundamentos.
- Navegacion de retorno en todas las historias tecnicas.
- Navegacion de retorno en todos los disenos detallados.
- Navegacion de retorno en guias base y plan MVP.
- Fix CSS para clicks en subitems del sidebar.
- Ajuste de tema y toggle del sidebar en Docsify.
- Fix GitHub Pages: agregar `.nojekyll` para assets y sidebar.
- Limpieza de referencias a proyecto base en la documentacion.
- Epica 02: renumeración y orden lógico de TA (F1→F7).
- TA-DS-F1 detalla implementación Decompose con mapeo URI → pantalla.