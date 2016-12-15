package com.mateuszwiater.csc444.minijavacompiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Scope {
    private final List<Klass> klasses;
    private final Stack<Klass> klassStack;
    private final Stack<Method> methodStack;
    private final Stack<Parameter> parameterStack;
    private final Stack<Statement> statementStack;
    private final Stack<Variable> variableStack;
    private final Stack<Type> typeStack;

    Scope() {
        klassStack = new Stack<>();
        methodStack = new Stack<>();
        parameterStack = new Stack<>();
        statementStack = new Stack<>();
        variableStack = new Stack<>();
        klasses = new ArrayList<>();
        typeStack = new Stack<>();
    }

    public Stack<Klass> getKlassStack() {
        return klassStack;
    }

    public Stack<Method> getMethodStack() {
        return methodStack;
    }

    public Stack<Parameter> getParameterStack() {
        return parameterStack;
    }

    public Stack<Statement> getStatementStack() {
        return statementStack;
    }

    public Stack<Variable> getVariableStack() {
        return variableStack;
    }

    public List<Klass> getKlasses() {
        return klasses;
    }

    public Stack<Type> getTypeStack() {
        return typeStack;
    }
}
