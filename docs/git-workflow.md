# Flujo de Trabajo Git

El flujo de ramas, convención de commits y PRs debe respetarse en cada cambio del proyecto.

## Estructura de Ramas
- Rama principal: `main`.
- Ramas feature desde `main`; se fusionan a `main` al terminar.

### Tipos de Ramas
| Tipo | Prefijo | Descripción | Ejemplo |
|------|---------|-------------|---------|
| Feature | `feat/` | Nuevas características o funcionalidades | `feat/notifications` |
| Fix | `fix/` | Corrección de errores | `fix/login-crash` |
| Documentation | `docs/` | Cambios en la documentación | `docs/api-reference` |
| Style | `style/` | Formato/estilo sin cambiar lógica | `style/lint-fixes` |
| Refactor | `refactor/` | Mejora interna sin cambio funcional | `refactor/authentication-flow` |
| Test | `test/` | Añadir/corregir pruebas | `test/login-integration` |
| Chore | `chore/` | Build/herramientas | `chore/gradle-update` |
| Hotfix | `hotfix/` | Fix urgente a producción | `hotfix/security-vulnerability` |
| Release | `release/` | Preparación de versión | `release/1.0.0` |

### Flujo de Trabajo
1. Crear rama desde `main`: `git checkout -b feat/<nombre> main`
2. Desarrollar y hacer push: `git push -u origin feat/<nombre>`
3. Abrir PR hacia `main`
4. Tras revisión/aprobación, fusionar a `main`

## Commits (Conventional Commits adaptado)
- Formato obligatorio: `<tipo>(<scope>): <descripción breve en minúsculas>`
- Cuerpo opcional con bullets técnicos; incluir `(#PR)` si aplica.
- Tipos en minúsculas; scope para HT usa `ht-XX`.
- Descripción ≤ 50 chars, minúsculas.

### Scopes sugeridos
- Historias técnicas: `ht-XX`
- Áreas: `auth`, `notif`, `nav`, `ui`, `config`, `test`, `docs`, `build`, `ci`, etc.

### Ejemplos correctos
```
feat(ht-09): developer panel with fcm topics
- panel con 9 herramientas y navegación uri-based
- pruebas optimizadas (2 min)
(#13)

fix(auth): resolve permission denied in firestore
(#11)

docs(testing): fcm reception testing strategy
```

### Ejemplos incorrectos
- Capitalización errónea (`Feat/ht 08 ...`)
- Scope mal formateado (`feat(HT-17-NOTIF)`)
- Falta de PR o descripción en mayúsculas.

## Pull Requests
1. Crear PR hacia `main`.
2. Completar plantilla de PR (descripción, tipo de cambio, pruebas, checklist).
3. Asignar al menos 1 revisor.
4. CI/CD debe pasar.
5. Fusión: usar "Squash and merge".

## Resolución de Conflictos
- Rebase frecuente con `main`: `git fetch origin && git rebase origin/main`.
- Resolver conflictos y continuar con `git rebase --continue`; usar merge solo si el rebase es complejo.

## Versionado
- SemVer: `MAJOR.MINOR.PATCH`.
- Etiquetado: `git tag -a v1.0.0 -m "Versión 1.0.0"`; `git push origin v1.0.0`.

## Consideraciones adicionales
- Commits atómicos.
- No commits directos a `main`.
- Hooks (pre-commit/pre-push) recomendados.
- Proteger `main` requiere PR y aprobación.
