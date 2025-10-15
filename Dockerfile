FROM openjdk:8
EXPOSE 8080
ADD target/hello_docker.jar hello_docker.jar
ENTRYPOINT ["java","-jar","/hello_docker.jar"]