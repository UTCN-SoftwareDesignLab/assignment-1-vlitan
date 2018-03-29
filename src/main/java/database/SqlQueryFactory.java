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
                            "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `account_type` VARCHAR(45) NOT NULL,\n" +
                            "  `amount` INT(11) NOT NULL,\n" +
                            "  `creation_date` DATE NOT NULL,\n" +
                            "  `Client_id` INT(11) NOT NULL,\n" +
                            "  PRIMARY KEY (`id`, `Client_id`),\n" +
                            "  CONSTRAINT `fk_Account_Client1`\n" +
                            "    FOREIGN KEY (`Client_id`)\n" +
                            "    REFERENCES `Client` (`id`)\n" +
                            "    ON DELETE NO ACTION\n" +
                            "    ON UPDATE NO ACTION)\n" +
                            "ENGINE = InnoDB\n" +
                            "DEFAULT CHARACTER SET = latin1;\n" +
                            "\n" +
                            "CREATE INDEX `fk_Account_Client1_idx` ON `Account` (`Client_id` ASC);";
            case TRANSFER: return
                    "CREATE TABLE IF NOT EXISTS `Transfer` (\n" +
                            "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `amount` INT(11) NOT NULL,\n" +
                            "  `source_account_id` INT(11) NOT NULL,\n" +
                            "  `destination_account_id` INT(11) NOT NULL,\n" +
                            "  `date` DATE NOT NULL,\n" +
                            "  `User_id` INT(11) NOT NULL,\n" +
                            "  PRIMARY KEY (`id`, `User_id`),\n" +
                            "  CONSTRAINT `fk_Transfer_User1`\n" +
                            "    FOREIGN KEY (`User_id`)\n" +
                            "    REFERENCES `User` (`id`)\n" +
                            "    ON DELETE NO ACTION\n" +
                            "    ON UPDATE NO ACTION)\n" +
                            "ENGINE = InnoDB\n" +
                            "DEFAULT CHARACTER SET = latin1;\n" +
                            "\n" +
                            "CREATE INDEX `fk_Transfer_User1_idx` ON `Transfer` (`User_id` ASC);";
            case ACCOUNT_HAS_TRANSFER : return
                    "CREATE TABLE IF NOT EXISTS `Account_has_Transfer` (\n" +
                            "  `Account_id` INT(11) NOT NULL,\n" +
                            "  `Account_Client_id` INT(11) NOT NULL,\n" +
                            "  `Transfer_id` INT(11) NOT NULL,\n" +
                            "  `Transfer_User_id` INT(11) NOT NULL,\n" +
                            "  PRIMARY KEY (`Account_id`, `Account_Client_id`, `Transfer_id`, `Transfer_User_id`),\n" +
                            "  CONSTRAINT `fk_Account_has_Transfer_Account1`\n" +
                            "    FOREIGN KEY (`Account_id` , `Account_Client_id`)\n" +
                            "    REFERENCES `Account` (`id` , `Client_id`)\n" +
                            "    ON DELETE NO ACTION\n" +
                            "    ON UPDATE NO ACTION,\n" +
                            "  CONSTRAINT `fk_Account_has_Transfer_Transfer1`\n" +
                            "    FOREIGN KEY (`Transfer_id` , `Transfer_User_id`)\n" +
                            "    REFERENCES `Transfer` (`id` , `User_id`)\n" +
                            "    ON DELETE NO ACTION\n" +
                            "    ON UPDATE NO ACTION)\n" +
                            "ENGINE = InnoDB\n" +
                            "DEFAULT CHARACTER SET = latin1;\n" +
                            "\n" +
                            "CREATE INDEX `fk_Account_has_Transfer_Transfer1_idx` ON `Account_has_Transfer` (`Transfer_id` ASC, `Transfer_User_id` ASC);\n" +
                            "\n" +
                            "CREATE INDEX `fk_Account_has_Transfer_Account1_idx` ON `Account_has_Transfer` (`Account_id` ASC, `Account_Client_id` ASC);";

            default:
                return  "SELECT 1;";

        }
    }


    public static String getInsertDataSQL(TableName table) {
        switch (table) {
            case CLIENT: return
                    "INSERT INTO `Client` (`name`, `identity_card_number`, `personal_numerical_code`,`address`)\n" +
                    " VALUES\n"+
                    "('client-name1', 'identity1', 'personalnb1', 'address1');\n"+
                    "INSERT INTO `Client` (`name`, `identity_card_number`, `personal_numerical_code`,`address`)\n" +
                    " VALUES\n"+
                    "('client-name2', 'identity2', 'personalnb2', 'address2');\n"+
                    "INSERT INTO `Client` (`name`, `identity_card_number`, `personal_numerical_code`,`address`)\n" +
                    " VALUES\n"+
                    "('client-name3', 'identity3', 'personalnb3', 'address3');\n";

            case USER: return
                    "INSERT INTO `User` (`username`, `password`) VALUES ('user-andrei', 'pass1');\n" +
                    "INSERT INTO `User` (`username`, `password`) VALUES ('user-mihai', 'pass2');\n" +
                    "INSERT INTO `User` (`username`, `password`) VALUES ('user-pavel', 'pass3');\n" +
                    "INSERT INTO `User` (`username`, `password`) VALUES ('user-chinezu', 'pass4');";

            case ACCOUNT: return
                    "INSERT INTO `Account` (`account_type`,`amount`,`creation_date`,`Client_id`)VALUES('CREDIT',2,'1901-03-02',1);\n" +
                    "INSERT INTO `Account` (`account_type`,`amount`,`creation_date`,`Client_id`)VALUES('DEBIT',43,'1944-03-02',1);\n" +
                    "INSERT INTO `Account` (`account_type`,`amount`,`creation_date`,`Client_id`)VALUES('CREDIT',5,'1905-03-02',2);";
            case TRANSFER: return "SELECT 1;";
            default:
                return  "SELECT 1;";

        }
    }
}
