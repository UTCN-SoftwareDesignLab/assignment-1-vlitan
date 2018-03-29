package repository;

import model.Client;

import java.util.List;

public interface ClientRepository {
    boolean addClient(Client client);
    boolean updateClient(Client client);
    List<Client> findAllClients();
    boolean deleteClientById(Client client);
    Client findClientById(int id);
}
