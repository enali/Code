addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.7")
// 用于在sbt中运行Junit5的测试代码
resolvers += Resolver.jcenterRepo
addSbtPlugin("net.aichler" % "sbt-jupiter-interface" % "0.7.0")