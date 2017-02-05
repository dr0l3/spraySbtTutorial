FROM anapsix/alpine-java

COPY ./target/scala-2.11/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]