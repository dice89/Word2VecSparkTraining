package de.unima.dws.alex.word2vec.usage;

import de.unima.dws.alex.word2vec.training.Config;
import de.unima.dws.alex.word2vec.training.ModelUtil;
import org.apache.spark.mllib.feature.Word2VecModel;

/**
 * Created by mueller on 04/02/15.
 */
public class Word2VecSimJava {

    public static void main(String[] args){
        Word2VecModel model = ModelUtil.loadWord2VecModel(Config.WORD2VEC_MODEL_PATH());
        String term1= "family";
        String term2 ="friends";


        double result = Word2VecSim.cousineSimilarityBetweenTerms(model,ModelUtil.porter_stem(term1),ModelUtil.porter_stem(term2));
        System.out.println("Similarity between " + term1 + " " + term2 +" is "+ result);
    }
}
