package com.so.view.page;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

import com.so.controller.ModalController;

public class Modal extends JFrame{

    private JTextField inputProcessName;
    private JTextField inputCommingTime;
    private JTextField inputBurst;
    private JButton btnAddProcess;

    private ModalController modalController = new ModalController(this);

    public JButton getBtnAddProcess(){
        return this.btnAddProcess;
    }

    public String getProcessName(){
        return this.inputProcessName.getText();
    }

    public String getCommingTime(){
        return this.inputCommingTime.getText();
    }

    public String getBurstTime(){
        return this.inputBurst.getText();
    }

    private void initComponets(){

        JLabel titleProcessName = new JLabel("Nombre Proceso: ");
        titleProcessName.setSize(new Dimension(200,20));
        titleProcessName.setFont(new Font("Arial",Font.PLAIN,15));
        titleProcessName.setLocation(10, 50);
        add(titleProcessName);

        inputProcessName = new JTextField();
        inputProcessName.setSize(new Dimension(200,20));
        inputProcessName.setHorizontalAlignment(JTextField.CENTER);
        inputProcessName.setFont(new Font("Arial",Font.PLAIN,15));
        inputProcessName.setLocation(((this.getWidth()-inputProcessName.getWidth())/2)+50, 50);
        add(inputProcessName);

        JLabel titleCommingTime = new JLabel("Tiempo de llegada: ");
        titleCommingTime.setSize(new Dimension(200,20));
        titleCommingTime.setFont(new Font("Arial",Font.PLAIN,15));
        titleCommingTime.setLocation(10, 100);
        add(titleCommingTime);

        inputCommingTime = new JTextField();
        inputCommingTime.setSize(new Dimension(200,20));
        inputCommingTime.setHorizontalAlignment(JTextField.CENTER);
        inputCommingTime.setFont(new Font("Arial",Font.PLAIN,15));
        inputCommingTime.setLocation(((this.getWidth()-inputCommingTime.getWidth())/2)+50, 100);
        add(inputCommingTime);

        JLabel titleBurst = new JLabel("Ráfaga: ");
        titleBurst.setSize(new Dimension(200,20));
        titleBurst.setFont(new Font("Arial",Font.PLAIN,15));
        titleBurst.setLocation(10, 150);
        add(titleBurst);

        inputBurst = new JTextField();
        inputBurst.setSize(new Dimension(200,20));
        inputBurst.setHorizontalAlignment(JTextField.CENTER);
        inputBurst.setFont(new Font("Arial",Font.PLAIN,15));
        inputBurst.setLocation(((this.getWidth()-inputBurst.getWidth())/2)+50, 150);
        add(inputBurst);

        btnAddProcess = new JButton("Agregar");
        btnAddProcess.setSize(new Dimension(150, 30));
        btnAddProcess.setFont(new Font("Arial",Font.PLAIN,15));
        btnAddProcess.setFocusable(false);
        btnAddProcess.setLocation((this.getWidth() - btnAddProcess.getWidth()) / 2, 200);
        add(btnAddProcess);
    }

    private void initListeners() {
        modalController.initListenrs();
    }

    public void initTemplate(int width,int height){
        setTitle("Agregar Proceso");
        setSize(new Dimension(width,height));
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponets();
        initListeners();
        setVisible(true);
    }
    
    
}
