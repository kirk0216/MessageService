#!/bin/bash

./mvnw package
docker build . -t cst8277/messageservice-app:local
docker save cst8277/messageservice-app:local > messageservice.tar
microk8s ctr image import messageservice.tar
