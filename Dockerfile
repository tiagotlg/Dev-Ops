FROM openjdk:17

WORKDIR /gamificacao-para-educacao

COPY target/*.jar /gamificacao-para-educacao/Gamificacao-para-Educacao-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "Gamificacao-para-Educacao-0.0.1-SNAPSHOT.jar"]
