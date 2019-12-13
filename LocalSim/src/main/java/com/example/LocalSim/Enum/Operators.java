package com.example.LocalSim.Enum;

public enum Operators {
    Airtel("Airtel"),
    Vodafone("Vodafone"),
    Jio("Jio"),
    BSNL("Bsnl");
    String label;

    Operators(String label) {
        this.label = label;
    }
}
