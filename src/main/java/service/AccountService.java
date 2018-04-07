package service;

import model.Account;
import model.Transfer;
import model.validator.Notification;

import java.util.List;

public interface AccountService {
    public Notification<Boolean> add(Account account);
    public Notification<Boolean> delete(Account account);
    public Notification<Boolean> update(Account account);
    public List<Account> findAll();
    public Notification<Account> findById(int id);
}
