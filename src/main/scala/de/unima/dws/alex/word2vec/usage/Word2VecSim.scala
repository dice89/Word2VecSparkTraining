package de.unima.dws.alex.word2vec.usage

import _root_.de.unima.dws.alex.word2vec.training.ModelUtil
import org.apache.spark.mllib.feature.Word2VecModel
import org.apache.spark.mllib.linalg.Vector

/**
 * Created by mueller on 03/02/15.
 */
object Word2VecSim extends App {

  val stemmed_model = ModelUtil.loadWord2VecModel("/Users/mueller/Coding/Word2Vectors/webbase10p/model_word2vec_stemmed.ser");
  val unstemmed_model = ModelUtil.loadWord2VecModel("/Users/mueller/Coding/Word2Vectors/webbase10p/model_word2vec_stemmed.ser");

  val term1 = "house"
  val term2 = "building"

  val similarity = cousineSimilarityBetweenTerms(stemmed_model, ModelUtil.porter_stem(term1), ModelUtil.porter_stem(term2))


  val unstemmed_similarity = cousineSimilarityBetweenTerms(unstemmed_model, term1, term2)

  def cousineSimilarityBetweenTerms(model: Word2VecModel, term1: String, term2: String): Double = {

    val vec_1: Vector = model.transform(term1)
    val vec_2: Vector = model.transform(term2)
    val cosine_sim = CosineSimilarity.cosineSimilarity(vec_1.toArray, vec_2.toArray)

    cosine_sim
  }


}
