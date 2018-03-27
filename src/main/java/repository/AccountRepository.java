package repository;

import model.Account;

import java.util.List;

public interface AccountRepository {
    boolean addAccount(Account account);
    boolean updateAccount(Account account);
    boolean deleteAccount(Account account);
    boolean dropAllAccounts();
    List<Account> findAllAccounts();
    Account findAccountById(int id);
}
