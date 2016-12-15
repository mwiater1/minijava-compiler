package com.mateuszwiater.csc444.minijavacompiler;

import java.util.ArrayList;
import java.util.List;

public class Klass extends Identifiable {
    private Klass superKlass;
    private final List<Method> methods;
    private final List<Klass> childKlasses;
    private final List<Variable> variables;

    public Klass() {
        methods = new ArrayList<>();
        variables = new ArrayList<>();
        childKlasses = new ArrayList<>();
    }

    public void addMethod(final Method method) {
        methods.add(method);
    }

    public void addChildKlass(final Klass klass) {
        childKlasses.add(klass);
    }

    public void addVariable(final Variable variable) {
        variables.add(variable);
    }

    public void setSuperKlass(Klass superKlass) {
        this.superKlass = superKlass;
    }

    public Klass getSuperKlass() {
        return superKlass;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public List<Klass> getChildKlasses() {
        return childKlasses;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    @Override
    public String toString() {
        return "Class " + getIdentifier();
    }
}
