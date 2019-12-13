package com.example.LocalSim.Enum;

public enum CompanyNames {
    Airtel("Airtle"),
    Vodafone("Vodafone"),
    Jio("Jio"),
    BSNL("Bsnl");
    String label;

    CompanyNames(String label) {
        this.label = label;
    }
}
