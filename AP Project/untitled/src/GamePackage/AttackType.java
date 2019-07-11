package GamePackage;

public enum AttackType {
    HYBRID {
        @Override
        public void attack() {

        }

        @Override
        public void defend() {

        }
    },
    MELEE {
        @Override
        public void attack() {
        }

        @Override
        public void defend() {

        }
    },
    RANGED {
        @Override
        public void attack() {
        }

        @Override
        public void defend() {

        }
    };

    public abstract void attack();
    public abstract void defend();

}
