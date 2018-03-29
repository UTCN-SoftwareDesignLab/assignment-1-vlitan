package model.builder;

import model.Account;
import model.AccountType;

import java.sql.Date;

public class AccountBuilder {
    private Account account;

    public AccountBuilder(){
        account = new Account();
    }

    public AccountBuilder setId(int id) {
        account.setId(id);
        return this;
    }

    public AccountBuilder setType(AccountType type) {
        account.setType(type);
        return this;
    }

    public AccountBuilder setAmount(int amount) {
        account.setAmount(amount);
        return this;
    }

    public AccountBuilder setCreationDate(Date creationDate) {
        account.setCreationDate(creationDate);
        return this;
    }

    public AccountBuilder setOwnerId(int id){
        account.setOwnerId(id);
        return this;
    }

    public Account build(){
        return account;
    }
}
