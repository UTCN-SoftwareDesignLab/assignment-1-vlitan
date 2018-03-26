package model.validator;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String USERNAME_REGEX = "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+$";
    private static final int MIN_PASSWORD_LENGTH = 8;

    private List<String> errors;

    public UserValidator(){
    }

    public boolean validate(User user){
        errors = new ArrayList<>();
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
        return errors.isEmpty();
    }

    private void validateUsername(String username) {
        if (!Pattern.compile(USERNAME_REGEX).matcher(username).matches()) {
            errors.add("Invalid username!");
        }
    }

    private void validatePassword(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH) {
            errors.add("Password too short!");
        }
        if (!containsSpecialCharacter(password)) {
            errors.add("Password must contain at least one special character!");
        }
        if (!containsDigit(password)) {
            errors.add("Password must contain at least one number!");
        }
    }

    private boolean containsSpecialCharacter(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        return m.find();
    }

    private boolean containsDigit(String s) {
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getErrors() {
        return errors;
    }
}
