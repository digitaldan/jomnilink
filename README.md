# jomnilink - A java library for connecting to HAI/Leviton Omni and Lumina home automation controllers using the HAI OmniLink II protocol.

# Building

## Basic jar
```
mvn clean package
```

## Jar with logging dependencies

```
mvn clean compile assembly:single
```

## Deploy staging jar (must have "STAGING" in pom version)

```
mvn clean deploy
```

## Deploy Release jar to Artifactory

```
mvn clean deploy -P release
```

## Crude example usage

```
java  -cp ./target/jomnilinkII-1.0-SNAPSHOT-jar-with-dependencies.jar  com.digitaldan.jomnilinkII.examples.Main  192.168.0.1 4369 1234567890abcdef1234567890abcdef
```
