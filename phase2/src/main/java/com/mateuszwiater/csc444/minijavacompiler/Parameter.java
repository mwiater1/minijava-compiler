package com.mateuszwiater.csc444.minijavacompiler;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Parameter {
    private final Variable variable;

    public Parameter(final Variable variable) {
        this.variable = variable;
    }

    public Variable getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return variable.toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 11)
                .append(variable.getType())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else if(!(obj instanceof Parameter)) {
            return false;
        }

        Parameter p = (Parameter) obj;

        return new EqualsBuilder()
                .append(variable.getType(), p.getVariable().getType())
                .isEquals();
    }
}
