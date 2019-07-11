package GamePackage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import java.util.*;


public class Leaderboard {
    private List<Account> accounts = new ArrayList<>();


    public Leaderboard(List<Account> accounts) {
        this.accounts = accounts;
        sortByWins();
    }

    public Leaderboard sortByWins() {
        accounts.sort(Comparator.comparing(Account::getWins).reversed()
                .thenComparing(Account::getUsername));
        for (Account account :
                accounts) {
            account.setRank(accounts.indexOf(account) + 1);
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < accounts.size(); i++) {
            stringBuilder.append(i + 1).append("- ")
                    .append(accounts.get(i).toString())
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    public StackPane createLeaderBoardTable(){
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);

        TableView tableView = new TableView<Account>();
        tableView.setPadding(new Insets(20, 20, 20, 20));
        tableView.setEditable(false);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setMaxSize(640, 900);

        TableColumn rankColumn = new TableColumn("Rank");
        rankColumn.setCellValueFactory(new PropertyValueFactory<Account, Integer>("rank"));
        rankColumn.getStyleClass().add("table-cells");
        rankColumn.setMinWidth(50);
        rankColumn.setPrefWidth(50);
        rankColumn.setMaxWidth(100);

        TableColumn usernameColumn = new TableColumn<Account, String>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
        usernameColumn.setMinWidth(200);
        usernameColumn.setPrefWidth(200);
        usernameColumn.setMaxWidth(300);
        usernameColumn.setEditable(false);
        usernameColumn.getStyleClass().add("table-cells");

        TableColumn winsColumn = new TableColumn<Account, Integer>("Wins");
        winsColumn.setCellValueFactory(new PropertyValueFactory<Account, Integer>("wins"));
        winsColumn.setMaxWidth(200);
        winsColumn.setPrefWidth(100);
        winsColumn.setMinWidth(100);
        winsColumn.setEditable(false);
        winsColumn.getStyleClass().add("table-cells");

        tableView.getColumns().addAll(rankColumn, usernameColumn, winsColumn);

        for (Account account :
                accounts) {
            tableView.getItems().add(account);
        }

        root.getChildren().add(tableView);
        return root;
    }
}