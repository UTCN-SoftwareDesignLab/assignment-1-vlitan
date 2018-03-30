package service;

import model.Account;
import model.Transfer;

import java.util.List;

public interface AccountService {
    public boolean add(Account account);
    public boolean delete(Account account);
    public boolean update(Account account);
    public List<Account> findAll();
    public boolean makeTransfer(Transfer transfer);
}
