#!/bin/bash

mvn clean

mvn compile

mvn install

clear

cd target

java -cp round-robin-1.0-SNAPSHOT.jar com.so.App