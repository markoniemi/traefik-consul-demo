# Traefic Consul SpringBoot demo

A demonstration of using Consul Connect service discovery and Traefik reverse proxy and load balancer with Spring Boot application. 

Run following commands:

    ./build.sh
    docker-compose up -d    

Browse to localhost/demo/hello

## Architecture

Traefik is a dynamic reverse proxy. ConsulConnect is a service discovery server. Traefik uses ConsulConnect as a backend. SpringBoot application registers a service to Consul with Traefik specific metadata. Traefik adds a new route using this metadata.

![architecture](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/markoniemi/traefik-consul-demo/master/architecture.uml)
