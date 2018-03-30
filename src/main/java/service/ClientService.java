package service;

import model.Client;

import java.util.List;

public interface ClientService {
    public boolean add(Client client);
    public boolean delete(Client client);
    public boolean update(Client client);
    public List<Client> findAll();
}
