package de.unima.dws.alex.word2vec.training

/**
 * Created by mueller on 03/02/15.
 */
object StartTrain extends App{

  val path_to_config:String = if(args.size <1 ) "config.txt" else args(0)

  Config.readFromFile(path_to_config)

  println("Start Training with the following Config")

  println(Config)

  SparkJobs.trainBasedOnWebBaseFiles(Config.WEBBBASE_CORPUS_LOCATION,Config.TRAIN_STEMMED)

  SparkJobs.sc.stop()

}
