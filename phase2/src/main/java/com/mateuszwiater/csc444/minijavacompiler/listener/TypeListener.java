package com.mateuszwiater.csc444.minijavacompiler.listener;

import com.mateuszwiater.csc444.minijavacompiler.MiniJavaBaseListener;
import com.mateuszwiater.csc444.minijavacompiler.MiniJavaParser;
import com.mateuszwiater.csc444.minijavacompiler.Scope;
import com.mateuszwiater.csc444.minijavacompiler.Type;

public class TypeListener extends MiniJavaBaseListener {
    final Scope scope;

    public TypeListener(final Scope scope) {
        this.scope = scope;
    }

    @Override
    public void exitType(MiniJavaParser.TypeContext ctx) {
        scope.getTypeStack().push(new Type(ctx.getText()));
    }
}
