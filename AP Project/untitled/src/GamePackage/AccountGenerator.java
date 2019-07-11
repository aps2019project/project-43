package GamePackage;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccountGenerator {

    public static ArrayList<Account> getAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File directory = new File("untitled\\src\\Accounts"); //todo check the path before the final run
            if (directory.isDirectory()) {
                File[] files = directory.listFiles();
                System.out.println("in try");
                for (File f :
                        files) {
                    accounts.add(objectMapper.readValue(f, Account.class));
                    System.out.println("getting account " + f.getName());
                }
                return accounts;
            }
            else{
                System.out.println("not a directory");
            }
        } catch (IOException e) {
            System.out.println("Accounts not Found");
            System.out.println("your in catch");
        }
        return accounts;
    }


}
