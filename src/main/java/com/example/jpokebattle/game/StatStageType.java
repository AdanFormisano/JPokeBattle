package com.example.jpokebattle.game;

public enum StatStageType {
    ATTACK, DEFENSE, SPECIAL_ATTACK, SPECIAL_DEFENSE, SPEED, ACCURACY, EVASION;

    public double getMultiplier(int stage) {
        switch (this) {
            case ACCURACY:
                double[] accMultiplier = {0.33, 0.37, 0.40, 0.50, 0.66, 0.75, 1.0, 1.33, 1.50, 1.66, 1.75, 1.87, 2.0};
                return accMultiplier[stage + 6];
            case EVASION:
                double[] evaMultiplier = {3.0, 2.66, 2.5, 2.0, 1.66, 1.33, 1.0, 0.75, 0.6, 0.5, 0.43, 0.36, 0.33};
                return evaMultiplier[stage + 6];
            default:
                if (stage >= 0) {
                    return (2.0 + stage) / 2.0;
                } else {
                    return 2.0 / (2 - stage);
                }
        }
    }
}
