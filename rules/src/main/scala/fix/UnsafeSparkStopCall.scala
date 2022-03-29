package fix

import scalafix.Diagnostic
import scalafix.v1.SyntacticRule
import scalafix.v1.SemanticRule
import scalafix.v1.SemanticDocument
import scalafix.v1.Patch
import scala.meta.Term

class UnsafeSparkStopCall extends SemanticRule("UnsafeSparkStopCall") {

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      // look up all `***.stop()` occurence
      case ap @ Term.Apply(
            slct @ Term.Select(t @ Term.Name(_), Term.Name("stop")),
            Nil
          ) =>
        import scalafix.v1._
        val sparkCtx =
          SymbolMatcher.normalized("org.apache.spark.SparkContext")
        // note: I don't know whether it is safe to call SparkSession#stop
        val sparkSession =
          SymbolMatcher.normalized("org.apache.spark.sql.SparkSession")
        // extract type info from `***` and raise lint warning if it is SparkContext#stop
        t.symbol.info.get.signature match {
          case MethodSignature(
                _,
                _,
                TypeRef(_, sparkCtx(_), List())
              ) =>
            scalafix.patch.Patch.lint(NoSparkCtxStop(ap.pos))
          case MethodSignature(
                _,
                _,
                TypeRef(_, sparkSession(_), List())
              ) =>
            scalafix.patch.Patch.lint(NoSparkSessStop(ap.pos))
          case _ => scalafix.patch.Patch.empty
        }
    }.asPatch
  }
  override def isLinter: Boolean = true
}
