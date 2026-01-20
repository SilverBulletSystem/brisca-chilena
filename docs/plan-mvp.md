# Plan MVP – Brisca Chilena 2026

## Navegacion
- [Volver al Inicio](README.md)

## Contexto y principios
- Clean Architecture + DDD, UI solo presentación; validación en dominio.
- SDUI (MagicsDui) sobre Design System Atomic (átomos→moléculas→organismos→pantallas→flujos).
- Internacionalización obligatoria: ninguna string hardcodeada en UI; interfaces segregadas por pantalla.
- Theming con `ThemeManager` y `ThemeDimens`; tokens preparados para accesibilidad y daltónicos (sin implementación visual completa en MVP).
- Navegación con Decompose; DI con Koin; Compose Multiplatform (Android/iOS/Web).
- Cache con Settings Multiplatform y persistencia con SQDelight.
- Feature flags con interfaz en dominio y múltiples proveedores (JSON local, Firestore).
- PRs pequeños y con foco: refinamiento continuo en Kanban (no sprints fijos).

## Alcance MVP (incluido)
- Plataformas: Android, iOS (clientes), Web (solo admin SDUI/mock; sin juego web en MVP).
- Ambientes/flavors: `mockserver`, `dev`, `prod` cada uno con `debug`/`release`.
- SDUI end-to-end: contrato JSON (Mockoon) → parser → render con organismos del DS.
- Design System Atomic + bitácora de componentes (storybook interno).
- Audio: soporte de sonidos ES/EN; si el spike de descarga en tiempo de ejecución se complica, ambos idiomas se empaquetan en el artefacto inicial (mainset de juego).
- Herramientas de desarrollador: State Record, External Record, Feature Flag Monitor, selector de ambientes.
- Feature Flags integradas en cliente con interfaz de dominio y múltiples proveedores (JSON local, Firestore u otros); no acoplado al origen de datos.
- Flujos app: Splash orquestador (versión/maintenance/seguridad básica/sesión/red), Force/Optional Update, Mantención, Sin Internet, Errores; Onboarding; Login/Crear Cuenta/Recuperar/Verificar/Cerrar Sesión; Home+Menú lateral; Perfil; Configuraciones; Notificaciones; Reglas; Ranking; FAQ; juego 1v1 y 2v2 (barajar, repartir, renuncio/vale, marcador, sala, pausa, historial, logros, capote); Chat; Soporte; Themas (solo tema por defecto seleccionable); Feature Flag Cliente UI; Bitácora de componentes; Monitores de estado y request.
- Network Ktor + Firestore inicial (auth/config/flags) para mobile; Web consume solo mockserver (backend real quedará para equipo 2).
- CI/CD: GitHub Flow, GitHub Actions multi-target, Makefile comandos básicos.

## Fuera de MVP (planificado)
- Bluetooth (cliente/administrador), Multimedia Manager general (salvo audio ES/EN incluido), tienda y publicidad, cifrado avanzada, autoapagado de flags por errores, panel/app interna, compra de temas/burlas, temas daltónicos implementados visualmente (solo tokens), autoapagado TaskOverToggle/CallToSeniorToggle, monetización real, backend propio para Web (solo mockserver en MVP).

## Etapas sugeridas (Kanban → Ready → In Progress → CR → Validación → Release)
1) Fundamentos y módulos: estructura base, namespaces, flavours, Makefile mínimo, Koin wiring.
2) CI/CD + calidad (GH Actions, Detekt con reglas, smoke tests; assemble rápido separado de tests).
3) Design System + tokens accesibilidad preparados + bitácora.
4) SDUI core con contrato Mockoon + intérprete + navegación Decompose.
5) State Record (captura, persistencia, replay básico).
6) External Record (HTTP interceptor + correlación con State Record).
7) Feature Flags + monitor UI.
8) Selector de ambientes.
9) Network (Ktor por flavor, Firestore mínimo).
10) Storage (Settings MP, SQDelight).
11) Flujos cliente (pantallas listadas) apoyados en SDUI y DS.
12) Audio ES/EN (bundle dual; spike descarga opcional).
13) Web admin mínimo (reuso DS/SDUI, mockserver; sin juego web).

## Épicas y entregables

