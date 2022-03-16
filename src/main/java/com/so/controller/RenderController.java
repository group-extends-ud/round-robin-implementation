package com.so.controller;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;
import com.so.model.context.RenderSuscriber;
import com.so.util.Util;

public class RenderController {
    private static RenderController renderController;

    private List<RenderSuscriber> subscriptors;

    private RenderController() {
        subscriptors = new ArrayList<>();
    }

    public void notifyRender() {
        subscriptors.forEach(subscriptor -> subscriptor.renderProcess(Util.getProcessListData()));
    }

    public void addSubscriptor(RenderSuscriber subscriptor) {
        subscriptors.add(subscriptor);
    }

    public static RenderController getRenderController() {
        if (Objects.isNull(renderController)) {
            renderController = new RenderController();
        }
        return renderController;
    }
}
