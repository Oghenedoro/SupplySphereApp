FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/product-service-0.0.1-SNAPSHOT.jar .
COPY wait-for-postgres.sh /
RUN chmod +x /wait-for-postgres.sh
ENTRYPOINT ["/wait-for-postgres.sh"]
