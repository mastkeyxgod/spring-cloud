FROM openjdk:17-jdk-alpine
COPY /build/libs/notification-service-1.0-SNAPSHOT.jar .
RUN apk add --no-cache bash
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
ENTRYPOINT ["/wait-for.sh", "gateway:8989", "--timeout=70", "--", "java", "-jar", "notification-service-1.0-SNAPSHOT.jar"]