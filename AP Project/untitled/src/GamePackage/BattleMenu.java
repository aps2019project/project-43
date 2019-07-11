package GamePackage;

import com.sun.javafx.geom.Rectangle;
import com.sun.javafx.sg.prism.NGPhongMaterial;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Control;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class BattleMenu extends GameMenu {

    private static GameMenu battleMenu = new BattleMenu();

    public static GameMenu getBattleMenu(){
        return battleMenu;
    }

    @Override
    public void setState(String input) {
        switch (input){
            case "exit":{
                MainMenu.goToMainMenu();
                break;
            }
        }
    }

    private void showMenu(){

    }

    ///*****************todo delete this comment********************
    private StackPane battleRoot = new StackPane();
    private GridPane battleGrid = new GridPane();
    private Cell[][] cells = new Cell[5][9];

    private BattleMenu(){

        createBattleGrid();
        initProperties();
    }

    @Override
    public StackPane getMenuRoot() {
        return battleRoot;
    }

    public static void goToBattleMenu(int value){
        GameMenu.getGameMenuRoot().getChildren().clear();
        ((BattleMenu) BattleMenu.getBattleMenu()).createBattle(value);
        addToGameMenuRoot(BattleMenu.getBattleMenu().getMenuRoot());

        GameMenu.setCurrentMenu(BattleMenu.getBattleMenu());
        ((BattleMenu) battleMenu).showMenu();
    }

    private void createBattle(int value){
        if(value == 1)
            createBSinglePlayer();
        createBMultiPlayer();
    }

    private void createBSinglePlayer(){

    }

    private void createBMultiPlayer(){

    }

    private void createBattleGrid(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 9; j++){
                cells[i][j] = new Cell(i, j);
                cells[i][j].getStyleClass().add("cell");
                battleGrid.add(cells[i][j], j, i);
            }
        }
        for (int i = 0; i < 5; i++){
            battleGrid.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE,
                    Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
        for (int i = 0; i < 9; i++){
            battleGrid.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE,
                    Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
        }
    }

    private void initProperties(){
        initGameFirstProps();
//        battleRoot.setBackground(new Background(new BackgroundImage(new Image("backgroundShop.jpg"), BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, new BackgroundSize(1700, 1000, true, true, true, true))));
        battleRoot.getStyleClass().add("background");

        StackPane midGround = new StackPane();
        midGround.getStyleClass().add("midground");
//        Image midGround = new Image("playground/midground@2x.png");
//        ImageView imageView = new ImageView(midGround);
//        imageView.getStyleClass().add("midground");
//        imageView.setBlendMode(BlendMode.COLOR_BURN);


        battleGrid.setPadding(new Insets(20));
        battleGrid.setHgap(2);
        battleGrid.setVgap(2);
        battleGrid.setMaxSize(1200, 600);
        battleGrid.setTranslateY(50);

        Box box = new Box(900, 500, 500);


        battleRoot.getChildren().addAll(midGround, battleGrid);
    }

    private void initGameFirstProps(){
        cells[2][0].getStyleClass().add("hero");
        cells[2][8].getStyleClass().addAll("hero-warlock", "enemy");
    }


}
