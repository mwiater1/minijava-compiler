package com.mateuszwiater.csc444.minijavacompiler;

public abstract class Identifiable {
    private final String identifier;

    Identifiable(final String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
