FROM openjdk:11
COPY /target/server-*.jar server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "server.jar"]