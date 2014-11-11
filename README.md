# JIMCy
## Info
JIMCy(Java In Memory Compiler) is a lightweight library wrapped around javax.tools.JavaCompiler.
It allows you to compile String-based sources without use of filesystem.
Currently only Scala api is availible.

## Example
```scala
import pl.krever.jimcy.JIMCompiler

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
```java
import pl.krever.jimcy.j_api.JIMCompiler
import pl.krever.jimcy.j_api.JIMCompiler.*

JIMCompiler jimCompiler = JIMCompiler.newCompiler()

CompilationTask task = jimCompiler.compilation(Arrays.asList(new CompilationUnit(simpleClassName, simpleClassSource)))
CompilationResult result = task.run()
Class<?> clazz = result.getClassLoader().loadClass(simpleClassName)
```
## Install
1. clone repository
2. run `sbt publish-local`
3. add `"pl.krever.jimcy" % "jimcy" % "0.1.0-SNAPSHOT"` to your dependency list

## TODO
* Move Java API to submodule
* Class name recognition from source code
* Publish to public repository

## Thanks
http://www.javablogging.com/dynamic-in-memory-compilation/

