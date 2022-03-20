package com.so.helper;

import com.so.controller.RenderController;
import com.so.model.algorithm.AlgorithmResolver;
import java.util.Objects;

public class AlgorithmThread extends Thread {

    private final AlgorithmResolver algorithmResolver;

    public AlgorithmThread(AlgorithmResolver algorithmResolver) {
        this.algorithmResolver = algorithmResolver;
    }

    @Override
    public void run() {
        if (Objects.nonNull(algorithmResolver)) {
            RenderController.getRenderController().setModifiable(Boolean.FALSE);
            algorithmResolver.resolve();
            RenderController.getRenderController().setModifiable(Boolean.TRUE);
        } else {
            System.err.println("Algoritm not selected");
        }
    }

}
