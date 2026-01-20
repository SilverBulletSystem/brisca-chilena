# TA-STORAGE-F1 — Wrapper Settings MP

## Navegacion
- [Volver a Epica](09-storage/epica.md)
- [Volver a Backlog](09-storage/backlog.md)

## Rama
- `feat/ht-storage-f1-settings`

## Objetivo
Diseñar un wrapper tipado sobre Settings Multiplatform con claves centralizadas y namespaces por módulo (flags/env/etc), evitando acoplar UI a la implementación.

## Alcance
- Tipos soportados: bool, int, double, string.
- Namespaces: prefijos por módulo (`flags.`, `env.`, `net.`, etc.).
- API sugerida:
  - `getBoolean(key, default)`, `putBoolean(key, value)` (análogo para otros tipos).
  - Helpers para leer sets comunes (ej. ambiente, TTL flags).
- Política de claves: lista única de claves conocidas para evitar colisiones.
- Errores: valores corruptos → usar default y log warning.

## Entregables (documento)
- API del wrapper, namespaces y lista de claves.

## Verificación futura
- Wrapper definido y listo para implementación infra. 