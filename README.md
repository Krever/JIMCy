# JIMCy
## Info
JIMCy(Java In Memory Compiler) is a lightweight library wrapped around javax.tools.JavaCompiler.
It allows you to compile String-based sources without use of filesystem.
Currently only Scala api is availible.

## Example
```scala
val simpleClassName = "HelloWorld"
val simpleClassSource = """
                        |public class HelloWorld {
                        |  public static void main(String args[]) {
                        |    System.out.println("This is in another java file");
                        |  }
                        |}
                        """.stripMargin

val jimCompiler = JIMCompiler.newCompiler()

val result = jimCompiler.compile(List((simpleClassName, simpleClassSource)))
val clazz = result.classLoader.loadClass(simpleClassName)
```
## TODO
* Java API

## Thanks
http://www.javablogging.com/dynamic-in-memory-compilation/

