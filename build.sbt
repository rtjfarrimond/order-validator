name := "order-validator"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core" % "2.0.0",
    "org.scalactic" %% "scalactic" % "3.1.2",
    "org.scalatest" %% "scalatest" % "3.1.2" % "test"
    )

// scalac options come from the sbt-tpolecat plugin so need to set any here

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)
