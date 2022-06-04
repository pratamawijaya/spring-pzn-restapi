FROM alpine:3.14

RUN  apk update \
  && apk upgrade \
  && apk add --update openjdk11 tzdata curl unzip bash \
  && rm -rf /var/cache/apk/*

COPY build/libs/springboot-pzn-restapi-0.0.1-SNAPSHOT.jar /app/application.jar

CMD ["java", "-jar", "app/application.jar"]