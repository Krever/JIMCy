package com.github.krever.jimcy.j_api

import javax.tools.JavaCompiler

object JIMCompilerFactory {

   def newCompiler(javaCompiler :JavaCompiler) :JIMCompiler = new JIMCompilerImpl(javaCompiler)

  def newCompiler() :JIMCompiler = new JIMCompilerImpl()

}
