# CONTEXTO DEL PROYECTO COMPENDIUM
## Para Informe de Evaluación - Unidad 1

---

## 1. SITUACIÓN PROBLEMÁTICA

En el ecosistema actual de videojuegos, los jugadores enfrentan varios desafíos al buscar información de calidad:

### Problemas Identificados:
1. **Dispersión de información**: Las guías están esparcidas en múltiples sitios (Reddit, YouTube, foros, wikis)
2. **Falta de actualización**: Muchas guías quedan obsoletas con los parches y actualizaciones
3. **Calidad variable**: No hay un sistema claro para identificar contenido útil vs contenido desactualizado o incorrecto
4. **Barrera de idioma**: La mayoría del contenido de calidad está en inglés
5. **Falta de recompensa**: Los creadores de contenido no tienen incentivos para mantener actualizado su trabajo

### Necesidades de los Usuarios:
- **Jugadores casuales**: Necesitan guías básicas para empezar y progresar
- **Jugadores competitivos**: Buscan builds optimizados, tier lists actualizadas y estrategias avanzadas
- **Creadores de contenido**: Quieren una plataforma donde compartir conocimiento y ser reconocidos
- **Comunidades**: Necesitan un espacio centralizado para discutir y colaborar

---

## 2. CONTEXTO DEL PROYECTO

### Nombre del Proyecto:
**COMPENDIUM** - Portal Colaborativo de Guías de Videojuegos

### Descripción:
Compendium es una plataforma web que centraliza la creación, compartición y valoración de contenido relacionado con videojuegos, incluyendo:
- Guías detalladas paso a paso
- Builds optimizados para diferentes estilos de juego
- Tier lists de personajes, armas y equipamiento
- Novedades y actualizaciones de juegos
- Comunidades por juego para retroalimentación

### Inspiración y Referencias:
El proyecto se inspira en plataformas exitosas como:

