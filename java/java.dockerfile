FROM codefresh/docker-jfrog-cli-go:initial-branch-c6eeb5c as build

# install java
RUN apk update && apk add openjdk8

# install maven
RUN apk add --no-cache curl tar bash procps

ARG MAVEN_VERSION=3.6.1
ARG USER_HOME_DIR="/root"
ARG SHA=b4880fb7a3d81edd190a029440cdf17f308621af68475a4fe976296e71ff4a4b546dd6d8a58aaafba334d309cc11e638c52808a4b0e818fc0fd544226d952544
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha512sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV M2_HOME /usr/share/maven
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

ENV JFROG_CLI_OFFER_CONFIG false

# Update Jfrog CLI
RUN curl -fL https://getcli.jfrog.io | sh \
  && mv jfrog /usr/local/bin

COPY docker-entrypoint.sh /
ENTRYPOINT ["/docker-entrypoint.sh"]

CMD ["sh","-c"]

