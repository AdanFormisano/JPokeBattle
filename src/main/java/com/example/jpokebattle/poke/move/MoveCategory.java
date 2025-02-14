package com.example.jpokebattle.poke.move;

public enum MoveCategory {
    PHYSICAL,
    SPECIAL,
    STATUS;

    public static MoveCategory fromString(String category) {
        return switch (category) {
            case "Physical" -> PHYSICAL;
            case "Special" -> SPECIAL;
            case "Status" -> STATUS;
            default -> null;
        };
    }

    public static String toString(MoveCategory category) {
        return switch (category) {
            case PHYSICAL -> "Physical";
            case SPECIAL -> "Special";
            case STATUS -> "Status";
        };
    }
}
