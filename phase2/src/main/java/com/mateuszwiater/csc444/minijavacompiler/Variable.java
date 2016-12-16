package com.mateuszwiater.csc444.minijavacompiler;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Variable extends Identifiable {
    private final Type type;

    public Variable(final Type type, final String identifier) {
        super(identifier);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Type: " + type + " Id: " + getIdentifier();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(57, 107)
                .append(type)
                .append(getIdentifier())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else if(!(obj instanceof Variable)) {
            return false;
        }

        Variable v = (Variable) obj;

        return new EqualsBuilder()
                .append(type, v.getType())
                .append(getIdentifier(), v.getIdentifier())
                .isEquals();
    }
}
