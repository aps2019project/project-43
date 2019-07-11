package GamePackage;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.util.Duration;

import static javafx.scene.layout.BackgroundRepeat.REPEAT;
import static javafx.scene.layout.BackgroundRepeat.ROUND;


public class MainMenu extends GameMenu {

    private static GameMenu mainMenu = new MainMenu();

    private StackPane mainMenuRoot = new StackPane();
//    private Scene mainMenuScene = new Scene(mainMenuRoot, Color.AQUA);

    private final StringProperty AccountUsernameProperty = new SimpleStringProperty(((AccountMenu) AccountMenu.getAccountMenu()).getLoggedAccount().getUsername());
    private final IntegerProperty AccountMoneyProperty = new SimpleIntegerProperty(15000);
    private final BooleanProperty isoverlayon = new SimpleBooleanProperty(false);

    private final Button battleButton = new Button("Battle");
    private final Button shopButton = new Button("Shop");
    private final Button collectionButton = new Button("Collection");
    private final Button leaderBoardButton = new Button("Leaderboard");
    private final Button logoutButton = new Button("Save & Logout");
    private final Button battleMultiplayerButton = new Button("MultiPlayer");
    private final Button battleSinglePlayerButton = new Button("SinglePlayer");
    private final Button backButton = new Button();
    private final Button backToMainMenu = new Button();

    private MainMenu() {

        createMainMenu();
        initProperties();
    }

    public static GameMenu getMainMenu() {
        return mainMenu;
    }

    @Override
    public void setState(String input) {
        input = input.trim();
        switch (input) {
            case "battle": {
                BattleMenu.goToBattleMenu(1);//todo changes the value
                break;
            }
            case "shop": {
                ShopMenu.goToShopMenu();
                break;
            }
            case "collection": {
                CollectionMenu.goToCollectionMenu();
                break;
            }
            case "exit": {
                AccountMenu.goToAccountMenu();
                break;
            }
            case "help": {
                showMenu();
                break;
            }
            default: {

            }
        }
    }

    private void showMenu() {
        System.out.print("*****Main Menu*****\n" +
                "1. Battle\n" +
                "2. Collection\n" +
                "3. Shop\n" +
                "4. Help\n" +
                "5. Exit\n");

    }

    public static void goToMainMenu() {
        GameMenu.getGameMenuRoot().getChildren().clear();
        addToGameMenuRoot(MainMenu.getMainMenu().getMenuRoot());
//        ((MainMenu) MainMenu.getMainMenu()).createMainMenu();

//        MenuPackage.setCurrentMenu(MainMenu.getMainMenu());
        ((MainMenu) mainMenu).showMenu();
    }

    @Override
    public StackPane getMenuRoot() {
        return mainMenuRoot;
    }

    private void createMainMenu() {
        AnchorPane avatarSection = createUsernameBox();
        VBox vMain = createsMenuButtons();


//        StackPane background = new StackPane();
        mainMenuRoot.setId("MainPane");
        mainMenuRoot.getChildren().addAll(avatarSection, vMain);
        addToGameMenuRoot(mainMenuRoot);

        initProperties();
    }

    private VBox createsMenuButtons() {
        VBox vMain = new VBox(10);
        vMain.setAlignment(Pos.CENTER_LEFT);
        vMain.setMinSize(500, 700);
        vMain.setPrefSize(500, 700);
        vMain.setMaxSize(500, 700);

        battleButton.getStyleClass().add("MainButton");

        collectionButton.getStyleClass().add("MainButton");

        shopButton.getStyleClass().add("MainButton");

        leaderBoardButton.getStyleClass().add("MainButton");

        logoutButton.getStyleClass().add("MainButton");

        vMain.getChildren().addAll(battleButton, collectionButton, shopButton, leaderBoardButton, logoutButton);
        vMain.getStyleClass().add("MainBox");

        return vMain;
    }

