package com.mateuszwiater.csc444.minijavacompiler.statement;

import java.util.List;

public class NestedStatement extends Statement {
    private List<Statement> statements;

    public NestedStatement(final List<Statement> statements) {
        this.statements = statements;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public String toString() {
        return "Nested Statement";
    }
}
