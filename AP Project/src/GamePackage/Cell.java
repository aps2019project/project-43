package GamePackage;

import java.util.ArrayList;

public class Cell {
    private int x;
    private int y;
    ArrayList<Buff> effected;
    private Force force;

    Cell(int x, int y){
        this.x = x;
        this.y = y;
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
}