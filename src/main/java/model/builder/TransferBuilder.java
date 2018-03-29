package model.builder;

import model.Account;
import model.Transfer;

import java.sql.Date;

public class TransferBuilder {
    private Transfer transfer;

    public TransferBuilder(){
        transfer = new Transfer();
    }

    public TransferBuilder setId(int id){
        transfer.setId(id);
        return this;
    }
    public TransferBuilder setAmount(int amount){
        transfer.setAmount(amount);
        return this;
    }
    public TransferBuilder setSourceAccount(Account account{
        transfer.setSourceAccount(account);
        return this;
    }
    public TransferBuilder setDestinationAccount(Account account){
        transfer.setDestinationAccount(account);
        return this;
    }
    public TransferBuilder setDate(Date date){
        transfer.setDate(date);
        return this;
    }
    public TransferBuilder setUserid(int id){
        transfer.setUserId(id);
        return this;
    }
    public Transfer build(){
        return transfer;
    }
}
