package GamePackage;

import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        while(true){
            GameMenu.getCurrentMenu().setState(scan.nextLine());
        }
    }
}
