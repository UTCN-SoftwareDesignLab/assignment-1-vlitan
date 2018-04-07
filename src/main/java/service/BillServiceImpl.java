package service;

import model.validator.Notification;
import repository.bank.ActionRepository;

public class BillServiceImpl implements  BillService{
    private ActionRepository actionRepository;

    public BillServiceImpl(ActionRepository actionRepository){
        this.actionRepository = actionRepository;
    }

    @Override
    public Notification<Boolean> payBill() {
        Notification<Boolean> billNotification = new Notification<>();
        billNotification.setResult(Boolean.TRUE);
        return billNotification;
    }
}
