# 📦 API REST de Registro de Usuarios - Spring Boot

Este proyecto es una API RESTful desarrollada con Spring Boot que permite registrar usuarios, validando su información y retornando respuestas en formato JSON. Los datos se almacenan temporalmente en una base de datos en memoria (H2).

---

## 🧰 Tecnologías Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Bean Validation (JSR-380)
- JWT (opcional)
- Swagger UI (opcional)
- Maven

---

## 📌 Requisitos

- Java 17+
- Maven 3.8+

---

## 🚀 Cómo Ejecutar el Proyecto

1. Clona este repositorio:

```bash
git clone https://github.com/tuusuario/registro-usuarios.git

cd registro-usuarios


1. Ejecuta la aplicación:
        mvn spring-boot:run

Accede a la API en:

http://localhost:8080
📬 Endpoints
POST /api/users

Crea un nuevo usuario.

📤 Request Body:

    {
      "name": "Juan Rodriguez",
      "email": "juan@rodriguez.org",
      "password": "hunter2",
      "phones": [
        {
          "number": "1234567",
          "citycode": "1",
          "contrycode": "57"
        }
      ]
    }

📥 Response de Éxito:

{
  "id": "uuid-generado",
  "created": "2025-05-18T08:30:00",
  "modified": "2025-05-18T08:30:00",
  "lastLogin": "2025-05-18T08:30:00",
  "token": "jwt-o-uuid-generado",
  "isActive": true
}

📥 Respuesta de Error:

{
  "mensaje": "El correo ya está registrado"
}
📘 Validaciones
El correo debe tener formato válido (usuario@dominio.com)

La contraseña debe cumplir con una expresión regular definida en application.properties

El correo debe ser único

Los campos obligatorios no pueden estar vacíos

🧪 Base de Datos H2
Puedes acceder a la consola de H2 en:


http://localhost:8080/h2-console
Credenciales por defecto:

JDBC URL: jdbc:h2:mem:testdb

Usuario: sa

Contraseña: (vacía)

🌐 Internacionalización (i18n)
Los mensajes de error están centralizados en archivos messages.properties y se traducen automáticamente según el encabezado Accept-Language en la petición HTTP.

Ejemplo:

Accept-Language: es
📒 Documentación Swagger (opcional)

Si tienes Swagger habilitado, accede a la documentación interactiva en:


http://localhost:8080/swagger-ui.html
🧱 Estructura del Proyecto

src/main/java/
├── controller
├── service
├── model
├── dto
├── repository
├── exception
└── config
🛡️ Autenticación JWT (opcional)
Si se incluye JWT, se genera un token por usuario al registrarse y se persiste en la base de datos.


📄 Licencia
Este proyecto es libre de uso con fines educativos y de evaluación.

🙋‍♂️ Contacto
Desarrollado por Felipe Feres – [felipe.feres9876@gmail.com]
Repositorio: GitHub