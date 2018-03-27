package model.validator;

import model.Account;
import model.Transfer;

import java.util.ArrayList;
import java.util.List;

public class TransferValidator {
    private static final int MIN_AMOUNT = 5;

    private List<String> errors;

    public TransferValidator(){
    }

    public boolean validate(Transfer transfer){
        errors = new ArrayList<>();
        validateAmount(transfer.getAmount());
        validateSourceAccount(transfer.getSourceAccount(), transfer.getAmount());
        validateAccount(transfer.getDestinationAccount());
        return errors.isEmpty();
    }

    public void validateAmount(int amount){
        if (amount < MIN_AMOUNT) {
            errors.add(String.format("can`t transfer an amount of money less than %d", MIN_AMOUNT));
        }
    }

    public void validateSourceAccount(Account account, int amount){
        validateAccount(account);
        if (account.getAmount() < amount){
            errors.add("can`t transfer more money that are in the account");
        }
    }

    public void validateAccount(Account account) {
        if (account == null) {
            errors.add("account can`t be null");
        }
    }

    public List<String> getErrors() {
        return errors;
    }
}
