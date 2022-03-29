package fix

import scalafix.Diagnostic
import scalafix.v1.SyntacticRule
import scalafix.v1.SemanticRule
import scalafix.v1.SemanticDocument
import scalafix.v1.Patch
import scala.meta.Term
import scala.meta.Init
import scala.meta.Type

class UnsafeSysExit extends SemanticRule("UnsafeSysExit") {
  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case ap @ Term.Apply(
            Term.Select(Term.Name("System"), Term.Name("exit")),
            _
          ) =>
        Patch.lint(NoSysExit(ap.pos))
    }.asPatch
  }
  override def isLinter: Boolean = true
}
