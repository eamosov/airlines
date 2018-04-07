#!/bin/sh
docker-entrypoint.sh postgres --version
gosu postgres pg_ctl -D /var/lib/postgresql/data start
cd /opt/airlines
exec java -jar target/airlines-1.0-SNAPSHOT.jar