package com.so.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
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
        TableColumn column;
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(90);
        table.setRowHeight(25);
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

    public void setInformation(String[][] information){
        this.information = information;
        this.removeAll();
        this.initTemplate();
        this.repaint();
    }

}