# Plantilla de historia tecnica (TA)

## Objetivo
- Resultado esperado y alcance exacto.

## Rama
- `feat/ht-xx-<slug>`

## Alcance
- Incluye.
- Excluye.

## Diseño
- Decisiones clave.
- Contratos/formatos si aplica.
- Validaciones y criterios.

## Entregables (doc)
- Lista de documentos actualizados/creados.

## Verificacion
- Comandos o criterios concretos.

## No incluido
- Explicitamente lo que queda fuera.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ht-xx): <titulo> (#FXX)`

**Incluye:**
- Documento TA actualizado.
- Backlog si cambia el orden.
- ADR si corresponde.

**Checklist:**
- [ ] Solo documentacion (sin codigo).
- [ ] Enlace a la epica correspondiente.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos (si aplica).
