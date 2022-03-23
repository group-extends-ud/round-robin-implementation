package com.so.model.algorithm.impl;

import com.so.controller.AlgorithmController;
import com.so.controller.RenderController;
import com.so.model.algorithm.AlgorithmResolver;
import com.so.model.core.CriticalSection;
import com.so.model.core.Process;
import com.so.util.Util;
import java.util.List;
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
                if (a.getCalculated() && !b.getCalculated()) {
                    return -1;
                } else if (!a.getCalculated() && b.getCalculated()) {
                    return 1;
                } else if (a.getCalculated() && b.getCalculated()) {
                    return 0;
                } else {
                    return a.getBurst() - b.getBurst();
                }
            }
        });
    }

    private Boolean verifyShortIncommingProcess(Process incommingProcess, Process currentProcess) {
        Process lasProcessTime = Util.getLastProcessExecuted(criticalSection.getQueueProcess());
        Boolean isValidInput = (Objects.nonNull(lasProcessTime) && lasProcessTime.getCalculated())
                ? (incommingProcess.getIncommingTime() <= currentProcess.getExecutedTime() + lasProcessTime.getEndTime())
                : incommingProcess.getIncommingTime() <= currentProcess.getExecutedTime() + currentProcess.getIncommingTime();
        return isValidInput;

    }

    private Integer findAlternativeProcess() {
        final List<Process> queueProcess = criticalSection.getQueueProcess();
        Process currentProcess = criticalSection.getCurrentProcess();
        Integer indexToReturn = -1;
        for (int i = 0; i < queueProcess.size(); ++i) {
            final Process process = queueProcess.get(i);
            if (!process.getCalculated()) {
                if (verifyShortIncommingProcess(process, currentProcess)) {
                    if (process.getBurst() < currentProcess.getBurst() - currentProcess.getExecutedTime()) {
                        if (!Objects.equals(currentProcess.getExecutedTime(), 0)) {
                            Util.generateRemanentProcess(currentProcess, criticalSection, String.format("%s%s",
                                    currentProcess.getName(), "-"), currentProcess.getExecutedTime());
                        }
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
            currentProcess = criticalSection.getCurrentProcess();

            if (Objects.nonNull(currentProcess)) {
                alternativeIndex = findAlternativeProcess();
                if (!Objects.equals(alternativeIndex, -1) || Objects.equals(currentProcess.getExecutedTime(), currentProcess.getBurst())) {
                    if (!Objects.equals(alternativeIndex, -1)) {
                        criticalSection.setIndexCurrentProcess(alternativeIndex);
                        criticalSection.setProcessInCriticalSection();
                    }
                    if (!Objects.equals(currentProcess.getExecutedTime(), 0)) {
                        Util.calculateProcess(currentProcess, criticalSection);
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
