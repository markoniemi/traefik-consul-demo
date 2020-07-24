# Traefic Consul SpringBoot demo

A demonstration of using Consul Connect service discovery and Traefik reverse proxy and load balancer with Spring Boot application. 

Run following commands:

    ./build.sh
    docker-compose -f infrastructure.yml up -d
    docker-compose -f demo-application-v1.yml -f demo-application-v2.yml up -d

scale services:

    docker-compose -f demo-application-v1.yml -f demo-application-v2.yml up -d --scale demo-application-v1=2 --scale demo-application-v2=2
    
start all services and scale:

    docker-compose -f infrastructure.yml -f demo-application-v1.yml -f demo-application-v2.yml up -d --scale demo-application-v1=2 --scale demo-application-v2=2

 * Open Portainer [http://localhost:9000](http://localhost:9000)
 * Open Consul [http://localhost:8500](http://localhost:8500)
 * Open Traefik [http://localhost:8080](http://localhost:8080)
 * Test v1 service [http://localhost/demo/v1/hello](http://localhost/demo/v1/hello)
 * Test v2 service [http://localhost/demo/v2/hello/name](http://localhost/demo/v2/hello/name)

## Architecture

Traefik is a dynamic reverse proxy. ConsulConnect is a service discovery server. Traefik uses ConsulConnect as a backend. SpringBoot application registers a service to Consul with Traefik specific metadata. Traefik adds a new route using this metadata.

![architecture](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/markoniemi/traefik-consul-demo/master/architecture.uml)

## todo

  * integration tests that load service from consul
