package com.so.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class TableRepresentation extends JPanel {

    private JTable table;

    public TableRepresentation(String[][] rows, String[] columns, int width, int height) {

        setLayout(null);

        setSize(width, height);
        initTemplate(rows, columns);
    }

    private void initComponents(String[][] rows, String[] colums) {
        table = new JTable(rows, colums);
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

    public void initTemplate(String[][] rows, String[] colums) {
        initComponents(rows, colums);
    }

}