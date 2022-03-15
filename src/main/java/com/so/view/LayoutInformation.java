package com.so.view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class LayoutInformation extends JPanel {
    
    public LayoutInformation(int width,int height){
        setSize(width,height);
        setBorder(BorderFactory.createLineBorder(Color.RED));
    }

}
