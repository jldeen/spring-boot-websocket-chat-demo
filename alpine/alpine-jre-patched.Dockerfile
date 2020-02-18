FROM adoptopenjdk/openjdk11:x86_64-alpine-jre11u-nightly
RUN apk update && apk add bash