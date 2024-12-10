FROM openjdk:21-jdk

WORKDIR /my-project
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar","/my-project/app.jar"]


