#!/bin/bash
docker exec -it -w /opt/deploy  web /opt/jboss/wildfly/bin/jboss-cli.sh --user=admin --password=Admin --connect --command="undeploy infovip-ear-1.0-SNAPSHOT.ear" 
