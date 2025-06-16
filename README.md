
#Prueba Técnica – Desarrollador Java Spring Boot  - Autenticación y Registro:
Este proyecto implementa una API REST en Java con Spring Boot que permite a los usuarios autenticarse contra la API externa DummyJSON y registrar cada autenticación válida en una base de datos PostgreSQL, conservando las cookies y el usuario autenticado.


Instrucciones de Ejecución:

# Dentro del cliente psql, crea la base de datos:
CREATE DATABASE prueba_telefonica;


# Dentro del cliente psql, crea la la tabla:

-- Crear la extensión UUID si no existe 
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE IF NOT EXISTS login_log (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY, 
    username VARCHAR(255) NOT NULL,
	email varchar(150),
    login_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    access_token TEXT,
    refresh_token TEXT
);

# Dentro del cliente psql, SELECT:

SELECT * FROM login_log;


Asegúrarse de que la configuración de la base de datos en src/main/resources/application.properties coincida con tus credenciales y nombre de la base de datos:

Properties(Mi caso)

spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/prueba_telefonica
spring.datasource.username=postgres
spring.datasource.password=D3v3l0pm3nt


# Compilación y Ejecución del Proyecto
Clona el repositorio:
Bash

git clone https://github.com/svelacai/prueba_telefonica.git
Compila el proyecto con Maven:
Bash
mvn clean install

Ejecuta la aplicación Spring Boot:
Bash
mvn spring-boot:run

La aplicación se iniciará en http://localhost:8080.
Usuario y Contraseña de Prueba
Puedes usar los usuarios de prueba desde GET https://dummyjson.com/users. Un ejemplo común es:

Usuario: emilys
Contraseña: emilyspass
Ejemplo curl de Login
Para probar el endpoint de login de tu API:


(En las fuentes del proyecto dejo una coleccion de ejemplo para el consumo del servicio llamado "Telefonica.postman_collection")


La respuesta de esta petición incluirá los datos de autenticación de DummyJSON (incluyendo refreshToken) y, si es exitosa, se registrará en base de datos.

Explicación de cómo se Guarda el Registro de Login
Cada vez que un usuario se autentica exitosamente a través de tu endpoint /api/auth/login, la aplicación registra esta información en la base de datos. Los pasos son los siguientes:

Llamada a DummyJSON: Envía las credenciales (username y password) al endpoint POST https://dummyjson.com/auth/login utilizando Feign Client.
Respuesta de DummyJSON: Si la autenticación es exitosa, DummyJSON devuelve un objeto que contiene, entre otros datos, el token (que actúa como accessToken) y el refreshToken.
Registro en Base de Datos: Los detalles de la autenticación exitosa, incluyendo el username, la login_time (momento actual), el access_token y el refresh_token se guardan en la tabla login_log de la base de datos PostgreSQL.