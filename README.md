# Reactive stack  - web services
This is a sample project to explore the cloud native architecture using <br/>
the spring reactive (non blocking) stack of services.

Instructions to run:
* mvn clean install
* docker-compose up -d
* Import the realm.json under db-service folder into keycloak for realm creation.
* Create couple of users, one with role READ_ONLY and the other with SUPER_USER.
* Access the api endpoints , using basic auth of the created resource user from the client.
**  The gateway will be responsible for fetching and relaying the access token to the services
**  For subsequent requests, gateway would also get the updated access token via the refresh token


# Done:
:white_check_mark: Spring Cloud Gateway <br/>
:white_check_mark: Spring Boot webflux (Reactive) services <br/>
:white_check_mark: Spring reactive data sources (r2dbc) - postgres <br/>
:white_check_mark: Spring cloud Discovery server - Eureka <br/>
:white_check_mark: Spring cloud Discovery clients - Eureka <br/>
:white_check_mark: Spring cloud Loadbalancing with reactive webclient and using Eureka <br/>
:white_check_mark: Circuit Breaker - Resilience4J <br/>
:white_check_mark: Keycloak OIDC Auth server <br/>
:white_check_mark: Spring oauth2 resource password grant implementation <br/>
:white_check_mark: Spring security <br/>
:white_check_mark: Spring actuator enablement for circuit breaker health integration <br/>
:white_check_mark: Micrometer integration with prometheus timeseries db <br/>
:white_check_mark: Grafana dashboard integration for metrics from prometheus ds <br/>
:white_check_mark: Docker <br/>
:white_check_mark: Docker Compose <br/>

# To Do
:question: Webflux unit tests <br/>
:question: Messaging <br/>
:question: Admin UI <br/>
:question: Zipkin/Sleuth for logging and tracing<br/>
