FROM hseeberger:scala-sbt
RUN sbt update
RUN sbt compile
ENTRYPOINT ["sbt" "run"]