package com.so.model.algorithm.impl;

import com.so.controller.AlgorithmController;
import com.so.controller.RenderController;
import com.so.model.algorithm.AlgorithmResolver;
import com.so.model.core.CriticalSection;
import com.so.model.core.Process;
import com.so.util.Constants;
import com.so.util.Util;
import java.util.Objects;

public class RoundRobinResolver extends AlgorithmResolver {

    @Override
    protected void orderProcessList() {
    }

    @Override
    public void lock() {
    }

    @Override
    public void resolve() throws InterruptedException {
        Process currentProcess;
        CriticalSection criticalSection = CriticalSection.getInstance();
        do {
            criticalSection.setProcessInCriticalSection();
            currentProcess = criticalSection.getCurrentProcess();

            if (Objects.nonNull(currentProcess)) {
                if (Objects.equals(currentProcess.getExecutedTime(), currentProcess.getBurst())) {
                    if (currentProcess.getBurst() > Constants.quantum) {
                        Util.generateRemanentProcess(currentProcess, criticalSection, String.format("%s%s",
                                currentProcess.getName(), "-"));
                    }
                    Util.calculateProcess(currentProcess, criticalSection);
                    AlgorithmController.getAlgorithmController().stopSemaphore();
                } else {
                    currentProcess.setExecutedTime(currentProcess.getExecutedTime() + 1);
                    AlgorithmController.getAlgorithmController().startSemaphore();
                }
                Thread.sleep(1000);
                RenderController.getRenderController().notifyRender();
            } else {
                Util.notifyError("Asegurese de que se haya ingresado almenos un proceso");
            }

        } while (criticalSection.getIndexCurrentProcess() < criticalSection.getQueueProcess().size());

        RenderController.getRenderController().renderGantDiagram();
    }

}
