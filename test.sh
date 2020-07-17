docker run \
-it --rm \
--network traefik-consul-demo_microservices \
-v "$PWD":/usr/src \
-v "$HOME/.m2":/root/.m2 \
-w /usr/src \
maven:3.6-jdk-8-alpine \
mvn -Dtest=*IT test
