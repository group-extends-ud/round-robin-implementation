package com.so.view;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JButton;

public class ControlPanel extends JPanel {
    
    private JButton btnStartProcess;
    private JButton btnAddProcess;
    private JPanel semaphore;

    private void initListeners() {
        btnStartProcess.addActionListener((e) -> {
            System.out.println("Start Process");
        });
        btnAddProcess.addActionListener((e) -> {
            System.out.println("Add Process");
        });
    }

    private void initComponents() {
        btnStartProcess = new JButton("Start Process");
        btnAddProcess = new JButton("Add Process");

        btnStartProcess.setSize(new Dimension(200, 20));
        btnAddProcess.setSize(new Dimension(200, 20));

        btnStartProcess.setLocation((getWidth() - btnStartProcess.getWidth()) / 2, (int)(getHeight() * 0.50));
        btnAddProcess.setLocation((getWidth() - btnStartProcess.getWidth()) / 2, (int)(getHeight() * 0.60));

        add(btnStartProcess);
        add(btnAddProcess);

        semaphore = new Semaphore(200, 200);
        semaphore.setLocation((getWidth() - semaphore.getWidth()) / 2, (int)(getHeight() * 0.10));

        add(semaphore);
    }

    public ControlPanel(int width,int height) {

        setLayout(null);

        setSize(width,height);
        initComponents();
        initListeners();
    }

}
