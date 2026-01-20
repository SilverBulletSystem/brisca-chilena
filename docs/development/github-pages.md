# GitHub Pages para Documentacion

## Objetivo
Publicar toda la documentacion del proyecto en GitHub Pages usando la carpeta `docs/` como fuente.

## Alcance
- Publicacion automatica de `docs/index.md` como portada.
- Navegacion por enlaces relativos entre documentos.
- Sin cambios de codigo ni generadores externos.

## Requisitos
- Repositorio en GitHub con rama `main`.
- Carpeta `docs/` en el root del repositorio.

## Configuracion en GitHub
1) Ir a **Settings → Pages** del repositorio.
2) En **Source**, seleccionar:
   - **Branch**: `main`
   - **Folder**: `/docs`
3) Guardar cambios.
4) Esperar la URL publicada (GitHub la mostrara en la misma seccion).

## Estructura recomendada
- `docs/index.md` como entrada principal.
- Enlaces relativos entre documentos, por ejemplo:
  - `docs/01-fundamentos/epica.md`
  - `docs/01-fundamentos/backlog.md`
  - `docs/01-fundamentos/diseno-detallado.md`

## Buenas practicas
- Mantener un indice en `docs/index.md` con enlaces a todas las epicas.
- Evitar enlaces absolutos al sistema de archivos local.
- Usar nombres de archivo estables para no romper links.

## Verificacion
- La pagina carga `docs/index.md`.
- Los enlaces de `docs/index.md` funcionan en el navegador.

## Fuera de alcance
- Configurar generadores (MkDocs, Docusaurus, etc.).
- Plantillas o temas personalizados.
