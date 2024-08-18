FROM amazoncorretto:17-alpine-jdk

COPY target/latestVideoclub-0.0.1-SNAPSHOT.jar  app.jar
COPY Wallet_videoclub/* app.jar

EXPOSE 10000



ENTRYPOINT ["java","-jar","/app.jar"]