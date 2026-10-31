FROM eclipse-temurin:17
LABEL maintainer="Gabriel Oliveira"
WORKDIR /app
COPY target/houseboss-0.0.1-SNAPSHOT.jar /app/houseboss.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Mahouseboss.jar"]