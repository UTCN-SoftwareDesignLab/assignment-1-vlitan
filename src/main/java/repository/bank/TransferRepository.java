package repository.bank;

import model.Transfer;
import model.User;

import java.util.List;

public interface TransferRepository {
    boolean addTransfer(Transfer transfer);
    boolean updateTransfer(Transfer transfer);
    List<Transfer> findAllTransfers();
    Transfer findTransferById(int id);
    List<Transfer> findByUser(User user);
}