- ### 1. Fundamentos del proyecto
- Objetivo: esqueleto KMP con módulos `core`, `commons`, `features`, `designsystem`, `magicsdui`, `inkribbon` (State Record), `toad` (External Record), `flags`, `network`, `storage`, flavours y Makefile.
- Entregables: namespaces `cl.silverbullet.multiplatform.brisca`; configuración flavours mock/dev/prod; DI Koin base por plataforma; tareas make básicas usando `assemble<Flavor><BuildType>` (p. ej. `assembleMockDebug`, `assembleDevDebug`, `assembleProdRelease`) para evitar `build` completo; spike de Detekt para reglas de malas prácticas (alineadas a las reglas de Cursor); **spike para configurar esquemas/flavors equivalentes en iOS (debug mock/dev/prod y release prod-tienda)**.
- Refinar en tareas pequeñas: creación de carpetas + ajustes Gradle; configuración flavours; seeds de Makefile; hooks de DI mínimos; spike Detekt + configuración inicial; targets assemble por flavor; spike iOS esquemas/xcconfig para ambientes.

### 2. Design System Atomic + accesibilidad preparada
- Objetivo: tokens de color/tipografía/espaciados con `ThemeDimens`; temas default + estructura para daltónicos (sin UI final); catálogo átomos→organismos y bitácora.
- Entregables: `ThemeManager`, tokens globales, componentes base (texto, botón, input, cards, lists), bitácora navegable por categoría; guideline de naming; strings por pantalla.
- Refinar: tokens + theming; átomos; moléculas; organismos; bitácora/storybook.

### 3. SDUI Core (MagicsDui) + Mockserver
- Objetivo: contrato JSON (Mockoon) y runtime que mapea a organismos/moléculas/átomos del DS con navegación Decompose.
- Enfoques SDUI: render a nivel de organismo, a nivel de molécula y a nivel de átomo (según granularidad requerida).
- Entregables: esquema versionado, parser, renderer, fallback seguro, soporte de flags/ambientes, ejemplo de pantallas desde JSON.
- Refinar: definir contrato; configurar Mockoon y muestras; parser; renderer; navegación SDUI; casos borde (layout inválido, versiones); decidir granularidad por pantalla/flujo.

### 4. State Record
- Objetivo: registrar pantallas/props por sesión y permitir replay básico para QA/dev.
- Entregables: modelo eventos, persistencia SQDelight, capturador de navegación/SDUI, visor en-app (lista + detalle), export simple (opcional enqueue).
- Refinar: schema DB; hook navegación; grabador; visor; replay mínimo.

### 5. External Record
- Objetivo: registrar requests/responses de cualquier origen (HTTP, BD, futuros Bluetooth/otros) y correlacionar con State Record.
- DDD: interfaz en dominio para eventos externos; implementaciones por canal (en MVP: HTTP; dejar contrato listo para BD/Bluetooth en releases futuros).
- Entregables: interceptor Ktor (HTTP), persistencia SQDelight, correlación por sesión/timestamp, visor en-app con filtros.
- Refinar: schema DB; interceptor HTTP; interfaz de canal externo; hook de correlación; visor; límites de retención; nota de extensión futura para Bluetooth/BD.

### 6. Feature Flags + Monitor
- Objetivo: capa de flags en dominio con proveedores JSON local y Firestore; UI de monitor para inspección/control.
- Entregables: interfaz de flags, proveedor local por flavor, proveedor Firestore, caché Settings, UI monitor, hooks SDUI/DS.
- Refinar: interfaz; proveedor local; proveedor Firestore; UI monitor; integración SDUI.

### 7. Selector de Ambientes
- Objetivo: permitir cambiar ambiente (mock/dev/prod) en cliente sin acoplarlo a flags; visible para dev/QA.
- Entregables: UI de selector, persistencia Settings, hooks de navegación/SDUI para reconfiguración de endpoints, guardas para release prod.
- Refinar: UX selector; persistencia; integración con configuración de network; controles por build type.

### 8. Network
- Objetivo: Ktor configurado por flavor y Firestore mínimo para mobile.
- Entregables: módulo network con timeouts/logging por ambiente; configuración de endpoints por flavor; wiring DI; pruebas smoke de conectividad mock/dev.
- Refinar: setup Ktor; endpoints/flavors; logging per environment; Firestore client mínimo; tests smoke de red.

