FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq
# workspace
WORKDIR /user/share/udemy

# Add .jar under target host
ADD target/selenium-docker.jar selenium-docker.jar
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/libs libs

# Add suite files
ADD bookFlightModule.xml    bookFlightModule.xml
ADD searchModule.xml searchModule.xml
ADD healthcheck.sh healthcheck.sh
RUN dos2unix healthcheck.sh

# BROWSER
# HUB_HOST
# Module
ENTRYPOINT sh healthcheck.sh
