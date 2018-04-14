package view;


import java.awt.*;

public class UserView extends BaseView {

    public UserView() throws HeadlessException {
        super();
        System.out.println("user view created");
    }

    @Override
     void addComponents() {
        OperationViewFactory operationViewFactory = OperationViewFactory.instance();
        this.getTpOperations().add("account", operationViewFactory.getAccountOperationsView());
        this.getTpOperations().add("client", operationViewFactory.getClientOperationsView());
        this.getTpOperations().add("misc", operationViewFactory.getMiscOperationsView());
        System.out.println("tab count " + this.getTpOperations().getTabCount());
    }







}
