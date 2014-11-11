package pl.krever.jimcy.j_api

import java.io.Writer
import java.{lang, util}
import javax.tools._

import pl.krever.jimcy.j_api.JIMCompiler.{CompilationResult, CompilationTask, CompilationUnit}

import scala.collection.JavaConversions._

class JIMCompilerImpl(private val javaCompiler: JavaCompiler ) extends JIMCompiler {

  protected val scalaJIMCompiler = pl.krever.jimcy.JIMCompiler.newCompiler(javaCompiler)

  def this() { this(ToolProvider.getSystemJavaCompiler) }

  override def compilation(compilationUnits: util.List[CompilationUnit]): CompilationTask[_] = {
    CompilationTaskImpl(compilationUnits)
  }


  case class CompilationTaskImpl[DiagnosticListenerType <: DiagnosticListener[_ >: JavaFileObject]]
  (
    private val compilationUnits: util.List[CompilationUnit],
    private val outputWriter: Writer = null,
    private val fileManager: JavaFileManager = javaCompiler.getStandardFileManager(null, null, null),
    private val diagnosticListener: DiagnosticListenerType = new DiagnosticCollector[JavaFileObject](),
    private val options: lang.Iterable[String] = null,
    private val annotationProcessedClasses: lang.Iterable[String] = null
    )
    extends CompilationTask[DiagnosticListenerType] {

    override def run(): CompilationResult[DiagnosticListenerType] = {
      //(scalaJIMCompiler.compile _).tupled(CompilationTaskImpl.unapply(this).get)
      val scalaResult = scalaJIMCompiler.compile(
      compilationEntries = compilationUnits.toList.map( u => (u.className, u.sourceCode)),
      outputWriter = outputWriter,
      fileManager = fileManager,
      diagnosticListener = diagnosticListener,
      options = options.toIterable,
      annotationProcessedClasses = annotationProcessedClasses.toIterable
      )
      val javaInMemoryClassLoader = new InMemoryClassLoader(scalaResult.classLoader)

      new CompilationResult[DiagnosticListenerType](scalaResult.status, scalaResult.diagnostics, javaInMemoryClassLoader)
    }


    override def withOutputWriter(writer: Writer) = this.copy(outputWriter = writer)

    override def withDiagnosticListener[NewDiagnosticListenerType <: DiagnosticListener[_ >: JavaFileObject]](newDiagnosticListener: NewDiagnosticListenerType): CompilationTask[NewDiagnosticListenerType] = {
      this.copy[NewDiagnosticListenerType](diagnosticListener = newDiagnosticListener)
    }

    override def withFileManager(fileManager: JavaFileManager) = this.copy(fileManager = fileManager)

    override def withOptions(options: lang.Iterable[String])= this.copy(options = options)

    override def withAnnotationProcessedClasses(classes: lang.Iterable[String]): CompilationTask[DiagnosticListenerType] = {
      this.copy(annotationProcessedClasses = classes)
    }
  }

}
