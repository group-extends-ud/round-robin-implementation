#!/bin/bash

mvn clean

mvn compile

mvn install

clear

java -cp target/round-robin-1.0-SNAPSHOT.jar com.so.App