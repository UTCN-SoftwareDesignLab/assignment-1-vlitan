package database;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ConnectionTester {
    private static JDBConnectionWrapper connectionWrapper;

    @BeforeClass
    public static void setupClass() throws Exception{
        connectionWrapper = new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(Schema.TEST));
    }

    @Test
    public void connectionCreated() throws Exception{
        assertNotNull(connectionWrapper);
    }

    @Test
    public void connectionSuccessful() throws Exception {
        assertTrue(connectionWrapper.testConnection());
    }
}
