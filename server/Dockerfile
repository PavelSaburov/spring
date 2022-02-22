FROM openjdk:8
COPY /target/client-*.jar client.jar
EXPOSE 9080
ENTRYPOINT ["java", "-jar", "client.jar"]