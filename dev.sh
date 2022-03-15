#!/bin/bash
export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64

mvn clean

mvn compile

mvn install

clear

java -cp target/round-robin-1.0-SNAPSHOT.jar com.so.App