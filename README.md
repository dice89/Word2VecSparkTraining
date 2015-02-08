# Word2Vec Trained for Apache Spark
Simple Project to train Word2Vec 100-dimensional word similarity vectors based on the Apache Spark utilty with the UMBC Webbase corpus[1] as training data.

There exists currently 3 sizes of vectors:

-XS : Trained on 10 % of the webbase corpus
- S : Trained on 25 % of the webbase corpus
- M : Trained on 50% of the webbase corpus

The preprocessing used can be separated into two different groups. For both group one paragraph of the webbase corpus was treated as a single document
- Unstemmed tokenized preprocessing : Uses simply the stanford core NLP tokenizer [2] to tokenize the given text
- Stemmed tokenized processing: Uses the same tokenization as above. But stemms the tokens using the porter stemmer implemented in apache lucene [3]

## Usage
The following libraries were used as depencies
- Spark Core
- Spark MLLib
- Apache Lucene
- Stanford Core NLP

Check the build.sbt for details
### Java

#### Stemmed
````
    Word2VecModel model_stemmed = ModelUtil.loadWord2VecModel("/Users/mueller/Coding/Word2Vectors/webbase10p/model_word2vec_stemmed.ser");
    
    String term1= "scholar";
    String term2 ="student";
 
    //To Stem terms the Porter Stemmer from Apache Lucene is used
    double similarity = Word2VecSim.cousineSimilarityBetweenTerms(model_stemmed,ModelUtil.porter_stem(term1),ModelUtil.porter_stem(term2));
````


### Unstemmed
````
    Word2VecModel model_unstemmed = ModelUtil.loadWord2VecModel("/Users/mueller/Coding/Word2Vectors/webbase10p/model_word2vec.ser");

    String term1= "scholar";
    String term2 ="student";
 
    double similarity = Word2VecSim.cousineSimilarityBetweenTerms(model_unstemmed,term1,term2);
    System.out.println("Similarity between unstemmed" + term1 + " " + term2 +" is "+ result_unstemmed);
````
### Scala 

TODO

##References

1.  http://ebiquity.umbc.edu/resource/html/id/351/UMBC-webbase-corpus
2.  http://nlp.stanford.edu/software/corenlp.shtml
3.  http://lucene.apache.org/core/3_0_3/api/contrib-snowball/









<script src="https://gist.github.com/dice89/2c313bd5cfff0a4fb599.js"></script>
