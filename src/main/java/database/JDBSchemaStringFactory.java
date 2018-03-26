package database;

public class JDBSchemaStringFactory {
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
