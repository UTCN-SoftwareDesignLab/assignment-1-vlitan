package view;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BoxLayout.Y_AXIS;

public abstract class BaseView extends JFrame{
    private JTabbedPane tpOperations;

    public BaseView(){
        this.setVisible(true);
        setSize(600, 800);
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tpOperations = new JTabbedPane();
        addComponents();
        add(tpOperations);
    }

    public JTabbedPane getTpOperations(){
        return tpOperations;
    }

    abstract void addComponents();
}
