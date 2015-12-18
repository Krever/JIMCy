package com.github.krever.jimcy.j_api;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.util.List;

public interface JIMCompiler {

    CompilationTask<DiagnosticCollector<JavaFileObject>> compilation(List<String> sources);

}
