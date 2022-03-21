/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.so.view.components;

import com.so.controller.AlgorithmController;
import com.so.controller.RenderController;
import com.so.model.context.RenderSuscriber;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;

/**
 *
 * @author jema0
 */
public class AlgorihmSelector extends JComboBox<String> implements RenderSuscriber {
    
    AlgorihmSelector(String[] items) {
        super(items);
        RenderController.getRenderController().addSubscriptor(this);
    }

    @Override
    public void renderProcess() {
        setEnabled(RenderController.getRenderController().isModifiable());
        repaint();
    }
    
}
