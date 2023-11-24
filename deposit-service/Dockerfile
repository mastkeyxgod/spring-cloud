FROM openjdk:17-jdk-alpine
EXPOSE 9090
COPY /build/libs/deposit-service-1.0-SNAPSHOT.jar .
RUN apk add --no-cache bash
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
ENTRYPOINT ["/wait-for.sh", "gateway:8989", "--timeout=70", "--", "java", "-jar", "deposit-service-1.0-SNAPSHOT.jar"]