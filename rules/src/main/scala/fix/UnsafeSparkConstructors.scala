package fix

import scalafix.Diagnostic
import scalafix.v1.SyntacticRule
import scalafix.v1.SemanticRule
import scalafix.v1.SemanticDocument
import scalafix.v1.Patch
import scala.meta.Term
import scala.meta.Init
import scala.meta.Type

class UnsafeSparkConstructors extends SemanticRule("UnsafeSparkConstructors") {
  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case konstructor @ Term.New(Init(Type.Name("SparkContext"), _, _)) =>
        Patch.lint(NoNewSparkContext(konstructor.pos))
      case konstructor @ Term.New(Init(Type.Name("SparkSession"), _, _)) =>
        Patch.lint(NoNewSparkSession(konstructor.pos))
    }.asPatch
  }
  override def isLinter: Boolean = true
}
