FROM amazoncorretto:17-alpine

RUN mkdir /opt/app

COPY target/home-assignment-0.0.1-SNAPSHOT.jar /opt/app/japp.jar

CMD java $JAVA_OPTS -jar /opt/app/japp.jar

EXPOSE 8080
