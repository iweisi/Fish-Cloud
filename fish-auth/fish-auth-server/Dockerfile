FROM openjdk:8-stretch
#FROM openjdk:8-jdk-windowsservercore-1809

MAINTAINER devifish@outlook.com

WORKDIR /project
ADD fish-auth-server.jar ./
EXPOSE 8020

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

ENV JAVA_OPTS="-Xms128m -Xmx256m -Xss256k -XX:+UseConcMarkSweepGC -Djava.security.egd=file:/dev/./urandom"
ENV SPRING_OPTS="-Dspring.profiles.active=prod"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS $SPRING_OPTS -jar fish-auth-server.jar"]