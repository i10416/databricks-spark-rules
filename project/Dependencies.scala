import sbt._
object Deps {
  val sparkVersion = "3.3.0"
  val scalafixVersion = "0.9.34"
  val sparkBasic = Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion
  )
  val sparkStream = "org.apache.spark" %% "spark-streaming" % sparkVersion

  val sparkAll = sparkBasic :+ sparkStream

  val collectionCompat = Seq(
    "org.scala-lang.modules" %% "scala-collection-compat" % "2.7.0"
  )
  val catsEffect = Seq(
    "org.typelevel" %% "cats-effect" % "3.3.9"
  )

  val scalafix = Seq(
    "ch.epfl.scala" %% "scalafix-core" % scalafixVersion
  )
}
