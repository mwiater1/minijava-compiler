package com.mateuszwiater.csc444.minijavacompiler.statement;

import com.mateuszwiater.csc444.minijavacompiler.expression.Expression;

public class SwitchStatement extends Statement {
    private Expression parameter, body;

    public Expression getParameter() {
        return parameter;
    }

    public void setParameter(Expression parameter) {
        this.parameter = parameter;
    }

    public Expression getBody() {
        return body;
    }

    public void setBody(Expression body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Switch Case Statement";
    }
}
