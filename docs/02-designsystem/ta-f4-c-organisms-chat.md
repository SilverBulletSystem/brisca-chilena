# TA-DS-F4-C — Organismos de chat/soporte y bitácora

## Navegacion
- [Volver a Epica](02-designsystem/epica.md)
- [Volver a Backlog](02-designsystem/backlog.md)

## Rama
- `feat/ht-ds-f4-c-organisms-chat`

## Objetivo
Definir y crear el catálogo de organismos de chat/soporte y su bitácora, con estados/props cerrados y validación visual. Sin implementación de código.

## Alcance
- Catálogo completo de organismos de chat/soporte del sistema de diseño.
- Bitácora: secciones/escenarios (estado typing, mensajes con/sin attachments, soporte con campos obligatorios).
- Ruta y comando Make para abrir esta pantalla.

## Dependencias
- TA-DS-F1 (tokens y theming).
- TA-DS-F2A (rutas y comandos Make).

## Diseño (doc)
### 1) Catálogo completo de organismos chat/soporte (Dui)
- `DuiMessageBubble`
- `DuiMessageInput` + send
- `DuiMessageList`
- `DuiTypingIndicator`
- `DuiAttachmentAction`
- `DuiSupportForm`

### 2) Estados y props por organismo (cerrado)
**DuiMessageBubble**
- Estados: own / remote.
- Estado visual: sent / delivered / read.

**DuiMessageInput**
- Estados: normal / disabled.
- Con placeholder y botón send.

**DuiMessageList**
- Estados: 3 mensajes (own/remote mix).

**DuiTypingIndicator**
- Estados: on / off.

**DuiAttachmentAction**
- Estados: enabled / disabled.

**DuiSupportForm**
- Campos: asunto, descripción, email.
- Estados: normal / error (campo requerido).

### 3) Bitácora de Chat/Soporte (pantalla)
- **Título:** `DuiText.Title` → “Bitácora de chat/soporte”.
- **Sección Chat básico:**
  - `DuiMessageList` + `DuiMessageBubble`.
- **Sección Input:**
  - `DuiMessageInput` + `DuiAttachmentAction`.
- **Sección Typing:**
  - `DuiTypingIndicator` (on/off).
- **Sección Soporte:**
  - `DuiSupportForm` (normal/error).

### 4) Navegación/Make
- Ruta: `app://bitacora/organisms/chat`.
- Comando: `make run-bitacora-organism-chat`.

## Wireframe (servilleta)
```
Bitácora de chat/soporte
────────────────────────────────────────
[Sección: Chat básico]
MessageList + MessageBubble (own/remote)

[Sección: Input]
MessageInput + AttachmentAction

[Sección: Typing]
TypingIndicator (on/off)

[Sección: Soporte]
SupportForm (normal/error)
```

## Entregables (documento)
- Props/estados de organismos de chat/soporte.
- Diseño de pantalla de bitácora de chat/soporte.
- Ruta y comando Make definidos.

## Verificación futura
- Props/estados cubren organismos definidos en esta historia.
- La pantalla respeta el wireframe definido.

## No incluido
- Implementación en código.

## Validación Dev (rol QA)
- [ ] Ejecutar `make run-bitacora-organism-chat` y verificar apertura sin clicks manuales.
- [ ] Validar escenarios: mensajes propios/remotos, estados sent/delivered/read, typing on/off, adjuntos on/off, soporte normal/error.

## Criterios de aceptacion
- La bitácora muestra todas las secciones definidas.
- Se visualizan los estados por organismo según esta historia.
- `run-bitacora-organism-chat` abre la pantalla correcta.

## Pull Request (contenido esperado)
**Titulo sugerido:** `docs(ds-f4c): bitacora chat/soporte (#2)`

**Incluye:**
- Actualización de `docs/02-designsystem/ta-f4-c-organisms-chat.md`.
- Entrada en `CHANGELOG.md` bajo `[Unreleased]`.

**Checklist:**
- [ ] Solo documentación (sin código).
- [ ] Enlace a la épica `docs/02-designsystem/epica.md`.
- [ ] Cumple `docs/git-workflow.md`.
- [ ] Entrada en `CHANGELOG.md` bajo `[Unreleased]`.
- [ ] `make detekt` pasa sin findings nuevos.