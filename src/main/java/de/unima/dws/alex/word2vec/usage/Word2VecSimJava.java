package de.unima.dws.alex.word2vec.usage;

import de.unima.dws.alex.word2vec.training.Config;
import de.unima.dws.alex.word2vec.training.ModelUtil;
import org.apache.spark.mllib.feature.Word2VecModel;
import scala.Tuple2;

/**
 * Created by mueller on 04/02/15.
 */
public class Word2VecSimJava {

    public static void main(String[] args){


        Word2VecModel model_stemmed = ModelUtil.loadWord2VecModel("/Users/mueller/Coding/Word2Vectors/webbase10p/model_word2vec_stemmed.ser");
        Word2VecModel model_unstemmed = ModelUtil.loadWord2VecModel("/Users/mueller/Coding/Word2Vectors/webbase10p/model_word2vec.ser");
        System.out.println("Stemmed example");
        System.out.println("#############################################");

        String term1= "scholar";
        String term2 ="student";


        double result = Word2VecSim.cousineSimilarityBetweenTerms(model_stemmed,ModelUtil.porter_stem(term1),ModelUtil.porter_stem(term2));
        System.out.println("Similarity between " + term1 + " " + term2 +" is "+ result);


        //get synonyms for term
        System.out.println("Get Top 20 Synonyms for "+term1);
        for (Tuple2<String, Object> lunch : model_stemmed.findSynonyms(ModelUtil.porter_stem(term1), 20)) {
            System.out.println(lunch._1());
        }


        System.out.println("UnStemmed example");
        System.out.println("#############################################");

        double result_unstemmed = Word2VecSim.cousineSimilarityBetweenTerms(model_unstemmed,term1,term2);
        System.out.println("Similarity between unstemmed" + term1 + " " + term2 +" is "+ result_unstemmed);

    }
}
