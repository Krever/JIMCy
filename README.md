# JIMCy
## Info
JIMCy(Java In Memory Compiler) is a lightweight library wrapped around javax.tools.JavaCompiler.
It allows you to compile String-based sources without use of filesystem.

[![Build Status](https://travis-ci.org/Krever/JIMCy.svg?branch=master)](https://travis-ci.org/Krever/JIMCy)
## Example
Following examples are minimal. API tries to be as customizable as the javax.tools.JavaCompiler.

### Scala
```scala
import com.github.krever.jimcy.JIMCompiler

val simpleClassSource = """
                        |public class HelloWorld {
                        |  public static void main(String args[]) {
                        |    System.out.println("This is in another java file");
                        |  }
                        |}
                        """.stripMargin

val jimCompiler = JIMCompiler.newCompiler()

val result = jimCompiler.compile(List(simpleClassSource))
val clazz = result.classLoader.loadClass(simpleClassName)
```
### Java
```java
import com.github.krever.jimcy.j_api.JIMCompiler

JIMCompiler compiler = JIMCompilerFactory.newCompiler();
CompilationTask<DiagnosticCollector<JavaFileObject>> compilationTask = compiler.compilation(Arrays.asList("sourceCode")));
CompilationResult<DiagnosticCollector<JavaFileObject>> compilationResult = compilationTask.run();

```
## Install
```
libraryDependencies += "com.github.krever" %% "jimcy-core" % "0.2.0"
libraryDependencies += "com.github.krever" %% "jimcy-java-api" % "0.2.0"
```


## Thanks
http://www.javablogging.com/dynamic-in-memory-compilation/

