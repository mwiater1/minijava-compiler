package com.mateuszwiater.csc444.minijavacompiler.expression;

public class NotExpression extends Expression {
    private Expression expression;

    public NotExpression(final Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
