package com.mateuszwiater.csc444.minijavacompiler.listener;

import com.mateuszwiater.csc444.minijavacompiler.Klass;
import com.mateuszwiater.csc444.minijavacompiler.MiniJavaBaseListener;
import com.mateuszwiater.csc444.minijavacompiler.MiniJavaParser;
import com.mateuszwiater.csc444.minijavacompiler.Scope;

public class KlassListener extends MiniJavaBaseListener {
    private final Scope scope;

    public KlassListener(final Scope scope) {
        this.scope = scope;
    }

    @Override
    public void enterMainClass(MiniJavaParser.MainClassContext ctx) {
        scope.getKlassStack().push(new Klass());
    }

    @Override
    public void exitMainClass(MiniJavaParser.MainClassContext ctx) {
        final Klass klass = scope.getKlassStack().pop();
        klass.setIdentifier(ctx.getToken(MiniJavaParser.Identifier, 0).getText());
        scope.getKlasses().add(klass);
    }

    @Override
    public void enterClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {
        scope.getKlassStack().push(new Klass());
    }

    @Override
    public void exitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {
        final Klass klass = scope.getKlassStack().pop();
        klass.setIdentifier(ctx.getToken(MiniJavaParser.Identifier, 0).getText());
        scope.getKlasses().add(klass);
    }

    @Override
    public void exitExtendClass(MiniJavaParser.ExtendClassContext ctx) {
        final Klass klass = new Klass();
        klass.setIdentifier(ctx.getStop().getText());
        scope.getKlassStack().peek().setSuperKlass(klass);
    }
}
