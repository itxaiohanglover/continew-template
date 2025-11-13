FROM openjdk:17

ARG JAR_FILE=./bin/*.jar
COPY ${JAR_FILE} /app/bin/app.jar
WORKDIR /app/bin

ENTRYPOINT ["java", \
            "-jar", \
            "-XX:+UseZGC", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dspring.profiles.active=prod", \
            "app.jar"]