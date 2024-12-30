FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/ecom.jar /ecom.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/ecom.jar"]

CMD ["-start"]