package GamePackage;


import java.util.Scanner;

public class Main {

    private static GamePackage.GameMenu currentMenu;
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
//        Account.makeFakeAccounts();
        AccountMenu.goToAccountMenu();
        while(currentMenu.execCommand(scan.nextLine()));
    }

    public static void setCurrentMenu(GameMenu currentMenu) {
        Main.currentMenu = currentMenu;
        currentMenu.showMenu();
    }
}
