PORT=${1:-8082}
docker run \
-it --rm \
-d \
--name demo-application-${PORT} \
-e PORT=${PORT} \
-e CONSUL_HOST=consul \
-e CONSUL_PORT=8500 \
-p ${PORT}:${PORT} \
--network traefik-consul-demo_microservices \
--network-alias demo \
markoniemi/traefik-consul-demo
