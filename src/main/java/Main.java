import model.Account;
import model.AccountType;
import model.builder.AccountBuilder;

import java.sql.Date;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {
        System.out.println("Such inteli, much J, very wOw");
        Account account = (new AccountBuilder()).setType(AccountType.CREDIT).build();
        System.out.print(account.getType().toString());
    }
}
