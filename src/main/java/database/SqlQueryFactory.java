package database;

import static database.Constants.Rights.*;
import static database.Constants.Roles.ADMINISTRATOR;
import static database.Constants.Roles.USER;

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
                    "  `password` VARCHAR(100) NOT NULL,\n" +
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
            case ACTION: return
                    "CREATE TABLE IF NOT EXISTS `Action` (\n" +
                            "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `description` VARCHAR(45) NOT NULL,\n" +
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
                            "CREATE INDEX `fk_Transfer_User1_idx` ON `Action` (`User_id` ASC);";
            case ROLE:
                return "  CREATE TABLE IF NOT EXISTS role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  UNIQUE INDEX role_UNIQUE (role ASC));";

            case RIGHT:
                return "  CREATE TABLE IF NOT EXISTS `right` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `right` VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (`id`)," +
                        "  UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                        "  UNIQUE INDEX `right_UNIQUE` (`right` ASC));";

            case ROLE_RIGHT:
                return "  CREATE TABLE IF NOT EXISTS role_right (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role_id INT NOT NULL," +
                        "  right_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  INDEX right_id_idx (right_id ASC)," +
                        "  CONSTRAINT role_id" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT right_id" +
                        "    FOREIGN KEY (right_id)" +
                        "    REFERENCES `right` (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

            case USER_ROLE:
                return "\tCREATE TABLE IF NOT EXISTS user_role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  user_id INT NOT NULL," +
                        "  role_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX user_id_idx (user_id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  CONSTRAINT user_fkid" +
                        "    FOREIGN KEY (user_id)" +
                        "    REFERENCES User (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT role_fkid" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

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
            case ACTION: return
                    "INSERT INTO `Action` (`description`, `date`,`User_id`) VALUES ('mock description1','1901-03-02',2);\n" +
                    "INSERT INTO `Action` (`description`, `date`,`User_id`) VALUES ('mock description2','1931-03-02',2);\n" +
                    "INSERT INTO `Action` (`description`, `date`,`User_id`) VALUES ('mock description2','1999-03-02',2);\n" +
                    "INSERT INTO `Action` (`description`, `date`,`User_id`) VALUES ('mock description3','1981-03-02',1);\n";
//            case ROLE:return
//                    "INSERT INTO `role`(`id`,`role`)VALUES(null,'"+ADMINISTRATOR+"';\n"+
//                    "INSERT INTO `role`(`id`,`role`)VALUES(null,'"+USER+"';\n";
//            case RIGHT:return
//                    "INSERT INTO `right`(`id`,`right`)VALUES(null,'"+CREATE_CLIENT+"';\n"+
//                    "INSERT INTO `right`(`id`,`right`)VALUES(null,'"+DELETE_CLIENT+"';\n"+
//                    "INSERT INTO `right`(`id`,`right`)VALUES(null,'"+UPDATE_CLIENT+"';\n"+
//                    "INSERT INTO `right`(`id`,`right`)VALUES(null,'"+CREATE_USER+"';\n"+
//                    "INSERT INTO `right`(`id`,`right`)VALUES(null,'"+DELETE_USER+"';\n"+
//                    "INSERT INTO `right`(`id`,`right`)VALUES(null,'"+UPDATE_USER+"';\n"+
//                    "INSERT INTO `right`(`id`,`right`)VALUES(null,'"+CREATE_ACCOUNT+"';\n"+
//                    "INSERT INTO `right`(`id`,`right`)VALUES(null,'"+DELETE_ACCOUNT+"';\n"+
//                    "INSERT INTO `right`(`id`,`right`)VALUES(null,'"+UPDATE_ACCOUNT+"';\n"+
//                    "INSERT INTO `right`(`id`,`right`)VALUES(null,'"+MAKE_TRANSFER+"';\n"+
//                    "INSERT INTO `right`(`id`,`right`)VALUES(null,'"+PAY_BILL+"';\n";
//            case ROLE_RIGHT:return
//                    "INSERT INTO `role_right`(`id`,`role`,`right`)VALUES(null,'"+ADMINISTRATOR+"','" +MAKE_TRANSFER+"';\n"+
//                    "INSERT INTO `role_right`(`id`,`role`,`right`)VALUES(null,'"+PAY_BILL+"';\n";
//            case USER_ROLE:return  "SELECT 1;";

            default:
                return  "SELECT 1;";

        }
    }
}
