package database;

import java.util.*;

import static database.Constants.Rights.*;
import static database.Constants.Roles.*;

public class Constants {
    public static class Roles {
        public static final String ADMINISTRATOR = "administrator";
        public static final String USER = "user";

        public static final String[] ROLES = new String[]{ADMINISTRATOR, USER};
    }

    public static class Rights {
        public static final String CREATE_CLIENT = "create_client";
        public static final String DELETE_CLIENT = "delete_client";
        public static final String UPDATE_CLIENT = "update_client";

        public static final String CREATE_USER = "create_user";
        public static final String DELETE_USER = "delete_user";
        public static final String UPDATE_USER = "update_user";

        public static final String CREATE_ACCOUNT = "create_account";
        public static final String DELETE_ACCOUNT = "delete_account";
        public static final String UPDATE_ACCOUNT = "update_account";

        public static final String MAKE_TRANSFER = "make_transfer";
        public static final String PAY_BILL = "pay_bill";
        public static final String VIEW_ACTIVITY = "view_activity";

        public static final String[] RIGHTS = new String[]{MAKE_TRANSFER, PAY_BILL, VIEW_ACTIVITY, UPDATE_USER, CREATE_USER, DELETE_USER, CREATE_CLIENT, DELETE_CLIENT, UPDATE_CLIENT, CREATE_ACCOUNT, DELETE_ACCOUNT, UPDATE_ACCOUNT};
    }

    public static Map<String, List<String>> getRolesRights() {
        Map<String, List<String>> ROLES_RIGHTS = new HashMap<>();
        for (String role : ROLES) {
            ROLES_RIGHTS.put(role, new ArrayList<>());
        }
        ROLES_RIGHTS.get(ADMINISTRATOR).addAll(Arrays.asList(RIGHTS));

        ROLES_RIGHTS.get(USER).addAll(Arrays.asList(CREATE_CLIENT, DELETE_CLIENT, UPDATE_CLIENT, CREATE_ACCOUNT, DELETE_ACCOUNT, UPDATE_ACCOUNT, MAKE_TRANSFER, PAY_BILL, VIEW_ACTIVITY));


        return ROLES_RIGHTS;
    }
}
