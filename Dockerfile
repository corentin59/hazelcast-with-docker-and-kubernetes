FROM java:8

MAINTAINER Corentin <corentin@azelart.fr>

COPY target/vlille-0.0.2-SNAPSHOT.jar app.jar

EXPOSE 80

ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=80"]

