package com.mateuszwiater.csc444.minijavacompiler.statement;

import com.mateuszwiater.csc444.minijavacompiler.expression.Expression;

public class PrintStatement extends Statement {
    private final Expression expression;

    public PrintStatement(final Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "Print Statement";
    }
}
