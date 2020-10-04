
# Traefic Consul SpringBoot demo

A demonstration of using Consul Connect service discovery and Traefik reverse proxy and load balancer with Spring Boot application.

## Architecture

Traefik is a dynamic reverse proxy. ConsulConnect is a service discovery server. Traefik uses ConsulConnect as a backend. SpringBoot application registers a service to Consul with Traefik specific metadata. Traefik adds a new route using this metadata.

![architecture](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/markoniemi/traefik-consul-demo/master/architecture.uml)

## Infrastructure 

Start Portainer and Traefik:

    docker-compose up -d portainer traefik consul
    
Open browser: 
 * Portainer [http://localhost:9000](http://localhost:9000)
 * Consul [http://localhost:8500](http://localhost:8500)
 * Traefik [http://localhost:8080](http://localhost:8080)

## Application

Build backend service and frontend application:

    ./build.sh
    docker-compose up -d

## Service discovery

Check that there are user-repository and user-application services in Consul: 
 * Consul [http://localhost:8500](http://localhost:8500)

 # stop user-repository in Portainer
 # note that user-repository shows error in Consul
 # start user-repository in Portainer
 # note how quickly user-repository becomes available in Consul

## Reverse proxy

Traefik shows a http port for user-application:
 * Traefik [http://localhost:8080](http://localhost:8080)

Application should be available through reverse proxy:
 * [http://localhost/users](http://localhost/users)

 # Login to application [http://localhost/users](http://localhost/users)
 # note service logs in Portainer

## Load balancing

scale services:

    docker-compose up -d --scale user-repository=2

 # Portainer shows two instances for user-repository
 # Consul shows two instances
 # Trafik shows two instances
 # Login to application
 # note service logs in Portainer
 # Refresh application or logout/login
 # note service logs in Portainer, services are called in round-robin manner

## Config

 # Login to application
 # Note the runtime environment value in footer
 # Add config value to Consul: config/user-application/runtime.environment=docker-compose
 # Refresh application view in browser
 # Note the new runtime environment value
 # note the application log in Portainer
 