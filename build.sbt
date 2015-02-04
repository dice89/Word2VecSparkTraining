name := "Word2VecSparkTraining"

version := "1.0"

scalaVersion := "2.10.4"

scalacOptions += "-target:jvm-1.7"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.2.0"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.2.0"

libraryDependencies += "org.scalanlp" % "epic_2.10" % "0.3"

libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.5.0"

libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.5.0" classifier "models"

libraryDependencies += "org.apache.lucene" % "lucene-analyzers-common" % "4.10.3"

libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.8"


