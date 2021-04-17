#!/usr/bin/env bash

echo "Initializing database..."
SUFFIX="/BaseDeDatos"
PWD="$(pwd)"
TARGET="${PWD%"$SUFFIX"}"/target

if ! docker network ls --format '{{.Name}}' | grep -w DiscoveryTeamNetwork &> /dev/null;
then
  echo "Creating Network..."
  docker network create DiscoveryTeamNetwork
fi
if ! docker ps --format '{{.Names}}' | grep -w DiscoveryTeamPostgreSQL &> /dev/null;
then
  echo "Starting PostgreSQL docker container..."
  docker run -d --network DiscoveryTeamNetwork --name DiscoveryTeamPostgreSQL -p 10017:5432 -e POSTGRES_PASSWORD=discoveryteam -v "${TARGET}"/psql/data:/var/lib/postgresql/data postgres:9.6-alpine

  echo "Waiting for PostgreSQL start on docker container..."
  sleep 30s

  echo "Creating database and roles..."
  docker exec -i DiscoveryTeamPostgreSQL sh -c 'psql -U postgres -c "DROP DATABASE IF EXISTS despachos_db;"'
  docker exec -i DiscoveryTeamPostgreSQL sh -c 'psql -U postgres -c "DROP ROLE IF EXISTS discoveryteam;"'
  docker exec -i DiscoveryTeamPostgreSQL sh -c 'psql -U postgres -c "DROP ROLE IF EXISTS grafana;"'
  docker exec -i DiscoveryTeamPostgreSQL sh -c "psql -U postgres -c \"CREATE ROLE discoveryteam LOGIN ENCRYPTED PASSWORD 'md5d4f1d655da05630f09a1353d24b6d334' NOSUPERUSER INHERIT CREATEDB NOCREATEROLE NOREPLICATION;\""
  docker exec -i DiscoveryTeamPostgreSQL sh -c "psql -U postgres -c \"CREATE ROLE grafana LOGIN ENCRYPTED PASSWORD 'md5e35d7a9d4e6d9d8ce3ab308b40ecbf76' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;\""
  docker exec -i DiscoveryTeamPostgreSQL sh -c "psql -U postgres -c \"CREATE DATABASE despachos_db WITH ENCODING='UTF8' OWNER=discoveryteam CONNECTION LIMIT=-1 TABLESPACE=pg_default;\""
  docker exec -i DiscoveryTeamPostgreSQL sh -c 'pg_restore -U postgres --dbname=despachos_db' < "${PWD}/despachos_db.sql"
else
  echo "Database already exists"
fi

echo "Init database script finished :)"
