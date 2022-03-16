package com.so.helper;

import com.so.model.algorithm.AlgorithmResolver;

public class AlgorithmThread extends Thread {

    private AlgorithmResolver algorithmResolver;

    public AlgorithmThread(AlgorithmResolver algorithmResolver) {
        this.algorithmResolver = algorithmResolver;
    }

    @Override
    public void run() {
        algorithmResolver.resolve();
    }
    
}
