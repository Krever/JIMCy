package pl.krever.jimcy.j_api

import java.io.Writer
import java.util
import javax.tools.{DiagnosticCollector, DiagnosticListener, JavaFileObject, JavaFileManager}

import org.mockito.Matchers
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import pl.krever.jimcy
import pl.krever.jimcy.j_api.JIMCompiler.CompilationUnit

import scala.collection.JavaConversions


class JavaApiTest extends Specification with Mockito {

  "Java JIMCompiler" should {
    "forward parameters to JIMCompiler" in {

      val scalaCompilerMock = smartMock[pl.krever.jimcy.JIMCompiler]
      scalaCompilerMock.compile[DiagnosticCollector[JavaFileObject]](any, any, any, any, any, any) returns pl.krever.jimcy.JIMCompiler.CompilationResult(true, new DiagnosticCollector[JavaFileObject], null)

      val jimCompiler = new JIMCompilerImpl() {
        override val scalaJIMCompiler = scalaCompilerMock
      }

      val compilationUnits = List(("test1", "test1source"))
      val compilationTask = jimCompiler.compilation(JavaConversions.seqAsJavaList(compilationUnits.map{ case (k,v) => new CompilationUnit(k,v) }) )

      val writer = smartMock[Writer]
      val fileManager = smartMock[JavaFileManager]
      val diagnosticListener :DiagnosticListener[JavaFileObject] = smartMock[DiagnosticListener[JavaFileObject]]
      val options = new util.ArrayList[String]()
      val classes = new util.ArrayList[String]()

      val filledCompilationTask = compilationTask
        .withOutputWriter(writer)
        .withFileManager(fileManager)
        .withDiagnosticListener(diagnosticListener)
        .withOptions(options)
        .withAnnotationProcessedClasses(classes)

      filledCompilationTask.run()
      there was one(scalaCompilerMock).compile(
        Matchers.eq(compilationUnits), Matchers.eq(writer), Matchers.eq(fileManager),
        Matchers.eq(diagnosticListener), Matchers.eq(JavaConversions.iterableAsScalaIterable(options)),
        Matchers.eq(JavaConversions.iterableAsScalaIterable(classes)))

    }

    "forward results from JIMCompiler" in {

      val resultDiagnosticListener = new DiagnosticCollector[JavaFileObject]
      val resultStatus = true
      val resultClassLoader = new jimcy.InMemoryClassLoader(Map())

      val scalaCompilerMock = smartMock[pl.krever.jimcy.JIMCompiler]
      scalaCompilerMock.compile[DiagnosticCollector[JavaFileObject]](any, any, any, any, any, any) returns pl.krever.jimcy.JIMCompiler.CompilationResult(resultStatus, resultDiagnosticListener, resultClassLoader)

      val jimCompiler = new JIMCompilerImpl() {
        override val scalaJIMCompiler = scalaCompilerMock
      }

      val compilationUnits = List(("test1", "test1source"))
      val compilationTask = jimCompiler.compilation(JavaConversions.seqAsJavaList(compilationUnits.map{ case (k,v) => new CompilationUnit(k,v) }) )
      val result = compilationTask.run

      result.getStatus must beEqualTo(resultStatus)
      result.getDiagnosticListener must beEqualTo(resultDiagnosticListener)
      result.getClassLoader must beEqualTo(new InMemoryClassLoader(resultClassLoader))
    }
  }

  "Java InMemoryClassLoader" should {
    "forward everything" in {
      val scalaLoader = smartMock[pl.krever.jimcy.InMemoryClassLoader]
      val javaLoader = new InMemoryClassLoader(scalaLoader)

      javaLoader.getAllClasses
      there was one(scalaLoader).allClasses

      javaLoader.clearAssertionStatus()
      there was one(scalaLoader).clearAssertionStatus()

      javaLoader.getResource("test1")
      there was one(scalaLoader).getResource("test1")

      javaLoader.getResourceAsStream("test2")
      there was one(scalaLoader).getResourceAsStream("test2")

      javaLoader.getResources("test3")
      there was one(scalaLoader).getResources("test3")

      javaLoader.loadClass("test4")
      there was one(scalaLoader).loadClass("test4")

      javaLoader.setClassAssertionStatus("test5", true)
      there was one(scalaLoader).setClassAssertionStatus("test5", true)

      javaLoader.setDefaultAssertionStatus(true)
      there was one(scalaLoader).setDefaultAssertionStatus(true)

      javaLoader.setPackageAssertionStatus("test6", true)
      there was one(scalaLoader).setPackageAssertionStatus("test6", true)


      true must beTrue
    }

  }

}
