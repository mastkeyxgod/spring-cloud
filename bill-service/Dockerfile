FROM openjdk:17-jdk-alpine
EXPOSE 8082
COPY /build/libs/bill-service-1.0-SNAPSHOT.jar .
RUN apk add --no-cache bash
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
ENTRYPOINT ["/wait-for.sh", "gateway:8989", "--timeout=70", "--", "java", "-jar", "bill-service-1.0-SNAPSHOT.jar"]