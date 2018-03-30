package service;

import model.Account;
import model.validator.AccountValidator;
import model.validator.Notification;
import repository.bank.AccountRepository;

import java.util.List;

public class AccoutServiceMySql implements AccountService {
    private AccountRepository accountRepository;

    public AccoutServiceMySql(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Notification<Boolean> add(Account account) {
        AccountValidator validator = new AccountValidator();
        Notification<Boolean> addNotification = new Notification<>();
        boolean isValid = validator.validate(account);

        if (isValid) {
            accountRepository.add(account);
            addNotification.setResult(Boolean.TRUE);
        } else {
            validator.getErrors().forEach(addNotification::addError);
            addNotification.setResult(Boolean.FALSE);
        }
        return addNotification;
    }

    @Override
    public Notification<Boolean> delete(Account account) {
        AccountValidator validator = new AccountValidator();
        Notification<Boolean> deleteNotification = new Notification<>();
        boolean isValid = validator.validate(account);

        if (isValid) {
            accountRepository.delete(account);
            deleteNotification.setResult(Boolean.TRUE);
        } else {
            validator.getErrors().forEach(deleteNotification::addError);
            deleteNotification.setResult(Boolean.FALSE);
        }
        return deleteNotification;

    }

    @Override
    public Notification<Boolean> update(Account account) {
        AccountValidator validator = new AccountValidator();
        Notification<Boolean> updateNotification = new Notification<>();
        boolean isValid = validator.validate(account);

        if (isValid) {
            accountRepository.update(account);
            updateNotification.setResult(Boolean.TRUE);
        } else {
            validator.getErrors().forEach(updateNotification::addError);
            updateNotification.setResult(Boolean.FALSE);
        }
        return updateNotification;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}

