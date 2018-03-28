package database;

public class SqlQueryFactory {
    public static String getCreateSQLForTable(TableName table) {
        switch (table) {
            case CLIENT: return
                    "CREATE TABLE IF NOT EXISTS `Client` (\n" +
                            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                            "  `name` VARCHAR(45) NOT NULL,\n" +
                            "  `identity_card_number` VARCHAR(45) NOT NULL,\n" +
                            "  `personal_numerical_code` VARCHAR(45) NOT NULL,\n" +
                            "  `address` VARCHAR(45) NOT NULL,\n" +
                            "  PRIMARY KEY (`id`))\n" +
                            "ENGINE = InnoDB;";
            case USER: return "CREATE TABLE IF NOT EXISTS `User` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` VARCHAR(45) NOT NULL,\n" +
                    "  `password` VARCHAR(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB;";
            case ACCOUNT: return
                    "CREATE TABLE IF NOT EXISTS `Account` (\n" +
                            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                            "  `account_type` VARCHAR(45) NOT NULL,\n" +
                            "  `amount` INT NOT NULL,\n" +
                            "  `creation_date` DATE NOT NULL,\n" +
                            "  `Client_id` INT NOT NULL,\n" +
                            "  `Transfer_id` INT NOT NULL,\n" +
                            "  PRIMARY KEY (`id`, `Client_id`, `Transfer_id`),\n" +
                            "  CONSTRAINT `fk_Account_Client1`\n" +
                            "    FOREIGN KEY (`Client_id`)\n" +
                            "    REFERENCES `Client` (`id`)\n" +
                            "    ON DELETE NO ACTION\n" +
                            "    ON UPDATE NO ACTION,\n" +
                            "  CONSTRAINT `fk_Account_Transfer1`\n" +
                            "    FOREIGN KEY (`Transfer_id`)\n" +
                            "    REFERENCES `Transfer` (`id`)\n" +
                            "    ON DELETE NO ACTION\n" +
                            "    ON UPDATE NO ACTION)\n" +
                            "ENGINE = InnoDB;\n" +
                            "\n" +
                            "CREATE INDEX `fk_Account_Client1_idx` ON `Account` (`Client_id` ASC);\n" +
                            "\n" +
                            "CREATE INDEX `fk_Account_Transfer1_idx` ON `Account` (`Transfer_id` ASC);\n";
            case TRANSFER: return
                    "CREATE TABLE IF NOT EXISTS `Transfer` (\n" +
                            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                            "  `amount` INT NOT NULL,\n" +
                            "  `source_account_id` INT NOT NULL,\n" +
                            "  `destination_account_id` INT NOT NULL,\n" +
                            "  `date` DATE NOT NULL,\n" +
                            "  `User_id` INT NOT NULL,\n" +
                            "  PRIMARY KEY (`id`, `User_id`),\n" +
                            "  CONSTRAINT `fk_Transfer_User1`\n" +
                            "    FOREIGN KEY (`User_id`)\n" +
                            "    REFERENCES `User` (`id`)\n" +
                            "    ON DELETE NO ACTION\n" +
                            "    ON UPDATE NO ACTION)\n" +
                            "ENGINE = InnoDB;\n" +
                            "\n" +
                            "CREATE INDEX `fk_Transfer_User1_idx` ON `Transfer` (`User_id` ASC);\n";
            default:
                return  "SELECT 1;";

        }
    }


    public static String getInsertDataSQL(TableName table) {
        switch (table) {
            case CLIENT: return "SELECT 1;";
            case USER: return
                    "INSERT INTO `User` (`username`, `password`) VALUES ('user-andrei', 'pass1');\n" +
                    "INSERT INTO `User` (`username`, `password`) VALUES ('user-mihai', 'pass2');\n" +
                    "INSERT INTO `User` (`username`, `password`) VALUES ('user-pavel', 'pass3');\n" +
                    "INSERT INTO `User` (`username`, `password`) VALUES ('user-chinezu', 'pass4');";
            case ACCOUNT: return "SELECT 1;";
            case TRANSFER: return "SELECT 1;";
            default:
                return  "SELECT 1;";

        }
    }
}
