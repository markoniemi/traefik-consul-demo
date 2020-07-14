FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN apk --no-cache add curl
ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/BOOT-INF/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
EXPOSE 8082
COPY target/traefik-consul-demo-0.1-SNAPSHOT.jar /app/traefik-consul-demo-0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-XX:MaxRAMFraction=1","-XshowSettings:vm","-jar","/app/traefik-consul-demo-0.1-SNAPSHOT.jar"]
HEALTHCHECK --interval=5s --timeout=3s --start-period=40s --retries=5 \
  CMD curl -f http://localhost:8082/traefik-consul-demo || exit 1
  