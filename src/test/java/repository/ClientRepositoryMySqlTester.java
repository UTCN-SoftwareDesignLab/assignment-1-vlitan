package repository;

import database.Bootstrap;
import database.JDBConnectionWrapper;
import database.JDBSchemaStringFactory;
import model.Client;
import model.builder.ClientBuilder;
import model.builder.UserBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static database.Schema.TEST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ClientRepositoryMySqlTester {
    private static ClientRepositoryMySql repository;
    private static final int INITIAL_COUNT = 3;

    @BeforeClass
    public static void setupClass()  {
        repository = new ClientRepositoryMySql(new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(TEST)));
    }

    @Before
    public void bootstrap()throws Exception{
        Bootstrap.main(null) ; // TODO add parameters to this
    }
    //TODO make this check against constants and insert also those constants in bootstrap
    @Test
    public void testSelect()throws Exception{
        List<Client> clients = repository.findAllClients();
        assertTrue(clients.size() == INITIAL_COUNT);
    }

    @Test
    public void testFindClientById(){
        int id = 2;
        Client client = repository.findClientById(id);
        assertEquals(client.getId(), id);
        assertEquals(client.getAddress(), "address2");
        assertEquals(client.getName(), "client-name2");
    }

    @Test
    public void testUpdateClient(){
        String newAddress = "new address";
        Client client = repository.findClientById(2);
        client.setAddress(newAddress);
        repository.updateClient(client);
        client = repository.findClientById(2);
        assertEquals(client.getAddress(), newAddress);
    }
//TODO
//    @Test
//    public void testDeleteClient(){
//        repository.deleteClientById(repository.findClientById(2));
//        assertEquals(repository.findClientById(2).getId(), 0);
//    }

    @Test
    public void testInsertClient(){
        List<Client> clients = repository.findAllClients();
        repository.addClient((new ClientBuilder())
                .setName("logofat")
                .setAddress("america")
                .setPersonalNumericalCode("123321")
                .setIdentityCardNumber("789f987")
                .build());
        Client user = repository.findClientById(clients.size() + 1);
        assertNotNull(user);
    }
}
