package service;

import database.JDBConnectionWrapper;
import model.Account;
import model.Transfer;
import model.validator.Notification;
import model.validator.TransferValidator;
import repository.bank.AccountRepository;
import repository.bank.AccountRepositoryMySql;
import repository.bank.ActionRepository;

public class TransferServiceImpl implements TransferService{
    private AccountRepository accountRepository;
    private ActionRepository actionRepository;

    public TransferServiceImpl(AccountRepository accountRepository, ActionRepository actionRepository){
        this.accountRepository = accountRepository;
        this.actionRepository = actionRepository;
    }

    @Override
    public Notification<Boolean> makeTransfer(Transfer transfer) {
        TransferValidator transferValidator = new TransferValidator();
        Notification<Boolean> madeTransferNotification = new Notification<>();
        boolean isValid = transferValidator.validate(transfer);
        if (isValid){
            executeTransfer(transfer);
            actionRepository.add(transfer);
            madeTransferNotification.setResult(Boolean.TRUE);
        }
        else{
            transferValidator.getErrors().forEach(madeTransferNotification::addError);
            madeTransferNotification.setResult(Boolean.FALSE);
        }
        return madeTransferNotification;
    }

    private void executeTransfer(Transfer transfer){
        Account sourceAccount = transfer.getSourceAccount();
        Account destinationAccount = transfer.getDestinationAccount();
        int amount = transfer.getAmount();
        sourceAccount.setAmount(sourceAccount.getAmount() - amount);
        destinationAccount.setAmount(destinationAccount.getAmount() + amount);
        accountRepository.update(sourceAccount);
        accountRepository.update(destinationAccount);
    }
}
