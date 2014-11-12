package pl.krever.jimcy.j_api;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.util.List;

public interface JIMCompiler {

    public abstract CompilationTask<DiagnosticCollector<JavaFileObject>> compilation(List<CompilationUnit> compilationUnits);

}
