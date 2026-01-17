# Épica 11 — Audio ES/EN

## Objetivo
Diseñar el soporte de audio para el MVP con dos idiomas (ES/EN), empaquetado inicial en el artefacto (mainset de juego) y dejar documentado un spike opcional para descarga en tiempo de ejecución. Debe integrarse con DS/SDUI (hooks de reproducción) y respetar i18n y toggles de sonido.

## Entregables
- Catálogo de eventos sonoros por flujo (juego, navegación, feedback).
- Estrategia de empaquetado dual ES/EN y selección por locale.
- Toggle de sonido (UI + flag) y fallback silencioso seguro.
- Hooks de reproducción (interfaz de dominio) y wiring a triggers de UI/SDUI.
- Spike opcional de descarga diferida (si se incluye).
- Plan de pruebas y observabilidad.

## Alcance
- Plataformas: Android/iOS (cliente). Web no prioritaria en este MVP.
- Audio incluido en el artefacto inicial; descarga runtime opcional como spike.
- Sonidos alineados a eventos de juego (barajar, repartir, jugadas, renuncio/vale, logros) y a navegación/errores.
- DDD: dominio define interfaz de audio; infra provee reproductor y assets.

## Fuera de alcance
- Motor de audio avanzado/3D; mezcla compleja.
- Descarga de packs personalizados (solo spike opcional documentado).

## Orden sugerido
1) F0 Dominio/contratos de audio → 2) F1 Catálogo ES/EN + empaquetado → 3) F2 Toggle/UX + flags → 4) F3 Hooks de reproducción (UI/SDUI) → 5) F4 Spike descarga opcional → 6) F5 Testing/observabilidad. 
