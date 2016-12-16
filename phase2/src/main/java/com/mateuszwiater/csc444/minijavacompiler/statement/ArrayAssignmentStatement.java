package com.mateuszwiater.csc444.minijavacompiler.statement;

import com.mateuszwiater.csc444.minijavacompiler.expression.Expression;

public class ArrayAssignmentStatement extends Statement {
    private String identifier;
    private Expression expression1, expression2;

    public ArrayAssignmentStatement(final String identifier, final Expression expression1, final Expression expression2) {
        this.identifier = identifier;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Expression getExpression1() {
        return expression1;
    }

    public Expression getExpression2() {
        return expression2;
    }

    @Override
    public String toString() {
        return "Array Assignment Statement";
    }
}