1. **Prydwen.gg** (https://www.prydwen.gg/)
   - Especializado en juegos gacha
   - Tier lists visuales claros y actualizados
   - Sistema de ratings y comentarios
   - Diseño moderno y responsivo

2. **Fextralife** (https://fextralife.com/)
   - Wikis completas de RPGs (Dark Souls, Elden Ring, etc.)
   - Guías exhaustivas con imágenes y videos
   - Comunidad activa
   - Sistema de búsqueda robusto

3. **EliteGuias** (https://www.eliteguias.com/)
   - Contenido en español
   - Guías paso a paso detalladas
   - Variedad de juegos
   - Enfoque en accesibilidad

### Valor Diferencial de Compendium:
- **Sistema de recompensas**: Los autores ganan puntos por actividad, calidad y utilidad del contenido
- **Gamificación**: Logros, badges y rankings para motivar la participación
- **Retroalimentación colaborativa**: La comunidad puede sugerir mejoras y actualizaciones
- **Multilenguaje**: Énfasis en contenido en español de calidad
- **Actualización comunitaria**: Sistema de notificaciones cuando hay cambios en los juegos

---

## 3. ALCANCE DE LA FASE 1 (UNIDAD 1)

### Objetivo de la Fase Actual:
Desarrollar la estructura base y las interfaces de usuario del portal, estableciendo:
- Sistema de navegación intuitivo
- Formularios para gestión de contenido
- Autenticación básica de usuarios
- Diseño responsive y profesional

### Funcionalidades Implementadas:
✅ Sistema de login y registro de usuarios  
✅ Dashboard principal con navegación  
✅ Listado de elementos (contexto: juegos/guías)  
✅ Formularios de creación y edición (CRUD simulado)  
✅ Vista de detalle de elementos  
✅ Validaciones del lado del cliente  
✅ Diseño responsive con Bootstrap  
✅ Arquitectura MVC con controladores definidos  

### Tecnologías Utilizadas:
- **Backend**: Spring Boot 3.5.7, Spring MVC
- **Frontend**: Thymeleaf, Bootstrap 5.3.2, Font Awesome
- **Desarrollo**: Java 17, Maven, Spring Boot DevTools
- **Base de datos** (preparada para U2): H2, Spring Data JPA

---

## 4. ROADMAP DEL PROYECTO

### Fase 1 - Unidad 1 (ACTUAL) ✅
- Estructura del proyecto
- Interfaces de usuario
- Navegación y rutas
- Validaciones cliente

### Fase 2 - Unidad 2 (PRÓXIMA)
- Implementación de Services
- Persistencia con JPA/Hibernate
- CRUD completo funcional
- Spring Security avanzado

### Fase 3 - Unidad 3 (FUTURA)
- Sistema de valoraciones y comentarios
- Editor de texto enriquecido completo
- Subida y gestión de imágenes
- Sistema de roles avanzado

### Fase 4 - Extensiones (FUTURAS)
- Sistema de recompensas
- Tier lists interactivas
- Comunidades por juego
- API REST para móvil
- Sistema de notificaciones

---

## 5. ARQUITECTURA Y DISEÑO

### Patrón de Diseño:
**MVC (Model-View-Controller)** implementado con Spring Boot

### Estructura de Capas:
```
Presentación (View)
    ↓ Thymeleaf Templates
Controlador (Controller)
    ↓ Spring MVC @Controller
Servicio (Service) [Fase 2]
    ↓ Lógica de Negocio
Repositorio (Repository) [Fase 2]
    ↓ Spring Data JPA
Modelo (Model)
    ↓ Entidades JPA
Base de Datos (H2) [Fase 2]
```

### Entidades Principales:
1. **Usuario**: Gestión de cuentas y autenticación
2. **Juego**: Catálogo de videojuegos
3. **Guía**: Contenido creado por usuarios
4. **Comentario**: Retroalimentación de la comunidad
5. **Role**: Sistema de permisos (USER, AUTOR, ADMIN)

---

## 6. CASOS DE USO PRINCIPALES

### Usuario No Autenticado:
- Ver página principal con guías destacadas
- Registrarse en el sistema
- Iniciar sesión

### Usuario Normal (Autenticado):
- Ver listado completo de guías
- Leer guías detalladas
- Comentar y valorar guías [Fase 2]
- Buscar y filtrar contenido

### Autor (Rol Especial):
- Todo lo del usuario normal +
- Crear nuevas guías
- Editar sus propias guías
- Subir imágenes y archivos
- Ver estadísticas de sus guías

### Administrador:
- Todo lo anterior +
- Gestionar todos los juegos
- Gestionar todos los usuarios
- Moderar contenido
- Gestionar roles y permisos

---

## 7. IMPACTO ESPERADO

### Para la Comunidad Gamer:
- **Centralización**: Un solo lugar para toda la información
- **Calidad**: Sistema de valoración asegura contenido útil
- **Actualización**: Notificaciones cuando hay cambios
- **Accesibilidad**: Contenido en español de calidad

### Para Creadores de Contenido:
- **Reconocimiento**: Sistema de recompensas y rankings
- **Visibilidad**: Plataforma dedicada para su trabajo
- **Retroalimentación**: Comentarios constructivos de la comunidad
- **Motivación**: Gamificación y logros

### Para el Ecosistema de Videojuegos:
- **Reducción de frustración**: Jugadores encuentran ayuda rápidamente
- **Retención de jugadores**: Mejor experiencia = más tiempo jugando
- **Comunidad más fuerte**: Espacio para colaborar y compartir

---

## 8. CONSIDERACIONES TÉCNICAS

### Escalabilidad:
El diseño modular permite crecer por fases:
- Fase 1: Validación de la UI/UX
- Fase 2: Core funcional
- Fase 3: Features avanzadas
- Fase 4: Optimizaciones y API

### Seguridad:
- Spring Security para autenticación
- Roles y permisos bien definidos
- Validaciones cliente y servidor
- Protección CSRF

### Responsive Design:
- Mobile-first approach
- Bootstrap grid system
- Navegación adaptable
- Imágenes optimizadas

---

## CONCLUSIÓN DEL CONTEXTO

Compendium no es solo una aplicación web de gestión; es una **plataforma social** diseñada para fortalecer la comunidad gamer hispanohablante, premiando la creación de contenido de calidad y facilitando el acceso a información actualizada y útil.

La Fase 1 establece las bases sólidas de UI/UX y arquitectura que permitirán, en fases posteriores, implementar un ecosistema completo de colaboración y gamificación.

---

**Nota para el Informe**: Este contexto debe aparecer en la sección de "Introducción" de tu PDF, destacando la visión del proyecto más allá de los requisitos mínimos de la evaluación.
