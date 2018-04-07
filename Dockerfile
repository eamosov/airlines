FROM postgres:9.6

RUN echo "deb http://http.debian.net/debian jessie-backports main" >> /etc/apt/sources.list.d/jessie-backports.list
RUN apt-get update
RUN apt-get install --no-install-recommends --no-install-suggests -y -t jessie-backports openjdk-8-jdk maven

ENV JAVA_OPTS "-Dfile.encoding=UTF-8"

ADD docker-entrypoint-initdb.d /docker-entrypoint-initdb.d
RUN mkdir -p /opt/airlines/src
ADD src /opt/airlines/src/
ADD pom.xml /opt/airlines/
RUN cd /opt/airlines && mvn clean install -Dmaven.test.skip=true
RUN docker-entrypoint.sh postgres --version \
    && gosu postgres pg_ctl -D /var/lib/postgresql/data start \
    && cd /opt/airlines && mvn test \
    && gosu postgres pg_ctl -D /var/lib/postgresql/data stop

ADD start.sh /
EXPOSE 8080
CMD ["/start.sh"]
