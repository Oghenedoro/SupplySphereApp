#!/bin/sh

# Wait until Postgres is ready
until nc -z postgres 5432; do
  echo "Waiting for Postgres..."
  sleep 1
done

# Start Spring Boot application
exec java -jar /app.jar
