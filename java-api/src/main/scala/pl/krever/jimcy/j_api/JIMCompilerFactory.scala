package pl.krever.jimcy.j_api

import javax.tools.JavaCompiler

class JIMCompilerFactory {

   def newCompiler(javaCompiler :JavaCompiler) :JIMCompiler = new JIMCompilerImpl(javaCompiler)

  def newCompiler() :JIMCompiler = new JIMCompilerImpl()

}
