package com.mateuszwiater.csc444.minijavacompiler.listener;

public class MiniJavaBaseListener extends com.mateuszwiater.csc444.minijavacompiler.MiniJavaBaseListener {
    protected final ListenerScope scope;

    MiniJavaBaseListener(final ListenerScope listenerScope) {
        scope = listenerScope;
    }
}
