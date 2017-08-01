FROM java:openjdk-8
VOLUME /tmp
ADD target/rabbitmq-learn.jar rabbitmq-learn.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /rabbitmq-learn.jar" ]