package com.mateuszwiater.csc444.minijavacompiler.listeners;

import com.mateuszwiater.csc444.minijavacompiler.MiniJavaBaseListener;
import com.mateuszwiater.csc444.minijavacompiler.MiniJavaParser;
import com.mateuszwiater.csc444.minijavacompiler.Parameter;
import com.mateuszwiater.csc444.minijavacompiler.Scope;

public class ParameterListener extends MiniJavaBaseListener {
    final Scope scope;

    public ParameterListener(final Scope scope) {
        this.scope = scope;
    }

    @Override
    public void exitParameter(MiniJavaParser.ParameterContext ctx) {
        // Add the parameter to the current method.
        scope.getMethodStack().peek().addParameter(new Parameter(scope.getVariableStack().pop()));
    }
}
