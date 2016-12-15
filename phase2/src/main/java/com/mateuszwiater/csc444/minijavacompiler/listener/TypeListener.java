package com.mateuszwiater.csc444.minijavacompiler.listener;

import com.mateuszwiater.csc444.minijavacompiler.MiniJavaParser;
import com.mateuszwiater.csc444.minijavacompiler.Type;

public class TypeListener extends MiniJavaBaseListener {

    TypeListener(final ListenerScope scope) {
        super(scope);
    }

    @Override
    public void exitType(MiniJavaParser.TypeContext ctx) {
        scope.getTypeStack().push(new Type(ctx.getText()));
    }
}
