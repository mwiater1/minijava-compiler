package com.mateuszwiater.csc444.minijavacompiler.listener;

import com.mateuszwiater.csc444.minijavacompiler.Klass;
import com.mateuszwiater.csc444.minijavacompiler.Method;
import com.mateuszwiater.csc444.minijavacompiler.MiniJavaBaseListener;
import com.mateuszwiater.csc444.minijavacompiler.MiniJavaParser;
import com.mateuszwiater.csc444.minijavacompiler.Scope;
import com.mateuszwiater.csc444.minijavacompiler.Variable;

public class MethodListener extends MiniJavaBaseListener {
    private final Scope scope;

    public MethodListener(final Scope scope) {
        this.scope = scope;
    }

    @Override
    public void enterMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
        scope.getMethodStack().push(new Method());
    }

    @Override
    public void exitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
        // Add the method to the current klass
        final Klass klass = scope.getKlassStack().peek();
        final Method method = scope.getMethodStack().pop();
        final Variable variable = scope.getVariableStack().pop();
        method.setIdentifier(variable.getIdentifier());
        method.setReturnType(variable.getType());
        klass.addMethod(method);
    }
}
