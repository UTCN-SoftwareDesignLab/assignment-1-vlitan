package view.OperationView;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

public abstract class OperationsView extends JPanel {
    private JTextArea  taOutputData;

    public void setOutputData(String data){
        taOutputData.setText(data);
    }

    public OperationsView(){
        this.setVisible(true);

        initialiseComponents();
        taOutputData = new JTextArea("output data");

        addComponents();
        add(taOutputData);
    }
    abstract void addComponents();
    abstract void initialiseComponents();
}
