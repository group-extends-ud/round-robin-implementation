package com.so.view.components;

import javax.swing.JPanel;
import com.so.util.Constants;
import com.so.controller.RenderController;
import com.so.model.context.RenderSuscriber;

public class LayoutInformation extends JPanel implements RenderSuscriber{

    private TableRepresentation processTable;

    private void initComponents(){
        final String[][] rows = new String[1][8];

        processTable = new TableRepresentation(rows, Constants.titlesTables, getWidth(), getHeight() / 2);
        processTable.setLocation(0, 0);
        add(processTable);
    }
    
    public LayoutInformation(int width,int height){
        setLayout(null);
        setSize(width,height);
        initComponents();

        RenderController.getRenderController().addSubscriptor(this);
    }

    @Override
    public void renderProcess(String[][] data) {
        processTable.updateInformation(data);
    }

}
