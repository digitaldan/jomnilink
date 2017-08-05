# jomnilink - A java library for connecting to HAI/Leviton Omni and Lumina home automation controllers using the HAI OmniLink II protocol.

[![TravisCI Build Status](https://travis-ci.org/digitaldan/jomnilink.svg?branch=master)](https://travis-ci.org/digitaldan/jomnilink)

# Download

## Snapshots

https://oss.sonatype.org/content/repositories/snapshots/com/github/digitaldan/jomnilink/

## Releases

https://repo1.maven.org/maven2/com/github/digitaldan/jomnilink/

# Building

## Basic jar
```
mvn clean package
```

## Jar with logging dependencies

```
mvn clean compile assembly:single
```

## Crude example usage

```
java  -cp ./target/jomnilinkII-1.0-SNAPSHOT-jar-with-dependencies.jar  com.digitaldan.jomnilinkII.examples.Main  192.168.0.1 4369 1234567890abcdef1234567890abcdef
```