### 9. Storage
- Objetivo: Settings MP para prefs ligeras y SQDelight integrado para persistencia (state/external records).
- Entregables: wrapper Settings MP; inicialización SQDelight; wiring DI; migración de config base (etapa temprana).
- Refinar: Settings wrapper; schema/init SQDelight; factories multiplataforma; pruebas básicas de lectura/escritura.

### 10. Flujos del cliente (SDUI-backed)
- Objetivo: pantallas definidas servidas vía SDUI + componentes DS.
- Entregables: JSONs de ejemplo en Mockoon para cada flujo; handlers de intents; navegación; estados/efectos; validaciones en dominio para auth/sesión.
- Refinar por lotes pequeños: Splash/Errores/Updates; Onboarding/Auth; Home+Menú; Reglas/Ranking/FAQ; Juego 1v1; Juego 2v2; Chat/Soporte; Monitores/Bitácora/Flags UI.

- #### Componentes DS necesarios (Atomic Design, prefijo `Dui`)
- Nota: los átomos son genéricos; la identidad por acción se logra con moléculas/organismos especializados que reusan átomos (sin duplicar lógica).
- Átomos: `DuiText` (Title/Subtitle/Body/Caption), `DuiIcon`, `DuiButton` (variedades), `DuiFab` (opcional), `DuiDivider`, `DuiSpacer`, `DuiChip`/`DuiTag`, `DuiBadge` (incluye numérica), `DuiProgress` (linear/circular), `DuiRadio`, `DuiSwitch` simple.
- Moléculas: `DuiIconButton`, `DuiTextField` (single/password, con iconos/label), `DuiCheckbox`, `DuiToggle` (switch con label/estado), `DuiListItem` (leading/trailing), `DuiSettingsItem` (opciones de configuración), `DuiCard`, `DuiBanner`, `DuiSnackbar`, `DuiDialog`/`DuiBottomSheet`, `DuiDropdown`/`DuiSelect`, `DuiTabs`/`DuiFilterChips`, `DuiAccordion`, `DuiAvatar`, `DuiItemCard`, `DuiTypingIndicator`, `DuiMessageStatus` (enviado/leído), botones con identidad: `DuiLoginButton`, `DuiRegisterButton`, `DuiRecoverButton`, `DuiVerifyAccountButton`, `DuiLogoutButton`; selectores: `DuiLanguageSelector`/`DuiLanguageOption`, `DuiSoundToggle` (ajuste rápido de sonido).
- Organismos: `DuiAppScaffold` (top/bottom bars, drawer), `DuiTopBar`, `DuiBottomNav`/`DuiBottomBar`, `DuiList`/`DuiSection`/`DuiGrid`, `DuiNavigationRail`/`DuiDrawer`/`DuiSideMenu`, `DuiSettingsList` (lista de configuración), `DuiEmptyState`, `DuiErrorState` (retry), `DuiLoading` (inline/fullscreen), `DuiThemeSelector`, `DuiEnvironmentSelector`/`DuiEnvironmentItem`, `DuiLogoutDialog`, `DuiSoundSetting`.
- Organismos dominio juego: `DuiCardFace`/`DuiNaipe`, `DuiCardDeck`/`DuiShuffleControl` (barajar), `DuiDealControl` (repartir), `DuiHand`/`DuiPlayList`/`DuiCardHand` (mano repartida), acciones con identidad: `DuiRenuncio`, `DuiCobrarRenuncio`, `DuiVale`, `DuiCobrarVale`, `DuiAcceptPlay`, `DuiRejectPlay`, `DuiReady`, `DuiStartMatch`, `DuiLeaveMatch`; estado/turno: `DuiTurnState`, `DuiTimer`/`DuiCountdown`; score: `DuiScoreboard1v1`, `DuiScoreboard2v2`, `DuiScoreTable1v1`, `DuiScoreTable2v2`, `DuiTeamPanel`/`DuiPlayerStatus`; match/session: `DuiMatchInviteCard` (aceptar partida), `DuiLobbyList`/`DuiPlayerSeat` (sala de jugadores), `DuiPauseOverlay`/`DuiResumeButton`, `DuiMatchHistoryList`; logros: `DuiAchievementItem` (logros), `DuiCapoteBanner` (capote).
- Organismos chat/soporte: `DuiMessageBubble`, `DuiMessageInput` + send, `DuiMessageList` (scroll), `DuiTypingIndicator`, `DuiAttachmentAction` (opcional), `DuiSupportForm`.
- Organismos flags/monitores/bitácora: `DuiFlagList` (con `DuiToggle`), `DuiTable`/two-column item, `DuiLogList` (timestamp + nivel), `DuiTimeline` (State/External Record), filtros (`DuiFilterChips`/`DuiSelect`).

