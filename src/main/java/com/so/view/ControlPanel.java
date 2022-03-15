package com.so.view;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.BorderFactory;
//import javax.swing.JButton;

public class ControlPanel extends JPanel {
    
    //private JButton btnStartProcess,btnAddProcess;

    public ControlPanel(int width,int height){
        setSize(width,height);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

}
