package pl.krever.jimcy.j_api;

import lombok.Getter;

import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.Writer;
import java.util.List;

public abstract class JIMCompiler {

    public static JIMCompiler newCompiler(JavaCompiler javaCompiler) {
        return new JIMCompilerImpl(javaCompiler);
    }

    public static JIMCompiler newCompiler() {
        return new JIMCompilerImpl();
    }


    public abstract CompilationTask compilation(List<CompilationUnit> compilationUnits);

    public interface CompilationTask<DiagnosticListenerType extends DiagnosticListener<? super JavaFileObject>>  {
        public CompilationTask<DiagnosticListenerType> withOutputWriter(Writer writer);
        public CompilationTask<DiagnosticListenerType> withFileManager(JavaFileManager fileManager);
        public <NewDiagnosticListenerType extends DiagnosticListener<? super JavaFileObject>>  CompilationTask<NewDiagnosticListenerType> withDiagnosticListener(NewDiagnosticListenerType diagnosticListener);
        public CompilationTask<DiagnosticListenerType> withOptions(Iterable<String> options);
        public CompilationTask<DiagnosticListenerType> withAnnotationProcessedClasses(Iterable<String> classes);

        public CompilationResult<DiagnosticListenerType> run();

    }

    //TODO Change to @Value after moving to submodule. Currently Scala compiles first and dont see lombok.
    public static class CompilationResult<DiagnosticListenerType> {
        private Boolean status;
        private DiagnosticListenerType diagnosticListener;
        private InMemoryClassLoader classLoader;

        public CompilationResult(Boolean status, DiagnosticListenerType diagnosticListener, InMemoryClassLoader classLoader){
            this.status = status;
            this.diagnosticListener = diagnosticListener;
            this.classLoader = classLoader;
        }

        public Boolean getStatus() {
            return status;
        }

        public DiagnosticListenerType getDiagnosticListener() {
            return diagnosticListener;
        }

        public InMemoryClassLoader getClassLoader() {
            return classLoader;
        }
    }

    //TODO Same problem with lombok as above
    public static class CompilationUnit {
        @Getter String className;
        @Getter String sourceCode;

        public CompilationUnit(String className, String sourceCode) {
            this.className = className;
            this.sourceCode = sourceCode;
        }
    }

}
