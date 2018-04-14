package view;

import view.OperationView.*;

public class OperationViewFactory {
    private static OperationViewFactory instance;

    private final AccountOperationsView accountOperationsView;
    private final ClientOperationsView clientOperationsView;
    private final MiscOperationsView miscOperationsView;
    private final UserOperationsView userOperationsView;



    public static OperationViewFactory instance() {
        if (instance == null) {
            instance = new OperationViewFactory();
        }
        return instance;
    }

    private OperationViewFactory(){
        accountOperationsView = new AccountOperationsView();
        clientOperationsView = new ClientOperationsView();
        miscOperationsView = new MiscOperationsView();
        userOperationsView = new UserOperationsView();
    }


    public AccountOperationsView getAccountOperationsView() {
        return accountOperationsView;
    }

    public ClientOperationsView getClientOperationsView() {
        return clientOperationsView;
    }

    public MiscOperationsView getMiscOperationsView() {
        return miscOperationsView;
    }

    public UserOperationsView getUserOperationsView() {
        return userOperationsView;
    }


}