    private AnchorPane createUsernameBox() {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getStyleClass().add("borderpane");
        anchorPane.setMaxSize(600, 300);
        anchorPane.setTranslateY(-300);
        anchorPane.setTranslateX(570);

//        borderPane.getStyleClass().add("avatarSection");
        HBox userBox = new HBox();
        userBox.setPrefSize(600, 300);
        userBox.setMaxSize(600, 300);
        userBox.setMinSize(600, 300);
        userBox.setAlignment(Pos.TOP_RIGHT);
        userBox.getStyleClass().add("avatar");

        HBox AvatarBox = new HBox();
        Image userAvatar = new Image("lyonar_argeonhighmayne1.jpg");
        ImageView avatarImageView = new ImageView(userAvatar);

        VBox nameNMoney = new VBox(10);
        Label name = new Label();
        name.setText(AccountUsernameProperty.get());
        name.getStyleClass().add("user-label");

        Label money = new Label();
        money.setText(Integer.toString(AccountMoneyProperty.get()));
        money.getStyleClass().add("user-label");
        nameNMoney.getChildren().addAll(name, money);


        AvatarBox.getChildren().add(avatarImageView);
        userBox.getChildren().addAll(nameNMoney, AvatarBox);
        anchorPane.getChildren().add(userBox);
        return anchorPane;
    }

