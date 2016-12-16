package com.mateuszwiater.csc444.minijavacompiler.expression;

public class IdentifierExpression extends Expression {
    private final String identifier;

    public IdentifierExpression(final String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
