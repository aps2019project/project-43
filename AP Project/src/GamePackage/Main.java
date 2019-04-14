package GamePackage;

public class Main {
    public static void main(String[] args) {
        Card m = new Minion(Minion.TroopSample.PERSIAN_ARCHER);
        ((Minion) m).init();
    }
}
