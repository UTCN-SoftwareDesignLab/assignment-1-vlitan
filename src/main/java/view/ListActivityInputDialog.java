package view;

import model.Client;
import model.ListActivityDTO;
import model.builder.ClientBuilder;
import model.validator.Notification;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class ListActivityInputDialog {
    private JTextField tfId;
    private JTextField  tfStartDate;
    private JTextField  tfEndDate;


    private JPanel panel;

    public ListActivityInputDialog(JPanel panel){
        this.panel = panel;
        initialiseComponents();
        addComponents();
    }

    private void addComponents(){
        panel.add(new Label("ueser id"));
        panel.add(tfId);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("start date"));
        panel.add(tfStartDate);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new Label("end date"));
        panel.add(tfEndDate);
    }

    private void initialiseComponents() {
        panel = new JPanel();
        tfId = new JTextField("   0");
        tfStartDate = new JTextField(12);
        tfEndDate = new JTextField(12);
    }

    public Notification<ListActivityDTO> getActivityDTO(){
        Notification<ListActivityDTO> activityNotification = new Notification<>();
        boolean parsed = false;
        int result;
        do {
            result = JOptionPane.showConfirmDialog(null, panel, "please enter client data", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    ListActivityDTO listActivityDTO = new ListActivityDTO(Integer.parseInt(tfId.getText()), Date.valueOf(tfStartDate.getText()), Date.valueOf(tfEndDate.getText()));
                    activityNotification.setResult(listActivityDTO);
                    return activityNotification;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(panel, e.getMessage(), "Parse error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } while (result != JOptionPane.CANCEL_OPTION && parsed == false);
        return activityNotification;
    }
}
