val scala212 = "2.12.15"
val scala213 = "2.13.8"

val commonSettings = Seq(
  scalacOptions ++= {
    import sbt.Opts.compile._
    Seq(
      deprecation,
      "-feature"
    )
  }
)

lazy val noPublishSettings = Seq(
  publish / skip := true,
  publishLocal / skip := true
)

inThisBuild(
  Seq(
    versionScheme := Some("early-semver"),
    organization := "dev.i10416",
    description := "scalafix rules for databricks spark",
    licenses := List(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    homepage := Some(url("https://github.com/i10416/databricks-spark-rules")),
    developers := List(
      Developer(
        "i10416",
        "Yoichiro Ito",
        "contact.110416+sparkrules@mail.com",
        url("https://github.com/i10416")
      )
    ),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/i10416/databricks-spark-rules"),
        "scm:git@github.com:i10416/databricks-spark-rules.git"
      )
    ),
    sonatypeCredentialHost := "s01.oss.sonatype.org",
    sonatypeRepository := "https://s01.oss.sonatype.org/service/local",
    scalaVersion := scala212,
    crossScalaVersions := Seq(scala212, scala213),
    semanticdbEnabled := true,
    semanticdbIncludeInJar := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    scalacOptions ++= List("-Yrangepos")
  )
)

lazy val rules = project
  .settings(commonSettings)
  .settings(
    name := "databricks-spark-rules",
    libraryDependencies ++= Deps.scalafix
  )
lazy val input = project
  .settings(
    libraryDependencies ++= Deps.sparkBasic
  )
  .settings(noPublishSettings)
lazy val output = project
  .settings(
    libraryDependencies ++= Deps.sparkBasic
  )
  .settings(noPublishSettings)
lazy val tests = project
  .settings(
    libraryDependencies += "ch.epfl.scala" %% "scalafix-testkit" % Deps.scalafixVersion % Test cross CrossVersion.full,
    scalafixTestkitOutputSourceDirectories :=
      (output / Compile / unmanagedSourceDirectories).value,
    scalafixTestkitInputSourceDirectories :=
      (input / Compile / unmanagedSourceDirectories).value,
    scalafixTestkitInputClasspath :=
      (input / Compile / fullClasspath).value
  )
  .settings(noPublishSettings)
  .dependsOn(rules)
  .enablePlugins(ScalafixTestkitPlugin)

lazy val core = project
  .in(file("."))
  .aggregate(
    rules,
    input,
    output,
    tests
  )
  .settings(noPublishSettings)
