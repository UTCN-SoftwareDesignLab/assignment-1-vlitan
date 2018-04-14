package view;

import java.awt.*;


public class AdminView extends BaseView {

    public AdminView() throws HeadlessException {
        super();
    }
@Override
     void addComponents() {
        OperationViewFactory operationViewFactory = OperationViewFactory.instance();
        this.getTpOperations().add("account", operationViewFactory.getAccountOperationsView());
        this.getTpOperations().add("client", operationViewFactory.getClientOperationsView());
        this.getTpOperations().add("misc", operationViewFactory.getMiscOperationsView());
        this.getTpOperations().add("user", operationViewFactory.getUserOperationsView());
    }
}
