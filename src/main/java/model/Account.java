package model;


import java.sql.Date;

public class Account {
    private int id;
    private AccountType type;
    private int amount;
    private Date creationDate;
    private int ownerId;

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }



    public Account() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString(){
        return "account\t" + id + "\t" + amount + "\t"+type.toString()+ "\t"+ creationDate.toString()+"\t"+ownerId;
    }
}
