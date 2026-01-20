# TA-DS-F1 — Navegación bitácoras

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f1-navigation`

## Objetivo
Definir la navegación de bitácoras (rutas + wiring Decompose) y comandos Make concretos para abrirlas sin interacción manual.

## Alcance
- Rutas internas por bitácora.
- Wiring Decompose para mapear URI → pantalla.
- Comandos Make concretos para abrir cada bitácora.

## Diseño (doc)
### 1) Pantallas destino (referencia)
- `ThemeCatalogScreen` (definida en TA-DS-F2).
- `AtomsBitacoraScreen` (definida en TA-DS-F3).
- `MoleculesBitacoraScreen` (definida en TA-DS-F4).
- `OrganismsBitacoraScreen` / `OrganismsGameScreen` / `OrganismsChatScreen` / `OrganismsFlagsScreen` (definidas en TA-DS-F5A-D).

### 2) Rutas internas
- Temas: `app://bitacora/theme`
- Átomos: `app://bitacora/atoms`
- Moléculas: `app://bitacora/molecules`
- Organismos: `app://bitacora/organisms`
- Organismos juego: `app://bitacora/organisms/game`
- Organismos chat: `app://bitacora/organisms/chat`
- Organismos flags: `app://bitacora/organisms/flags`

### 3) Navegación Decompose (obligatoria, diseño de implementación)

**Estructura de configuración**
- `sealed class BitacoraConfig`:
  - `object Theme`
  - `object Atoms`
  - `object Molecules`
  - `object Organisms`
  - `object OrganismsGame`
  - `object OrganismsChat`
  - `object OrganismsFlags`
- Regla: cada configuración debe mapear 1:1 a una pantalla.

**Stack y navegación**
- Crear `StackNavigation<BitacoraConfig>`.
- Definir `childStack` en el componente raíz de bitácoras:
  - `initialConfiguration = BitacoraConfig.Theme`
  - `handleBackButton = true`
  - `childFactory` mapea `BitacoraConfig` → componente/pantalla específica.

**Mapeo URI → configuración**
- Crear función pura `parseBitacoraUri(uri: String): BitacoraConfig?`.
- Reglas explícitas:
  - `app://bitacora/theme` → `BitacoraConfig.Theme`
  - `app://bitacora/atoms` → `BitacoraConfig.Atoms`
  - `app://bitacora/molecules` → `BitacoraConfig.Molecules`
  - `app://bitacora/organisms` → `BitacoraConfig.Organisms`
  - `app://bitacora/organisms/game` → `BitacoraConfig.OrganismsGame`
  - `app://bitacora/organisms/chat` → `BitacoraConfig.OrganismsChat`
  - `app://bitacora/organisms/flags` → `BitacoraConfig.OrganismsFlags`
- Fallback: si `parseBitacoraUri` retorna `null`, navegar a `BitacoraConfig.Theme`.

**API de navegación interna**
- `navigateTo(config: BitacoraConfig)` debe usar `navigation.bringToFront(config)`.
- `openUri(uri: String)`:
  - `parseBitacoraUri(uri)` y navegar a la config resultante.
  - Aplicar fallback si no coincide.

**Wiring mínimo esperado (pseudocódigo)**
```
sealed class BitacoraConfig { object Theme; object Atoms; object Molecules; object Organisms; object OrganismsGame; object OrganismsChat; object OrganismsFlags }

val navigation = StackNavigation<BitacoraConfig>()
val childStack = childStack(
  source = navigation,
  initialConfiguration = BitacoraConfig.Theme,
  handleBackButton = true,
  childFactory = { config, ctx -> /* mapear a componentes */ }
)

fun openUri(uri: String) {
  val config = parseBitacoraUri(uri) ?: BitacoraConfig.Theme
  navigation.bringToFront(config)
}
```

**Resultado esperado**
- `make run-bitacora-*` abre directamente la pantalla correspondiente sin pasos extra.
- La navegación es determinista y sin decisiones implícitas del dev.

### 4) Comandos Make (existentes y obligatorios)
- `make run-bitacora-theme`
- `make run-bitacora-atoms`
- `make run-bitacora-molecule`
- `make run-bitacora-organism`
- `make run-bitacora-organism-game`
- `make run-bitacora-organism-chat`
- `make run-bitacora-organism-flags`

### 5) Reglas
- Las rutas deben mapear 1:1 a cada comando.
- La navegación debe llevar a la pantalla correcta definida en cada TA.

## Validación Dev (rol QA)
- [ ] Ejecutar cada comando Make y verificar apertura de pantalla correspondiente.
- [ ] Confirmar que cada URI navega al `child` correcto.

## Entregables (documento)
- Lista de rutas y targets Make.
- Mapeo URI → pantalla.

## Wireframes (servilleta)
- Se definen en TA-DS-F2/F3/F4/F5.

## Verificación futura
- Comandos y rutas claros para uso por dev/QA al implementar.

## No incluido
- Diseño de pantallas de bitácora (van en TA-DS-F2/F3/F4/F5).

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ds-f1): navegacion bitacoras (#2)`

**Incluye:**
- Actualización de `docs/02-designsystem/ta-f1-navigation.md`.
- Entrada en `CHANGELOG.md` bajo `[Unreleased]`.

**Checklist:**
- [ ] Solo documentación (sin código).
- [ ] Enlace a la épica `docs/02-designsystem/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.