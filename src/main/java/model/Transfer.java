package model;

import java.sql.Date;

public class Transfer extends Action{

    private int amount;
    private Account sourceAccount;
    private Account destinationAccount;

    public Transfer(){
    }

    public Transfer(int amount, Account sourceAccount, Account destinationAccount, Date date, int userId) {
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.setDate(date);
        this.setUserId(userId);
    }

    public Transfer(int id, int amount, Account sourceAccount, Account destinationAccount, Date date, int userId) {
        this(amount, sourceAccount, destinationAccount, date, userId);
        this.setId(id);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    @Override
    public String getDescription(){
        return "" + amount + " transfered from account " + sourceAccount.getId() + " to account " + destinationAccount.getId();
    }
}
