# Diseno detallado - Epica 3 MagicsDui (SDUI) (Nivel 4)

## Navegacion
- [Volver a Epica](03-magicsdui/epica.md)
- [Volver a Backlog](03-magicsdui/backlog.md)

## 1. Contexto y objetivo
MagicsDui es el mecanismo de Server Driven UI (SDUI) en Brisca. Permite definir y variar pantallas completas desde el servidor sin publicar una nueva version de la app, reutilizando JetBrains Compose y el Design System Dui. El contrato externo se modela como un DTO agnostico al transporte (no depende de JSON ni de Mockoon) y se traduce a dominio antes de renderizar.

Objetivo de este nivel: dejar el diseno de implementacion listo para construir, incluyendo decisiones de arquitectura, contratos, validaciones, rutas, flags y reglas de fallback. El resultado debe permitir que el equipo implemente SDUI sin ambiguedades ni decisiones pendientes.

Ademas, este proyecto busca probar y comparar los dos enfoques (pantallas clasicas vs SDUI) con fines educativos, para entender cual se adapta mejor al producto y por que.

## 2. Que es SDUI en Brisca
SDUI (Server Driven UI) es un modelo donde la estructura y contenido de una pantalla se definen en un contrato remoto y el cliente lo interpreta para renderizar UI real con Dui. En Brisca, SDUI se usa para experimentar y comparar contra el enfoque clasico, y para habilitar cambios de UI sin publicar una nueva version.

Principios en Brisca:
- La decision es **por pantalla completa**, nunca por secciones.
- Cada pantalla clasica tiene un equivalente SDUI con el mismo `screenId`.
- SDUI no reemplaza pantallas criticas (ForceUpdate, Maintenance, Offline); esas permanecen clasicas.
- El renderer SDUI no decide navegacion: solo renderiza lo que el router le pide.

## 3. Arquitectura SDUI en Brisca
Componentes y responsabilidades:
- **Router (Decompose)**: decide si la pantalla sera clasica o SDUI segun guards y flags.
- **FetchSchema**: obtiene el DTO externo para un `screenId` y `env` (fuente agnostica).
- **ParseSchema**: valida, aplica defaults y convierte DTO a AST de dominio.
- **Renderer**: transforma el AST en composables Dui con estilos, i18n y estados.
- **Navigation Handler**: ejecuta intents/acciones SDUI y navega por Decompose.

Flujo general (end-to-end):
1) Decompose resuelve la ruta de pantalla.
2) Si SDUI aplica, solicita contrato por `screenId`.
3) Se valida y normaliza el contrato (AST).
4) Se renderiza con `MagicsDuiRenderScreen`.
5) Las acciones SDUI se traducen a intents y rutas de Decompose.

## 4. Decision de pantalla (clasica vs SDUI)
La decision se toma en el router/guard y aplica a la pantalla completa. El renderer no decide rutas ni reemplaza pantallas por su cuenta.

Reglas base:
- Todas las pantallas tienen equivalente SDUI, incluyendo ForceUpdate.
- SDUI se opera a nivel de organismos; el estado sigue en el cliente.
- El uso de SDUI por pantalla se controla por Feature Flag.
- Cada pantalla debe tener su flag de alternancia clasico/SDUI.
- En el MVP estos flags se manejan hardcodeados en el cliente; mas adelante se gestionan desde el panel de flags.
- Si SDUI falla (fetch o parse), se hace fallback a la pantalla clasica equivalente y se registra en Toad para trazabilidad del error.

Ejemplos:
- `SplashScreen` evalua flags y decide:
  - `HomeScreen` clasico
  - o `MagicsDuiRenderScreen(screenId="home")`
- `SplashScreen` -> ForceUpdate puede resolverse como clasico o SDUI segun flag.

## 5. Modelo de dominio (implementacion)
Entidades base:
- `Schema` { id, version, screenId, root: Node }
- `Node` { id, type, props, children, style, flags, env, state }
- `Props` (tipo por componente)
- `Style` (roles, no valores crudos)
- `Action` { type, target, extras? }

Ambientes (flavors):
- Los flavors representan ambientes y se definen en Gradle (mockserver, dev, produccion).
- En Debug se habilita un selector de ambientes para alternar entre mockserver/dev/produccion.
- En Release no existe selector; el ambiente queda fijo por build.

