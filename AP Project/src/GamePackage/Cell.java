package GamePackage;

import java.util.ArrayList;

public class Cell {
    private int x;
    private int y;
    private ArrayList<Effect> effects = new ArrayList<>();
    private Force force;
    private Flag flag;
    private Collectible item=null;

    Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setItem(Collectible item) {
        this.item = item;
    }

    public Collectible getItem() {
        return item;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public void addEffect(Spell spell) {
        effects.add(new Effect(spell));
    }

    public void endTurn() {
        for(int i=0;i<effects.size();i++){
            if(effects.get(i).endTurn()) effects.remove(i--);
        }
    }

    public boolean isBusy() {
        return force!=null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setForce(Force force) {
        this.force = force;
    }

    public int getDistance(Cell cell) {
        int x1=getX(), y1=getY(), x2=cell.getX(), y2=cell.getY();
        return ((x2>x1)?(x2-x1):(x1-x2)) + ((y2>y1)?(y2-y1):(y1-y2));
    }

    public Force getForce() {
        return force;
    }

    void putFlag(Flag flag){
        this.flag = flag;
    }

    void loseFlag(){
        flag=null;
    }

    Flag getFlag(){
        return flag;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    @Override
    public boolean equals(Object o) {
        return o!=null && o instanceof Cell && ((Cell) o).x == x && ((Cell) o).y == y;
    }
}

