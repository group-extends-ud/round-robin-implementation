package com.so.view.page;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Dimension;

import com.so.view.components.ControlPanel;
import com.so.view.components.LayoutInformation;

public class Menu extends JFrame {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel controls;
    private JPanel informationLayout;

    private void initComponents(){
        controls = new ControlPanel((int)(screenSize.getWidth()*0.20), this.getHeight());
        controls.setLocation((this.getWidth()-controls.getWidth()), 0);
        add(controls);

        informationLayout = new LayoutInformation((int)(screenSize.getWidth()*0.80), this.getHeight());
        informationLayout.setLocation(0, 0);
        add(informationLayout);
    }

    public void initTemplate(){
        setLayout(null);
        setTitle("Round Robin");
        setSize(screenSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
