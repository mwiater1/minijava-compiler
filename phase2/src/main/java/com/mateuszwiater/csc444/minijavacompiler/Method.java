package com.mateuszwiater.csc444.minijavacompiler;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class Method extends Identifiable {
    private Type returnType;
    private final List<Variable> variables;
    private final List<Parameter> parameters;
    private final List<Statement> statements;

    public Method() {
        this.variables = new ArrayList<>();
        this.parameters = new ArrayList<>();
        this.statements = new ArrayList<>();
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public void addVariable(final Variable variable) {
        variables.add(variable);
    }

    public void addParameter(final Parameter parameter) {
        parameters.add(parameter);
    }

    public void addStatement(final Statement statement) {
        statements.add(statement);
    }

    public Type getReturnType() {
        return returnType;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public String toString() {
        return "Method " + getIdentifier() + " Returns " + getReturnType();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(3, 27)
                .append(parameters)
                .append(getIdentifier())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else if(!(obj instanceof Method)) {
            return false;
        }

        Method m = (Method) obj;

        return new EqualsBuilder()
                .append(parameters, m.getParameters())
                .append(getIdentifier(), m.getIdentifier())
                .isEquals();
    }
}
