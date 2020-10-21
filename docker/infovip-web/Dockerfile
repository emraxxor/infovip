FROM jboss/wildfly:18.0.1.Final

USER root 
RUN mkdir -p /opt/ivip-images/media
RUN mkdir -p /opt/ivip-images/user
RUN chown -R jboss:jboss /opt/ivip-images

USER jboss

COPY postgresql /opt/jboss/wildfly/modules/system/layers/base/org/postgresql 
COPY mysql /opt/jboss/wildfly/modules/system/layers/base/com/mysql

COPY standalone-full.xml /opt/jboss/wildfly/standalone/configuration/standalone-full.xml

RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin --silent

VOLUME /opt/ivip-images/media
VOLUME /opt/ivip-images/user

EXPOSE 8843
EXPOSE 9990

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-c", "standalone-full.xml"]

