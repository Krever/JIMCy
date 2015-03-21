# JIMCy
## Info
JIMCy(Java In Memory Compiler) is a lightweight library wrapped around javax.tools.JavaCompiler.
It allows you to compile String-based sources without use of filesystem.

[![Build Status](https://travis-ci.org/Krever/JIMCy.svg?branch=master)](https://travis-ci.org/Krever/JIMCy)
## Example
Following example are minimal. API tries to be as customizable as the javax.tools.JavaCompiler.

### Scala
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
### Java
```java
import pl.krever.jimcy.j_api.JIMCompiler

JIMCompiler compiler = JIMCompilerFactory.newCompiler();
CompilationTask<DiagnosticCollector<JavaFileObject>> compilationTask = compiler.compilation(Arrays.asList(new CompilationUnit("className", "sourceCode")));
CompilationResult<DiagnosticCollector<JavaFileObject>> compilationResult = compilationTask.run();

```
## Install
add `dependsOn(RootProject(uri("https://github.com/Krever/JIMCy.git")))`


## Thanks
http://www.javablogging.com/dynamic-in-memory-compilation/

