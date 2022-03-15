package com.so.view;

import javax.swing.JPanel;

public class LayoutInformation extends JPanel {

    private JPanel processTable;

    private void initComponents(){
        final String[][] rows = {{"P1", "1", "2", "3"}, {"P2", "2", "3", "4"}, {"P3", "3", "4", "5"}, {"P4", "4", "5", "6"}};
        final String[] columns = {"Process ID", "Arrival Time", "Burst Time", "Priority"};

        processTable = new TableRepresentation(rows, columns, getWidth(), getHeight() / 2);
        processTable.setLocation(0, 0);
        add(processTable);
    }
    
    public LayoutInformation(int width,int height){

        setLayout(null);

        setSize(width,height);
        initComponents();
    }

}
