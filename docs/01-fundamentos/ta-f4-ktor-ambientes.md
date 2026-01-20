# TA-F4 — Ktor y ambientes (mock/dev/prod)

## Navegacion
- [Volver a Epica](01-fundamentos/epica.md)
- [Volver a Backlog](01-fundamentos/backlog.md)

## Objetivo
Diseñar la configuración de red con Ktor para resolver ambientes `mock/dev/prod` de forma consistente con el proyecto base, incluyendo el detalle de implementación esperado.

## Rama
- Crear desde `main`: `feat/ht-f4-ktor-ambientes`.

## Alcance
- Contrato de ambiente en `core/network`.
- Resolución de `BASE_URL` y `ENVIRONMENT_NAME` en Android.
- Lineamientos de cliente Ktor base (sin detalles de observabilidad).
- Configuración de Mockoon CLI para `mock/dev/prod` (estructura, puertos y comandos).

## Diseño
1) Contrato de ambiente:
   - Definir `NetworkEnvironment` con valores `mock`, `dev`, `prod`.
   - Exponer `baseUrl` y `environmentName` desde config.
2) Resolución por plataforma:
   - Android: desde `BuildConfig` y `strings.xml` (definidos en esta historia).
3) Android flavors/buildTypes:
   - Definir productFlavors `mock`, `dev`, `prod`.
   - `applicationIdSuffix`: `.mock`, `.dev`, (prod sin sufijo).
   - `versionNameSuffix`: `-mock`, `-dev`, (prod sin sufijo).
   - `buildTypes`: `debug` (minify false), `release` (minify según política futura).
   - `buildConfigField` por flavor: `BASE_URL`.
   - Resources por flavor: `strings.xml` con `environment_name` y `api_base_url`.
3) Cliente Ktor base:
   - Cliente en `core/network` con `baseUrl` inyectado.
   - Timeouts y headers estándar definidos como placeholders.
4) Compatibilidad:
   - Mantener nombres de claves alineados con el proyecto base.
5) Estrategia inicial de URLs (sin backend aún):
   - `mock`, `dev` y `prod` apuntan temporalmente a **Mockoon CLI** en la misma IP.
   - Se diferencian por **puerto**:
     - `mock` → `http://<LOCAL_IP>:3002`
     - `dev` → `http://<LOCAL_IP>:3003`
     - `prod` → `http://<LOCAL_IP>:3004`
   - `LOCAL_IP` es **manual** (configurado como `MOCKOON_HOST`).
   - Esto es **temporal** hasta que se desarrollen las APIs reales.
   - Cuando existan URLs reales, se reemplaza por ambiente sin cambiar el contrato.
6) Comandos Gradle por buildType:
   - `mock`: `assembleMockDebug`, `assembleMockRelease`
   - `dev`: `assembleDevDebug`, `assembleDevRelease`
   - `prod`: `assembleProdDebug`, `assembleProdRelease`
   - Uso esperado:
     - Dev diario: `mock-debug`.
     - QA: `dev-release`.
     - Prod: `prod-release`.
     - `prod-debug` solo para depurar errores puntuales.
7) Mockoon CLI (estructura y convenciones):
   - Estructura:
     - `mockserver/brisca-mockoon.json` (config principal).
     - `mockserver/README.md` (uso local).
   - Convención de puertos por ambiente:
     - `mock` → `3002`, `dev` → `3003`, `prod` → `3004`.
   - No usar `localhost` en Android (el emulador lo resuelve a sí mismo).
8) IP definida manualmente (sin auto-detección):
   - La IP se define manualmente al configurar el mockserver.
   - Fuente sugerida: `local.properties` o variable de entorno `MOCKOON_HOST`.
   - Propiedad Gradle `-PlocalIp=<IP>` para inyectar IP en build.
9) Gradle (detalle de implementación esperada):
   - `composeApp/build.gradle.kts`:
     - Leer `localIp` desde propiedad Gradle (definida manualmente).
     - Definir `MOCKOON_PORT` por ambiente (`3002/3003/3004`) y `MOCKOON_BASE_URL` usando `localIp`.
     - Agregar tarea `updateIosIp` (para el spike iOS) con `__REPLACE_*__`.
10) Android - seguridad HTTP (cleartext):
   - Requerido para consumir `http://<LOCAL_IP>` en `mock/dev/prod`.
   - `network_security_config.xml` para debug/mock/dev/prod con cleartext permitido.
   - `network_security_config_prod.xml` con cleartext deshabilitado.
   - `AndroidManifest.xml` con `android:networkSecurityConfig` via `manifestPlaceholders`.
11) Makefile (levantar ambientes):
   - `env-start-mock`, `env-start-dev`, `env-start-prod`:
     - Levantan Mockoon CLI en el puerto correspondiente (3002/3003/3004).
     - Deben usar `MOCKOON_HOST` configurado manualmente.
     - Si el puerto ya está en uso, mostrar mensaje claro: "Ambiente <env> ya está levantado (puerto <port>)".
   - `env-stop-mock`, `env-stop-dev`, `env-stop-prod`:
     - Detienen el proceso Mockoon del puerto correspondiente, si existe.
     - Si no hay proceso, mostrar: "Ambiente <env> no está levantado".
12) Makefile (probar ambientes):
   - `env-check-mock`, `env-check-dev`, `env-check-prod`:
     - Validan que `/health` responde en el puerto del ambiente.
     - Imprimen la URL actual usando `MOCKOON_HOST`.
13) Mockoon CLI (comandos requeridos):
   - `mockoon-start-mock`, `mockoon-start-dev`, `mockoon-start-prod`.
   - Comando base esperado:
     `mockoon-cli start --data mockserver/brisca-mockoon.json --hostname 0.0.0.0 --port <PORT> --daemon-off`.

## Entregables (doc)
- Este TA con contratos y lineamientos.
- Checklist de validación futura:
  - [ ] `NetworkEnvironment` definido con `mock/dev/prod`.
  - [ ] Android expone `BASE_URL`.
  - [ ] `mock/dev/prod` resuelven a Mockoon con puertos distintos mientras no exista backend real.
  - [ ] `assembleMockDebug/Release`, `assembleDevDebug/Release`, `assembleProdDebug/Release` definidos.
  - [ ] `env-start-mock|dev|prod` definidos.
  - [ ] `env-check-mock|dev|prod` definidos.
  - [ ] `env-stop-mock|dev|prod` definidos.
  - [ ] Mockoon CLI inicia sin errores.
  - [ ] `/health` responde en `mock/dev/prod`.
  - [ ] `MOCKOON_HOST` documentado como valor manual.
  - [ ] `network_security_config.xml` habilita HTTP solo en debug/mock/dev/prod.
  - [ ] `mockoon-start-mock|dev|prod` definidos.

## No incluido
- Implementación de HttpClient.
- Logging/observabilidad de red.
- iOS (se valida en el spike HT-F8).

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ht-f4): ktor ambientes base (#F4)`

**Incluye:**
- Creación de `docs/01-fundamentos/ta-f4-ktor-ambientes.md`.
- Actualización de `docs/01-fundamentos/backlog.md`.

**Checklist:**
- [ ] Solo documentacion (sin codigo).
- [ ] Enlace a la epica `docs/01-fundamentos/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.