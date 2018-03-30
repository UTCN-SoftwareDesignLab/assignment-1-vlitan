package service;

import model.validator.Notification;

public interface BillService {
    public Notification<Boolean> payBill();
}
