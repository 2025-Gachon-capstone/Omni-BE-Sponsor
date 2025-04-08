# builder image
FROM amazoncorretto:17-al2-jdk AS builder

RUN mkdir /Omni-BE-Sponsor
WORKDIR /Omni-BE-Sponsor

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean bootJar

# runtime image
FROM amazoncorretto:17.0.12-al2

ENV TZ=Asia/Seoul
ENV PROFILE=${PROFILE}

RUN mkdir /Omni-BE-Sponsor
WORKDIR /Omni-BE-Sponsor

COPY --from=builder /Omni-BE-Sponsor/build/libs/Omni-BE-Main-* /Omni-BE-Sponsor/app.jar

CMD ["sh", "-c", " \
    java -Dspring.profiles.active=${PROFILE} \
         -jar /Omni-BE-Sponsor/app.jar"]
