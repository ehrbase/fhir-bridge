# FHIR Bridge [![CircleCI Status](https://circleci.com/gh/ehrbase/fhir-bridge.svg?style=shield)](https://circleci.com/gh/ehrbase/fhir-bridge)

FHIR Bridge is an official component of [EHRbase](https://ehrbase.org/).
The purpose of the application is to act as a broker between an HL7 FHIR client and an openEHR server.

The implementation is based on [Apache Camel](https://camel.apache.org/) and [Open eHealth Integration Platform](https://github.com/oehf/ipf).

## Getting Started

### Prerequisites

* JDK (>= 11.0.2)
* Apache Maven (>= 3.6.0)
* EHRbase (>= v0.14.0)
* [Optional] ELK Stack: Elasticsearch, Kibana and Logstash (>= 7.7.0)

### Build the application

```shell script
$ mvn clean install
```

### Build the application and execute integration tests

```shell script
$ mvn clean install -DskipITs=false
```

:warning: When using `-DskipITs=false` option, please make sure you have an EHRbase instance up and running. The easiest way to achieve that is to use one of the provided docker-compose files in the **docker** folder:

```shell script
# Start up an EHRbase instance
cd docker
docker-compose -f docker-compose-light.yml up
```

### Run the application

```bash
$ java -jar fhir-bridge-1.0.0-SNAPSHOT.jar
```

## Docker and Docker Compose

### Build the Docker image

```
$ mvn clean spring-boot:build-image
```

### Start a Docker container

```bash
$ docker run -p 8888:8888 -e "FHIR_BRIDGE_EHRBASE_BASE_URL=http://172.17.0.1:8080/ehrbase/rest/openehr/v1/" \
             --name=fhir-bridge ehrbaseorg/fhir-bridge
```

### Setup a full environment using Docker Compose

```bash
$ cd docker
$ docker-compose -f docker-compose-full.yml up
```

## Configuration Properties

| Key                                                        | Default Value                                    | Description                                                 |
| :--------------------------------------------------------- | :----------------------------------------------- | :---------------------------------------------------------- |
| `fhir-bridge.ehrbase.base-url`                             | `http://localhost:8080/ehrbase/rest/openehr/v1/` | Base URL for the EHRbase running instance.                  |
| `fhir-bridge.ehrbase.security.type`                        | `basic_auth`                                     | HTTP authorization type used by EHRbase.                    |
| `fhir-bridge.ehrbase.security.username`                    | `myuser`                                         | Basic Auth username.                                        |
| `fhir-bridge.ehrbase.security.password`                    | `myPassword432`                                  | Basic Auth password.                                        |
| `fhir-bridge.ehrbase.template.prefix`                      | `classpath:/opt/`                                | Prefix to apply to template names.                          |
| `fhir-bridge.fhir.jpa.allow-external-references`           | `true`                                           | Allow remote references.                                    |
| `fhir-bridge.fhir.validation.terminology.mode`             | `none`                                           | Terminology validation mode: `embedded`, `server`, `none`   |
| `fhir-bridge.fhir.validation.terminology.server-url`       |                                                  | Base URL of the server used for the terminology validation. |
| `ipf.atna.audit-enabled`                                   | `true`                                           | Whether auditing is enabled.                                |
| `ipf.atna.audit-repository-host`                           | `localhost`                                      | Host of the ATNA repository to send the events to.          |
| `ipf.atna.audit-repository-port`                           | `3001`                                           | Port of the ATNA repository to send the events to.          |
| `spring.application.name`                                  | `FHIR Bridge`                                    | Application name.                                           |
| `spring.datasource.password`                               |                                                  | Login password of the database.                             |
| `spring.datasource.url`                                    |                                                  | JDBC URL of the database.                                   |
| `spring.datasource.username`                               |                                                  | Login username of the database.                             |
| `spring.jpa.properties.hibernate.dialect`                  | `org.hibernate.dialect.H2Dialect`                | Tells Hibernate to generate the appropriate SQL statements. |
| `spring.jpa.properties.hibernate.search.default.indexBase` | `${java.io.tmpdir}/fhir-bridge-poc/indexes`      | Default base directory for the indexes.                     |
| `server.port`                                              | `8888`                                           | Server HTTP port.                                           |
| `server.servlet.context-path`                              | `/fhir-bridge-poc`                               | Context path of the application.                            |
