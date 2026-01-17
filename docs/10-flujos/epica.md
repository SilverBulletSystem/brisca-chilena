# Épica 10 — Flujos del cliente (SDUI + DS)

## Objetivo
Documentar los flujos del cliente soportados en el MVP, servidos vía SDUI y renderizados con el Design System. Incluir dominio de navegación/estado, contratos SDUI por flujo y atajos de pruebas.

## Entregables
- Lista de flujos y sus pantallas/nodos SDUI requeridos.
- Contratos SDUI por flujo (referencia a MagicsDui) y dependencias de domain (auth/sesión).
- Handlers de intents/navegación y validaciones de sesión/versión/conectividad.
- Make/deeplinks para abrir cada flujo rápidamente.
- Plan de pruebas por flujo.

## Alcance
- Flujos incluidos (MVP): Splash/Errores/Updates; Onboarding/Auth (login/crear/recuperar/verificar/cerrar sesión); Home+Menú lateral; Perfil; Configuraciones; Notificaciones; Reglas; Ranking; FAQ; Juego 1v1; Juego 2v2; Chat; Soporte; Monitores/Bitácora/Flags UI; Themas (tema por defecto).
- Ambientes: mock/dev/prod (SDUI apunta a mock en MVP).
- DDD: validaciones de sesión/versión en dominio; UI solo presenta SDUI + DS.

## Fuera de alcance
- Tienda, publicidad, Bluetooth, multimedia manager (quedan fuera del MVP).
- Backend real para Web (solo mockserver en MVP).

## Orden sugerido
1) F0 Dominio de navegación/guards → 2) F1 Contratos SDUI por flujo → 3) F2 Handlers/intents → 4) F3 Make/deeplinks → 5) F4 Testing por flujo. 
