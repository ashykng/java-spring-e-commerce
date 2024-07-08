FROM openjdk:21

EXPOSE 8080

COPY . .

ENTRYPOINT ["java", "-jar", "./target/online-shop-0.0.1-SNAPSHOT.jar"]
