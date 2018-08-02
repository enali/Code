package example.extralib

import spray.json._

/**
  * string -- parseJson --> jsValue -- convertTo[T] --> scala class
  * string <-- xxPrint -- jsValue <-- toJson -- scala class
  */
case class Person(name: String, age: Int)

class Color(val name: String, val red: Int, val green: Int, val blue: Int) {
  override def toString: String = s"$name, $red, $green, $blue"
}

object MyJsonProtocol extends DefaultJsonProtocol {
  // for case class, use jsonFormatN
  implicit val personJsonFormat: RootJsonFormat[Person] = jsonFormat2(Person)

  // for normal class, define the read/write function
  // convert Color to array
  implicit object ColorJsonFormat1 extends RootJsonFormat[Color] {
    override def write(c: Color): JsValue =
      JsArray(JsString(c.name), JsNumber(c.red), JsNumber(c.green), JsNumber(c.blue))
    override def read(value: JsValue): Color = value match {
      case JsArray(Vector(JsString(name), JsNumber(red), JsNumber(green), JsNumber(blue))) =>
        new Color(name, red.toInt, green.toInt, blue.toInt)
      case _ => deserializationError("Color expected")
    }
  }

  // convert Color to object
  implicit object ColorJsonFormat2 extends RootJsonFormat[Color] {
    override def write(c: Color): JsObject = JsObject(
      "name" -> JsString(c.name),
      "red" -> JsNumber(c.red),
      "green" -> JsNumber(c.green),
      "blue" -> JsNumber(c.blue)
    )
    override def read(value: JsValue): Color = {
      value.asJsObject.getFields("name", "red", "green", "blue") match {
        case Seq(JsString(name), JsNumber(red), JsNumber(green), JsNumber(blue)) =>
          new Color(name, red.toInt, green.toInt, blue.toInt)
        case _ => deserializationError("Color expected")
      }
    }
  }
}

object SprayJsonExample {
  import MyJsonProtocol._

  // define the Person class' json reader/writer

  def str2class(): Unit = {
    val source = """{ "name": "enali", "age": 12 }"""
    val jsonAst = source.parseJson
    val personObj = jsonAst.convertTo[Person]
    println(personObj)

    val color1 = """["CadetBlue", 95, 158, 160]"""
    val color2 = """{"name":"CadetBlue","red":95,"green":158,"blue":160}"""
    val color1obj = color1.parseJson.convertTo[Color](ColorJsonFormat1)
    val color2obj = color2.parseJson.convertTo[Color](ColorJsonFormat2)
    println(color1obj)
    println(color2obj)
  }

  def class2str(): Unit = {
    val personObj = Person("enali", 12)
    val jsonAst = personObj.toJson  // use implicit jsonFormat
    val jsonStr = jsonAst.compactPrint  // prettyPrint, sortedPrint
    println(jsonStr)

    val color = new Color("CadetBlue", 95, 158, 160)
    println(color.toJson(ColorJsonFormat1).compactPrint)
    println(color.toJson(ColorJsonFormat2).compactPrint)
  }

  def main(args: Array[String]): Unit = {
    println("----字符串转类对象----")
    str2class()

    println("----类对象转字符串----")
    class2str()
  }
}
