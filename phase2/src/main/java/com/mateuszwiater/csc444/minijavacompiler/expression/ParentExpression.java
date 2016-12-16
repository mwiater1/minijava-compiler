package com.mateuszwiater.csc444.minijavacompiler.expression;

public class ParentExpression extends Expression {
    private Expression expression;

    public ParentExpression(final Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
