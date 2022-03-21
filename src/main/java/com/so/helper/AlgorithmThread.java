package com.so.helper;

import com.so.model.core.Process;
import com.so.controller.AlgorithmController;
import com.so.controller.RenderController;
import com.so.model.algorithm.AlgorithmResolver;
import com.so.model.core.CriticalSection;
import com.so.util.Constants;
import com.so.util.Util;
import java.util.Objects;

public class AlgorithmThread extends Thread {

    private final AlgorithmResolver algorithmResolver;

    public AlgorithmThread(AlgorithmResolver algorithmResolver) {
        this.algorithmResolver = algorithmResolver;
    }

    @Override
    public void run() {
        if (Objects.nonNull(algorithmResolver)) {
            System.out.println(
                    String.format(
                            "Using %s algorithm",
                            algorithmResolver.getClass().getSimpleName()
                    )
            );
            RenderController.getRenderController().setModifiable(Boolean.FALSE);
            try {
                algorithmResolver.resolve();
            } catch (InterruptedException ex) {
                final Process currentProcess = CriticalSection.getInstance().getCurrentProcess();
                if (!Objects.equals(currentProcess.getBurst(), currentProcess.getExecutedTime())) {
                    final Process remanentProcess = Util.generateRemanentProcess(currentProcess, CriticalSection.getInstance(),
                            String.format("%s*", currentProcess.getName()), currentProcess.getExecutedTime());

                    remanentProcess.setLockedTime(Constants.lockingTime);
                    remanentProcess.setDisplayJobExecuted(remanentProcess.getBurst());
                }
                AlgorithmController.getAlgorithmController().createMainThread(algorithmResolver);
            }
            RenderController.getRenderController().setModifiable(Boolean.TRUE);
        } else {
            System.err.println("Algoritm not selected");
        }
    }

}
