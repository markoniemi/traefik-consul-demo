FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN apk --no-cache add curl
ARG DEPENDENCY=target/dependency
ENV CONSUL_HOST=consul
ENV CONSUL_PORT=8500
ENV VERSION=v1
ENV PATH_PREFIX=/users
ENV PORT 8083
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/BOOT-INF/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
EXPOSE ${PORT}
COPY target/user-application-0.1-SNAPSHOT.war /app/user-application-0.1-SNAPSHOT.war
ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-XX:MaxRAMFraction=1","-XshowSettings:vm","-jar","/app/user-application-0.1-SNAPSHOT.war"]
HEALTHCHECK --interval=5s --timeout=3s --start-period=40s --retries=5 \
  CMD curl -f http://localhost:8083/users/actuator/health || exit 1
  