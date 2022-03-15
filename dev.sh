#!/bin/bash

mvn clean

mvn compile

mvn install

clear

java -jar target/round-robin-1.0.0.jar