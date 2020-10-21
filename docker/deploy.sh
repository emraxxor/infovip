EAR="infovip-ear-1.0-SNAPSHOT.ear"

docker exec -it -w /opt/deploy  web /opt/jboss/wildfly/bin/jboss-cli.sh --user=admin --password=Admin --connect --command="deploy target/${EAR}"
