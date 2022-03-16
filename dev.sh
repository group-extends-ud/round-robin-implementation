#!/bin/bash

opcion=$1

if [[ $opcion = use-11 ]]
then
    export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64
fi

mvn clean

mvn compile

mvn install

clear

java -jar target/round-robin-1.0.0.jar