# Diseno detallado - Epica 2 Design System (Nivel 4)

## Contexto
El Design System es la base visual y funcional para SDUI y UI nativa. Define tokens, accesibilidad y catalogos de componentes con bitacoras.

## Alcance
- Tokens (color, tipografia, dimensiones, shapes).
- Theming con soporte para daltonicos a nivel de tokens.
- Catalogo de atomos, moleculas y organismos Dui.
- Bitacoras navegables por categoria con atajos Make.
- i18n estricto en componentes DS.

## TA-DS-F1 Tokens y theming
- Definir roles de color, tipografia, ThemeDimens y shapes.
- Estructura de temas: default + variantes daltonicas.
- Integracion documentada con ThemeManager.

## TA-DS-F2 Make shortcuts bitacoras
- Rutas internas para bitacoras.
- Targets Make para abrir bitacoras sin clicks.

## TA-DS-F3 Atomos y bitacora
- Catalogo completo de atomos Dui y estados.
- Pantalla de bitacora con secciones por categoria.

## TA-DS-F4 Moleculas y bitacora
- Catalogo completo de moleculas Dui y variantes.
- Pantalla de bitacora con props y estados.

## TA-DS-F5 Organismos y bitacoras
- Organismos generales, juego, chat/soporte, flags/monitores.
- Bitacoras por lote para evitar PRs grandes.

## TA-DS-F6 Accesibilidad
- Contraste, estados, tamanos minimos, reduce motion.
- Checklist por componente.

## TA-DS-F7 i18n estricto
- Sin strings literales en DS.
- Textos via interfaces por pantalla.
