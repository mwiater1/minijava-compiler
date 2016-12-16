package com.mateuszwiater.csc444.minijavacompiler;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Type extends Identifiable {

    public Type(final String type) {
        super(type);
    }

    @Override
    public String toString() {
        return getIdentifier();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(21, 49)
                .append(getIdentifier())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else if(!(obj instanceof Type)) {
            return false;
        }

        Type t = (Type) obj;

        return new EqualsBuilder()
                .append(getIdentifier(), t.getIdentifier())
                .isEquals();
    }
}
