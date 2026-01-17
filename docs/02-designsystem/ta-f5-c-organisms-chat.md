# TA-DS-F5-C — Organismos de chat/soporte y bitácora

## Rama
- `feat/ht-ds-f5-c-organisms-chat`

## Objetivo
Definir catálogo y bitácora para organismos de chat/soporte con navegación Make. Sin implementación.

## Alcance
- Organismos chat/soporte: `DuiMessageBubble`, `DuiMessageInput` + send, `DuiMessageList`, `DuiTypingIndicator`, `DuiAttachmentAction`, `DuiSupportForm`.
- Bitácora: secciones/escenarios (estado typing, mensajes con/sin attachments, soporte con campos obligatorios).
- Navegación/Make: comando para abrir la bitácora de chat/soporte sin interacción manual.

## Diseño (doc)
1) Catálogo con props/estados: mensajes propios/remotos, estado enviado/leído, typing on/off, adjuntos on/off.
2) Pantalla “Bitácora Organismos Chat/Soporte”: agrupación por escenarios (chat normal, soporte, typing, adjuntos).
3) Ruta interna propuesta: `app://bitacora/organisms/chat`.
4) Target Make propuesto: `make run-bitacora-organisms-chat`.

## Entregables (documento)
- Catálogo y props de organismos de chat/soporte.
- Diseño de pantalla de bitácora de chat/soporte.
- Ruta y comando Make definidos.

## Verificación futura
- Cobertura de organismos de chat/soporte completa.
- Comando/ruta documentados.

## No incluido
- Implementación en código.

## Validación Dev (rol QA)
- [ ] Ejecutar `make run-bitacora-organisms-chat` y verificar apertura sin clicks manuales.
- [ ] Validar escenarios: mensajes propios/remotos, estado enviado/leído, typing on/off, adjuntos on/off, soporte con campos obligatorios, visibles aunque sean placeholders.
