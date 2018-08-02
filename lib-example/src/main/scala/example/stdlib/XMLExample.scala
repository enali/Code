package example.stdlib

import scala.xml.XML

object XMLExample {

  // 提取元素值,
  // '\'提取匹配的直属子元素, '\\'提取匹配的所有的子元素,
  // 匹配参数为"@attr"时, 提取属性值为attr的元素; 匹配参数为"_"时, 匹配所有子元素
  // '\@'提取当前元素中属性为attr的文本
  def getAllDependency(file: String): Array[MavenDependency] = {
    val xml = XML.load(file)
    // 版本号通常会在properties属性中进行全局配置, 这里解析properties, 转为Map
    val properties = ((xml \ "properties") \ "_").map { property =>
      (property.label, property.text)  // #label是标签名
    }.toMap
    val verpat = """\$\{(.*)\}""".r
    (xml \\ "dependency").map { dep =>
      val version = (dep \ "version").text.trim
      val realVersion =
        if (version.isEmpty) None
          // 如果版本号匹配${xx.yy.zz}, 则从properties中取相应值
        else Some(verpat.findFirstMatchIn(version).map(m => properties(m.group(1))).getOrElse(version))
      MavenDependency(
        (dep \ "groupId").text,
        (dep \ "artifactId").text,
        realVersion
      )
    }.toArray
  }

  def main(args: Array[String]): Unit = {
    val file = "example/stdlib/xml_example.xml"
    val url = getClass.getClassLoader.getResource(file)
    println(getAllDependency(url.getPath).mkString("\n"))
  }

  case class MavenDependency(groupId: String, artifactId: String, version: Option[String])

}
