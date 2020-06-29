# Spring Reactive stack - web services
This is a sample project to explore the cloud native architecture using the spring reactive (non blocking) stack of service components.

Instructions to run:
* mvn clean install
* docker-compose up -d
* Import the realm.json under db-service folder into keycloak for realm creation.
* Create couple of users, one with role READ_ONLY and the other with SUPER_USER.
* Access the api endpoints , using basic auth of the created resource user from the client.
  *  The gateway will be responsible for fetching and relaying the access token to the services
  *  For subsequent requests, gateway would also get the updated access token via the refresh token

*** Please refer to test-data.md for all endpoints.

## Components covered as part of this POC:

*  Spring Cloud Gateway <br/>
*  Spring Boot webflux (Reactive) services <br/>
*  Spring reactive data sources (r2dbc) - postgres <br/>
*  Spring cloud Discovery server - Eureka <br/>
*  Spring cloud Discovery clients - Eureka <br/>
*  Spring cloud Loadbalancing with reactive webclient and using Eureka <br/>
*  Circuit Breaker - Resilience4J <br/>
*  Keycloak OIDC Auth server <br/>
*  Spring oauth2 resource password grant implementation <br/>
*  Spring cloud security <br/>
*  Spring actuator enablement for circuit breaker health integration <br/>
*  Micrometer integration with prometheus timeseries db <br/>
*  Grafana dashboard integration for metrics from prometheus ds <br/>
*  Logstash for log collection from the service containers <br/>
*  Elastic Search for log aggregation and indexing from logstash <br/>
*  Kibana for log monitoring using Logstash and Elasticsearch (ELK) <br/>
*  Minio for S3 buckets (static content object storage) <br/>
*  Spring cloud gateway to create AWS s4 signature and proxy s3 service endpoint <br/>
*  Spring Boot Admin server and Spring boot clients for monitoring services <br/>
*  Docker/Docker Compose <br/>
