# TA-DS-F6 — Accesibilidad preparada

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f6-accessibility`

## Objetivo
Definir criterios completos de accesibilidad del Design System (contraste, foco/estados, tamaños mínimos, motion y contenido accesible) para que ningún equipo tome decisiones adicionales. Sin implementación de código.

## Alcance
- Contraste y roles de color por tema (incluye variantes daltónicas).
- Estados: focus/hover/pressed/disabled/selected/error con reglas visuales.
- Tamaños mínimos: touch target, tipografía legible y alturas mínimas.
- Motion: reglas de “reduce motion” y duraciones.
- Contenido accesible: `contentDescription`, labels y orden.

## Dependencias
- TA-DS-F2 (tokens y theming).
- TA-DS-F3/TA-DS-F4 (catálogo de componentes para checklist).

## Modelo de dominio
- **AccessibilityRule**: regla verificable (ratio, tamaño, estado).
- **ContrastRule**: ratio por tipo de contenido.
- **FocusRule**: apariencia de foco y navegación.
- **MotionRule**: reglas de animación/tiempos.

## Contratos y datos
- Todos los tamaños referenciados a `ThemeDimens` (sin `.dp` hardcodeado).
- Los colores se toman de `MaterialTheme.colorScheme`.
- Las bitácoras deben incluir validación visual de estados y foco.

## Diseño (doc)
### 1) Contraste (obligatorio)
- **Texto normal**: ratio mínimo **4.5:1**.
- **Texto grande** (>=24sp o >=18sp bold): ratio mínimo **3:1**.
- **Iconos y elementos no-texto**: ratio mínimo **3:1** contra su fondo.
- **Estados error/warning/success**: mantener **4.5:1** para texto y **3:1** para iconos.
- **Daltónicos**: solo se reemplazan `primary/secondary` y sus containers/on* (definido en TA-DS-F2); deben mantener ratios anteriores.

### 2) Focus, pressed, disabled
- **Focus ring**:
  - Grosor: `ThemeDimens.BORDER_WIDTH`.
  - Color: `MaterialTheme.colorScheme.primary`.
  - Offset visual: 2dp (usar token `ThemeDimens.SPACE_2`).
  - Radio: igual al shape del componente.
- **Pressed**: overlay con `primary` al **12%** de alpha.
- **Disabled**:
  - Contenido (texto/icono): alpha **38%**.
  - Fondo: alpha **12%**.
- **Selected**: usar `secondaryContainer` con texto `onSecondaryContainer`.

### 3) Tamaños mínimos (touch + lectura)
- **Touch target**: mínimo **48dp** (usar `ThemeDimens.TOUCH_TARGET_MIN`).
- **Inputs**: `ThemeDimens.INPUT_HEIGHT_SINGLE` y `ThemeDimens.INPUT_HEIGHT_MULTI`.
- **Dropdowns**: `ThemeDimens.DROPDOWN_HEIGHT`.
- **Iconos interactivos**: `ThemeDimens.ICON_MD` como tamaño base.
- **Line height recomendado**:
  - Títulos: 1.2x
  - Body: 1.4x
  - Caption: 1.3x

### 4) Motion (reduce motion)
- **Duraciones estándar**:
  - Short: **150ms**
  - Medium: **250ms**
  - Long: **350ms**
- **Reduce motion ON**:
  - Evitar animaciones de entrada/salida.
  - Reemplazar por cambios de estado instantáneos (0ms).
- **Transiciones de estado**: solo opacidad/escala leve (<=0.98) cuando no esté activo reduce motion.

### 5) Contenido accesible (TalkBack/VoiceOver)
- Todo `DuiIcon` requiere `contentDescription` o `null` explícito si es decorativo.
- Botones y acciones deben tener label textual (visible o accesible).
- Campos deben tener label persistente (no solo placeholder).
- Orden de foco: arriba → abajo, izquierda → derecha.

### 6) Checklist por componente (obligatorio en bitácoras)
**Átomos**
- `DuiText`: contraste 4.5:1; tamaños y line-height correctos.
- `DuiButton`/`DuiFab`: focus ring visible; touch 48dp; estado disabled correcto.
- `DuiIcon`: contentDescription válido; contraste 3:1 si es informativo.

**Moléculas**
- `DuiTextField`: label persistente; error con texto legible; foco visible.
- `DuiCheckbox`/`DuiRadio`/`DuiSwitch`: estados claros; touch 48dp.
- `DuiDialog`/`DuiBottomSheet`: foco inicial en título; CTA accesibles.

**Organismos**
- `DuiTopBar`/`DuiBottomNav`: ítems con label; estado seleccionado claro.
- `DuiList`/`DuiGrid`: foco por ítem; navegación secuencial.
- `DuiMessageList`: lectura lineal; timestamps legibles.
- `DuiFlagList`: toggles con label y estado on/off accesible.

## UI y UX
- Las bitácoras deben incluir un bloque “Accesibilidad” con checklist visual por sección.
- Ningún componente interactivo puede quedar sin estado focus visible.

## Flujos y secuencias
1) Abrir una bitácora.
2) Revisar contraste y estados en cada sección.
3) Validar tamaños mínimos y orden de foco.

## Seguridad y privacidad
- No aplica.

## Performance
- No aplica.

## Observabilidad
- No se requieren eventos.

## Testing
- Verificación manual por checklist.
- Validación visual de estados focus/disabled.

## Criterios de aceptacion
- Reglas de contraste y tamaños explícitas y verificables.
- Checklist por componente completo.
- Reglas de focus/disabled y motion definidas.

## Entregables (documento)
- Guía de accesibilidad con reglas numéricas.
- Checklist por categoría de componente.

## Verificación futura
- QA puede evaluar accesibilidad sin decisiones adicionales.

## No incluido
- Implementación en código.
- Auditorías automáticas o tooling.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ds-f6): accesibilidad ds (#2)`

**Incluye:**
- Actualización de `docs/02-designsystem/ta-f6-accessibility.md`.
- Entrada en `CHANGELOG.md` bajo `[Unreleased]`.

**Checklist:**
- [ ] Solo documentación (sin código).
- [ ] Enlace a la épica `docs/02-designsystem/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.