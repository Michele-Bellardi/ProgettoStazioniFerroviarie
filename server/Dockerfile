FROM openjdk:21-jdk-slim

COPY ./out/artifacts/Progettino_jar/Progettino.jar /app/Progettino.jar
COPY Mappa-delle-stazioni-ferroviarie-in-Italia.csv /app/Mappa-delle-stazioni-ferroviarie-in-Italia.csv

WORKDIR /app

EXPOSE 1050

CMD ["java", "-jar", "Progettino.jar"]