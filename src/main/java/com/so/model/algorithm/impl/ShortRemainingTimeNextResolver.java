package com.so.model.algorithm.impl;

import com.so.controller.AlgorithmController;
import com.so.controller.RenderController;
import com.so.model.algorithm.AlgorithmResolver;
import com.so.model.core.CriticalSection;
import com.so.model.core.Process;
import com.so.util.Util;
import java.util.Objects;

/**
 *
 * @author jema0
 */
public class ShortRemainingTimeNextResolver extends AlgorithmResolver {

    private final CriticalSection criticalSection = CriticalSection.getInstance();

    @Override
    protected void orderProcessList() {
        criticalSection.getQueueProcess().sort((Process a, Process b) -> {
            if (a.getIncommingTime() < b.getIncommingTime()) {
                return -1;
            } else if (a.getIncommingTime() > b.getIncommingTime()) {
                return 1;
            } else {
                if(a.getCalculated() && !b.getCalculated()) {
                    return -1;
                } else if(!a.getCalculated() && b.getCalculated()) {
                    return 1;
                } else if(a.getCalculated() && b.getCalculated()) {
                    return 0;
                } else {
                    return a.getBurst() - b.getBurst();
                }
            }
        });
    }

    private Integer findAlternativeProcess() {
        Process currentProcess = criticalSection.getCurrentProcess();
        Integer indexToReturn = -1;
        final Integer currentProcessIndex = criticalSection.getIndexCurrentProcess(currentProcess);
        for (int i = 0; i < currentProcessIndex; ++i) {
            final Process process = criticalSection.getQueueProcess().get(i);
            if (!process.getCalculated()) {
                if (Objects.equals(process.getIncommingTime(), currentProcess.getIncommingTime())) {
                    if (process.getBurst() < currentProcess.getBurst() - currentProcess.getExecutedTime()) {
                        Util.generateRemanentProcess(currentProcess, criticalSection, String.format("%s%s",
                                currentProcess.getName(), "-"), currentProcess.getExecutedTime());
                        indexToReturn = i;
                    }
                }
            }
        }
        return indexToReturn;
    }

    @Override
    public void lock() {
    }

    @Override
    public void resolve() throws InterruptedException {
        Process currentProcess;
        orderProcessList();
        criticalSection.setProcessInCriticalSection();
        Integer alternativeIndex;
        do {
            orderProcessList();
            currentProcess = criticalSection.getCurrentProcess();

            if (Objects.nonNull(currentProcess)) {
                alternativeIndex = findAlternativeProcess();
                if (Objects.equals(currentProcess.getExecutedTime(), currentProcess.getBurst())) {
                    Util.calculateProcess(currentProcess, criticalSection);
                    if(!Objects.equals(alternativeIndex, -1)) {
                        criticalSection.setIndexCurrentProcess(alternativeIndex);
                        criticalSection.setProcessInCriticalSection();
                    }
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
