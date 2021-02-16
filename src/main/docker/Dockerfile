FROM frolvlad/alpine-oraclejre8:slim
MAINTAINER un@mail.com
ADD demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]