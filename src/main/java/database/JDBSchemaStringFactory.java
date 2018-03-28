package database;

public class JDBSchemaStringFactory {
    //TODO refactor this into ConnectionFactory
    //also, https://twitter.com/nixcraft/status/973112128570667008
    private static final String PRODUCTION_SCHEMA_STRING = "bank";
    private static final String TEST_SCHEMA_STRING = "bank_test";

    public static String getSchemaString(Schema schema){
        switch (schema) {
            case PRODUCTION:
                return PRODUCTION_SCHEMA_STRING;
            case TEST:
                return TEST_SCHEMA_STRING;
            default:
                return "";
        }
    }
}
