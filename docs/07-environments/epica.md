# Épica 7 — Selector de Ambientes

## Objetivo
Permitir cambiar de ambiente (mock/dev/prod) en el cliente sin acoplarlo a flags ni SDUI, con persistencia local y reconfiguración segura de endpoints. Incluye UI de selector (organismo cliente), lógica de dominio y atajos Make.

## Entregables
- Modelo de dominio de ambiente (`EnvValue`, `EnvConfig`) y servicio de resolución.
- Persistencia local de ambiente elegido (Settings MP) y carga en startup.
- UI de selector (organismo Dui) con i18n/accesibilidad.
- Hooks de reconfiguración: network (Ktor base URL), providers (flags, SDUI fetch), monitor de estado.
- Comandos Make/deeplinks para abrir selector sin interacción manual.
- Plan de pruebas y observabilidad.

## Alcance
- Plataformas: Android/iOS; Web consume mock en MVP.
- Ambientes soportados: mock/dev/prod; extensible.
- Seguridad: proteger selección en builds release prod (ocultar o requerir guard).
- DDD: dominio define `EnvProvider` y `EnvRepository`; infra aplica a red/flags/SDUI.

## Fuera de alcance
- Selector como SDUI: es cliente, no se sirve desde backend.
- Edición de endpoints en runtime (solo selección de preconfigurados).

## Orden sugerido
1) F0 Dominio/contratos → 2) F1 Persistencia/config → 3) F2 UI selector → 4) F3 Reconfiguración de servicios → 5) F4 Make/deeplinks → 6) F5 Testing/observabilidad.
