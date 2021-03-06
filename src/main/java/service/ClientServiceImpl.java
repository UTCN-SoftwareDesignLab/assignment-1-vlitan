package service;

import model.Client;
import model.validator.AccountValidator;
import model.validator.ClientValidator;
import model.validator.Notification;
import repository.bank.AccountRepository;
import repository.bank.ClientRepository;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Notification<Boolean> add(Client client) {
        ClientValidator validator = new ClientValidator();
        Notification<Boolean> addNotification = new Notification<>();
        boolean isValid = validator.validate(client);

        if (isValid) {
            clientRepository.add(client);
            addNotification.setResult(Boolean.TRUE);
        } else {
            validator.getErrors().forEach(addNotification::addError);
            addNotification.setResult(Boolean.FALSE);
        }
        return addNotification;
    }

    @Override
    public Notification<Boolean> delete(Client client) {
        ClientValidator validator = new ClientValidator();
        Notification<Boolean> deleteNotification = new Notification<>();
        boolean isValid = validator.validate(client);

        if (isValid) {
            clientRepository.deleteById(client);
            deleteNotification.setResult(Boolean.TRUE);
        } else {
            validator.getErrors().forEach(deleteNotification::addError);
            deleteNotification.setResult(Boolean.FALSE);
        }
        return deleteNotification;
    }

    @Override
    public Notification<Boolean> update(Client client) {

        ClientValidator validator = new ClientValidator();
        Notification<Boolean> updateNotification = new Notification<>();
        boolean isValid = validator.validate(client);

        if (isValid) {
            clientRepository.update(client);
            updateNotification.setResult(Boolean.TRUE);
        } else {
            validator.getErrors().forEach(updateNotification::addError);
            updateNotification.setResult(Boolean.FALSE);
        }
        return updateNotification;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