### 11. Audio ES/EN
- Objetivo: soporte de sonidos en español e inglés; despliegue inicial incluye ambos idiomas en el artefacto (mainset de juego). Spike opcional: descarga en tiempo de ejecución; si se complica, se omite la descarga y se mantienen assets empaquetados.
- Entregables: catálogo de eventos sonoros por pantalla/flujo (juego, navegación, feedback), carga y reproducción condicionada por locale, fallback silencioso seguro, toggle de sonido, estrategia de almacenamiento (bundle inicial) y, si es viable, endpoint/flujo de descarga diferida.
- Refinar: lista de sonidos por flujo; empaquetado dual ES/EN; hook de locale; descarga opcional (spike); pruebas de latencia y peso.

### 12. Web (solo admin en MVP)
- Objetivo: versión web admin mínima usando mockserver (sin juego web ni Firestore).
- Entregables: target web configurado; build/deploy scripts; vistas admin mínimas (flags/ambientes/bitácora SDUI); consumo de mockserver.
- Refinar: setup web target; adaptaciones DS/SDUI web; smokes; Make.

### 13. CI/CD + Calidad
- Objetivo: pipelines GH Actions multi-target con make; análisis estático; smoke tests rápidos.
- Entregables: workflows Android/iOS/Web; make goals alineados; Detekt configurado (reglas del proyecto); smoke tests mock flavor; publicación interna (Play Internal, TestFlight, deploy web); jobs separados para assemble rápido vs. stage de unit tests (los tests no se corren en `assemble` local); cache de dependencias.
- Refinar: workflows base; cache deps; detekt config; tests mínimos; despliegues mock; gates de calidad; separar stages assemble (rápido) y test.

### 14. Analytics
- Objetivo: estandar de tagging y captura de eventos transversal al DS.
- Entregables: taxonomia de eventos, collector, provider externo, monitor local.

### 15. Notificaciones Push
- Objetivo: soporte push para promociones y anuncios propios.
- Entregables: permisos, tokens, routing por deeplink, observabilidad.

### 16. Encrypted Storage
- Objetivo: cifrar datos locales y definir gestion de claves.
- Entregables: wrapper cifrado de Settings y DB, plan de migracion.

### 17. Suscripcion
- Objetivo: habilitar modelo de suscripcion y entitlements.
- Entregables: integracion Play/StoreKit, UI de gestion, validacion de recibos.

### 18. Publicidad
- Objetivo: publicidad propia gestionada por el equipo.
- Entregables: modelo de anuncios, scheduling, placements UI y medicion.

### 19. Tienda
- Objetivo: catalogo y compra de items (temas, naipes, burlas).
- Entregables: catalogo, inventario, UI de tienda e integracion de compra.

### 20. Multimedia Manager
- Objetivo: descarga y cache de assets pesados bajo demanda.
- Entregables: catalogo de assets, downloader, politicas de cache y limpieza.

### 21. Bluetooth
- Objetivo: partidas locales sin internet.
- Entregables: discovery, sesion host/cliente, sincronizacion de partida.

### 22. App Panel
- Objetivo: panel interno para monitoreo y alarmas.
- Entregables: ingestion desde records, UI admin y alertas.

### 23. Resilience Toggles
- Objetivo: autoapagado de features ante errores repetidos y escalamiento.
- Entregables: deteccion de errores, reglas y escalation al panel.

## Criterios para PR pequeños
- Máx ~300-400 LOC netos por PR (guía); una epic se baja en múltiples PR secuenciales.
- Cada PR debe agregar valor observable: contrato, parser parcial, un componente DS, etc.
- Siempre separar: contrato/documentación vs. implementación; esquemas DB vs. visores; proveedor de flags vs. UI monitor.
- Acompañar PR con entrada de backlog/ADR si cambia contrato o arquitectura.

## Próximos pasos
- Crear tablero Kanban con estas épicas.
- Consolidar guías internas (Makefile, Detekt, estructura de docs).
- Definir contratos SDUI iniciales y esquemas de State/External Record.
- Configurar Mockoon base (JSON) y targets de Makefile para mockserver.