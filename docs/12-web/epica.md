# Épica 12 — Web admin (mockserver, sin juego)

## Objetivo
Definir el alcance mínimo para el target web (admin) en el MVP: consumo de mockserver, vistas admin básicas (flags/ambientes/SDUI samples) reutilizando DS/SDUI, sin juego web ni Firestore. Documentar configuración de build/deploy y límites.

## Entregables
- Alcance funcional web admin (flags/ambientes/SDUI samples).
- Configuración de build/deploy web (Make/GH Actions) reutilizando base.
- Consumo de mockserver para data; sin Firestore en web.
- Adaptaciones de DS/SDUI para web (hover/inputs).
- Smokes básicos en web.

## Alcance
- Web solo admin en MVP: panel mínimo para ver flags/ambientes y bitácora/SDUI samples.
- Backend: mockserver exclusivamente (sin Firestore).
- Reuso: DS/SDUI/Make existentes donde aplique.

## Fuera de alcance
- Juego web, chat/juego en browser.
- Backend real para web (equipo 2 futuro).

## Orden sugerido
1) F0 Alcance/contratos web admin → 2) F1 Config build/deploy web → 3) F2 Adaptaciones DS/SDUI para web → 4) F3 Smokes → 5) F4 Make/atajos → 6) F5 Testing/observabilidad. 
