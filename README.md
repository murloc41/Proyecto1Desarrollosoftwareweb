# Compendium - Portal de Guías de Videojuegos

## 📋 Descripción del Proyecto

**Compendium** es una plataforma web colaborativa dedicada a la comunidad gamer, donde los usuarios pueden crear, compartir y descubrir contenido de alta calidad sobre sus videojuegos favoritos.

### Contexto y Problemática

En la actualidad, los jugadores buscan información confiable sobre:
- Guías detalladas de progresión y mecánicas
- Builds optimizados para diferentes estilos de juego
- Tier lists actualizadas de personajes, armas y equipamiento
- Novedades y actualizaciones de sus juegos favoritos
- Comunidades activas donde compartir estrategias

**Compendium** nace como solución para centralizar este contenido, permitiendo que la comunidad cree, valore y mejore colaborativamente las guías, premiando a los creadores más activos y útiles.

## 🎯 Funcionalidades Principales

### Para la Fase 1 (Unidad 1 - Evaluación Actual)
- ✅ Sistema de autenticación (Login/Registro)
- ✅ Dashboard principal con navegación intuitiva
- ✅ Listado de juegos disponibles
- ✅ Formularios CRUD para gestión de contenido
- ✅ Diseño responsive con Bootstrap
- ✅ Validaciones del lado del cliente

### Para Fases Futuras (Unidad 2 en adelante)
- 🔄 **Sistema de Guías:**
  - Creación con editor de texto enriquecido (Quill)
  - Categorización (Tutorial, Estrategia, Build, Secretos, Logros, Speedrun)
  - Niveles de dificultad (Principiante, Intermedio, Avanzado, Experto)
  - Subida de imágenes y archivos adjuntos
  
- 🔄 **Sistema de Valoración:**
  - Rating por estrellas
  - Comentarios y retroalimentación
  - Sistema de votos (útil/no útil)
  
- 🔄 **Sistema de Roles:**
  - **Usuario Normal:** Solo lectura y comentarios
  - **Autor:** Puede crear y editar sus propias guías
  - **Administrador:** Control total del sistema
  
- 📅 **Futuras Implementaciones:**
  - Sistema de recompensas por actividad
  - Tier Lists interactivas
  - Comunidades por juego
  - Sistema de logros y badges
  - Notificaciones de actualizaciones
  - Búsqueda avanzada y filtros

## 🛠️ Tecnologías Utilizadas

### Backend
- **Spring Boot 3.5.7** - Framework principal
- **Spring Security** - Autenticación y autorización
- **Spring Data JPA** - Persistencia de datos
- **H2 Database** - Base de datos en desarrollo
- **Lombok** - Reducción de código boilerplate
- **Validation API** - Validaciones del lado del servidor

### Frontend
- **Thymeleaf** - Motor de plantillas
- **Bootstrap 5.3.2** - Framework CSS
- **Font Awesome 6.4.2** - Iconos
- **Quill Editor** - Editor de texto enriquecido
- **JavaScript vanilla** - Interactividad del cliente

### Herramientas de Desarrollo
- **Maven** - Gestión de dependencias
- **Spring Boot DevTools** - Desarrollo ágil
- **Java 17** - Versión del lenguaje

## 📁 Estructura del Proyecto

