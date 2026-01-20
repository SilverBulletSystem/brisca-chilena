# TA-DS-F7 — i18n estricto en Design System

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f7-i18n`

## Objetivo
Definir pautas estrictas para asegurar que ningún componente del DS hardcodea strings y que todo texto proviene de interfaces/contratos de strings (ES/EN), sin decisiones abiertas para el dev. Sin implementación de código.

## Alcance
- Reglas de uso de strings en componentes Dui (átomos/moléculas/organismos).
- Ejemplo de cómo inyectar strings en DS desde las interfaces de cada pantalla/feature.
- Definición de interfaces mínimas para bitácoras DS.
- Checklist de verificación i18n por categoría.

## Dependencias
- Epica 1 (i18n base).
- TA-DS-F1 (rutas/bitácoras).

## Relacion con TA-F6 i18n base
- **TA-F6 (Fundamentos)** define el mecanismo minimo (AppLanguage, StringsProvider, Splash).
- **TA-DS-F7 (Design System)** define reglas estrictas para que todos los componentes y bitacoras consuman esos contratos sin strings literales.
- No hay duplicidad: F6 crea la base tecnica; F7 define las reglas de uso en el DS.

## Modelo de dominio
- **StringsContract**: interfaz de strings por pantalla.
- **BitacoraStrings**: interfaz específica para bitácoras del DS.

## Contratos y datos
- **Regla 0**: ningún texto visible proviene de literales en el DS.
- **Regla 1**: todo texto en DS entra por props o interfaces de strings.
- **Regla 2**: placeholders también deben venir de interfaces.
- **Regla 3**: `contentDescription` siempre desde strings.

## Diseño (doc)
### 1) Regla principal (obligatoria)
- Ninguna string literal en DS: **todo texto** (títulos, botones, labels, errores, vacíos, placeholders, accesibilidad) debe venir de interfaces.

### 2) Interfaces por pantalla (interfaces segregadas)
- Cada pantalla expone su interfaz:
  - `SplashStrings`, `LoginStrings`, `HomeStrings`, etc.
- El DS recibe textos vía props, nunca resuelve idioma internamente.

### 3) Interfaces mínimas para bitácoras DS
Definir interfaces mínimas para pantallas de bitácora:

**BitacoraThemeStrings**
- `title`
- `sectionDefaultLight`
- `sectionDefaultDark`
- `sectionDeuteranopiaLight`
- `sectionDeuteranopiaDark`
- `sectionTritanopiaLight`
- `sectionTritanopiaDark`
- `sectionAchromatopsiaLight`
- `sectionAchromatopsiaDark`
- `sectionHighContrastLight`
- `sectionHighContrastDark`
- `descriptionDefault`
- `descriptionDaltonic`

**BitacoraAtomsStrings**
- `title`
- `sectionTypography`
- `sectionIcons`
- `sectionColors`
- `sectionActions`
- `sectionLayout`
- `sectionChipsBadges`
- `sectionProgress`
- `sectionInputs`

**BitacoraMoleculesStrings**
- `title`
- `sectionInputs`
- `sectionLists`
- `sectionMedia`
- `sectionFeedback`
- `sectionSecondaryNav`
- `sectionIdentity`
- `sectionChatStatus`

**BitacoraOrganismsStrings**
- `title`
- `sectionGeneral`
- `sectionGame`
- `sectionChat`
- `sectionFlags`

### 4) Ejemplo de uso (contrato)
Un componente DS debe recibir textos desde props:
- `titleText`, `bodyText`, `errorText`, `ctaText`, `contentDescription`.
- Si el componente es reutilizable, el texto siempre viene “desde afuera”.

### 5) Composición
- Bitácoras: títulos, secciones y labels deben venir de interfaces de strings de bitácora.
- Composables internos no pueden crear textos por defecto.

### 6) Checklist i18n (obligatorio)
**Átomos**
- [ ] `DuiText` recibe texto desde strings.
- [ ] `DuiIcon` tiene `contentDescription` desde strings o `null` explícito si decorativo.

**Moléculas**
- [ ] `DuiTextField` usa `label`, `placeholder`, `errorText` desde strings.
- [ ] `DuiDialog`/`DuiBottomSheet` reciben `title`, `body`, `cta` desde strings.

**Organismos**
- [ ] `DuiTopBar`/`DuiBottomNav` etiquetas desde strings.
- [ ] `DuiEmptyState`/`DuiErrorState` textos desde strings.
- [ ] `DuiMessageList` y `DuiSupportForm` labels desde strings.

## Entregables (documento)
- Guía i18n para DS con reglas, contratos y checklist.
- Interfaces mínimas de bitácoras DS.

## Verificación futura
- QA puede validar que no existen textos literales en DS.
- Todos los textos visibles y `contentDescription` provienen de interfaces.

## No incluido
- Implementación de strings ni wiring real.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ds-f7): i18n estricto ds (#2)`

**Incluye:**
- Actualización de `docs/02-designsystem/ta-f7-i18n-ds.md`.
- Entrada en `CHANGELOG.md` bajo `[Unreleased]`.

**Checklist:**
- [ ] Solo documentación (sin código).
- [ ] Enlace a la épica `docs/02-designsystem/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.