package pl.krever.jimcy

import javax.tools.JavaCompiler
import javax.tools.JavaCompiler.CompilationTask

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class JIMCompilerTest extends Specification with Mockito {

  val simpleClassName = "HelloWorld"
  val simpleClassSource = """
                            |public class HelloWorld {
                            |  public static void main(String args[]) {
                            |    System.out.println("This is in another java file");
                            |  }
                            |}
                          """.stripMargin

  val emptyClassName = "Empty"
  val emptyClassSource = """
                            |public class Empty {
                            |
                            |}
                          """.stripMargin


  "JIMCompiler" should {

    "compile and load simple class without error" in {

      val jimCompiler = JIMCompiler.newCompiler()

      val result = jimCompiler.compile(List((simpleClassName, simpleClassSource)))

      result.status must beTrue
      result.classLoader.loadClass(simpleClassName) must not beNull
    }

    "compile and load multiple classes" in {
      val jimCompiler = JIMCompiler.newCompiler()

      val result = jimCompiler.compile(List((simpleClassName, simpleClassSource), (emptyClassName, emptyClassSource)))

      result.status must beTrue
      result.classLoader.loadClass(simpleClassName) must not beNull;
      result.classLoader.loadClass(emptyClassName) must not beNull;
      result.classLoader.allClasses must have size 2
    }

    "use provided java compiler" in {
      //create mocks
      val javaCompiler = smartMock[JavaCompiler]
      val compilationTask = mock[CompilationTask]

      //stub methods
      javaCompiler.getTask(any, any, any, any, any, any) returns compilationTask
      compilationTask.call() returns true

      //test
      val jimCompiler = JIMCompiler.newCompiler(javaCompiler)
      jimCompiler.compile(List())

      //verify
      there was one(javaCompiler).getTask(any, any, any, any, any, any)
      there was one(compilationTask).call()
    }


  }

}
