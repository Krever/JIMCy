package pl.krever.jimcy

import java.io.Writer
import java.net.URI
import javax.tools.JavaFileObject.Kind
import javax.tools._

import pl.krever.jimcy.JIMCompiler.CompilationResult

import scala.collection.JavaConverters._

object JIMCompiler {

  def newCompiler(compiler: JavaCompiler) = new JIMCompiler(compiler)

  def newCompiler() = {
    val compiler = ToolProvider.getSystemJavaCompiler
    if (compiler == null) throw new RuntimeException("Java Compiler could not be found. Please use JDK instead of JRE.")
    new JIMCompiler(compiler)
  }

  case class CompilationResult[DiagnosticListenerType <: DiagnosticListener[_ >: JavaFileObject]]
  (status: Boolean, diagnostics: DiagnosticListenerType, classLoader: InMemoryClassLoader)
}


class JIMCompiler private(val javaCompiler: JavaCompiler) {

  def compile[DiagnosticListenerType <: DiagnosticListener[_ >: JavaFileObject]]
  (sources: List[String],
   outputWriter: Writer = null,
   fileManager: JavaFileManager = javaCompiler.getStandardFileManager(null, null, null),
   diagnosticListener: DiagnosticListenerType = new DiagnosticCollector[JavaFileObject](),
   options: Iterable[String] = Iterable(),
   annotationProcessedClasses: Iterable[String] = Iterable()): CompilationResult[DiagnosticListenerType] = {

    val compilationUnits = sources.map(s => JavaStringSource(JavaClassNameFinder.findName(s), s))
    val inMemoryFileManager = new InMemoryFileManager(fileManager)

    val compilationTask = javaCompiler.getTask(outputWriter, inMemoryFileManager, diagnosticListener,
      options.asJava, annotationProcessedClasses.asJava, compilationUnits.asJava)

    val success = compilationTask.call()

    CompilationResult[DiagnosticListenerType](success, diagnosticListener, inMemoryFileManager.classLoader)
  }

  private case class JavaStringSource(name: String, code: String)
    extends SimpleJavaFileObject(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE) {

    override def getCharContent(ignoreEncodingErrors: Boolean): CharSequence = code
  }

}
