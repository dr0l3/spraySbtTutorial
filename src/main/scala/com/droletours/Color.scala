package com.droletours

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

/**
  * Created by dr0l3 on 2/4/17.
  */
case class Color(name: String, red: Int, green: Int, blue: Int)

object MyJsonProtocol extends DefaultJsonProtocol {
  implicit val JsonFormat: RootJsonFormat[Color] = jsonFormat4(Color)
}
