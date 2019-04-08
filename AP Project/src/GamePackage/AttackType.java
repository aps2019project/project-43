package GamePackage;

public enum AttackType {
    HYBRID {
        @Override
        public void attack() {
        }
    },
    MELEE {
        @Override
        public void attack() {
        }
    },
    RANGED {
        @Override
        public void attack() {
        }
    };

    public abstract void attack();
}
