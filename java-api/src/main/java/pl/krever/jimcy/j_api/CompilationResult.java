package pl.krever.jimcy.j_api;

public class CompilationResult<DiagnosticListenerType> {

    private Boolean status;
    private DiagnosticListenerType diagnosticListener;
    private InMemoryClassLoader classLoader;

    public CompilationResult(Boolean status, DiagnosticListenerType diagnosticListener, InMemoryClassLoader classLoader) {
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
