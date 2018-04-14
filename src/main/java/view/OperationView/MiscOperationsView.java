package view.OperationView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MiscOperationsView extends OperationsView{
    private JButton btnMakeTransfer;
    private JButton btnPayBill;

    @Override
    void addComponents() {
        add(btnMakeTransfer);
        add(btnPayBill);
    }

    @Override
    void initialiseComponents() {
        btnMakeTransfer = new JButton("make transfer");
        btnPayBill = new JButton("pay bill");
    }

    public void setBtnMakeTransferActionListener(ActionListener actionListener){
        btnMakeTransfer.addActionListener(actionListener);
    }

    public void setBtnPayBillActionListener(ActionListener actionListener){
        btnPayBill.addActionListener(actionListener);
    }

}
