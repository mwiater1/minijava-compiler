package com.mateuszwiater.csc444.minijavacompiler.expression;

public class BooleanLiteralExpression extends Expression {
    private boolean aBoolean;

    public BooleanLiteralExpression(final boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public boolean getBoolean() {
        return aBoolean;
    }
}
