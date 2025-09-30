#!/bin/sh
until nc -z postgres 5432; do
  echo "Waiting for Postgres..."
  sleep 1
done

exec java -jar /app/product-service-0.0.1-SNAPSHOT.jar
