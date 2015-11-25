FROM java:8

MAINTAINER Corentin <corentin@azelart.fr>

COPY target/vlille-0.0.2.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=80"]

