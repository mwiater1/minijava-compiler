package com.mateuszwiater.csc444.minijavacompiler.listener;

import com.mateuszwiater.csc444.minijavacompiler.Klass;

import java.util.List;

public class ListenerFactory {
    private final ListenerScope listenerScope;
    private final TypeListener typeListener;
    private final KlassListener klassListener;
    private final MethodListener methodListener;
    private final VariableListener variableListener;
    private final ParameterListener parameterListener;
    private final StatementListener statementListener;


    public ListenerFactory() {
        listenerScope = new ListenerScope();
        typeListener = new TypeListener(listenerScope);
        klassListener = new KlassListener(listenerScope);
        methodListener = new MethodListener(listenerScope);
        variableListener = new VariableListener(listenerScope);
        parameterListener = new ParameterListener(listenerScope);
        statementListener = new StatementListener(listenerScope);
    }

    public List<Klass> getKlasses() {
        return listenerScope.getKlasses();
    }

    public ListenerScope getListenerScope() {
        return listenerScope;
    }

    public TypeListener getTypeListener() {
        return typeListener;
    }

    public KlassListener getKlassListener() {
        return klassListener;
    }

    public MethodListener getMethodListener() {
        return methodListener;
    }

    public VariableListener getVariableListener() {
        return variableListener;
    }

    public ParameterListener getParameterListener() {
        return parameterListener;
    }

    public StatementListener getStatementListener() {
        return statementListener;
    }
}
