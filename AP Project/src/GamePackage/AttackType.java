package GamePackage;

public enum AttackType {
    HYBRID {
        @Override
        public boolean isInRangeForDefend(Attacker attacker, Attacker defender){
            return true;
        }

        @Override
        public boolean isInRangeForAttack(Attacker attacker, Attacker defender){
            return true;
        }
    },
    MELEE {
        @Override
        public boolean isInRangeForDefend(Attacker attacker, Attacker defender){
            return isAdjacent(attacker.getCardLocation(), defender.getCardLocation());
        }
        @Override
        public boolean isInRangeForAttack(Attacker attacker, Attacker defender){
            return isAdjacent(attacker.getCardLocation(), defender.getCardLocation());
        }

    },
    RANGED {
        @Override
        public boolean isInRangeForDefend(Attacker attacker, Attacker defender){
            return !isAdjacent(attacker.getCardLocation(), defender.getCardLocation());
        }
        @Override
        public boolean isInRangeForAttack(Attacker attacker, Attacker defender){
            return !isAdjacent(attacker.getCardLocation(), defender.getCardLocation()) && getDistance(attacker.getCardLocation(), defender.getCardLocation()) <= attacker.attackRange;
        }
    };

    public abstract boolean isInRangeForDefend(Attacker attacker, Attacker defender);
    public abstract boolean isInRangeForAttack(Attacker attacker, Attacker defender);

    boolean isAdjacent(Cell cell, Cell other){
        int x=cell.getX(), x2=other.getX(), y=cell.getY(), y2=other.getY();
        if(x2 == x-1 || x2 == x+1 || x2 == x){
            if(y2 == y-1 || y2 == y+1 || y2 == y){
                return x!=y;
            }
        }
        return false;
    }

    int getDistance(Cell cell1, Cell cell2) {
        int x1=cell1.getX(), x2=cell2.getX(), y1=cell1.getY(), y2=cell2.getY();
        return ((x2>x1)?(x2-x1):(x1-x2)) + ((y2>y1)?(y2-y1):(y1-y2));
    }

}
