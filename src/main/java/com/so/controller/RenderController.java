package com.so.controller;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;
import com.so.model.context.RenderSuscriber;
import com.so.util.Util;
import com.so.view.components.GantDiagram;

public class RenderController {

    private static RenderController renderController;
    private List<RenderSuscriber> subscriptors;
    private GantDiagram diagramController = GantDiagram.getInstance();

    private RenderController() {
        subscriptors = new ArrayList<>();
    }

    public void notifyRender() {
        subscriptors.forEach(subscriptor -> subscriptor.renderProcess(Util.getProcessListData()));
    }

    public void addSubscriptor(RenderSuscriber subscriptor) {
        subscriptors.add(subscriptor);
    }

    public void renderGantDiagram() {
        String[][] data = Util.getProcessListData();
        diagramController.removeAllElements();
        for (int i = 0; i < data.length; i++) {
            diagramController.addProcess(data[i][0], Integer.valueOf(data[i][3]), Integer.valueOf(data[i][4]));
        }
        notifyRender();
    }

    public static RenderController getRenderController() {
        if (Objects.isNull(renderController)) {
            renderController = new RenderController();
        }
        return renderController;
    }
}
