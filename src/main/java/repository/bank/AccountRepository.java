package repository.bank;

import model.Account;
import model.Client;

import java.util.List;

public interface AccountRepository {
    boolean add(Account account);
    boolean update(Account account);
    boolean delete(Account account);
    List<Account> findAll();
    List<Account> findByClient(Client client);
    Account findById(int id);
}
