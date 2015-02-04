package de.unima.dws.alex.word2vec.coreNLP;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import scala.collection.mutable.IndexedSeq;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mueller on 04/02/15.
 */
public class CoreNLP {

    public static String[] tokenize(String text, StanfordCoreNLP pipeline) {


        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);

        // these are all the sentences in this document
        // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        ArrayList<String> test = new ArrayList<String>();
        for(CoreMap sentence: sentences) {

            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                test.add(word);
            }
        }

       return  test.toArray(new String[test.size()]);
    }
}
