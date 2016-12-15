package com.mateuszwiater.csc444.minijavacompiler.listener;

import com.mateuszwiater.csc444.minijavacompiler.Klass;
import com.mateuszwiater.csc444.minijavacompiler.Method;
import com.mateuszwiater.csc444.minijavacompiler.Type;
import com.mateuszwiater.csc444.minijavacompiler.Variable;
import com.mateuszwiater.csc444.minijavacompiler.statement.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class ListenerScope {
    private final List<Klass> klasses;
    private final Stack<Type> typeStack;
    private final Stack<Klass> klassStack;
    private final Stack<Method> methodStack;
    private final Stack<Variable> variableStack;
    private final Stack<Statement> statementStack;

    ListenerScope() {
        klasses        = new ArrayList<>();
        typeStack      = new Stack<>();
        klassStack     = new Stack<>();
        methodStack    = new Stack<>();
        variableStack  = new Stack<>();
        statementStack = new Stack<>();
    }

    public List<Klass> getKlasses() {
        return klasses;
    }

    public Stack<Type> getTypeStack() {
        return typeStack;
    }

    public Stack<Klass> getKlassStack() {
        return klassStack;
    }

    public Stack<Method> getMethodStack() {
        return methodStack;
    }

    public Stack<Variable> getVariableStack() {
        return variableStack;
    }

    public Stack<Statement> getStatementStack() {
        return statementStack;
    }
}
