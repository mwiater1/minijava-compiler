package com.mateuszwiater.csc444.minijavacompiler.expression;

public class IntegerLiteralExpression  extends Expression {
    private final int integer;

    public IntegerLiteralExpression(final int integer) {
        this.integer = integer;
    }

    public int getInteger() {
        return integer;
    }
}
