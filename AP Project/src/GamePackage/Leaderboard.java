package GamePackage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {
    private List<Account> accounts = new ArrayList<>();

    public Leaderboard(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Leaderboard sortByWins() {
        accounts.sort(Comparator.comparing(Account::getWins).reversed()
                .thenComparing(Account::getUsername));
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
}
