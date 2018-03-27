package repository;

import model.Transfer;

import java.util.List;

public interface TransferRepository {
    boolean addTransfer(Transfer transfer);
    boolean updateTransfer(Transfer transfer);
    List<Transfer> findAllTransfers();
    Transfer findTransferById(int id);
}
