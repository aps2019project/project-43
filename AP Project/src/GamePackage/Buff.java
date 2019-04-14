package GamePackage;

public enum Buff {
    ON_ATTACK {
        @Override
        public void affectBuff() {
        }
    },
    HOLY {
        @Override
        public void affectBuff() {
        }
    },
    POWER {
        @Override
        public void affectBuff() {
        }
    },
    WEAKNESS {
        @Override
        public void affectBuff() {
        }
    },
    POISON {
        @Override
        public void affectBuff() {
        }
    },
    STUN {
        @Override
        public void affectBuff() {
        }
    },
    DISARM {
        @Override
        public void affectBuff() {
        }
    };

    public abstract void affectBuff();
}
