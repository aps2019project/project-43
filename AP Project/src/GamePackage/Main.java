package GamePackage;

import java.util.Scanner;

public class Main {

    private static GameMenu currentMenu = AccountMenu.goToAccountMenu();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String testCommands=
                "create account\n" +
                        "ali\n" +
                        "\n" +
                        "logout\n" +
                        "create account\n" +
                        "shahab\n" +
                        "\n" +
                        "logout\n" +
                        "create account\n" +
                        "mammad\n" +
                        "\n" +
                        "logout\n" +
                        "login\n" +
                        "ali\n" +
                        "\n" +
                        "main menu\n" +
                        "shop\n" +
                        "buy kaveh\n" +
                        "buy Eagle\n" +
                        "buy Piran\n" +
                        "buy Bahman\n" +
                        "buy eagle\n" +
                        "buy give\n" +
                        "exit\n" +
                        "collection\n" +
                        "create deck maindeck\n" +
                        "select deck maindeck\n" +
                        "add kaveh to deck maindeck\n" +
                        "add eagle to deck maindeck\n" +
                        "add Piran to deck maindeck\n" +
                        "add Bahman to deck maindeck\n" +
                        "add eagle to deck maindeck\n" +
                        "exit\n" +
                        "exit\n" +
                        "logout\n" +
                        "login\n" +
                        "shahab\n" +
                        "\n" +
                        "main menu\n" +
                        "shop\n" +
                        "buy rostam\n" +
                        "buy siavash\n" +
                        "buy siavash\n" +
                        "buy PersianHorseman\n" +
                        "buy Panther\n" +
                        "buy Panther\n" +
                        "buy Panther\n" +
                        "buy Panther\n" +
                        "buy Panther\n" +
                        "buy Panther\n" +
                        "buy Panther\n" +
                        "buy Panther\n" +
                        "buy Panther\n" +
                        "buy Panther\n" +
                        "buy Panther\n" +
                        "buy eagle\n" +
                        "buy eagle\n" +
                        "buy eagle\n" +
                        "buy eagle\n" +
                        "buy eagle\n" +
                        "buy persianarcher\n" +
                        "exit\n" +
                        "collection\n" +
                        "create deck maindeck\n" +
                        "select deck maindeck\n" +
                        "add rostam to deck maindeck\n" +
                        "add siavash to deck maindeck\n" +
                        "add siavash to deck maindeck\n" +
                        "add PersianHorseman to deck maindeck\n" +
                        "add Panther to deck maindeck\n" +
                        "add Panther to deck maindeck\n" +
                        "add Panther to deck maindeck\n" +
                        "add Panther to deck maindeck\n" +
                        "add Panther to deck maindeck\n" +
                        "add Panther to deck maindeck\n" +
                        "add Panther to deck maindeck\n" +
                        "add Panther to deck maindeck\n" +
                        "add Panther to deck maindeck\n" +
                        "add Panther to deck maindeck\n" +
                        "add Panther to deck maindeck\n" +
                        "add eagle to deck maindeck\n" +
                        "add eagle to deck maindeck\n" +
                        "add eagle to deck maindeck\n" +
                        "add eagle to deck maindeck\n" +
                        "add eagle to deck maindeck\n" +
                        "add persianarcher to deck maindeck\n" +
                        "exit\n" +
                        "battle\n" +
                        "single player\n"
                ;
        for(String s:testCommands.split("\n")){
            System.out.println("**** "+s);
            exec(s);
        }
        while(exec(scan.nextLine()));
    }

    public static boolean exec(String str){
        return currentMenu.execCommand(str);
    }

    public static void setCurrentMenu(GameMenu currentMenu) {
        Main.currentMenu = currentMenu;
        currentMenu.showMenu();
    }
}
