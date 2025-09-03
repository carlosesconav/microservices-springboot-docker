# Proyecto Microservicios - Customers y Accounts

Este proyecto contiene dos microservicios (`customers` y `accounts`) conectados a una base de datos MySQL usando Docker Compose.  

## Requisitos

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- Java 17 (para desarrollo local si deseas correr sin Docker)
- Gradle

## Estructura del proyecto

```
.
├── customers/       # Microservicio Customers
├── accounts/        # Microservicio Accounts
├── docker-compose.yml
└── README.md
```

## Configuración

### Docker Compose

El archivo `docker-compose.yml` ya configura:

- MySQL 8 con base de datos `devsu`
- Usuario root con contraseña `root123`
- Microservicios `customers` (puerto 8081) y `accounts` (puerto 8082) conectados a una base de datos MySQL

> ⚠️ Se recomienda no usar `root` en producción. Se puede crear un usuario normal para los servicios.

### Spring Boot

Los microservicios están configurados para conectarse a MySQL con estas propiedades:

```properties
spring.datasource.url=jdbc:mysql://db:3306/devsu
spring.datasource.username=root
spring.datasource.password=root123
spring.jpa.hibernate.ddl-auto=update
```

> Ajusta estas propiedades si cambias el usuario de la base de datos.

## Cómo iniciar el proyecto

1. Construir y levantar los contenedores:

```bash
docker-compose up --build
```

2. Espera a que MySQL esté listo (el healthcheck tarda unos segundos).  

3. Accede a los microservicios:  

- Customers: [http://localhost:8081](http://localhost:8081)  
- Accounts: [http://localhost:8082](http://localhost:8082)

4. Para detener los contenedores:

```bash
docker-compose down
```

5. Para reiniciar sin reconstruir:

```bash
docker-compose up
```

## Volúmenes

La base de datos MySQL persiste en el volumen `db_data`:

```yaml
volumes:
  db_data:
```
