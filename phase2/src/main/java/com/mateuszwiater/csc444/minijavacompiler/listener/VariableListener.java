package com.mateuszwiater.csc444.minijavacompiler.listener;

import com.mateuszwiater.csc444.minijavacompiler.Klass;
import com.mateuszwiater.csc444.minijavacompiler.Method;
import com.mateuszwiater.csc444.minijavacompiler.MiniJavaParser;
import com.mateuszwiater.csc444.minijavacompiler.Variable;

public class VariableListener extends MiniJavaBaseListener {

    VariableListener(final ListenerScope scope) {
        super(scope);
    }

    @Override
    public void exitFieldDeclaration(MiniJavaParser.FieldDeclarationContext ctx) {
        // Add a variable to the current klass
        final Klass klass = scope.getKlassStack().peek();
        klass.addVariable(scope.getVariableStack().pop());
    }

    @Override
    public void exitLocalDeclaration(MiniJavaParser.LocalDeclarationContext ctx) {
        final Method method = scope.getMethodStack().peek();
        method.addVariable(scope.getVariableStack().pop());
    }

    @Override
    public void exitTypeIdentifier(MiniJavaParser.TypeIdentifierContext ctx) {
        scope.getVariableStack().push(new Variable(scope.getTypeStack().pop(), ctx.getStop().getText()));
    }
}
