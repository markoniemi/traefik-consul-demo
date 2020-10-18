# build only one module:
# ./build -pl user-repository -am

docker run \
-it --rm \
-v /var/run/docker.sock:/var/run/docker.sock \
-v "$PWD":/usr/src \
-v "$HOME/.m2":/root/.m2 \
-w /usr/src \
maven:3.6-jdk-8-alpine \
mvn package -DskipTests=true -P docker "$@"
