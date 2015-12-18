package com.github.krever.jimcy

import javax.tools.JavaFileObject.Kind
import javax.tools.ToolProvider

import org.specs2.mutable.Specification


class InMemoryFileManagerTest extends Specification {

  "InMemoryFileManager" should {
    "use InMemoryClassLoader" in {

      val fileManager = new InMemoryFileManager(ToolProvider.getSystemJavaCompiler.getStandardFileManager(null, null, null))
      val classLoader = fileManager.getClassLoader(null)

      classLoader must beAnInstanceOf[InMemoryClassLoader]
    }

    "return InMemoryJavaClassObject as JavaFileForOutput" in {

      val fileManager = new InMemoryFileManager(ToolProvider.getSystemJavaCompiler.getStandardFileManager(null, null, null))
      val fileForOutput = fileManager.getJavaFileForOutput(null, "test", Kind.CLASS, null)

      fileForOutput must beAnInstanceOf[InMemoryJavaClassObject]
    }



  }

}
