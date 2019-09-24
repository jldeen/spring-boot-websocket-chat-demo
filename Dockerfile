# FROM openjdk:11-jre-slim
FROM jfrogjd-docker.jfrog.io/jldeen/alpine-jre-patched:latest
RUN apk update && apk add bash
ENV PORT 8080
EXPOSE 8080
COPY ./target /opt/target
WORKDIR /opt/target

CMD ["/bin/bash", "-c", "find -type f -name '*.jar' | xargs java -jar"]