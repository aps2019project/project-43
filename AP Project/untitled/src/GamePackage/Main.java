package GamePackage;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Scanner;

public class Main extends Application {
    public static Scanner scan = new Scanner(System.in);

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = GameMenu.getGameMenuRoot();
        Scene scene = new Scene(root);
//        while(true){
//            MenuPackage.getCurrentMenu().setState(scan.nextLine());
//        }
        scene.setCursor(Cursor.OPEN_HAND);
        scene.getStylesheets().addAll("Login.css", "MainMenu.css", "ShopMenu.css", "Battle.css");
        primaryStage.setMinWidth(1700);
        primaryStage.setMinHeight(1000);
        primaryStage.setWidth(1900);
        primaryStage.setHeight(1000);
        primaryStage.centerOnScreen();

//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.setFullScreen(true);
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
