package com.mateuszwiater.csc444.minijavacompiler.statement;

import com.mateuszwiater.csc444.minijavacompiler.expression.Expression;

public class VariableAssignmentStatement extends Statement {
    private final String identifier;
    private final Expression expression;

    public VariableAssignmentStatement(final String identifier, final Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "Variable Assignment Statement";
    }
}
