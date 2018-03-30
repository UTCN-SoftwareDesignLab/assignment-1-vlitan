package repository.bank;

import model.Client;

import java.util.List;

public interface ClientRepository {
    boolean add(Client client);
    boolean update(Client client);
    List<Client> findAll();
    boolean deleteById(Client client);
    Client findById(int id);
}
