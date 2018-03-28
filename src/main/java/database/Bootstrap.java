package database;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static database.Schema.TEST;
import static database.TableName.*;

public class Bootstrap {
/*
*   USER,
    ROLE,
    CLIENT,
    TRANSFER,
    ACCOUNT,
    RIGHT,
    ROLE_RIGHT,
    USER_ROLE
* */
    private static final TableName[] ORDERED_TABLES = new TableName[]{CLIENT, USER, TRANSFER, ACCOUNT};//ordered tables for creation
    private static final List<Schema> schemasToBootstrap = new ArrayList<Schema>(Arrays.asList(TEST));
    public static void main(String[] args) throws SQLException {

        dropAll(schemasToBootstrap);

        bootstrapTables(schemasToBootstrap);

        bootstrapData(schemasToBootstrap);
    }


    private static void bootstrapData(List<Schema> schemas) {
        //Orderd is important. TODO find a way to improve this
        bootstrapClientData(schemas);
        bootstrapUserData(schemas);
        bootstrapTransferData(schemas);
        bootstrapAccountData(schemas);
    }

    private static void bootstrapAccountData(List<Schema> schemas) {

    }

    private static void bootstrapTransferData(List<Schema> schemas) {
    }

    private static void bootstrapUserData(List<Schema> schemas) {
    }

    private static void bootstrapClientData(List<Schema> schemas) {
        
    }

    private static void bootstrapTables(List<Schema> schemas)  throws SQLException {
        for (Schema schema : schemas) {
            System.out.println("Dropping all tables in schema: " + schema);
            Connection connection = new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(schema)).getConnection();
            Statement statement = connection.createStatement();

            for (TableName tableName : ORDERED_TABLES){
                String createTableSQL = SQLTableFactory.getCreateSQLForTable(tableName);
                statement.execute(createTableSQL);
            }
        }
        System.out.println("Done creating tables");
    }

    private static void dropAll(List<Schema> schemas) throws SQLException {
        for (Schema schema : schemas) {
            System.out.println("Dropping all tables in schema: " + schema);

            Connection connection = new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(schema)).getConnection();
            Statement statement = connection.createStatement();

            String dropSQL = "TRUNCATE `Account`; \n" +
                            "DROP TABLE `Account`; \n" +
                            "TRUNCATE `Transfer`; \n" +
                            "DROP TABLE `Transfer`; \n" +
                            "TRUNCATE `User`; \n" +
                            "DROP TABLE `User`; \n" +
                            "TRUNCATE `Client`; \n" +
                            "DROP TABLE  `Client`;";

            statement.execute(dropSQL);
        }

        System.out.println("Done dropping tables");
    }
}
