/*
rule = UnsafeSparkConstructors
 */
package fix
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext

object InvokeUnsafeConstructor extends App {
  val sc = new SparkContext()
}
