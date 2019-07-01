package GamePackage;

public class Item extends Spell{

    @Override
    public String toString() {
        return getId()+": "+"Name : " + getName() + " - Desc : " + getInfo();
    }

}