```
compendium/
├── src/
│   ├── main/
│   │   ├── java/com/instituto/compendium/
│   │   │   ├── controller/          # Controladores MVC
│   │   │   │   ├── AppController.java       # CRUD básico (evaluación U1)
│   │   │   │   ├── AuthController.java      # Login/Registro
│   │   │   │   ├── GuiaController.java      # Gestión de guías
│   │   │   │   ├── JuegoController.java     # Gestión de juegos
│   │   │   │   ├── UsuarioController.java   # Gestión de usuarios
│   │   │   │   └── HomeController.java      # Navegación principal
│   │   │   │
│   │   │   ├── model/               # Entidades del dominio
│   │   │   │   ├── Usuario.java
│   │   │   │   ├── Role.java
│   │   │   │   ├── Juego.java
│   │   │   │   ├── Guia.java
│   │   │   │   ├── Comentario.java
│   │   │   │   └── Archivo.java
│   │   │   │
│   │   │   ├── repository/          # Repositorios JPA
│   │   │   │   ├── UsuarioRepository.java
│   │   │   │   ├── RoleRepository.java
│   │   │   │   ├── JuegoRepository.java
│   │   │   │   └── GuiaRepository.java
│   │   │   │
│   │   │   ├── service/             # Lógica de negocio
│   │   │   │   ├── UsuarioService.java
│   │   │   │   ├── JuegoService.java
│   │   │   │   └── GuiaService.java
│   │   │   │
│   │   │   ├── config/              # Configuración
│   │   │   │   └── SecurityConfig.java
│   │   │   │
│   │   │   └── CompendiumApplication.java
│   │   │
│   │   └── resources/
│   │       ├── templates/           # Vistas Thymeleaf
│   │       │   ├── layouts/
│   │       │   │   └── base.html           # Layout base
│   │       │   ├── login.html              # Login (U1)
│   │       │   ├── registro.html           # Registro (U1)
│   │       │   ├── index.html              # Dashboard (U1)
│   │       │   ├── form-crear.html         # Crear elemento (U1)
│   │       │   ├── form-editar.html        # Editar elemento (U1)
│   │       │   ├── detalle.html            # Detalle elemento (U1)
│   │       │   ├── guias/
│   │       │   │   ├── form.html           # Formulario guías
│   │       │   │   ├── lista.html          # Listado guías
│   │       │   │   └── ver.html            # Ver guía
│   │       │   ├── juegos/
│   │       │   │   ├── form.html           # Formulario juegos
│   │       │   │   └── lista.html          # Listado juegos
│   │       │   └── usuarios/
│   │       │       ├── registro.html       # Registro usuarios
│   │       │       └── lista.html          # Gestión usuarios
│   │       │
│   │       ├── static/
│   │       │   └── styles.css              # Estilos personalizados
│   │       │
│   │       └── application.properties      # Configuración
│   │
│   └── test/                        # Tests unitarios
│
├── uploads/                         # Archivos subidos
├── pom.xml                         # Dependencias Maven
└── README.md                       # Este archivo
```

## 🚀 Instalación y Ejecución

### Requisitos Previos
- Java 17 o superior
- Maven 3.6+
- IDE recomendado: IntelliJ IDEA o VS Code

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone <url-repositorio>
cd compendium
```

2. **Compilar el proyecto**
```bash
mvn clean install
```

3. **Ejecutar la aplicación**
```bash
mvn spring-boot:run
```

4. **Acceder a la aplicación**
- URL: http://localhost:8080
- Consola H2: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:file:./data/compendium`
  - Usuario: `sa`
  - Contraseña: (vacío)

### Usuarios de Prueba

**Administrador:**
- Usuario: `admin`
- Contraseña: `admin123`
- Permisos: Control total del sistema

**Autor:**
- Usuario: `autor1`
- Contraseña: `autor123`
- Permisos: Crear y editar guías

**Usuario Normal:**
- Usuario: `usuario1`
- Contraseña: `user123`
- Permisos: Solo lectura

## 📝 Evaluación - Unidad 1 (Fase 1)

### Cumplimiento de Requisitos

#### 1. Estructura del Proyecto ✅
- [x] Proyecto Spring Boot funcional
- [x] Dependencias: Spring Web, Thymeleaf, DevTools
- [x] Paquetización correcta
- [x] Contenido estático configurado

#### 2. Interfaces de Usuario ✅
- [x] `login.html` - Inicio de sesión
- [x] `registro.html` - Registro de usuarios
- [x] `index.html` - Dashboard con listado
- [x] `form-crear.html` - Formulario crear
- [x] `form-editar.html` - Formulario editar
- [x] `detalle.html` - Vista detalle (opcional)
- [x] Navegación clara entre vistas
- [x] Diseño responsive con Bootstrap

#### 3. Validaciones Cliente ✅
- [x] Atributos HTML5 (required, minlength, type, pattern)
- [x] Validación JavaScript contraseñas coinciden
- [x] Confirmación eliminación elementos

#### 4. Controladores ✅
- [x] `AuthController` - Login/Registro sin lógica
- [x] `AppController` - CRUD simulado
- [x] Métodos retornan solo vistas/redirecciones
- [x] Sin lógica de negocio (preparado para U2)

## 🎨 Referencias de Diseño

El diseño de Compendium se inspira en:
- **Prydwen.gg** - Tier lists claros y organizados
- **Fextralife** - Wiki detallada con navegación intuitiva
- **EliteGuias** - Contenido en español accesible

## 📄 Licencia

Proyecto académico - Instituto/Universidad
Asignatura: Desarrollo de Software Web II
