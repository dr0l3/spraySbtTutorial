FROM anapsix/alpine-java

COPY ./target/scala-2.11/color.jar color.jar

ENTRYPOINT ["java","-jar","color.jar"]