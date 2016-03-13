import org.apache.spark.SparkContext
import org.apache.spark.mllib.fpm.FPGrowth

/**
  * Created by ubuntu on 3/12/16.
  */
object SampleFPGrowth {
  def main(args: Array[String]) {
    val transactions = Seq(
      "r z h k p",
      "z y x w v u t s",
      "s x o n r",
      "x z y m t s q e",
      "z",
      "x z y r q t p")
      .map(_.split(" "))
    val sc = new SparkContext("local[2]", "Chapter 5 App")
    val rdd = sc.parallelize(transactions, 2).cache()

    val fpg = new FPGrowth()

    val model6 = fpg
      .setMinSupport(0.2)
      .setNumPartitions(1)
      .run(rdd)

    model6.freqItemsets.collect().foreach { itemset =>
        println(itemset.items.mkString("[", ",", "]") + ", " + itemset.freq)
    }
  }
}
