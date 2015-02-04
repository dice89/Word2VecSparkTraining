package de.unima.dws.alex.word2vec.training

/**
 * Created by mueller on 03/02/15.
 */

import java.io._
import java.util.Properties


import edu.stanford.nlp.pipeline.StanfordCoreNLP
import epic.preprocess.{MLSentenceSegmenter, TreebankTokenizer}
import org.apache.spark.mllib.feature.{Word2Vec, Word2VecModel}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.tartarus.snowball.ext.PorterStemmer

import scala.collection.parallel.immutable.ParSeq
import scala.io.Source

/**
 * Scala Object does contain all Spark Jobs used in the analysis pipeline
 * Created by mueller on 23/01/15.
 */
object SparkJobs {


  val conf = new SparkConf()
    .setAppName("Alex Master Thesis")
    .setMaster("local["+Config.NO_OF_CORES+"]")
    .set("spark.executor.memory", Config.RAM+"g")
  //.set("spark.rdd.compress", "true")

  val sc = new SparkContext(conf)
  

  def trainBasedOnWebBaseFiles(path: String, stemmed: Boolean): Unit = {
    val folder: File = new File(path)

    val files: ParSeq[File] = folder.listFiles(new TxtFileFilter).toIndexedSeq.par

    var i = 0;

    //preprocess files parallel
    val training_data_raw: ParSeq[RDD[Seq[String]]] = files.map(file => {
      val props = new Properties();
      props.setProperty("annotators", "tokenize, ssplit");
      val pipeline = new StanfordCoreNLP(props);
      //preprocess line of file


      val rdd_lines: Iterator[Option[Seq[String]]] = for (line <- Source.fromFile(file).getLines) yield {
        if (stemmed) {
          processWebBaseLineStemmed(pipeline,line)
        } else {
          processWebBaseLine(pipeline, line)
        }
      }
      val filtered_rdd_lines = rdd_lines.filter(line => line.isDefined).map(line => line.get).toList
      println(s"File $i done")
      i = i + 1
      sc.parallelize(filtered_rdd_lines)
    })


    val rdd_file = training_data_raw.seq.reduceLeft((A, B) => {
      A.union(B)
    })

    val starttime = System.currentTimeMillis()
    println("Start Training")
    val word2vec = new Word2Vec()

    word2vec.setVectorSize(100)
    val model: Word2VecModel = word2vec.fit(rdd_file)

    println("Training time: " + (System.currentTimeMillis() - starttime))
    if(stemmed){
      ModelUtil.storeWord2VecModel(model, Config.STEMMED_WORD2VEC_MODEL_PATH)

    }else {
      ModelUtil.storeWord2VecModel(model, Config.WORD2VEC_MODEL_PATH)
    }

  }


  def processWebBaseLine(pipeline: StanfordCoreNLP, line: String): Option[Seq[String]] = {
    if (line.isEmpty) {
      Option.empty
    } else {
      val text = line.replace("\"", "")
      val sentences: IndexedSeq[IndexedSeq[String]] = ModelUtil.tokenizeText(text, pipeline)
      val words_seq = sentences.map(sentence => sentence.map(word => {
        word.toLowerCase()
      })).flatten.toSeq
      Option(words_seq)
    }
  }

  def processWebBaseLineStemmed(pipeline: StanfordCoreNLP, line: String): Option[Seq[String]] = {
    if (line.isEmpty) {
      Option.empty
    } else {
      val text = line.replace("\"", "")
      val sentences: IndexedSeq[IndexedSeq[String]] = ModelUtil.tokenizeText(text, pipeline)
      val words_seq = sentences.map(sentence => sentence.map(word => {
        ModelUtil.porter_stem(word.toLowerCase())
      })).flatten.toSeq
      Option(words_seq)
    }
  }







}
