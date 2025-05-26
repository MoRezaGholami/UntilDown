package com.Mohammadreza.UD20.Models;

public enum Ability {
    Vitality("Vitality"),
    Damager("Damager"),
    Procrease("Procrease"),
    Amocrease("Amocrease"),
    Speedy("Speedy");


    private final String name;
    Ability(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
