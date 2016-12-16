package com.mateuszwiater.csc444.minijavacompiler.expression;

public class InstantiationExpression extends Expression {
    private String identifier;

    public InstantiationExpression(final String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
