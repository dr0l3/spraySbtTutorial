FROM hseeberger/scala-sbt

RUN git clone https://github.com/dr0l3/spraySbtTutorial.git

RUN cd spraySbtTutorial

WORKDIR spraySbtTutorial

RUN sbt update

RUN sbt compile

ENTRYPOINT ["sbt","run"]