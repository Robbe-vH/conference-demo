FROM openjdk:20-bullseye

#ARG DB_USER
#ARG DB_PASS
#
#ENV DB_URL="jdbc:postgresql://localhost:5432/postgres"
#ENV DB_USER=${DB_USER}
#ENV DB_PASS=${DB_PASS}

COPY target/conference-demo-0.0.1-SNAPSHOT.jar /app.jar

WORKDIR /

EXPOSE 5000

CMD ["java", "-jar", "app.jar"]

LABEL authors="robbevanhoppe"
