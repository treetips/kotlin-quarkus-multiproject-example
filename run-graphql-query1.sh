#!/bin/sh

script='query allFilms {
  allFilms {
    title
    releaseDate
  }
}'

script="$(echo $script)"

curl -H 'Content-Type: application/json' \
     -X POST -d "{ \"query\": \"$script\"}" http://127.0.0.1:8080/graphql \
     | jq .
