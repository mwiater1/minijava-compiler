package com.mateuszwiater.csc444.minijavacompiler;

import com.mateuszwiater.csc444.minijavacompiler.expression.Expression;
import com.mateuszwiater.csc444.minijavacompiler.statement.Statement;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class Method extends Identifiable {
    private Type returnType;
    private Expression returnExpression;
    private final List<Variable> variables;
    private final List<Parameter> parameters;
    private final List<Statement> statements;

    public Method(final String identifier, final Type returnType, final List<Parameter> parameters, final List<Variable> variables, final List<Statement> statements, final Expression returnExpression) {
        super(identifier);
        this.returnType = returnType;
        this.variables = variables;
        this.parameters = parameters;
        this.statements = statements;
        this.returnExpression = returnExpression;
    }

    public Type getReturnType() {
        return returnType;
    }

    public Expression getReturnExpression() {
        return returnExpression;
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
