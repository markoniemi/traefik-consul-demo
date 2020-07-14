docker run \
-it --rm \
-d \
-p 8082:8082 \
--network traefik-consul-demo_microservices \
--network-alias demo \
markoniemi/traefik-consul-demo