Servicios de dominio:
- `SchemaValidationService`: valida version, estructura minima y tipos de nodos; clasifica errores criticos vs recuperables.
- `SchemaNormalizationService`: aplica defaults (props/estado/estilo) y normaliza el AST para render estable.
- `NodeVisibilityService`: filtra nodos segun flags y ambiente (env) antes del render.
- `StyleResolutionService`: resuelve roles de estilo a tokens Dui y rechaza valores crudos.
- `NavigationGuardService`: valida acciones y destinos permitidos (allowlist) antes de exponerlos a UI.
- `SduiErrorRecorder`: registra errores y warnings SDUI (fetch/parse/render) usando el repositorio de Toad.

## 6. Contrato SDUI (DTO externo)
El contrato describe una pantalla completa y debe ser estable entre versiones.

Campos obligatorios por pantalla:
- `schemaVersion` (compatibilidad por major).
- `screenId` (id estable, igual al equivalente clasico).
- `root` (nodo raiz del arbol).

Campos por nodo:
- `id`: string unico.
- `type`: tipo SDUI (organismo/molecula/atomo).
- `props`: objeto tipado segun `type`.
- `children?`: lista de nodos hijos (cuando aplique).
- `flags?`: lista de flags requeridas.
- `env?`: lista de ambientes permitidos.
- `style?`: roles de estilo (no valores crudos).
- `state?`: estado visual (`enabled|disabled|error|focus|pressed`).
- `actions?`: lista de acciones permitidas.

Reglas del contrato:
- `screenId` debe ser estable y unico por pantalla.
- `props` de texto usan keys i18n, nunca literales.
- `style` usa roles (color/typography/dimens/shape), no valores crudos.
- `action.type` allowlist: `deeplink|navigate|open_url|dispatch_intent`.

Ejemplo (Login, simplificado, con niveles):
```
screenId: "login"
root:
  type: "appScaffold"        # organismo
  children:
    - type: "text"            # atomo
      props: { textKey: "login_title" }
    - type: "textField"       # molecula
      props: { labelKey: "login_email", placeholderKey: "login_email_hint" }
    - type: "textField"       # molecula
      props: { labelKey: "login_password", state: "default" }
    - type: "button"          # atomo
      props: { labelKey: "login_cta" }
      actions:
        - { type: "dispatch_intent", target: "login" }
```

**Wireframe/Mockup de Login:**

![Login Screen Wireframe](../assets/login-wireframe.png)

*Diseño visual esperado para la pantalla Login (tanto clásica como SDUI deben renderizar el mismo diseño)*

## 7. Parser/Validator (reglas concretas)
Validaciones obligatorias (con comportamiento):
- `schemaVersion`: si es incompatible, se rechaza el contrato completo y se registra en Toad.
- `screenId`: requerido y no vacio; si falta, se rechaza la pantalla y se registra en Toad.
- `root` y `type`: el nodo raiz debe ser un organismo valido; si no, se rechaza la pantalla y se registra en Toad.
- `props` minimas por tipo:
  - `text`: requiere `textKey`.
  - `textField`: requiere `labelKey`.
  - `button`: requiere `labelKey` y `action` si es interactivo.
- `flags/env`: se filtran nodos antes de render; si todos los hijos quedan ocultos, se aplica empty state.
- `style`: roles validos; si llegan valores crudos se ignoran con warning.
- Guardrails: profundidad <= 25, nodos <= 500, payload <= 200 KB; si se excede, se rechaza pantalla y se registra en Toad.

Fallback:
- Errores criticos -> fallback a pantalla clasica equivalente con registro en Toad.
- Errores recuperables -> omitir nodo y log warning en Toad.
- Fallo de fetch/parse -> fallback a pantalla clasica equivalente con registro en Toad.

## 8. Renderer (mapa SDUI -> Dui)
El renderer consume el **AST (Abstract Syntax Tree)** ya validado y no aplica reglas de negocio. Solo transforma nodos SDUI a composables Dui.

Reglas del renderer:
- Cada `type` SDUI mapea 1:1 a un componente Dui (atomos, moleculas, organismos).
- El renderer no decide visibilidad; recibe el arbol ya filtrado por flags/env.
- Estilos se aplican solo via tokens Dui (roles); no se aceptan valores crudos.
- Textos siempre se resuelven via interfaces de strings.
- Acciones SDUI se traducen a intents y rutas de Decompose mediante el Navigation Handler.

Errores en render:
- Si un nodo no puede renderizarse por falta de props criticas, se omite y se registra warning en Toad.
- Si el nodo raiz no puede renderizarse, se hace fallback a pantalla clasica equivalente y se registra en Toad.

