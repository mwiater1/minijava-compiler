package com.mateuszwiater.csc444.minijavacompiler;

public abstract class Identifiable {
    private String identifier;

    Identifiable() {
        identifier = null;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
