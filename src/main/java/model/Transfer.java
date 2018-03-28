package model;

import java.util.Date;

public class Transfer {

    private int id;
    private int amount;
    private Account sourceAccount;
    private Account destinationAccount;
    private Date date;
    private int userId;

    public Transfer(int amount, Account sourceAccount, Account destinationAccount, Date date, int userId) {
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.date = date;
        this.userId = userId;
    }

    public Transfer(int id, int amount, Account sourceAccount, Account destinationAccount, Date date, int userId) {
        this(amount, sourceAccount, destinationAccount, date, userId);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