## 9. Home: diseno dual (clasico y SDUI)
Se definen dos variantes:
1) **HomeScreen clasico**
   - Implementado con Dui en Compose.
   - Navegacion normal de Decompose.
2) **MagicsDuiHome**
   - Renderiza `screenId="home"` via `MagicsDuiRenderScreen`.
   - Contrato SDUI controla la pantalla completa.

Diseno actual de Home (estructura):
- **Card de usuario** con estadisticas de partidas.
- **Modalidades de juego** en un **carrusel**:
  - 1 vs maquina
  - 1 vs 1
  - 2 vs 2
- **Card de tienda** (CTA hacia tienda).
- **Tabs inferiores**: HOME / HISTORIAS / CHAT (por ahora solo HOME activa).

Navegacion inferior:
- HomeScreen usa navegacion inferior.
- Es posible combinar Home clasico con tabs SDUI (ej. HISTORIAS SDUI) y tabs clasicos (ej. CHAT clasico).

Ejemplo (Compose + Tabs):
- Tab HOME:
  - Si `sdui_home_enabled` -> `MagicsDuiRenderScreen(screenId="home")`
  - Si no -> `HomeScreen` clasico
- Tab HISTORIAS:
  - Si `sdui_stories_enabled` -> `MagicsDuiRenderScreen(screenId="stories")`
  - Si no -> `StoriesScreen` clasico
- Tab CHAT:
  - Siempre `ChatScreen` clasico

Flag propuesto:
- `sdui_home_enabled` (true -> SDUI, false -> clasico).

Regla:
- La decision se toma antes de navegar a Home.
- Si SDUI falla, fallback a Home clasico.

## 10. Rutas/Deeplinks/Make
Rutas base (clasicas):
- `app://home`
- `app://home/stories`
- `app://home/chat`

Rutas SDUI directas (para pruebas):
- `app://sdui?id=home`
- `app://sdui?id=stories`
- `app://sdui?id=login`
- `app://sdui?id=force-update`

Regla de resolucion:
- La decision por flag ocurre **antes** de navegar.
- El router define el `screenId` y navega a `app://sdui?id=<screenId>` cuando corresponde.

Ejemplos de combinacion:
1) **Todo clasico**:
   - `HOME -> app://home`
   - `HISTORIAS -> app://home/stories`
   - `CHAT -> app://home/chat`
2) **Home SDUI, Historias y Chat clasicos**:
   - `HOME -> app://sdui?id=home` (flag `sdui_home_enabled`)
   - `HISTORIAS -> app://home/stories`
   - `CHAT -> app://home/chat`
3) **Home clasico, Historias SDUI, Chat clasico**:
   - `HOME -> app://home`
   - `HISTORIAS -> app://sdui?id=stories` (flag `sdui_stories_enabled`)
   - `CHAT -> app://home/chat`

Make (si aplica):
- `make run-sdui-screen-home` -> abre `screenId=home`.
- `make run-sdui-screen-stories` -> abre `screenId=stories`.
- `make run-sdui-screen-login` -> abre `screenId=login`.

## 11. Errores, fallback y logging
Reglas de error:
- Errores criticos -> fallback a pantalla clasica equivalente con registro en Toad.
- Warnings -> log estructurado (id, type, reason).
- Si el fetch falla -> usar ultimo schema valido o fallback a pantalla clasica equivalente con registro en Toad.

Registro en Toad:
- Rechazos de contrato (version, screenId, root, guardrails) se registran en Toad.
- Warnings de parser/renderer se registran en Toad con severidad `warning`.
- Fallo de fetch/parse y fallback a clasico se registran en Toad.

Registro en Inkribbon:
- Navegaciones SDUI y fallback a clasico deben quedar como eventos en Inkribbon para reproducibilidad de sesion.
- Render exitoso de pantalla SDUI debe registrarse como evento de render en Inkribbon.
- Render exitoso de pantalla clasica (Screen) debe registrarse como evento en Inkribbon.

## 12. Testing/QA
Checklist minimo:
- `HomeScreen` clasico funciona sin SDUI.
- `MagicsDuiHome` renderiza con `screenId=home`.
- Flag `sdui_home_enabled` alterna correctamente.
- Fallback a clasico si SDUI falla.
- Mientras Inkribbon/Toad no existan, usar dos abstracciones: `SduiToadRecorder` y `SduiInkribbonRecorder`, mas un `SduiLogger` para logs estructurados.
- Cuando existan Inkribbon/Toad, estos recorders se conectan a los modulos reales sin cambiar el dominio.