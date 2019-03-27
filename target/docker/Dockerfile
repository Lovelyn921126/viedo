# Version 0.0.1
FROM java:8

MAINTAINER Yanan Li "84912899@11.com"

# 环境变量
ENV WORK_PATH /home/project/dockerdemo
ENV APP_NAME viedo.war
ENV APP_VERSION 0.0.1-SNAPSHOT

EXPOSE 8080

#USER
#USER user:group

#VOLUME
VOLUME ["/home/project", "/tmp/data"]

#ADD

#COPY
COPY $APP_NAME $WORK_PATH/

#LABEL
#STOPSIGNAL
#ARG
#ONBUILD

# WORKDIR
WORKDIR $WORK_PATH

# ENTRYPOINT 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom"]

# CMD
CMD ["-jar", "viedo.war"]