    private StackPane createBattleSubButtons() {
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.setMinSize(1000, 1000);
        root.setPrefSize(1000, 1000);
        root.setMaxSize(1800, 1000);
        if (!isoverlayon.get()) {
            root.getStyleClass().add("BattleSubMenu");
//        root.setPrefSize(400, 400);
//            root.setMaxSize(0, 0);
            HBox hbox = new HBox(100);
            VBox singleplayerBox = new VBox(-100);
            VBox multiplayerBox = new VBox(-100);
            singleplayerBox.setAlignment(Pos.CENTER);
            multiplayerBox.setAlignment(Pos.CENTER);

            Image singlePImage = new Image("singlePlayerBackground-Cropped.jpg");
            ImageView singlePlayerImageView = new ImageView(singlePImage);
            singlePlayerImageView.setFitWidth(500);
            singlePlayerImageView.setFitHeight(900);
            singlePlayerImageView.getStyleClass().add("battleSubmenuImage");

            Image multiPImage = new Image("multiPlayerBackground-Cropped.jpg");
            ImageView multiPlayerImageView = new ImageView(multiPImage);
            multiPlayerImageView.relocate(0, 0);
            multiPlayerImageView.setFitHeight(900);
            multiPlayerImageView.setFitWidth(500);
            multiPlayerImageView.getStyleClass().add("battleSubmenuImage");

            battleMultiplayerButton.getStyleClass().add("MainButton");
            battleSinglePlayerButton.getStyleClass().add("MainButton");

            hbox.getStyleClass().add("hbox");
            backButton.getStyleClass().add("backButton");
            backButton.setPrefSize(100, 100);
            VBox backButtonVbox = new VBox();
            backButtonVbox.getChildren().add(backButton);
            backButtonVbox.setPrefSize(400, 300);

            root.setTranslateY(-1200);
//            KeyValue widthKeyValue = new KeyValue(root.translateXProperty(), 800, Interpolator.EASE_IN);
            KeyValue heightKeyValue = new KeyValue(root.translateYProperty(), 25, Interpolator.EASE_IN);
//            KeyFrame widthKeyFrame = new KeyFrame(Duration.millis(500), widthKeyValue);
            KeyFrame heightKeyFrame = new KeyFrame(Duration.millis(500), heightKeyValue);
            Timeline timeline = new Timeline(heightKeyFrame);
            timeline.play();

            backButton.setOnMouseClicked(event -> {
                isoverlayon.set(false);
                KeyValue battleX = new KeyValue(root.translateXProperty(), -2000, Interpolator.EASE_OUT);
                KeyFrame battleOutKeyFrame = new KeyFrame(Duration.millis(500), battleX);
                Timeline battleOutTimeline = new Timeline(battleOutKeyFrame);
                battleOutTimeline.play();
            });

            singleplayerBox.setOnMouseEntered(event -> {
                Image image = new Image("singlePlayerBackground-Full.jpg");
                BackgroundImage backgroundImage = new BackgroundImage(image, ROUND,
                        ROUND, BackgroundPosition.CENTER, new BackgroundSize(1700, 1000, true, true, true, true));
                hbox.setBackground(new Background(backgroundImage));

            });

            singleplayerBox.setOnMouseExited(event -> {
                Stop[] stops = new Stop[]{new Stop(0, Color.rgb(0, 0, 0, 0.2)),
                        new Stop(1, Color.rgb(0, 0, 200, 0.5)),  new Stop(2, Color.rgb(0, 0, 0, 0.2))};
                hbox.setBackground(new Background(new BackgroundFill(new LinearGradient(0, 0, 1, 1, true,
                        CycleMethod.REFLECT, stops), CornerRadii.EMPTY, Insets.EMPTY)));
            });

            singleplayerBox.setOnMouseClicked(event -> {

            });

            multiplayerBox.setOnMouseEntered(event -> {
                Image image = new Image("multiPlayerBackground-Full.jpg");
                BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.SPACE,
                        BackgroundRepeat.SPACE, BackgroundPosition.CENTER, new BackgroundSize(1700, 1000, true, true, true, true));
                hbox.setBackground(new Background(backgroundImage));
            });

            multiplayerBox.setOnMouseExited(event -> {
                Stop[] stops = new Stop[]{new Stop(0, Color.rgb(0, 0, 0, 0.2)),
                        new Stop(1, Color.rgb(0, 0, 200, 0.5)),  new Stop(2, Color.rgb(0, 0, 0, 0.2))};
                hbox.setBackground(new Background(new BackgroundFill(new LinearGradient(0, 0, 1, 1, true,
                        CycleMethod.REFLECT, stops), CornerRadii.EMPTY, Insets.EMPTY)));
            });

            singleplayerBox.getChildren().addAll(singlePlayerImageView, battleSinglePlayerButton);
            multiplayerBox.getChildren().addAll(multiPlayerImageView, battleMultiplayerButton);
            hbox.getChildren().addAll(backButtonVbox, singleplayerBox, multiplayerBox);
            root.getChildren().addAll(hbox);

            return root;
        }
        return null;
    }

    private void initProperties() {
        battleButton.setOnMouseClicked(event -> {
            mainMenuRoot.getChildren().add(createBattleSubButtons());
            isoverlayon.set(true);
        });

//        collectionButton.setOnMouseClicked(event -> );

        shopButton.setOnMouseClicked(event -> {
            ShopMenu.goToShopMenu();
        });

        leaderBoardButton.setOnMouseClicked(event -> {
            showLeaderBoard();
        });

        logoutButton.setOnMouseClicked(event -> {
            Account.saveAccount(((AccountMenu) AccountMenu.getAccountMenu()).getLoggedAccount());
            AccountMenu.goToAccountMenu();
        });

        backToMainMenu.getStyleClass().add("backButton");
        backToMainMenu.setPrefSize(60, 60);
        backToMainMenu.setPadding(new Insets(20));
        backToMainMenu.setOnMouseClicked(event -> {
            MainMenu.goToMainMenu();
        });

        battleSinglePlayerButton.setOnMouseClicked(event -> {
            BattleMenu.goToBattleMenu(1);
        });
        AccountUsernameProperty.addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equals(newValue))
                AccountUsernameProperty.setValue(((AccountMenu) AccountMenu.getAccountMenu()).getLoggedAccount().getUsername());
        });

        AccountMoneyProperty.addListener(((observable, oldValue, newValue) -> {
            if (!oldValue.equals(newValue))
                AccountMoneyProperty.setValue((((AccountMenu) AccountMenu.getAccountMenu()).getLoggedAccount().getMoney()));
        }));

    }

    private void showLeaderBoard() {
        GameMenu.getGameMenuRoot().getChildren().clear();

        StackPane leaderBoardPane = new StackPane();
        leaderBoardPane.getChildren().addAll(createLeaderBoard());
        Image image = new Image("backgroundLeaderBoard.jpg");
        leaderBoardPane.setBackground(new Background(new BackgroundImage(image, ROUND, REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1700, 1000, true, true, true, true))));

        GameMenu.addToGameMenuRoot(leaderBoardPane);
    }

    private BorderPane createLeaderBoard() {
        Leaderboard leaderboard = new Leaderboard(AccountGenerator.getAccounts());
        System.out.println("Account size is : " + AccountGenerator.getAccounts().size());

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20));
        borderPane.setCenter(leaderboard.createLeaderBoardTable());
        borderPane.setTop(backToMainMenu);

        return borderPane;
    }

}
