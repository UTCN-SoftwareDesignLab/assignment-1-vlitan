package model.builder;

import model.Account;
import model.Action;
import model.Transfer;

public class TransferBuilder extends ActionBuilder {
    private Transfer transfer;

    public TransferBuilder(){
        transfer = new Transfer();
    }
    public TransferBuilder setSourceAccount(Account account){
        transfer.setSourceAccount(account);
        return this;
    }
    public TransferBuilder setDestinationAccount(Account account){
        transfer.setDestinationAccount(account);
        return this;
    }
    public TransferBuilder setAmout(int amount){
        transfer.setAmount(amount);
        return this;
    }
//TODO this does not look like a great idea at all.
    //a software architect spills its coffee every time this is executed
    @Override
    public Transfer build(){
        Action action = super.build();
        transfer.setId(action.getId());
        transfer.setDate(action.getDate());
        transfer.setUserId(action.getUserId());
        return transfer;
    }
}
