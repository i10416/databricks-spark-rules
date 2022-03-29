package fix

import scalafix.Diagnostic
import scalafix.lint.LintSeverity
import scala.meta.inputs.Position
case class NoMaster(override val position: Position) extends Diagnostic {
  override def message: String =
    "avoid hard-coding spark master setting in jar jobs"
  override def severity: LintSeverity = LintSeverity.Warning
}
