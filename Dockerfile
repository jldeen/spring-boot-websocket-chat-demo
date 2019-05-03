FROM maven:3.5.4-jdk-8-alpine as BUILD

COPY . /usr/src/app
ADD settings.xml /root/.m2/settings.xml
RUN mvn --batch-mode -f /usr/src/app/pom.xml clean package

FROM openjdk:10-jre-slim
ENV PORT 8080
EXPOSE 8080
COPY --from=BUILD /usr/src/app/target /opt/target
WORKDIR /opt/target

CMD ["/bin/bash", "-c", "find -type f -name '*.jar' | xargs java -jar"]
