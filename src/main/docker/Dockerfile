FROM java
VOLUME /tmp
ADD target/account-be.jar account-be.jar
RUN bash -c 'touch /account-be.jar'
EXPOSE 18072
ENTRYPOINT ["/bin/sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /account-be.jar"]
