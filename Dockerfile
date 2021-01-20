FROM openjdk:8-jdk-alpine
# FROM openjdk:11-jre-slim
RUN apk update && apk add bash
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/websocket-demo-1.0.2-SNAPSHOT.jar spring-boot-websocket-chat-demo.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar spring-boot-websocket-chat-demo.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar spring-boot-websocket-chat-demo.jar
