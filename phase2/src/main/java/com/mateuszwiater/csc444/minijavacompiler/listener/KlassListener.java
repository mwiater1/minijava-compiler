package com.mateuszwiater.csc444.minijavacompiler.listener;

import com.mateuszwiater.csc444.minijavacompiler.Klass;
import com.mateuszwiater.csc444.minijavacompiler.MiniJavaParser;

public class KlassListener extends MiniJavaBaseListener {

    KlassListener(final ListenerScope scope) {
        super(scope);
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
