package GamePackage;

public class GameObject implements Generateble {

    private String name;
    private int price;
    private String file;
    private int id;
    private String info;
    private Account owner;
    private Cell location;

    public Cell getLocation() {
        return location;
    }
    public void setLocation(Cell location) {
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public int getPrice(){
        return price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFilePath() {
        return file;
    }

    @Override
    public void setFilePath(String filePath) {
        file = filePath;
    }

    public String getInfo() {
        return info;
    }

    public void setOwner(Account owner){
        this.owner = owner;
    }

    public Account getOwner() {
        return owner;
    }
}
