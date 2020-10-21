#!/bin/bash
PATH=/bin:/usr/bin:/sbin:/usr/sbin
NAME=elasticsearch
DEFAULT=/etc/default/$NAME
ES_HOME=/usr/share/$NAME
MAX_OPEN_FILES=65535
ES_PATH_CONF=/etc/$NAME
MAX_MAP_COUNT=262144
ES_JAVA_OPTS="-DXms$XMS -DXmx$XMX -Ddiscovery.type=single-node"

sh -c "${ES_HOME}/bin/${NAME}"

