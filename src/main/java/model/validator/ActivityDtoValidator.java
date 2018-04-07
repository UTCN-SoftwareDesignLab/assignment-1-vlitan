package model.validator;

import model.Account;
import model.ListActivityDTO;

import java.util.ArrayList;
import java.util.List;

public class ActivityDtoValidator {
    private static final int MIN_AMOUNT_OF_MONEY = 0;

    private List<String> errors;

    public ActivityDtoValidator(){
    }

    public boolean validate(ListActivityDTO listActivityDTO){
        errors = new ArrayList<>();
        validateDates(listActivityDTO);
        return errors.isEmpty();
    }

    private void validateDates(ListActivityDTO listActivityDTO){
        if (listActivityDTO.getStartDate().compareTo(listActivityDTO.getEndDate()) < 0){
            errors.add("start date cant be older the end date!");
        }
    }


    public List<String> getErrors() {
        return errors;
    }

}
