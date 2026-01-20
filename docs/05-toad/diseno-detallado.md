# Diseno detallado - Epica 5 Toad (External Record) (Nivel 4)

## Navegacion
- [Volver a Epica](05-toad/epica.md)
- [Volver a Backlog](05-toad/backlog.md)

## Contexto
External Record registra requests externos y los correlaciona con sesiones.

## Alcance
- Dominio y contratos agnosticos de canal.
- Persistencia SQDelight.
- Interceptor HTTP.
- Visor y filtros.

## TA-TOAD-F0 Dominio
- Request/Response traces y sanitizacion.

## TA-TOAD-F1 Schema DB
- Tablas requests/responses con retencion.

## TA-TOAD-F2 Interceptor HTTP
- Captura, truncado y hash; correlacion.

## TA-TOAD-F3 Writer
- Buffer y flush con limites.

## TA-TOAD-F4 Correlacion
- CorrelationId compartido con Inkribbon.

## TA-TOAD-F5 Viewer
- UI monitor lista/detalle con filtros.

## TA-TOAD-F6 Make
- Atajos para abrir monitor y trazas.

## TA-TOAD-F7 Testing
- Checklist QA y observabilidad.