FROM ubuntu:16.04


RUN groupadd -g 1000 elasticsearch && useradd elasticsearch -u 1000 -g 1000 

# install APT transport and wget
RUN apt-get update && \
  apt-get install  -y --no-install-recommends \
  apt-transport-https \
  wget -y \
  curl -y

RUN wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.6.2-amd64.deb --no-check-certificate

RUN yes | dpkg -i elasticsearch-7.6.2-amd64.deb


COPY mappings /opt/mappings

WORKDIR /opt/mappings

RUN /etc/init.d/elasticsearch start && \
    echo "Waiting for elasticsearch...." &&\
    sleep 20  && \
    bash indices.sh


EXPOSE 9200 

ENV XMS="1g"
ENV XMX="1g"
ENV ES_PATH_CONF="/etc/elasticsearch"

COPY entrypoint.sh /usr/local/bin/docker-entrypoint.sh 

RUN chown elasticsearch:elasticsearch /usr/local/bin/docker-entrypoint.sh 

RUN ulimit -n 65535

RUN echo "network.host: 0.0.0.0" >> /etc/elasticsearch/elasticsearch.yml
RUN echo "discovery.type: single-node" >> /etc/elasticsearch/elasticsearch.yml
RUN echo "cluster.name: \"ivip-cluster\"" >> /etc/elasticsearch/elasticsearch.yml

USER elasticsearch


CMD ["bash", "/usr/local/bin/docker-entrypoint.sh"]


