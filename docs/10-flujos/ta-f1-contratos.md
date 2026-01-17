# TA-FLUJOS-F1 — Contratos SDUI por flujo

## Rama
- `feat/ht-flujos-f1-contratos`

## Objetivo
Enumerar los flujos/pantallas del MVP y mapear para cada uno los organismos SDUI necesarios, props clave y dependencias de flags/env/auth, usando la taxonomía Dui y MagicsDui.

## Alcance
- Flujos incluidos:
  - Splash/errores/updates/mantenimiento/sin internet.
  - Onboarding/Auth: login, crear cuenta, recuperar, verificar, cerrar sesión.
  - Home + menú lateral.
  - Perfil, Configuraciones, Notificaciones.
  - Reglas, Ranking, FAQ.
  - Juego 1v1, juego 2v2 (barajar, repartir, renuncio/vale, marcador, sala, pausa, historial, logros, capote).
  - Chat, Soporte.
  - Monitores/Bitácora/Flags UI.
  - Themas (tema por defecto).
- Por flujo:
  - Pantallas y organismos Dui requeridos (lista por tipo SDUI).
  - Props mínimos: ids, labels (keys), flags/env necesarios.
  - Datos de sesión/auth requeridos (ej. token/uid) — como dependencia de dominio, no en SDUI.
- Dependencias
  - Flags que habilitan/ocultan secciones.
  - Ambiente (mock/dev/prod) si afecta endpoints de datos.

## Entregables (documento)
- Tabla por flujo con organismos SDUI, props clave y dependencias.

## Verificación futura
- Contratos listos para producir JSON Mockoon por flujo. 
