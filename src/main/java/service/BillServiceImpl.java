package service;

import model.validator.Notification;

public class BillServiceImpl implements  BillService{
    @Override
    public Notification<Boolean> payBill() {
        return (new Notification<Boolean>());
    }
}
