FROM amazoncorretto:17-alpine-jdk

COPY target/latestVideoclub-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 10000
ADD Wallet_videoclub  /Wallet_videoclub


ENTRYPOINT ["java","-jar","/app.jar"]