package fix

import scalafix.Diagnostic
import scalafix.lint.LintSeverity
import scala.meta.inputs.Position
case class NoSparkCtxStop(override val position: Position) extends Diagnostic {

  override def message: String =
    "Do not call SparkContext#stop() in Databricks Spark. " +
      "See https://docs.databricks.com/jobs.html#use-the-shared-sparkcontext"
  override def severity = LintSeverity.Warning

}

case class NoSparkSessStop(override val position: Position) extends Diagnostic {

  override def message: String =
    "Do not call SparkSession#stop() in Databricks Spark. " +
      "See https://docs.databricks.com/jobs.html#use-the-shared-sparkcontext"
  override def severity = LintSeverity.Warning

}
case class NoSysExit(override val position: Position) extends Diagnostic {
  override def message: String =
    "Do not call System.exit() in Databricks Spark. " +
      "See https://docs.databricks.com/jobs.html#use-the-shared-sparkcontext"
  override def severity = LintSeverity.Warning
}

case class NoNewSparkContext(override val position: Position)
    extends Diagnostic {
  override def message: String =
    "Do not manually create new SparkContext instance in Databricks Spark. Use `SparkContext.getOrCreate()` instread." +
      " See https://docs.databricks.com/jobs.html#use-the-shared-sparkcontext"
  override def severity = LintSeverity.Warning
}

case class NoNewSparkSession(override val position: Position)
    extends Diagnostic {
  override def message: String =
    "Do not manually create new SparkSession instance in Databricks Spark. Use `SparkSession.builder().getOrCreate()` instread." +
      " See https://docs.databricks.com/jobs.html#use-the-shared-sparkcontext"
  override def severity = LintSeverity.Warning
}
