//package controller.OperationsController;
//
//import model.User;
//import view.OperationViewFactory;
//
//public class OperationControllerFactory {
//    private static OperationControllerFactory instance;
//
//    private final AccountOperationsController accountOperationsController;
//    private final ClientOperationsController clientOperationsController;
//    private final MiscOperationsController miscOperationsController;
//    private final UserOperationsController userOperationsController;
//
//    public static OperationControllerFactory instance(User user) {
//        if (instance == null) {
//            instance = new OperationControllerFactory(user);
//        }
//        return instance;
//    }
//
//    private OperationControllerFactory(User user){
//        ComponentFactory componentFactory = ComponentFactory.instance();
//        OperationViewFactory operationViewFactory = OperationViewFactory.instance();
//        accountOperationsController = new AccountOperationsController(user, operationViewFactory.getAccountOperationsView(),
//                componentFactory.getRoleRightsService(),
//                componentFactory.getAccountService(),
//                componentFactory.getActionService());
//        clientOperationsController = new ClientOperationsController(user, operationViewFactory.getClientOperationsView(),
//                componentFactory.getRoleRightsService(),
//                componentFactory.getClientService(),
//                componentFactory.getActionService());
//        miscOperationsController = new MiscOperationsController(user, operationViewFactory.getMiscOperationsView(),
//                componentFactory.getRoleRightsService(),
//                componentFactory.getAccountService(),
//                componentFactory.getTransferService(),
//                componentFactory.getActionService());
//        userOperationsController = new UserOperationsController(user, operationViewFactory.getUserOperationsView(),
//                componentFactory.getRoleRightsService(),
//                componentFactory.getUserService(),
//                componentFactory.getActionService());
//    }
//
//    public AccountOperationsController getAccountOperationsController() {
//        return accountOperationsController;
//    }
//
//    public ClientOperationsController getClientOperationsController() {
//        return clientOperationsController;
//    }
//
//    public MiscOperationsController getMiscOperationsController() {
//        return miscOperationsController;
//    }
//
//    public UserOperationsController getUserOperationsController() {
//        return userOperationsController;
//    }
//}
