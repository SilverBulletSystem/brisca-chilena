# Índice de documentación

Bienvenido. Usa este índice para navegar toda la documentación del proyecto.

## Inicio rápido
- [Guia de niveles](00-niveles-documentacion.md)
- [Glosario](00-glosario.md)
- [Resumen alto nivel](00-resumen-alto-nivel.md)
- [Plan general](plan-mvp.md)

## Plantillas y guias
- [Plantilla diseno detallado](00-plantilla-diseno-detallado.md)
- [Plantilla epica](00-plantilla-epica.md)
- [Plantilla backlog](00-plantilla-backlog.md)
- [Plantilla TA](00-plantilla-ta.md)
- [Guia de estilo de documentacion](development/documentation-style-guide.md)
- [GitHub Pages (documentacion)](development/github-pages.md)

## Proceso y reglas
- [Templates Cursor](development/cursor-templates.md)
- [Estandar Git/Branches/Commits](git-workflow.md)
- [Plantilla de Pull Request y reglas de changelog](pr-template.md)

## ADRs
- [ADR-0001 Estandar de iconos](architecture/decisions/adr-0001-estandar-iconos.md)
- [ADR-0002 Estrategia de idiomas escalable](architecture/decisions/adr-0002-estrategia-idiomas.md)
- [ADR-0003 Renderer SDUI con mapa de estrategias](architecture/decisions/adr-0003-renderer-strategy.md)

## Epicas
### Epica 01 - Fundamentos
- [Epica](01-fundamentos/epica.md)
- [Backlog](01-fundamentos/backlog.md)
- [Diseno detallado](01-fundamentos/diseno-detallado.md)
- TAs:
  - [TA-F1 Namespace y estructura](01-fundamentos/ta-f1-namespace-estructura.md)
  - [TA-F2 Koin baseline](01-fundamentos/ta-f2-koin-baseline.md)
  - [TA-F3 Network base](01-fundamentos/ta-f3-network-base.md)
  - [TA-F4 Ktor y ambientes](01-fundamentos/ta-f4-ktor-ambientes.md)
  - [TA-F5 Detekt](01-fundamentos/ta-f5-detekt.md)
  - [TA-F6 i18n base](01-fundamentos/ta-f6-i18n-base.md)
  - [TA-F7 Libs y config](01-fundamentos/ta-f7-libs-config.md)
  - [TA-F8 Flavors iOS](01-fundamentos/ta-f8-flavors-ios.md)

### Epica 02 - Design System
- [Epica](02-designsystem/epica.md)
- [Backlog](02-designsystem/backlog.md)
- [Diseno detallado](02-designsystem/diseno-detallado.md)
- TAs:
  - [TA-F1 Navegación bitácoras](02-designsystem/ta-f1-navigation.md)
  - [TA-F2 Tokens y theming](02-designsystem/ta-f2-tokens-theming.md)
  - [TA-F3 Atoms bitacora](02-designsystem/ta-f3-atoms-bitacora.md)
  - [TA-F4 Molecules bitacora](02-designsystem/ta-f4-molecules-bitacora.md)
  - [TA-F5-A Organisms general](02-designsystem/ta-f5-a-organisms-general.md)
  - [TA-F5-B Organisms game](02-designsystem/ta-f5-b-organisms-game.md)
  - [TA-F5-C Organisms chat](02-designsystem/ta-f5-c-organisms-chat.md)
  - [TA-F5-D Organisms flags](02-designsystem/ta-f5-d-organisms-flags.md)
  - [TA-F6 Accessibility](02-designsystem/ta-f6-accessibility.md)
  - [TA-F7 i18n DS](02-designsystem/ta-f7-i18n-ds.md)

### Epica 03 - MagicsDui (SDUI)
- [Epica](03-magicsdui/epica.md)
- [Backlog](03-magicsdui/backlog.md)
- [Diseno detallado](03-magicsdui/diseno-detallado.md)
- TAs:
  - [TA-F0 Domain](03-magicsdui/ta-f0-domain.md)
  - [TA-F1 Contract](03-magicsdui/ta-f1-contract.md)
  - [TA-F2 Parser](03-magicsdui/ta-f2-parser.md)
  - [TA-F3 Renderer](03-magicsdui/ta-f3-renderer.md)
  - [TA-F4 Make](03-magicsdui/ta-f4-make.md)
  - [TA-F5 Samples](03-magicsdui/ta-f5-samples.md)
  - [TA-F6 Flags + ambientes](03-magicsdui/ta-f6-flags-ambientes.md)
  - [TA-F7 Login Screen](03-magicsdui/ta-f7-login-screen.md)
  - [TA-F8 Splash Screen](03-magicsdui/ta-f8-splash-screen.md)
  - [TA-F9 Home Screen](03-magicsdui/ta-f9-home-screen.md)
  - [TA-F10 Integration Test](03-magicsdui/ta-f10-testing.md)

