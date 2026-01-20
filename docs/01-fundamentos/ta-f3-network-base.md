# TA-F3 — Network base (URL única temporal)

## Navegacion
- [Volver a Epica](01-fundamentos/epica.md)
- [Volver a Backlog](01-fundamentos/backlog.md)

## Objetivo
Diseñar la primera integración de red para poder **probar** el cliente Ktor (versión más reciente), usando la API pública de Rick and Morty, con detalle de implementación esperado.

## Rama
- Crear desde `main`: `feat/ht-f3-network-base`.

## Alcance
- Cliente Ktor base con versión más reciente (distinta al proyecto base).
- Una llamada simple GET a la API de Rick and Morty para validar conectividad.
- Sin configuración de ambientes en esta historia.
- Agregar Ktor al Gradle y crear el cliente real.

## Diseño
1) API pública de prueba:
   - Usar Rick and Morty API:
     - Base: `https://rickandmortyapi.com`
     - Endpoint de prueba: `/api/character`
   - Esta API se usa solo para validar conectividad del cliente.
2) Ktor base:
   - Cliente en `core/network` con Ktor en su versión más reciente.
   - Usar URL absoluta para la prueba (sin `BASE_URL` ni ambientes).
   - GET simple a `/api/character`.
   - La respuesta se valida solo para comprobar conexión (no parseo).
3) Gradle:
   - Agregar dependencias de Ktor **3.3.3** en `libs.versions.toml` y `composeApp/build.gradle.kts` (última estable).
   - Nota: el proyecto base usa `3.1.3` por bug de D8; aquí se usa **3.3.3** como última estable.
   - En las notas de versiones 3.2.x–3.3.x no aparece mención explícita al bug D8; sí hay issues Android/R8 (3.2.1/3.3.2), por lo que la validación sigue siendo obligatoria.
   - Incluir engine Android (`ktor-client-okhttp`) y `ktor-client-content-negotiation` (aunque no se use parseo aún).
4) Koin:
   - Registrar el `HttpClient` en `applicationModule` de HT-F2.
   - Inyectar el cliente en la clase/función de prueba de red.
5) Verificación:
   - Output en logs confirmando status `200` y respuesta no vacía.

## Entregables (doc)
- Este TA con el detalle de implementación.
- Checklist de validación futura:
  - [ ] GET a `/api/character` responde 200.
  - [ ] Ktor actualizado a **3.3.3**.
  - [ ] Se valida que la versión elegida no tiene el bug D8 del base.
  - [ ] Dependencias Ktor declaradas en Gradle.
  - [ ] `HttpClient` registrado en Koin.

## No incluido
- Ambientes `mock/dev/prod`.
- Mockoon CLI.
- Lógica de dominio/feature.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ht-f3): network base rick and morty (#F3)`

**Incluye:**
- Creación de `docs/01-fundamentos/ta-f3-network-base.md`.
- Actualización de `docs/01-fundamentos/backlog.md`.

**Checklist:**
- [ ] Solo documentacion (sin codigo).
- [ ] Enlace a la epica `docs/01-fundamentos/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.