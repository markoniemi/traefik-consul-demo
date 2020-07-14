# Traefic Consul SpringBoot demo

A demonstration of using Consul Connect service discovery and Traefik reverse proxy with Spring Boot application. 

Run following commands:

    docker-compose up -d
    ./run.sh

Browse to localhost/demo/hello

## Architecture

Traefik is a dynamic reverse proxy. ConsulConnect is a service discovery server. Traefik uses ConsulConnect as a backend. SpringBoot application registers a service to Consul with Traefik specific metadata. Traefik adds a new route using this metadata.

