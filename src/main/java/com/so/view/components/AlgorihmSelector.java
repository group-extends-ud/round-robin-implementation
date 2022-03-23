package com.so.view.components;

import com.so.controller.RenderController;
import com.so.model.context.RenderSuscriber;
import com.so.util.Constants;

import javax.swing.JComboBox;

/**
 *
 * @author jema0
 */
public class AlgorihmSelector extends JComboBox<String> implements RenderSuscriber {
    
    AlgorihmSelector(String[] items) {
        super(items);
        RenderController.getRenderController().addSubscriptor(this);
        addItemListener((event) -> {
            Constants.Context.setCurrentAlgorithm(String.valueOf(event.getItem()));
        });
    }

    @Override
    public void renderProcess() {
        setEnabled(RenderController.getRenderController().isModifiable());
        repaint();
    }
    
}
