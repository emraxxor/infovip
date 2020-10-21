#!/bin/bash

curl -XPUT 'localhost:9200/activity-post' -H 'Content-Type: application/json'
curl -XPUT 'localhost:9200/users' -H 'Content-Type: application/json'
curl -XPUT 'localhost:9200/user-media-photo-comment' -H 'Content-Type: application/json'
curl -XPUT 'localhost:9200/user-media-photo' -H 'Content-Type: application/json'
curl -XPUT 'localhost:9200/user-media' -H 'Content-Type: application/json'
curl -XPUT 'localhost:9200/timeline' -H 'Content-Type: application/json'
curl -XPUT 'localhost:9200/logs' -H 'Content-Type: application/json'

curl -XPUT 'localhost:9200/activity-post/_mapping' -H 'Content-Type: application/json' -d @activity.json
