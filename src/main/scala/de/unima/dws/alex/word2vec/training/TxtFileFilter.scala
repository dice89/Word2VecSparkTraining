package de.unima.dws.alex.word2vec.training

import java.io.{File, FileFilter}

/**
 * Created by mueller on 02/02/15.
 */
class TxtFileFilter extends  FileFilter{
  def accept(file:File):Boolean = {
    return file.getName().endsWith(".txt")
  }
}
