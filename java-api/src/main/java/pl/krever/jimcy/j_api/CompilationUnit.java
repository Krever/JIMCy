package pl.krever.jimcy.j_api;

import lombok.Value;

@Value
public class CompilationUnit {
    String className;
    String sourceCode;
}
