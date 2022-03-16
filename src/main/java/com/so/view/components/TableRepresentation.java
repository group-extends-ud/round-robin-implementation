package com.so.view.components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Dimension;

public class TableRepresentation extends JPanel {

    private JTable table;
    private Dimension sizeTable;
    private String[][] information;
    private String[] columns;

    public TableRepresentation(String[][] rows, String[] columns, int width, int height) {
        this.information = rows;
        this.columns = columns;
        this.sizeTable = new Dimension(width,height);
        initTemplate();
    }

    private void initComponents() {
        table = new JTable(information, columns);
        table.setSize(getWidth(), getHeight());
        table.setLocation(0, 0);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for(int column = 0; column < table.getColumnCount(); column++){
            table.getColumnModel().getColumn(column).setCellRenderer(centerRenderer);
        }
        JScrollPane jsp = new JScrollPane(table);
        jsp.setSize(getWidth(), getHeight());
        jsp.setLocation(0, 0);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(jsp);
        jsp.updateUI();
    }

    public void initTemplate() {
        setLayout(null);
        setSize(sizeTable);
        initComponents();
    }

    public void updateInformation(String[][] information){
        this.information = information;
        this.removeAll();
        this.initTemplate();
        this.repaint();
    }

}