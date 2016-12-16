package com.mateuszwiater.csc444.minijavacompiler.statement;

import com.mateuszwiater.csc444.minijavacompiler.expression.Expression;

public class IfElseStatement extends Statement {
    private Expression expression;
    private Statement ifBlock, elseBlock;

    public IfElseStatement(final Expression expression, final Statement ifBlock, final Statement elseBlock) {
        this.expression = expression;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    public Expression getExpression() {
        return expression;
    }

    public Statement getIfBlock() {
        return ifBlock;
    }

    public Statement getElseBlock() {
        return elseBlock;
    }

    @Override
    public String toString() {
        return "If Else Statement";
    }
}
