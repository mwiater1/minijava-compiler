package com.mateuszwiater.csc444.minijavacompiler.listener;

import com.mateuszwiater.csc444.minijavacompiler.MiniJavaParser;
import com.mateuszwiater.csc444.minijavacompiler.Parameter;

public class ParameterListener extends MiniJavaBaseListener {

    ParameterListener(final ListenerScope scope) {
        super(scope);
    }

    @Override
    public void exitParameter(MiniJavaParser.ParameterContext ctx) {
        // Add the parameter to the current method.
        scope.getMethodStack().peek().addParameter(new Parameter(scope.getVariableStack().pop()));
    }
}
