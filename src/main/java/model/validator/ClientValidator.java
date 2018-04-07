package model.validator;

import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ClientValidator {
    private static final String CLIENT_NAME_REGEX = "[a-zA-Z]";
    private static final String PERSONAL_NUMERICAL_CODE_REGEX = "[1-6][0-9]{9}";
    private static final String IDENTITY_CARD_NUMBER_REGEX = "[0-9]{4}";

    private List<String> errors;


    public boolean validate(Client client){
        errors = new ArrayList<>();
        validateName(client.getName());
        validatePersonalNumericalCode(client.getPersonalNumericalCode());
        validateIdentityCardNumber(client.getIdentityCardNumber());
        validateAddress(client.getAddress());
        return errors.isEmpty();
    }

    private void validateAddress(String address) {
        //TODO
    }

    private void validateName(String name){
//        if (!Pattern.compile(CLIENT_NAME_REGEX).matcher(name).matches()) {
//            errors.add("Invalid name!");
//        }
    }

    private void validatePersonalNumericalCode(String personalNumericalCode){
        if (!Pattern.compile(PERSONAL_NUMERICAL_CODE_REGEX).matcher(personalNumericalCode).matches()) {
            errors.add("Invalid personal numerical code!");
        }
    }


    private void validateIdentityCardNumber(String identityCardNumber){
        if (!Pattern.compile(IDENTITY_CARD_NUMBER_REGEX).matcher(identityCardNumber).matches()) {
            errors.add("Invalid identity card number!");
        }
    }

    public List<String> getErrors() {
        return errors;
    }
}
