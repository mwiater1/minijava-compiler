package com.mateuszwiater.csc444.minijavacompiler;

import java.util.List;

public class Klass extends Identifiable {
    private Klass superKlass;
    private final List<Method> methods;
    private final List<Variable> variables;

    public Klass(final String identifier) {
        super(identifier);
        methods = null;
        variables = null;
    }

    public Klass(final String identifier, final Klass superKlass, final List<Variable> variables, final List<Method> methods) {
        super(identifier);
        this.methods = methods;
        this.variables = variables;
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

    public List<Variable> getVariables() {
        return variables;
    }

    @Override
    public String toString() {
        return "Class " + getIdentifier();
    }
}
