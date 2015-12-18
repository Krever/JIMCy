package com.github.krever.jimcy.j_api;

import javax.tools.DiagnosticListener;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.Writer;

public interface CompilationTask<DiagnosticListenerType extends DiagnosticListener<? super JavaFileObject>>  {
    CompilationTask<DiagnosticListenerType> withOutputWriter(Writer writer);

    CompilationTask<DiagnosticListenerType> withFileManager(JavaFileManager fileManager);

    <NewDiagnosticListenerType extends DiagnosticListener<? super JavaFileObject>> CompilationTask<NewDiagnosticListenerType> withDiagnosticListener(NewDiagnosticListenerType diagnosticListener);

    CompilationTask<DiagnosticListenerType> withOptions(Iterable<String> options);

    CompilationTask<DiagnosticListenerType> withAnnotationProcessedClasses(Iterable<String> classes);

    CompilationResult<DiagnosticListenerType> run();

}
