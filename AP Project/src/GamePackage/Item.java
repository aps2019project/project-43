package GamePackage;

public class Item {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void printStats() {
        System.out.printf("Name : %s - Desc : %s\n", name, description);
    }
}
