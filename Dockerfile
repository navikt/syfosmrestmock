FROM navikt/java:11
COPY build/libs/syfosmrestmock-*.jar app.jar
ENV JAVA_OPTS='-Dlogback.configurationFile=logback-remote.xml'
