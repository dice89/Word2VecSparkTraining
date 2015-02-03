package de.unima.dws.alex.word2vec.training

import scala.io.Source

/**
 * Created by mueller on 03/02/15.
 */
object Config {
  val STEMMED_WORD2VEC_MODEL_PATH: String = "models/model_word2vec_stemmed.ser"

  val WORD2VEC_MODEL_PATH: String = "models/model_word2vec.ser"

  var WEBBBASE_CORPUS_LOCATION: String = "webbase_corpus"

  var NO_OF_CORES: String = "2"

  var RAM: String = "12"

  var TRAIN_STEMMED:Boolean = true

  def readFromFile(path: String): Unit = {
    Source.fromFile(path).getLines.foreach(line => {
      val splitted_line = line.split("=")
      println(splitted_line.toSeq)
      if (splitted_line(0).equals("NO_CORES")) {
        NO_OF_CORES = splitted_line(1).trim
      } else if (splitted_line(0).equals("RAM")) {
        RAM = splitted_line(1).trim
      } else if (splitted_line(0).equals("CORPUS_LOC")) {
        WEBBBASE_CORPUS_LOCATION = splitted_line(1).trim
      }else if (splitted_line(0).equals("TRAIN_STEMMED")) {
        TRAIN_STEMMED = splitted_line(1).trim.toBoolean
      }
    })
  }

  override def toString():String = {
    s"Number of Cores: $NO_OF_CORES , RAM: $RAM , STEMMED?: $TRAIN_STEMMED , WEBBBASE_CORPUS_LOCATION: $WEBBBASE_CORPUS_LOCATION "
  }
}