### Epica 04 - Inkribbon (State Record)
- [Epica](04-inkribbon/epica.md)
- [Backlog](04-inkribbon/backlog.md)
- [Diseno detallado](04-inkribbon/diseno-detallado.md)
- TAs:
  - [TA-F0 Domain](04-inkribbon/ta-f0-domain.md)
  - [TA-F1 Schema](04-inkribbon/ta-f1-schema.md)
  - [TA-F2 Hooks](04-inkribbon/ta-f2-hooks.md)
  - [TA-F3 Writer](04-inkribbon/ta-f3-writer.md)
  - [TA-F4 Viewer](04-inkribbon/ta-f4-viewer.md)
  - [TA-F5 Replay](04-inkribbon/ta-f5-replay.md)
  - [TA-F6 Make](04-inkribbon/ta-f6-make.md)
  - [TA-F7 Testing](04-inkribbon/ta-f7-testing.md)

### Epica 05 - Toad (External Record)
- [Epica](05-toad/epica.md)
- [Backlog](05-toad/backlog.md)
- [Diseno detallado](05-toad/diseno-detallado.md)
- TAs:
  - [TA-F0 Domain](05-toad/ta-f0-domain.md)
  - [TA-F1 Schema](05-toad/ta-f1-schema.md)
  - [TA-F2 Interceptor](05-toad/ta-f2-interceptor.md)
  - [TA-F3 Writer](05-toad/ta-f3-writer.md)
  - [TA-F4 Correlation](05-toad/ta-f4-correlation.md)
  - [TA-F5 Viewer](05-toad/ta-f5-viewer.md)
  - [TA-F6 Make](05-toad/ta-f6-make.md)
  - [TA-F7 Testing](05-toad/ta-f7-testing.md)

### Epica 06 - Flags + Monitor
- [Epica](06-flags/epica.md)
- [Backlog](06-flags/backlog.md)
- [Diseno detallado](06-flags/diseno-detallado.md)
- TAs:
  - [TA-F0 Domain](06-flags/ta-f0-domain.md)
  - [TA-F1 Providers](06-flags/ta-f1-providers.md)
  - [TA-F2 Cache](06-flags/ta-f2-cache.md)
  - [TA-F3 Monitor UI](06-flags/ta-f3-monitor-ui.md)
  - [TA-F4 SDUI](06-flags/ta-f4-sdui.md)
  - [TA-F5 Make](06-flags/ta-f5-make.md)
  - [TA-F6 Testing](06-flags/ta-f6-testing.md)

### Epica 07 - Selector de Ambientes
- [Epica](07-environments/epica.md)
- [Backlog](07-environments/backlog.md)
- [Diseno detallado](07-environments/diseno-detallado.md)
- TAs:
  - [TA-F0 Domain](07-environments/ta-f0-domain.md)
  - [TA-F1 Persistence](07-environments/ta-f1-persistence.md)
  - [TA-F2 UI](07-environments/ta-f2-ui.md)
  - [TA-F3 Reconfigure](07-environments/ta-f3-reconfigure.md)
  - [TA-F4 Make](07-environments/ta-f4-make.md)
  - [TA-F5 Testing](07-environments/ta-f5-testing.md)

### Epica 08 - Network
- [Epica](08-network/epica.md)
- [Backlog](08-network/backlog.md)
- [Diseno detallado](08-network/diseno-detallado.md)
- TAs:
  - [TA-F0 Domain](08-network/ta-f0-domain.md)
  - [TA-F1 Config](08-network/ta-f1-config.md)
  - [TA-F2 Client](08-network/ta-f2-client.md)
  - [TA-F3 Interceptors](08-network/ta-f3-interceptors.md)
  - [TA-F4 Firestore](08-network/ta-f4-firestore.md)
  - [TA-F5 Smokes](08-network/ta-f5-smokes.md)
  - [TA-F6 Make](08-network/ta-f6-make.md)
  - [TA-F7 Testing](08-network/ta-f7-testing.md)

### Epica 09 - Storage
- [Epica](09-storage/epica.md)
- [Backlog](09-storage/backlog.md)
- [Diseno detallado](09-storage/diseno-detallado.md)
- TAs:
  - [TA-F0 Domain](09-storage/ta-f0-domain.md)
  - [TA-F1 Settings](09-storage/ta-f1-settings.md)
  - [TA-F2 DB config](09-storage/ta-f2-dbconfig.md)
  - [TA-F3 DI](09-storage/ta-f3-di.md)
  - [TA-F4 Smokes](09-storage/ta-f4-smokes.md)
  - [TA-F5 Make](09-storage/ta-f5-make.md)
  - [TA-F6 Testing](09-storage/ta-f6-testing.md)

