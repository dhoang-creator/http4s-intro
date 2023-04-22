ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.4"

lazy val root = (project in file("."))
  .settings(
    name := "http4s-intro"
  )

val http4sVersion = "0.23.18"
val http4sBlaze = "0.23.14"

libraryDependencies ++= Seq(
  // Cats Effect
  "org.typelevel" %% "cats-core"            % "2.9.0",
  "org.typelevel" %% "cats-effect"          % "3.4.9",

  // http4s
  "org.http4s"    %% "http4s-dsl"           % http4sVersion,
  "org.http4s"    %% "http4s-blaze-server"  % http4sBlaze,
  "org.http4s"    %% "http4s-blaze-client"  % http4sBlaze
)