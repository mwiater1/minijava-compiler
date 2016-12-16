package com.mateuszwiater.csc444.minijavacompiler.expression;

public abstract class BiExpression extends Expression {
    private Expression expression1, expression2;

    BiExpression(final Expression expression1, final Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    public Expression getExpression1() {
        return expression1;
    }

    public Expression getExpression2() {
        return expression2;
    }
}
