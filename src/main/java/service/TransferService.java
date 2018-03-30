package service;

import model.Transfer;
import model.validator.Notification;

public interface TransferService {
    public Notification<Boolean> makeTransfer(Transfer transfer);

}
