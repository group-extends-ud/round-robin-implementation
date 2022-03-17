package com.so.view.components;

import javax.swing.JPanel;
import com.so.util.Constants;
import com.so.controller.RenderController;
import com.so.model.context.RenderSuscriber;

public class LayoutInformation extends JPanel implements RenderSuscriber{

    private TableRepresentation processTable;
    private GantDiagram diagramController = GantDiagram.getInstance();
    private JPanel gantDiagram;

    private void initComponents(){
        final String[][] rows = new String[1][8];
        processTable = new TableRepresentation(rows, Constants.titlesTables, getWidth(), (int)(getHeight()*0.40));
        processTable.setLocation(0, 0);
        add(processTable);

        gantDiagram = diagramController.getGrafica();
        gantDiagram.setSize(getWidth(),(int)(getHeight()*0.55));
        gantDiagram.setLocation(0, (int)(getHeight()*0.40));
        add(gantDiagram);
        
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
        gantDiagram = diagramController.getGrafica();
        repaint();
    }

}
