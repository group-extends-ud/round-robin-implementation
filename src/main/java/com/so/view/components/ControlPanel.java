package com.so.view.components;

import javax.swing.JPanel;

import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;

import com.so.controller.AlgorithmController;
import com.so.model.algorithm.AlgorithmResolver;
import com.so.model.algorithm.impl.RoundRobinResolver;
import com.so.view.page.Modal;

public class ControlPanel extends JPanel {
    
    private JButton btnStartProcess;
    private JButton btnAddProcess;
    private JPanel semaphore;

    private AlgorithmResolver resolver;


    private void initListeners() {
        btnStartProcess.addActionListener((e) -> {
           resolver = new RoundRobinResolver();

           AlgorithmController.getAlgorithmController().createThread(resolver);

        });
        
        btnAddProcess.addActionListener((e) -> {
            Modal modal = new Modal();
            modal.initTemplate(450, 300);
        });
    }

    private void initComponents() {
        btnStartProcess = new JButton("Start Process");
        btnAddProcess = new JButton("Add Process");

        btnStartProcess.setSize(new Dimension(200, 50));
        btnStartProcess.setFont(new Font("Arial",Font.PLAIN,20));
        btnStartProcess.setFocusable(false);
        btnStartProcess.setLocation((getWidth() - btnStartProcess.getWidth()) / 2, (int)(getHeight() * 0.50));
        add(btnStartProcess);

        btnAddProcess.setSize(new Dimension(200, 50));
        btnAddProcess.setFont(new Font("Arial",Font.PLAIN,20));
        btnAddProcess.setFocusable(false);
        btnAddProcess.setLocation((getWidth() - btnStartProcess.getWidth()) / 2, (int)(getHeight() * 0.60));
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
