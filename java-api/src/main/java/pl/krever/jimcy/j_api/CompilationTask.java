package pl.krever.jimcy.j_api;

import javax.tools.DiagnosticListener;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.Writer;

public interface CompilationTask<DiagnosticListenerType extends DiagnosticListener<? super JavaFileObject>>  {
    public CompilationTask<DiagnosticListenerType> withOutputWriter(Writer writer);
    public CompilationTask<DiagnosticListenerType> withFileManager(JavaFileManager fileManager);
    public <NewDiagnosticListenerType extends DiagnosticListener<? super JavaFileObject>>  CompilationTask<NewDiagnosticListenerType> withDiagnosticListener(NewDiagnosticListenerType diagnosticListener);
    public CompilationTask<DiagnosticListenerType> withOptions(Iterable<String> options);
    public CompilationTask<DiagnosticListenerType> withAnnotationProcessedClasses(Iterable<String> classes);

    public CompilationResult<DiagnosticListenerType> run();

}
