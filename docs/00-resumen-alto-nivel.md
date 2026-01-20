# Resumen alto nivel - Brisca Chilena 2026

## Proposito
Crear una suite multiplataforma para construir aplicaciones de negocio (iniciando con Brisca) con SDUI, Design System atomic, observabilidad local y escalabilidad en features.

## Alcance MVP
- Plataformas: Android, iOS (cliente), Web admin (solo mockserver, sin juego).
- SDUI end-to-end: contrato JSON -> parser -> renderer Dui.
- Design System atomic con bitacoras de componentes.
- Herramientas dev: State Record, External Record, Feature Flag Monitor, selector de ambientes.
- Red: Ktor + Firestore minimo (lectura).
- Storage: Settings MP + SQDelight.
- Flujos MVP: splash, auth, home, juego 1v1/2v2, chat, soporte, monitores, themas basicos.

## Fuera de MVP (planificado)
- Tienda, publicidad, multimedia manager general, Bluetooth, cifrado avanzado.
- Panel interno completo, autoapagado de flags por errores, monetizacion real.
- Temas daltónicos implementados visualmente (solo tokens en MVP).
- Backend web real (se usa mockserver).

## Arquitectura base
- Clean Architecture + DDD.
- UI solo presentacion (Compose Multiplatform).
- Navegacion: Decompose.
- DI: Koin.
- i18n obligatorio con interfaces por pantalla.
- Theming con ThemeManager y ThemeDimens.

## Modulos principales
- `designsystem`: componentes Dui, tokens y bitacoras.
- `magicsdui`: SDUI (contrato, parser, renderer).
- `inkribbon`: State Record (captura y replay).
- `toad`: External Record (requests externos).
- `flags`: feature flags + monitor.
- `network`: Ktor + Firestore minimo.
- `storage`: Settings MP + SQDelight.
- `commons`, `core`, `features`: dominios compartidos y casos de uso.

## Flujos clave (alto nivel)
- **Splash**: valida version, mantenimiento, seguridad, red y sesion, luego enruta.
- **SDUI**: backend entrega contrato; parser valida y renderer pinta DS.
- **State/External Record**: captura estados y requests con correlacion.
- **Feature Flags**: resolucion local/remote con cache y monitor UI.
- **Selector de ambientes**: cambia endpoints y refresca caches.

## Reglas transversales
- Sin strings hardcode en UI; usar interfaces segregadas por pantalla.
- Sin dimensiones hardcode; usar `ThemeDimens`.
- Accesibilidad prevista (contraste, focus, reduce motion).
- Observabilidad local (logs, timelines y replay).

## Mapa de epicas (alto nivel)
- 1 Fundamentos: estructura base, flavors, DI, i18n y calidad.
- 2 Design System: tokens, accesibilidad y bitacoras de componentes.
- 3 MagicsDui: contrato SDUI, parser, renderer y samples.
- 4 Inkribbon: state record, visor y replay basico.
- 5 Toad: external record con correlacion y monitor.
- 6 Flags: proveedores, cache y monitor UI.
- 7 Ambientes: selector y reconfiguracion segura.
- 8 Network: Ktor y Firestore minimo por ambiente.
- 9 Storage: Settings MP y SQDelight.
- 10 Flujos: pantallas MVP servidas por SDUI.
- 11 Audio: ES/EN con toggle y hooks.
- 12 Web admin: mockserver y vistas admin minimas.
- 13 CI/CD: workflows, detekt, smokes y gates.
- 14 Analytics: tagging transversal y providers.
- 15 Push: permisos, tokens y routing.
- 16 Encrypted: cifrado local y gestion de claves.
- 17 Suscripcion: planes, entitlements y compras.
- 18 Publicidad: anuncios propios y medicion.
- 19 Tienda: catalogo, compras e inventario.
- 20 Multimedia: descarga y cache de assets.
- 21 Bluetooth: partidas locales sin internet.
- 22 App Panel: monitoreo y alarmas internas.
- 23 Resilience Toggles: autoapagado y escalamiento.
