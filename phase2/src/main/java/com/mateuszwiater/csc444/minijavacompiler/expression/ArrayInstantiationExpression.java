package com.mateuszwiater.csc444.minijavacompiler.expression;

public class ArrayInstantiationExpression extends Expression {
    private Expression expression;

    public ArrayInstantiationExpression(final Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
