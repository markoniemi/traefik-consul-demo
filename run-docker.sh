PORT=${1:-8082}
docker run \
-it --rm \
-d \
--name demo-application-${PORT} \
-e PORT=${PORT} \
-p ${PORT}:${PORT} \
--network traefik-consul-demo_microservices \
--network-alias demo \
markoniemi/traefik-consul-demo
