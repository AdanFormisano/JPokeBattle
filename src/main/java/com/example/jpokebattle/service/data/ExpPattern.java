package com.example.jpokebattle.service.data;

public enum ExpPattern {
    FAST {
        @Override
        public int getRequiredExp(int level) {
            return (int) (4 * Math.pow(level, 3) / 5);
        }
    },
    MEDIUM_FAST {
        @Override
        public int getRequiredExp(int level) {
            return (int) Math.pow(level, 3);
        }
    },
    MEDIUM_SLOW {
        @Override
        public int getRequiredExp(int level) {
            return (int) (6 * Math.pow(level, 3) / 5 - 15 * Math.pow(level, 2) + 100 * level - 140);
        }
    },
    SLOW {
        @Override
        public int getRequiredExp(int level) {
            return (int) (5 * Math.pow(level, 3) / 4);
        }
    },
    ERRATIC {
        @Override
        public int getRequiredExp(int level) {
            if (level < 50) {
                return (int) (Math.pow(level, 3) * (100 - level) / 50);
            } else if (level < 68) {
                return (int) (Math.pow(level, 3) * (150 - level) / 100);
            } else if (level < 98) {
                return (int) (Math.pow(level, 3) * Math.floor((double) (1911 - 10 * level) / 3) / 500);
            } else {
                return (int) (Math.pow(level, 3) * (160 - level) / 100);
            }
        }
    },
    FLUCTUATING {
        @Override
        public int getRequiredExp(int level) {
            if (level < 15) {
                return (int) (Math.pow(level, 3) * ((Math.floor((double) (level + 1) / 3) + 24)) / 50);
            } else if (level < 36) {
                return (int) (Math.pow(level, 3) * (level + 14) / 50);
            } else {
                return (int) (Math.pow(level, 3) * ((Math.floor((double) level / 2) + 32)) / 50);
            }
        }
    };

    public abstract int getRequiredExp(int level);
}
