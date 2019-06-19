name := "getting-dressed"

version := "0.1"

scalaVersion := "2.13.0"

val scalazVersion = "7.2.27"

resolvers ++= Seq(
  "Typesafe" at "http://repo.typesafe.com/typesafe/releases/",
  "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"
)

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
  "org.scalaz" %% "scalaz-core" % "7.2.27",

  "org.scalactic" %% "scalactic" % "3.0.8" % "test",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "org.mockito" % "mockito-core" % "2.28.2" % "test"
)

mainClass in (Compile, packageBin) := Some("gdapp.Main")
