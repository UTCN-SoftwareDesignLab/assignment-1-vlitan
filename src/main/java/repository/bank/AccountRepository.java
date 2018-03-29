package repository.bank;

import model.Account;
import model.Client;

import java.util.List;

public interface AccountRepository {
    boolean addAccount(Account account);
    boolean updateAccount(Account account);
    boolean deleteAccount(Account account);
    List<Account> findAllAccounts();
    List<Account> findByClient(Client client);
    Account findAccountById(int id);
}
