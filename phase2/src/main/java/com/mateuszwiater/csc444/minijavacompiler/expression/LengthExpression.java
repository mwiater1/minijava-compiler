package com.mateuszwiater.csc444.minijavacompiler.expression;

public class LengthExpression extends Expression {
    private Expression expression;

    public LengthExpression(final Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
