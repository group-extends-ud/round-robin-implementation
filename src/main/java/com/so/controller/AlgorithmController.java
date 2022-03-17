package com.so.controller;

import java.util.Objects;

import com.so.helper.AlgorithmThread;
import com.so.model.algorithm.AlgorithmResolver;

public class AlgorithmController {

    private static AlgorithmController algorithmController;
    private Thread algorithmThread;
    
    private Boolean runningStatus = false;

    private AlgorithmController() {}

    public static AlgorithmController getAlgorithmController() {
        if (Objects.isNull(algorithmController)) {
            algorithmController = new AlgorithmController();
        }
        return algorithmController;
    }

    public Thread getThread() {
        return algorithmThread;
    }

    public void createMainThread(AlgorithmResolver algorithmResolver) {
        algorithmThread = new AlgorithmThread(algorithmResolver);
        algorithmThread.start();
    }
    
    public Boolean isRunning() {
        return runningStatus;
    }
    
    public void startSemaphore() {
        runningStatus = true;
    }
    
    public void stopSemaphore() {
        runningStatus = false;
    }

}