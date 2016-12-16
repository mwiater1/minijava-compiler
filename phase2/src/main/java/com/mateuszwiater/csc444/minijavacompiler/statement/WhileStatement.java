package com.mateuszwiater.csc444.minijavacompiler.statement;

import com.mateuszwiater.csc444.minijavacompiler.expression.Expression;

public class WhileStatement extends Statement {
    private Expression expression;
    private Statement statement;

    public WhileStatement(final Expression expression, final Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    public Expression getExpression() {
        return expression;
    }

    public Statement getStatement() {
        return statement;
    }

    @Override
    public String toString() {
        return "While Statement";
    }
}
