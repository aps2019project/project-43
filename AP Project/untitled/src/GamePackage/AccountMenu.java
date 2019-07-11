package GamePackage;

import javafx.animation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class AccountMenu extends GameMenu {
    private static GameMenu accountMenu = new AccountMenu();
    private Account loggedAccount;
    private HashMap<String, Account> userMap = new HashMap<>();


    private final BooleanProperty signupProperty = new SimpleBooleanProperty(false);
    private final BooleanProperty loginProperty = new SimpleBooleanProperty(false);
    private final BooleanProperty cancellProperty = new SimpleBooleanProperty(false);
    private final BooleanProperty goToMainMebuProperty = new SimpleBooleanProperty(false);

    private StackPane background = new StackPane();
    private StackPane accountRootGroup = new StackPane();
    private StackPane accountRoot = new StackPane();
//    private Scene accountScene = new Scene(accountRoot, 1200, 700);

    private final HBox overlay = new HBox();
    private final HBox buttonsOverlay = new HBox(5);
    private final VBox textOverlay = new VBox();
    private final Label usernameLabel = new Label("Username:");
    private final Label passwordLabel = new Label("Password:");
    private final Label lOvrSubText = new Label();
    private final TextField usernameField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final Button signUpButton = new Button("Sign Up");
    private final Button loginButton = new Button("Login");
    private final Button quiteButton = new Button("Quit");


    private AccountMenu() {

        createAccountMenu();
        initProperties();
    }

    public static GameMenu getAccountMenu() {
        return accountMenu;
    }

    @Override
    public void setState(String input) {
        input = input.trim();
        if (loggedAccount == null) {
            switch (input) {
                case "create account": {
//                    logicalcreateAccount();
                    break;
                }
                case "login": {
//                    logicallogin();
                    break;
                }
                case "help": {
                    showMenu();
                    break;
                }
                default: {
                    System.out.println("Invalid Command");
                }
            }
        } else {
            switch (input) {
                case "main menu": {
                    MainMenu.goToMainMenu();
                    break;
                }
                case "show leaderboard": {
                    showLeaderboard();
                    break;
                }
                case "save": {

                    break;
                }
                case "logout": {
                    logout();
                    break;
                }
                case "help": {
                    showMenu();
                    break;
                }
                default: {
                    System.out.println("Invalid Command");
                }
            }
        }
    }

    private void showMenu() {
        if (loggedAccount == null) {
            System.out.print("1. Create account\n" +
                    "2. Login\n" +
                    "3. Help\n");
        } else {
            System.out.print("1. Main menu\n" +
                    "2. Show leaderboard\n" +
                    "3. Save\n" +
                    "4. Logout\n" +
                    "5. Help\n");
        }
    }

    private boolean createAccount() {
        if (usernameField.getText().equals("")) {
            return false;
        } else {
            File AccountFile = new File("untitled\\src\\Accounts\\" + usernameField.getText() + ".json"); //todo check the path before the final run
            Account newAccount;
            try {
                if (AccountFile.exists()) {
                    //todo new overlay choose another username
                    return false;
                } else {
                    throw new IOException();
                }
            } catch (IOException e) {
                newAccount = new Account(usernameField.getText(), passwordField.getText());
                loggedAccount = newAccount;
                Account.saveAccount(newAccount);
                System.out.println("Your account created successfully ");
                return true;
            }
        }
    }

    private boolean login() {
        File AccountFile = new File("untitled\\src\\Accounts\\" + usernameField.getText() + ".json");//todo check the path $
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Account newAccount = objectMapper.readValue(AccountFile, Account.class);
            if (newAccount.getPassword().equals(passwordField.getText())) {
                loggedAccount = newAccount;
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            //todo you already have a account
            return false;
        }
    }

    private void showLeaderboard() {
        System.out.println(new Leaderboard(Account.getAccounts()).sortByWins());
    }

    private void logout() {
        if (loggedAccount != null) {
            loggedAccount = null;
            System.out.println("You're successfully logged out");
        } else
            System.out.println("You're not even logged in");
    }

    public Account getLoggedAccount() {
        return loggedAccount;
    }

    public static void goToAccountMenu() {
        GameMenu.getGameMenuRoot().getChildren().clear();
        addToGameMenuRoot(AccountMenu.getAccountMenu().getMenuRoot());
//        GameMenu.setCurrentMenu(AccountMenu.getAccountMenu());
        ((AccountMenu) accountMenu).showMenu();
    }

    @Override
    public Scene getMenuScene() {
//        return accountScene;
        return null;
    }

    @Override
    public StackPane getMenuRoot() {
        return accountRoot;
    }

    private void createAccountMenu() {
        VBox vMain = createVMain();
        HBox titleBox = createTitle();
        titleBox.setAlignment(Pos.TOP_LEFT);


        background.setId("LoginPane");
//        playMusic();

        accountRootGroup.getChildren().addAll(titleBox, vMain);
        accountRoot.getChildren().addAll(background, accountRootGroup);
        addToGameMenuRoot(accountRoot);
    }


    private HBox createTitle() {
        HBox titleBox = new HBox(-150);
        titleBox.setAlignment(Pos.CENTER);

        HBox titleLabelBox = new HBox();
        HBox subtitleLabelBox = new HBox();
        Label titleLabel = new Label("Duelyst");
        titleLabel.getStyleClass().add("loginLabel");
        Label titleSubLabel = new Label("Team 43");
        titleSubLabel.setId("subTitle");

        titleLabelBox.setMaxHeight(250);
        subtitleLabelBox.setMaxHeight(280);
//        titleLabelBox.setPrefSize(600, 50);
//        titleBox.setPrefSize(800, 50);
        subtitleLabelBox.setAlignment(Pos.BOTTOM_LEFT);

        KeyValue titleOpacity = new KeyValue(titleLabel.opacityProperty(), 0, Interpolator.LINEAR);
        KeyFrame titleKeyframe = new KeyFrame(Duration.millis(1000), titleOpacity);
        Timeline timeline = new Timeline(titleKeyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();

        titleLabelBox.getChildren().add(titleLabel);
        subtitleLabelBox.getChildren().add(titleSubLabel);

        titleBox.getChildren().addAll(titleLabelBox, subtitleLabelBox);
        return titleBox;
    }

//    private void playMusic() {
//        String musicFile = "src/For the King.mp3";
//        Media media = new Media(new File(musicFile).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setOnEndOfMedia(() -> new MediaPlayer(new Media(new File("src/The King of The Highlands.mp3").toURI().toString())));
//        mediaPlayer.play();
//    }

    private VBox createVMain() {
        VBox vMain = new VBox(10);

        usernameField.setPrefSize(BUTTON_PREF_WIDTH, BUTTON_PREF_HEIGHT);
        usernameField.setAlignment(Pos.CENTER);
        usernameField.getStyleClass().add("loginButton");
        usernameField.setMaxSize(600, 100);

        passwordField.setAlignment(Pos.CENTER);
        passwordField.setPrefSize(BUTTON_PREF_WIDTH, BUTTON_PREF_HEIGHT);
        passwordField.getStyleClass().add("loginButton");
        passwordField.setMaxSize(600, 100);

        HBox buttonsBox = new HBox(10);
        buttonsBox.setAlignment(Pos.CENTER);

        signUpButton.setPrefSize(BUTTON_PREF_WIDTH, BUTTON_PREF_HEIGHT);
        signUpButton.setAlignment(Pos.CENTER);
        signUpButton.getStyleClass().add("loginButton");

        loginButton.setAlignment(Pos.CENTER);
        loginButton.setPrefSize(BUTTON_PREF_WIDTH, BUTTON_PREF_HEIGHT);
        loginButton.getStyleClass().add("loginButton");

        quiteButton.setPrefSize(BUTTON_PREF_WIDTH, BUTTON_PREF_HEIGHT);
        quiteButton.setAlignment(Pos.CENTER);
        quiteButton.getStyleClass().setAll("loginButton");

        buttonsBox.getChildren().addAll(signUpButton, loginButton, quiteButton);
        vMain.setAlignment(Pos.BOTTOM_CENTER);
        vMain.getChildren().addAll(usernameField, passwordField, buttonsBox);
        return vMain;
    }

    private class Overlay implements ChangeListener<Boolean> {

        private final Button btn1, btn2;
        private final String message, warning;
        private final String style1, style2;


        public Overlay(String message, String warning, Button btn1, Button btn2, String style1, String style2) {
            this.message = message;
            this.warning = warning;
            this.btn1 = btn1;
            this.btn2 = btn2;
            this.style1 = style1;
            this.style2 = style2;
        }

        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (newValue) {
                overlay.getStyleClass().setAll("game-overlay", style1);
//                lOvrText.setText(message);
//                lOvrText.getStyleClass().setAll("game-label",style2);
                lOvrSubText.setText(warning);
                lOvrSubText.getStyleClass().setAll("game-label", "game-lblWarning");
                textOverlay.getChildren().setAll(lOvrSubText);
                buttonsOverlay.getChildren().setAll(btn1);
                if (btn2 != null) {
                    buttonsOverlay.getChildren().add(btn2);
                }

                ((AccountMenu) AccountMenu.getAccountMenu()).accountRoot.getChildren().addAll(overlay, buttonsOverlay);
            }
        }
    }

    private void initProperties() {
        usernameField.setText("Username");

        overlay.setAlignment(Pos.CENTER);
        overlay.getChildren().setAll(textOverlay);
        textOverlay.setAlignment(Pos.CENTER);
        buttonsOverlay.setAlignment(Pos.CENTER);
        buttonsOverlay.setSpacing(10);

        signUpButton.setOnMouseClicked(event -> signupbuttonClicked());

        loginButton.setOnMouseClicked(event -> loginButtonClicked());

        quiteButton.setOnMouseClicked(event -> quiteButtonClicked());

        goToMainMebuProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                MainMenu.goToMainMenu();
                goToMainMebuProperty.set(false);
            }
        });
    }

    private void signupbuttonClicked() {
        if (createAccount()){
            goToMainMebuProperty.set(true);
            usernameField.clear();
            passwordField.clear();
    }
}

    private void loginButtonClicked() {
        if (login()){
            goToMainMebuProperty.set(true);
            usernameField.clear();
            passwordField.clear();
        }
    }

    private void quiteButtonClicked() {
        System.exit(104);
    }

    public void setGoToMainMebuProperty(boolean value) {
        goToMainMebuProperty.set(value);
    }

}
