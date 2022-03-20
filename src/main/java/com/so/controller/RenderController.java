package com.so.controller;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;
import com.so.model.context.RenderSuscriber;
import com.so.util.Util;
import com.so.view.components.GantDiagram;

public class RenderController {

    private static RenderController renderController;
    private final List<RenderSuscriber> subscriptors;
    private final GantDiagram diagramController = GantDiagram.getInstance();
    private Boolean modifiable = true;

    private RenderController() {
        subscriptors = new ArrayList<>();
    }

    public void notifyRender() {
        subscriptors.forEach(subscriptor -> subscriptor.renderProcess());
    }

    public void addSubscriptor(RenderSuscriber subscriptor) {
        subscriptors.add(subscriptor);
    }

    public void renderGantDiagram() {
        String[][] data = Util.getProcessListData();
        diagramController.removeAllElements();
        for (String[] data1 : data) {
            diagramController.addProcess(data1[0], Integer.valueOf(data1[4]), Integer.valueOf(data1[5]));
        }
        notifyRender();
    }
    
    public Boolean isModifiable() {
        return modifiable;
    }
    
    public void setModifiable(Boolean modifiable) {
        this.modifiable = modifiable;
        notifyRender();
    }

    public static RenderController getRenderController() {
        if (Objects.isNull(renderController)) {
            renderController = new RenderController();
        }
        return renderController;
    }
}
