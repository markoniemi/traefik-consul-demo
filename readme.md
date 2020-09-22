
# Traefic Consul SpringBoot demo

A demonstration of using Consul Connect service discovery and Traefik reverse proxy and load balancer with Spring Boot application.

## Architecture

Traefik is a dynamic reverse proxy. ConsulConnect is a service discovery server. Traefik uses ConsulConnect as a backend. SpringBoot application registers a service to Consul with Traefik specific metadata. Traefik adds a new route using this metadata.

![architecture](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/markoniemi/traefik-consul-demo/master/architecture.uml)

## Infrastructure 

Start Portainer, Consul and Traefik:

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

Check that there are user-repository-v1 and user-application-v1 services in Consul: [http://localhost:8500](http://localhost:8500)

1. Stop user-repository in Portainer
2. Note that user-repository-v1 shows error in Consul
3. Start user-repository in Portainer
4. Note how quickly user-repository-v1 becomes available in Consul

## Reverse proxy

Traefik shows a http port for user-application: [http://localhost:8080](http://localhost:8080)

Application should be available through reverse proxy: [http://localhost/users](http://localhost/users)

1. Login to application [http://localhost/users](http://localhost/users)
2. Note service logs in Portainer

Traefik also show alternative address for Consul: [http://http://consul.docker.localhost](http://http://consul.docker.localhost)

## Load balancing

scale services:

    docker-compose up -d --scale user-repository=2

1. Portainer shows two instances for user-repository
2. Consul shows two instances of service user-repository-v1
3. Trafik shows two instances
4. Login to application
5. Note service logs in Portainer
6. Refresh application or logout/login
7. Note service logs in Portainer, services are called in round-robin manner

## Config

1. Login to application
2. Note the runtime environment value in footer
3. Add config value to Consul: config/user-application/runtime/environment=production
4. Refresh application view in browser
5. Note the new runtime environment value
6. Note the application log in Portainer
 
