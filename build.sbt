ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.4"

lazy val root = (project in file("."))
  .settings(
    name := "POSOTemplate"
  )

lazy val zioVersion = "2.0.10"

libraryDependencies ++= Seq(

)

testFrameworks += TestFramework("zio.test.sbt.ZTestFramework")