package fix
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext

object InvokeSparkSessionStop extends App {
  val sparkCtx = SparkContext.getOrCreate()
  val sess = SparkSession.builder().getOrCreate()
  sess.stop()
  sparkCtx.stop()
}
