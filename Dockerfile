FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/cloud-kitchen-0.0.1-SNAPSHOT.war app.war
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.war"]
