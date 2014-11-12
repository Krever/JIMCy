package pl.krever.jimcy.j_api;

import java.util.List;

public interface JIMCompiler {

    public abstract CompilationTask compilation(List<CompilationUnit> compilationUnits);

}
