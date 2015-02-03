package de.unima.dws.alex.word2vec.usage

import org.apache.spark.mllib.feature.Word2VecModel
import org.apache.spark.mllib.linalg.Vector

/**
 * Created by mueller on 03/02/15.
 */
object Word2VecSim {
  def cousineSimilarityBetweenTerms(model: Word2VecModel, term1: String, term2: String): Double = {

    val vec_1: Vector = model.transform(term1)
    val vec_2: Vector = model.transform(term2)
    val cosine_sim = CosineSimilarity.cosineSimilarity(vec_1.toArray, vec_2.toArray)

    cosine_sim
  }
}
