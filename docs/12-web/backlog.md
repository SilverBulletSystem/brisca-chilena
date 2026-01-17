# Backlog técnico — Épica 12 Web admin

Referencia: `12-web/epica.md`. Solo documentación, sin implementación.

## F0 – Alcance/contratos web admin
- Definir vistas mínimas: monitor de flags (read-only), selector de ambiente (cliente), bitácora/SDUI samples (navegables).
- Contratos de datos: consumo desde mockserver; sin Firestore en web.
- Rama: `feat/ht-web-f0-alcance`.

## F1 – Config build/deploy web
- Configuración target web (Compose Multiplatform) reutilizando proyecto base.
- Build y deploy scripts (Make/GH Actions) para web mock.
- Rama: `feat/ht-web-f1-build`.

## F2 – Adaptaciones DS/SDUI para web
- Ajustes de hover/focus/inputs en DS para web.
- Compatibilidad SDUI renderer en web (limitado a admin).
- Rama: `feat/ht-web-f2-ds-sdui`.

## F3 – Smokes web
- Smokes básicos: carga de app web, fetch a mockserver, render de flags/bitácora.
- Rama: `feat/ht-web-f3-smokes`.

## F4 – Make/atajos
- Comandos: `make web-run-mock`, `make web-smoke`.
- Rama: `feat/ht-web-f4-make`.

## F5 – Testing/observabilidad
- Checklist QA/Dev: vistas cargan, consume mockserver, sin Firestore, DS/SDUI web ok.
- Logs de errores; sin PII.
- Rama: `feat/ht-web-f5-testing`. 
