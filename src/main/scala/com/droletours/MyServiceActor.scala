package com.droletours

import akka.actor.{Actor, ActorContext}
import spray.json._
import spray.routing._

import scala.util.Random

class MyServiceActor extends Actor with MyService{

  def actorRefFactory: ActorContext = context

  def receive: Receive = runRoute(myRoute)

}

trait MyService extends HttpService {

  def myRoute: Route = {
    {
      import MyJsonProtocol.JsonFormat
      get {
        path("hello") {
          complete (Color("Some random color", 200, 123, 152).toJson.prettyPrint)
        } ~
        path("hi") {
          complete (Color("Some color with hi", 223, 312, 222).toJson.prettyPrint)
        } ~
        path("colormesilly") {
            //this is a closure. Variables defined here are final e.i. they are not recomputed on path invoke
          complete (getRandomColorWithText("your own rainbow color").toJson.prettyPrint)
        } ~
        path("making a change") {
          complete (Color("making a change", 42, 42, 42).toJson.prettyPrint)
        }
      }
    }
  }

  def getRandomColorWithText(text: String): Color = {
    //variables defined here are recomputed on invoke
    val r = Random.nextInt(265)
    val g = Random.nextInt(265)
    val b = Random.nextInt(265)
    Color(text, r, g, b)
  }
}