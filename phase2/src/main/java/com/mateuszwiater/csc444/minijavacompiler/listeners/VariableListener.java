package com.mateuszwiater.csc444.minijavacompiler.listeners;

import com.mateuszwiater.csc444.minijavacompiler.Klass;
import com.mateuszwiater.csc444.minijavacompiler.Method;
import com.mateuszwiater.csc444.minijavacompiler.MiniJavaBaseListener;
import com.mateuszwiater.csc444.minijavacompiler.MiniJavaParser;
import com.mateuszwiater.csc444.minijavacompiler.Scope;
import com.mateuszwiater.csc444.minijavacompiler.Type;
import com.mateuszwiater.csc444.minijavacompiler.Variable;

public class VariableListener extends MiniJavaBaseListener {
    private Scope scope;

    public VariableListener(final Scope scope) {
        this.scope = scope;
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
    public void exitVar(MiniJavaParser.VarContext ctx) {
        // Create a variable
        scope.getVariableStack().push(new Variable(scope.getTypeStack().pop(), ctx.getStop().getText()));
    }
}
