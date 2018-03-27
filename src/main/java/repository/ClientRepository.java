package repository;

import model.Client;

import java.util.List;

public interface ClientRepository {
    boolean addClient(Client client);
    boolean updateClient(Client client);
    boolean dropAllClients();
    List<Client> findAllClients();
    Client findClientById(int id);
}
