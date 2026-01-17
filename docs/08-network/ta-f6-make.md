# TA-NET-F6 — Make/atajos Network

## Rama
- `feat/ht-net-f6-make`

## Objetivo
Definir comandos Make para ejecutar smokes de red y mostrar configuración actual por FLAVOR sin intervención manual.

## Alcance
- Comandos sugeridos:
  - `make net-smoke FLAVOR=mock|dev|prod` → ejecuta smokes (TA-F5).
  - `make net-info FLAVOR=mock|dev|prod` → imprime config (baseUrl, timeouts, loggingLevel).
- Validaciones:
  - En release prod, comandos deben respetar guard (sin exponer credenciales).
  - Salida clara y i18n donde aplique.

## Entregables (documento)
- Tabla de comandos/variables y comportamiento esperado.

## Verificación futura
- Atajos reproducibles y seguros. 
