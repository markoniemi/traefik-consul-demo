FROM openjdk:8-jdk-alpine
ARG DEPENDENCY=target/dependency
VOLUME /tmp
RUN apk --no-cache add curl
ENV CONSUL_HOST=consul
ENV CONSUL_PORT=8500
ENV PORT=8083
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/BOOT-INF/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
EXPOSE ${PORT}
COPY target/user-application-0.1-SNAPSHOT.war /app/app.war
ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-XX:MaxRAMFraction=1","-XshowSettings:vm","-jar","/app/app.war"]
HEALTHCHECK --interval=5s --timeout=3s --start-period=40s --retries=5 \
  CMD curl -f http://localhost:${PORT}/users/actuator/health || exit 1
  