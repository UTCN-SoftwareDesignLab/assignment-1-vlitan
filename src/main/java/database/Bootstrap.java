package database;

import com.sun.org.apache.bcel.internal.generic.TargetLostException;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
    private static final TableName[] ORDERED_TABLES = new TableName[]{CLIENT, USER, ACTION, ACCOUNT,  ROLE, RIGHT, ROLE_RIGHT, USER_ROLE};//ordered tables for creation
    private static final List<Schema> schemasToBootstrap = new ArrayList<Schema>(Arrays.asList(TEST));
    public static void main(String[] args) throws SQLException {
        for (Schema schema : schemasToBootstrap) {
            dropAll(schema);
            bootstrapTables(schema);
            bootstrapData(schema);
        }
    }


    private static void bootstrapData(Schema schema) throws SQLException{
        System.out.println("[Bootstrap] Populating all tables in schema: " + schema);
        Connection connection = new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(schema)).getConnection();
        Statement statement = connection.createStatement();

        for (TableName tableName : ORDERED_TABLES){
            String createTableSQL = SqlQueryFactory.getInsertDataSQL(tableName);
            statement.execute(createTableSQL);
        }
    }

    private static void bootstrapTables(Schema schema)  throws SQLException {
        System.out.println("[Bootstrap] Creating all tables in schema: " + schema);
        Connection connection = new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(schema)).getConnection();
        Statement statement = connection.createStatement();

        for (TableName tableName : ORDERED_TABLES) {
            String createTableSQL = SqlQueryFactory.getCreateSQLForTable(tableName);
            statement.execute(createTableSQL);
        }
    }

    private static void dropAll(Schema schema) throws SQLException {
        System.out.println("[Bootstrap] Dropping all tables in schema: " + schema);

        Connection connection = new JDBConnectionWrapper(JDBSchemaStringFactory.getSchemaString(schema)).getConnection();
        Statement statement = connection.createStatement();

        String dropSQL =
                        "TRUNCATE `role_right`; \n" +
                        "DROP TABLE `role_right`; \n" +
                        "TRUNCATE `right`; \n" +
                        "DROP TABLE `right`; \n" +
                        "TRUNCATE `user_role`; \n" +
                        "DROP TABLE `user_role`; \n" +
                        "TRUNCATE `Account`; \n" +
                        "DROP TABLE `Account`; \n" +
                        "TRUNCATE `Action`; \n" +
                        "DROP TABLE `Action`; \n" +
                        "TRUNCATE `User`; \n" +
                        "DROP TABLE `User`; \n" +
                        "TRUNCATE `Client`; \n" +
                        "DROP TABLE  `Client`;\n"+
                        "TRUNCATE `role`; \n";

        statement.execute(dropSQL);
    }
}
