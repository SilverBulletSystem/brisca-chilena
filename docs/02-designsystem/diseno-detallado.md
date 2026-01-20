# Diseno detallado - Epica 2 Design System (Nivel 4)

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## 1. Contexto y objetivo
El Design System (Dui) es la base visual y funcional para SDUI y UI nativa. Define tokens, theming, accesibilidad e i18n, y entrega bitácoras para inspección y QA.

Incluye:
- Tokens (color, tipografía, dimensiones, shapes).
- Theming con variantes (incluye tokens para daltonismo).
- Catálogo de átomos, moléculas y organismos Dui.
- Bitácoras navegables por categoría con atajos Make.
- i18n estricto en componentes DS.

Excluye:
- Pantallas de negocio y flujos de usuario.
- Contratos SDUI y data providers.

## 2. Dependencias
- Epica 1 (Fundamentos): i18n base, ThemeDimens y reglas de calidad.
- ADRs de arquitectura y estilo (Atomic Design, i18n, ThemeDimens).

## 3. Modelo de dominio
- **Token**: valor atómico reutilizable (color, tipografía, espaciado, shape).
- **Theme**: conjunto de tokens agrupados por rol (default + variantes).
- **ComponentSpec**: definición de props/estados de un componente Dui.
- **BitacoraSection**: agrupación de componentes por categoría.
- **AccessibilityRule**: regla verificable por componente (contraste, tamaño mínimo, foco).

## 4. Contratos y datos
- **Naming de tokens**:
  - Colores: `color.primary`, `color.onPrimary`, `color.surface`, `color.error`.
  - Tipografía: `text.title`, `text.subtitle`, `text.body`, `text.caption`.
  - Dimensiones: `dimens.spacing.*`, `dimens.radius.*`.
- **Estructura de temas**:
  - `default`, `daltonicA`, `daltonicB` (nombres por definir en TA-DS-F1).
- **Interfaces de strings**:
  - Por bitácora/pantalla DS (TA-DS-F6).

## 5. UI y UX
- Bitácoras con lista de categorías y detalles por componente.
- Estados por componente: normal, disabled, loading, error (si aplica).
- Accesibilidad: foco visible, contrastes mínimos, tamaños táctiles.

## 6. Flujos y secuencias
1) Abrir bitácora por comando Make.
2) Seleccionar categoría (átomos, moléculas u organismos).
3) Ver variantes y estados del componente.
4) Validar checklist de accesibilidad e i18n.

## 7. Seguridad y privacidad
- No maneja PII ni datos sensibles.

## 8. Performance
- Bitácoras con listas perezosas (lazy) para grandes catálogos.
- Evitar previews pesadas simultáneas.

## 9. Observabilidad
- Logs mínimos en modo debug para navegación de bitácoras.

## 10. Testing
- Checklist manual por componente (estados + accesibilidad + i18n).
- Smoke de navegación de bitácoras.

## 11. Criterios de aceptacion
- Tokens y themes documentados con naming consistente.
- Catálogo de átomos, moléculas y organismos completo y versionado.
- Bitácoras navegables por categoría con comandos Make.
- Checklist de accesibilidad e i18n definido y aplicado.

## 12. Pendientes y riesgos
- Definir naming final de variantes de tema.
- Riesgo de duplicación de componentes sin reglas claras.