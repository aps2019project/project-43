package GamePackage;

import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ShopMenu extends GameMenu {

    private static GameMenu shopMenu = new ShopMenu();

    private ShopMenu() {

        createShopMenu();
        initProperties();
    }

    public static GameMenu getShopMenu() {
        return shopMenu;
    }

    @Override
    public void setState(String input) {
        input = input.trim();
        String[] command = input.split(" ");
        AccountMenu accountMenu = (AccountMenu) AccountMenu.getAccountMenu();
        Shop playersShop = accountMenu.getLoggedAccount().getShop();
        switch (command[0]) {
            case "show": {
                if (command.length > 1) {
                    if (command[1].equals("collection")) {
                        playersShop.showCollection();
                    }
                } else {
                    playersShop.show();
                }
                break;
            }
            case "search": {
                if (command[1].equals("collection")) {
                    playersShop.searchCollection(command[2]);
                } else {
                    playersShop.search(command[1]);
                }
                break;
            }
            case "sell": {
                for (int i = 1; i < command.length; i++) {
                    playersShop.sellCard(Integer.parseInt(command[i]));
                }
                break;
            }
            case "buy": {
                playersShop.buyCard(command[1]);
                break;
            }
            case "exit": {
                MainMenu.goToMainMenu();
                break;
            }
            case "help": {
                showMenu();
                break;
            }
            default: {
                System.out.println("invalid Command");
                break;
            }
        }
    }

    private void showMenu() {
        System.out.print("1. Show\n" +
                "2. Show collection\n" +
                "3. Search[item name|card name]\n" +
                "4. Search collection[item name|card name]\n" +
                "5. Buy[card name|item name]\n" +
                "6. Sell[card ID]\n" +
                "7. Exit\n" +
                "8. help\n");
    }



    private StackPane shopMenuRoot = new StackPane();
    private BorderPane shopPane = new BorderPane();

    private Button backToMainMenu = new Button();


    @Override
    public StackPane getMenuRoot() {
        return shopMenuRoot;
    }

    public static void goToShopMenu() {
        GameMenu.getGameMenuRoot().getChildren().clear();
        GameMenu.addToGameMenuRoot(ShopMenu.getShopMenu().getMenuRoot());
//        GameMenu.setCurrentMenu(ShopMenu.getShopMenu());
        ((ShopMenu) shopMenu).showMenu();
    }

    private  void createShopMenu(){
        StackPane background = new StackPane();
        background.setId("shopBackground");

        createTitle();
        createTabs();

        shopMenuRoot.getChildren().addAll(background, shopPane);
    }

    private void createTitle(){
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getStyleClass().add("titleBox");

        Label title = new Label("Shop");
        titleBox.getChildren().add(title);

        shopPane.setTop(titleBox);
    }

    private void createTabs(){
        TabPane shopTab = new TabPane();
        shopTab.setSide(Side.LEFT);
        shopTab.setPrefSize(1700, 800);
        shopTab.setTabMinWidth(100);
        shopTab.setTabMinHeight(300);
        shopTab.setTabMaxWidth(120);
        shopTab.setTabMaxHeight(300);

        Tab heroTab = new Tab("Hero");
        ScrollPane heroPane = new ScrollPane();
        heroTab.setContent(heroPane);
        GridPane gridPane = new GridPane();
        heroPane.setContent(gridPane);
        for (Card card :
                CardGenerator.heroGenerator()) {
            gridPane.getChildren().add(card.showCard());
        }

        Tab minionTab = new Tab("Minion");
        ScrollPane minionPane = new ScrollPane();
        minionTab.setContent(minionPane);



        Tab spellTab = new Tab("Spell");
        ScrollPane spellPane = new ScrollPane();
        spellTab.setContent(spellPane);

        Tab itemTab = new Tab("Item");
        ScrollPane itempane = new ScrollPane();
        itemTab.setContent(itempane);

        shopTab.getTabs().addAll(heroTab, minionTab, spellTab, itemTab);
        shopTab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        HBox backButtonBox = new HBox();
        backButtonBox.setAlignment(Pos.CENTER_LEFT);
        backButtonBox.getChildren().add(backToMainMenu);

        shopPane.setCenter(shopTab);
        shopPane.setBottom(backButtonBox);
    }

    private void initProperties(){
        backToMainMenu.setId("backButton");
        backToMainMenu.setPrefSize(100, 100);
        backToMainMenu.setMaxSize(50, 50);
        backToMainMenu.setMinSize(10, 10);
        backToMainMenu.setOnMouseClicked(event ->{
            MainMenu.goToMainMenu();
        });

    }
}
