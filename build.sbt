name := """dynamoDB-demo"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs
)

libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.9.0"

libraryDependencies += "com.google.inject" % "guice" % "3.0"

libraryDependencies += "org.projectlombok" % "lombok" % "1.14.8"

libraryDependencies += "ma.glasnost.orika" % "orika-core" % "1.3.5"


