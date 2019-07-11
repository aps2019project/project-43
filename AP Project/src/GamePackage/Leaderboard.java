package GamePackage;

import java.util.Comparator;
import java.util.List;

public class Leaderboard {
    private List<Account> accounts;

    Leaderboard(List<Account> accounts) {
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
            stringBuilder.append(i + 1).append("- ").append(accounts.get(i).toString()).append(accounts.get(i).getClient() != null ? " (online)" : "")
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
