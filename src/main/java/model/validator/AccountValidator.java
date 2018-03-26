package model.validator;

import model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountValidator {
    private static final int MIN_AMOUNT_OF_MONEY = 0;

    private List<String> errors;

    public AccountValidator(){
    }

    public boolean validate(Account account){
        errors = new ArrayList<>();
        validateAmount(account.getAmount());
        return errors.isEmpty();
    }

    public void validateAmount(int amount){
        if (amount < MIN_AMOUNT_OF_MONEY) {
            errors.add(String.format("account can`t an amount of money less than %d", MIN_AMOUNT_OF_MONEY));
        }
    }

    public List<String> getErrors() {
        return errors;
    }

}
