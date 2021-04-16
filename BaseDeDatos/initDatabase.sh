#!/usr/bin/env bash

echo "Initializing database..."
SUFFIX="/src/main/resources/database"
PWD="$(pwd)"
TARGET="${PWD%"$SUFFIX"}"/target

if ! docker network ls --format '{{.Name}}' | grep -w C3PNetwork &> /dev/null;
then
  echo "Creating Network..."
  docker network create C3PNetwork
fi
if ! docker ps --format '{{.Names}}' | grep -w C3PPostgreSQL &> /dev/null;
then
  echo "Starting docker container..."
  docker run -d --network C3PNetwork --name C3PPostgreSQL -p 10017:5432 -e POSTGRES_PASSWORD=c3p -v "${TARGET}"/psql/data:/var/lib/postgresql/data postgres:9.6-alpine

  echo "Waiting for PostgreSQL start on docker container..."
  sleep 30s

  echo "Creating database and roles..."
  docker exec -i C3PPostgreSQL sh -c 'psql -U postgres -c "DROP DATABASE IF EXISTS despachos_db;"'
  docker exec -i C3PPostgreSQL sh -c 'psql -U postgres -c "DROP ROLE IF EXISTS c3p;"'
  docker exec -i C3PPostgreSQL sh -c 'psql -U postgres -c "DROP ROLE IF EXISTS grafana;"'
  docker exec -i C3PPostgreSQL sh -c "psql -U postgres -c \"CREATE ROLE c3p LOGIN ENCRYPTED PASSWORD 'md5fbe6c2205cddbbec09b8044143d30b66' NOSUPERUSER INHERIT CREATEDB NOCREATEROLE NOREPLICATION;\""
  docker exec -i C3PPostgreSQL sh -c "psql -U postgres -c \"CREATE ROLE grafana LOGIN ENCRYPTED PASSWORD 'md546970436689c54fc1b3a4d112192ba42' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;\""
  docker exec -i C3PPostgreSQL sh -c "psql -U postgres -c \"CREATE DATABASE despachos_db WITH ENCODING='UTF8' OWNER=c3p CONNECTION LIMIT=-1 TABLESPACE=pg_default;\""
  docker exec -i C3PPostgreSQL sh -c 'psql -U postgres -d despachos_db' < "${PWD}/despachos_db.sql"
else
  echo "Database already exists"
fi

echo "Init database script finished :)"
