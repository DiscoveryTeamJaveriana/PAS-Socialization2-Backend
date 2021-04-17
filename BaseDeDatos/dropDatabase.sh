#!/usr/bin/env bash

SUFFIX="/BaseDeDatos"
PWD="$(pwd)"
TARGET="${PWD%"$SUFFIX"}"/target
echo "$TARGET"

echo "Stopping DiscoveryTeamPostgreSQL Container"
docker stop DiscoveryTeamPostgreSQL

echo "Removing DiscoveryTeamPostgreSQL Container"
docker rm -v DiscoveryTeamPostgreSQL

echo "Deleting data from volume ${TARGET}/psql"
rm -rf "${TARGET}"/psql

echo "Drop database script finished :)"

if docker network ls --format '{{.Name}}' | grep -w DiscoveryTeamNetwork &> /dev/null;
then
  if ! docker inspect -f '{{range $n, $conf := .Containers}} {{$conf}} {{end}}' DiscoveryTeamNetwork | grep 'DiscoveryTeamPostgreSQL\|DiscoveryTeamGrafana' &> /dev/null;
  then
    echo "Removing Network..."
    docker network rm DiscoveryTeamNetwork
  fi
fi
