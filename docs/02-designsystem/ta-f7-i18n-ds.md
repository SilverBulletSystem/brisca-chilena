# TA-DS-F7 — i18n estricto en Design System

## Rama
- `feat/ht-ds-f7-i18n`

## Objetivo
Definir pautas para asegurar que ningún componente del DS hardcodea strings y que todo texto proviene de interfaces/contratos de strings (ES/EN). Sin implementación.

## Alcance
- Reglas de uso de strings en componentes Dui (átomos/moléculas/organismos).
- Ejemplo de cómo inyectar strings en DS desde las interfaces de cada pantalla/feature.

## Diseño (doc)
1) Regla principal
   - Ninguna string literal en componentes DS; recibir contratos de strings desde la capa de presentación.
2) Interfaces
   - Documentar que cada feature/pantalla expone su interfaz de strings (`SplashStrings`, `LoginStrings`, etc.) y el DS consume textos via props.
3) Ejemplo
   - Componente DS con props `titleText`, `ctaText`, `errorText` provenientes de la interfaz de strings del feature.
4) Composición
   - Nav/bitácoras: strings de títulos, secciones y labels también deben venir de contratos.

## Entregables (documento)
- Pauta i18n para DS con ejemplo de props y origen de strings.
- Checklist:
  - [ ] Sin strings literales en DS.
  - [ ] Props de texto provienen de interfaces de strings por pantalla/feature.

## No incluido
- Implementación de strings ni wiring real.