### Epica 10 - Flujos del cliente
- [Epica](10-flujos/epica.md)
- [Backlog](10-flujos/backlog.md)
- [Diseno detallado](10-flujos/diseno-detallado.md)
- TAs:
  - [TA-F0 Guards](10-flujos/ta-f0-guards.md)
  - [TA-F1 Contratos](10-flujos/ta-f1-contratos.md)
  - [TA-F2 Handlers](10-flujos/ta-f2-handlers.md)
  - [TA-F3 Make](10-flujos/ta-f3-make.md)
  - [TA-F4 Testing](10-flujos/ta-f4-testing.md)

### Epica 11 - Audio ES/EN
- [Epica](11-audio/epica.md)
- [Backlog](11-audio/backlog.md)
- [Diseno detallado](11-audio/diseno-detallado.md)
- TAs:
  - [TA-F0 Domain](11-audio/ta-f0-domain.md)
  - [TA-F1 Catalog](11-audio/ta-f1-catalog.md)
  - [TA-F2 Toggle](11-audio/ta-f2-toggle.md)
  - [TA-F3 Hooks](11-audio/ta-f3-hooks.md)
  - [TA-F4 Spike download](11-audio/ta-f4-spike-download.md)
  - [TA-F5 Testing](11-audio/ta-f5-testing.md)

### Epica 12 - Web admin (mockserver)
- [Epica](12-web/epica.md)
- [Backlog](12-web/backlog.md)
- [Diseno detallado](12-web/diseno-detallado.md)
- TAs:
  - [TA-F0 Alcance](12-web/ta-f0-alcance.md)
  - [TA-F1 Build](12-web/ta-f1-build.md)
  - [TA-F2 DS + SDUI](12-web/ta-f2-ds-sdui.md)
  - [TA-F3 Smokes](12-web/ta-f3-smokes.md)
  - [TA-F4 Make](12-web/ta-f4-make.md)
  - [TA-F5 Testing](12-web/ta-f5-testing.md)

### Epica 13 - CI/CD + Calidad
- [Epica](13-cicd/epica.md)
- [Backlog](13-cicd/backlog.md)
- [Diseno detallado](13-cicd/diseno-detallado.md)
- TAs:
  - [TA-F0 Workflows](13-cicd/ta-f0-workflows.md)
  - [TA-F1 Make](13-cicd/ta-f1-make.md)
  - [TA-F2 Detekt](13-cicd/ta-f2-detekt.md)
  - [TA-F3 Smokes](13-cicd/ta-f3-smokes.md)
  - [TA-F4 Publish](13-cicd/ta-f4-publish.md)
  - [TA-F5 Gates](13-cicd/ta-f5-gates.md)

### Epica 14 - Analytics
- [Epica](14-analytics/epica.md)
- [Backlog](14-analytics/backlog.md)
- [Diseno detallado](14-analytics/diseno-detallado.md)

### Epica 15 - Notificaciones Push
- [Epica](15-push/epica.md)
- [Backlog](15-push/backlog.md)
- [Diseno detallado](15-push/diseno-detallado.md)

### Epica 16 - Encrypted Storage
- [Epica](16-encrypted/epica.md)
- [Backlog](16-encrypted/backlog.md)
- [Diseno detallado](16-encrypted/diseno-detallado.md)

### Epica 17 - Suscripcion
- [Epica](17-suscripcion/epica.md)
- [Backlog](17-suscripcion/backlog.md)
- [Diseno detallado](17-suscripcion/diseno-detallado.md)

### Epica 18 - Publicidad
- [Epica](18-publicidad/epica.md)
- [Backlog](18-publicidad/backlog.md)
- [Diseno detallado](18-publicidad/diseno-detallado.md)

### Epica 19 - Tienda
- [Epica](19-tienda/epica.md)
- [Backlog](19-tienda/backlog.md)
- [Diseno detallado](19-tienda/diseno-detallado.md)

### Epica 20 - Multimedia Manager
- [Epica](20-multimedia/epica.md)
- [Backlog](20-multimedia/backlog.md)
- [Diseno detallado](20-multimedia/diseno-detallado.md)

### Epica 21 - Bluetooth
- [Epica](21-bluetooth/epica.md)
- [Backlog](21-bluetooth/backlog.md)
- [Diseno detallado](21-bluetooth/diseno-detallado.md)

### Epica 22 - App Panel
- [Epica](22-app-panel/epica.md)
- [Backlog](22-app-panel/backlog.md)
- [Diseno detallado](22-app-panel/diseno-detallado.md)

### Epica 23 - Resilience Toggles
- [Epica](23-resilience-toggles/epica.md)
- [Backlog](23-resilience-toggles/backlog.md)
- [Diseno detallado](23-resilience-toggles/diseno-detallado.md)