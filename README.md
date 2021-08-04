# CRUD de Usuario y Rol Spring Boot con MySQL 8 y docker
Spring Boot with MySQL JPA, Docker to microservice, user and roll CRUD

Proyecto desarrollado en el curso de Spring Boot en el CIC IPN, al cual se le agregó la integración con docker para un despliegue como microservicio.
También se integró una prueba de concepto con una base de datos RDS creada en AWS.

Para hacer funcionar el proyecto debes crear una base de datos MySQL versión 8+ con nombre example_db y configurarla en el archivo src/main/resources/application.yml

Los endpoints útiles que puedes usar por el navegador son:

http://localhost:8081/user/all

http://localhost:8081/user/newUserFormThymeleaf

http://localhost:8081/rol/all

http://localhost:8081/rol/newRolFormThymeleaf

Tambien tiene endpoints para REST que se pueden verificar en la clase UserRestController y de esta forma integrarlo con Angular por ejemplo

Para la parte de docker se puede seguir la guía:
https://www.adictosaltrabajo.com/2016/06/07/desarrollo-de-microservicios-con-spring-boot-y-docker/

#Para dudas o comentarios puedes ir a la página:

https://mencrypto.com


