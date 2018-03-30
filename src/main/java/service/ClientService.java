package service;

import model.Client;
import model.validator.Notification;

import java.util.List;

public interface ClientService {
    public Notification<Boolean> add(Client client);
    public Notification<Boolean> delete(Client client);
    public Notification<Boolean> update(Client client);
    public List<Client> findAll();
}
