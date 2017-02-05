package com.droletours

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._

class MyServiceSpec extends Specification with Specs2RouteTest with MyService {
  def actorRefFactory = system
  
  "MyService" should {

    "return a color for GET requests to the hi path" in {
      Get("/hi") ~> myRoute ~> check {
        responseAs[String] must contain("Some color with hi")
      }
    }

    "return a color for GET requests to the hello path" in {
      Get("/hello") ~> myRoute ~> check {
        responseAs[String] must contain("Some random color")
      }
    }

    "return a greeting for GET requests to the colormesilly path" in {
      Get("/colormesilly") ~> myRoute ~> check {
        responseAs[String] must contain("rainbow")
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/kermit") ~> myRoute ~> check {
        handled must beFalse
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> sealRoute(myRoute) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }
  }
}
