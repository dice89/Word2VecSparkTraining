package de.unima.dws.alex.word2vec.training

import java.io.{ObjectOutputStream, FileOutputStream, ObjectInputStream, FileInputStream}
import java.util
import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations.{TextAnnotation, SentencesAnnotation, TokensAnnotation}
import edu.stanford.nlp.ling.CoreLabel
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import org.apache.spark.mllib.feature.Word2VecModel
import org.tartarus.snowball.ext.PorterStemmer
import scala.collection.JavaConversions._
import scala.collection.immutable.IndexedSeq
import scala.collection.mutable

/**
 * Created by mueller on 04/02/15.
 */
object ModelUtil extends  App {

  def loadWord2VecModel(file: String): Word2VecModel = {
    val file_in = new FileInputStream(file)
    val obj_in = new ObjectInputStream(file_in);

    val model: Word2VecModel = obj_in.readObject().asInstanceOf[Word2VecModel]

    obj_in.close()
    file_in.close()

    model
  }

  def storeWord2VecModel(model: Word2VecModel, file: String): Unit = {
    val file_out = new FileOutputStream(file)
    val obj_out = new ObjectOutputStream(file_out)
    obj_out.writeObject(model);
    obj_out.flush()
    obj_out.close()
    file_out.flush()
    file_out.close()
  }

  def porter_stem(a: String): String = {
    val stemmer: PorterStemmer = new PorterStemmer()
    stemmer.setCurrent(a)
    if (stemmer.stem()) {
      stemmer.getCurrent
    }else {
      a
    }

  }



  def tokenizeText(text:String, pipeline:StanfordCoreNLP) :IndexedSeq[IndexedSeq[String]] = {

    // create an empty Annotation just with the given text
    val document = new Annotation(text);

    // run all Annotators on this text
    pipeline.annotate(document);

    val sentences = document.get(classOf[SentencesAnnotation]);

    val tokens: IndexedSeq[IndexedSeq[String]] = sentences.map(sentence  => sentence.get(classOf[TokensAnnotation]).toIndexedSeq.map(token => token.get(classOf[TextAnnotation]).replaceAll("[^\\w\\s]",""))).toIndexedSeq

    tokens
  }
}
