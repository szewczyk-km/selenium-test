FROM openjdk:10-jre-slim
MAINTAINER "Krzysztof Szewczyk"

ADD test.sh /usr/local/bin/run
RUN chmod u+x /usr/local/bin/run

ADD selenium-test-jar-with-dependencies.jar /srv/tests.jar

CMD run

