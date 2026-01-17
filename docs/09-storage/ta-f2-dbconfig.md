# TA-STORAGE-F2 — Config DB / SQDelight

## Rama
- `feat/ht-storage-f2-dbconfig`

## Objetivo
Definir la configuración de la base de datos SQDelight por plataforma/ambiente: nombre, ruta, versión inicial y alcance de tablas (State/External Record en MVP).

## Alcance
- `DbConfig` por env (mock/dev/prod) y plataforma (Android/iOS):
  - Nombre de DB (ej. `brisca.db`).
  - Ruta/driver según plataforma.
  - Versión inicial (1) con tablas de Inkribbon y Toad; otras tablas futuras documentadas.
- Migración mínima:
  - Estrategia de versionado; cambios futuros via ALTER/migraciones.
- Seguridad:
  - Sin cifrado en MVP; dejar nota para cifrado futuro.

## Entregables (documento)
- Tabla de config por env/plataforma, versión inicial y política de migración mínima.

## Verificación futura
- Config lista para factories/DI y smokes. 
