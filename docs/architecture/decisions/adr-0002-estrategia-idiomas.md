# ADR-0002 Estrategia de idiomas escalable

## Navegacion
- [Volver al listado](architecture/decisions/README.md)

## 1. Contexto
El proyecto debe soportar multiples idiomas en el tiempo sin re-trabajo ni hardcodes. Se requiere una estrategia que permita:
- Escalar a nuevos idiomas sin modificar UI.
- Evitar textos literales en componentes.
- Separar contratos por pantalla para mantener mantenibilidad.

## 2. Decision
Se adopta una estrategia de i18n basada en:
1) **Interfaces de strings por pantalla/feature** (interfaces segregadas por pantalla).
2) **Enum central `AppLanguage`** con idiomas soportados.
3) **`StringsProvider`** que resuelve implementaciones por idioma.
4) **Fallback definido**: si falta traduccion, usar idioma por defecto (ES).

## 3. Detalle de implementacion (diseno)
### 3.1 Contratos por pantalla
Cada pantalla define su propia interfaz de strings, por ejemplo:
- `SplashStrings`
- `LoginStrings`
- `HomeStrings`
- `BitacoraThemeStrings`, `BitacoraAtomsStrings`, etc.

Regla: todo texto visible y `contentDescription` proviene de estas interfaces.

### 3.2 Enum central
`AppLanguage` lista los idiomas soportados:
- `ES`, `EN` (base)
- Se agregan nuevos idiomas sin tocar los componentes.

### 3.3 StringsProvider
Contrato:
- `fun getStrings(language: AppLanguage): AppStrings`
- `AppStrings` agrupa interfaces por pantalla.

Implementacion inicial:
- In-memory (mapa por idioma).
- Sin IO ni dependencia de plataforma.

### 3.4 Fallback
Si un string no existe para un idioma:
- Fallback a ES.
- La app no debe fallar ni dejar textos vacios.

## 4. Consecuencias
**Positivas**
- Escalabilidad para multiples idiomas.
- UI desacoplada del idioma.
- QA puede validar idioma por pantalla.

**Negativas**
- Mayor cantidad de interfaces y archivos por pantalla.
- Trabajo inicial para definir contratos completos.

## 5. Criterios de aceptacion
- No existen strings literales en componentes UI.
- `AppLanguage` define los idiomas soportados.
- `StringsProvider` centraliza el acceso a strings.
- Fallback ES definido y documentado.
