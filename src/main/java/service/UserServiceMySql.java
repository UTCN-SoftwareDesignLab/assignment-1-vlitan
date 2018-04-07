package service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.User;
import model.validator.ClientValidator;
import model.validator.Notification;
import model.validator.UserValidator;
import repository.bank.ClientRepository;
import repository.bank.UserRepository;
import service.UserService;

import java.util.List;

public class UserServiceMySql implements UserService{

    private UserRepository userRepository;

    public UserServiceMySql(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Notification<Boolean> add(User user) {
        UserValidator validator = new UserValidator();
        Notification<Boolean> addNotification = new Notification<>();
        boolean isValid = validator.validate(user);

        if (isValid) {
            userRepository.add(user);

            addNotification.setResult(Boolean.TRUE);
        } else {
            validator.getErrors().forEach(addNotification::addError);
            addNotification.setResult(Boolean.FALSE);
        }
        return addNotification;
    }

    @Override
    public Notification<Boolean> update(User user) {
        UserValidator validator = new UserValidator();
        Notification<Boolean> updateNotification = new Notification<>();
        boolean isValid = validator.validate(user);

        if (isValid) {
            userRepository.update(user);

            updateNotification.setResult(Boolean.TRUE);
        } else {
            validator.getErrors().forEach(updateNotification::addError);
            updateNotification.setResult(Boolean.FALSE);
        }
        return updateNotification;
    }

    @Override
    public Notification<Boolean> deleteById(User user) {
        UserValidator validator = new UserValidator();
        Notification<Boolean> deleteNotification = new Notification<>();
        boolean isValid = validator.validate(user);

        if (isValid) {
            userRepository.deleteById(user);
            deleteNotification.setResult(Boolean.TRUE);
        } else {
            validator.getErrors().forEach(deleteNotification::addError);
            deleteNotification.setResult(Boolean.FALSE);
        }
        return deleteNotification;
    }

    @Override
    public Notification<User> findById(User user) {
        UserValidator validator = new UserValidator();
        Notification<User> findNotification = new Notification<>();
        boolean found = !userRepository.findById(user.getId()).hasErrors();

        if (found) {
            findNotification.setResult( userRepository.findById(user.getId()).getResult());
        } else {
            validator.getErrors().forEach(findNotification::addError);
        }
        return findNotification;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
