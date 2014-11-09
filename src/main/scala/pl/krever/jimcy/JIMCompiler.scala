package pl.krever.jimcy

import java.io.Writer
import java.net.URI
import javax.tools.JavaFileObject.Kind
import javax.tools._

import scala.collection.convert.Wrappers.IterableWrapper


object JIMCompiler {
  def newCompiler(compiler: JavaCompiler = ToolProvider.getSystemJavaCompiler) = new JIMCompiler(compiler)
}


class JIMCompiler private(compiler: JavaCompiler) {

  private implicit def toJavaIterable[T](sIterable: Iterable[T]): java.lang.Iterable[T] =
    if (sIterable == null) null else IterableWrapper(sIterable)


  def compile[DiagnosticListenerType <: DiagnosticListener[_ >: JavaFileObject]]
  (compilationEntries: List[(String, String)],
   outputWriter: Writer = null,
   fileManager: JavaFileManager = compiler.getStandardFileManager(null, null, null),
   diagnosticListener: DiagnosticListenerType = new DiagnosticCollector[JavaFileObject](),
   options: Iterable[String] = null,
   annotationProcessedClasses: Iterable[String] = null): CompilationResult[DiagnosticListenerType] = {

    val compilationUnits = compilationEntries.map(JavaSourceFromString.tupled)
    val inMemoryFileManager = new InMemoryFileManager(fileManager)

    val compilationTask = compiler.getTask(outputWriter, inMemoryFileManager, diagnosticListener,
      options, annotationProcessedClasses, compilationUnits)

    val success = compilationTask.call()

    CompilationResult[DiagnosticListenerType](success, diagnosticListener, inMemoryFileManager.classLoader)
  }

  case class JavaSourceFromString(name: String, code: String)
    extends SimpleJavaFileObject(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE) {

    override def getCharContent(ignoreEncodingErrors: Boolean): CharSequence = code
  }

  case class CompilationResult[DiagnosticListenerType <: DiagnosticListener[_ >: JavaFileObject]]
  (status: Boolean, diagnostics: DiagnosticListenerType, classLoader: InMemoryClassLoader)

}
