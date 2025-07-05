FROM gradle:7-jdk11 as build

COPY --chown=gradle:gradle . /app/src
WORKDIR /app/src
RUN gradle build --no-daemon 

FROM adoptopenjdk/openjdk11:alpine-jre 
WORKDIR /app
RUN addgroup -S svcuser \
    && adduser -S svcuser -G svcuser \
    && chown --recursive svcuser:svcuser /app

COPY --from=build /app/src/build/libs/*.jar /app/
USER svcuser
ENV TZ="Asia/Ho_Chi_Minh"
EXPOSE 9720
ENTRYPOINT exec java $JAVA_OPTS -XX:+UnlockExperimentalVMOptions -XX:+UseContainerSupport -Dspring.profiles.active=uat -Djava.security.egd=file:/dev/./urandom -jar /app/account-service-1.0.jar

