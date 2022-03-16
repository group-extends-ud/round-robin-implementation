package com.so.controller;

import java.util.Objects;

import com.so.helper.AlgorithmThread;
import com.so.model.algorithm.AlgorithmResolver;

public class AlgorithmController {

    private static AlgorithmController algorithmController;
    private Thread thread;

    private AlgorithmController() {}

    public static AlgorithmController getAlgorithmController() {
        if (Objects.isNull(algorithmController)) {
            algorithmController = new AlgorithmController();
        }
        return algorithmController;
    }

    public Thread getThread() {
        return thread;
    }

    public void createThread(AlgorithmResolver algorithmResolver) {
        thread = new AlgorithmThread(algorithmResolver);
        thread.start();
    }

}