package pl.krever.jimcy.j_api;

import lombok.Value;

@Value
public class CompilationResult<DiagnosticListenerType> {
    private Boolean status;
    private DiagnosticListenerType diagnosticListener;
    private InMemoryClassLoader classLoader;
}
