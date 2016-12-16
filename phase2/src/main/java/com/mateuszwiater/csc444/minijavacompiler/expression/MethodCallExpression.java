package com.mateuszwiater.csc444.minijavacompiler.expression;

import java.util.List;

public class MethodCallExpression extends Expression {
    private final String identifier;
    private final Expression expression;
    private final List<Expression> expressions;

    public MethodCallExpression(final String identifier, final Expression expression, final List<Expression> expressions) {
        this.identifier = identifier;
        this.expression = expression;
        this.expressions = expressions;
    }

    public Expression getExpression() {
        return expression;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
