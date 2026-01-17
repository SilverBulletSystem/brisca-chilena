# TA-STORAGE-F5 — Make/atajos Storage

## Rama
- `feat/ht-storage-f5-make`

## Objetivo
Definir comandos Make para ejecutar smokes de storage (Settings/DB) por FLAVOR sin pasos manuales.

## Alcance
- Comandos sugeridos:
  - `make storage-smoke FLAVOR=mock|dev|prod`
- Validaciones:
  - Salida clara; indicar ruta/nombre de DB usada.
  - En release prod, evitar alterar datos reales (usar claves de test y limpieza).

## Entregables (documento)
- Tabla de comandos/variables y comportamiento esperado.

## Verificación futura
- Atajos reproducibles y seguros. 
