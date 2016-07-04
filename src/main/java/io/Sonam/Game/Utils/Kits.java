package io.Sonam.Game.Utils;

public enum Kits {

    DEFAULT("default"),
    CHAMPION("champion"),
    ARCHER("archer"),
    KNIGHT("knight"),
    CHEMIST("chemist"),
    PYRO("pyro"),
    ARMORER("armorer"),
    SCOUT("scout");

    private String label;

    Kits(String label) {
        this.label = label;
    }

    public Kits getType(String type) {
        for(Kits kits : values()) {
            if(type.equalsIgnoreCase(kits.name())) {
                return kits;
            }
        }
        return null;
    }

}
