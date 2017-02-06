package com.droletours

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

object Constants {
  def actorSystemName = "on-spray-can"
  def actorServiceName = "demo-service"
  def localhost = "localhost"
  def port = 8080
}

object Boot extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem(Constants.actorSystemName)

  // create and start our service actor
  val service = system.actorOf(Props[MyServiceActor], Constants.actorServiceName)

  implicit val timeout = Timeout(5 seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(service, interface = Constants.localhost, port = Constants.port)
}
