/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.so.model.algorithm.impl;

import com.so.controller.AlgorithmController;
import com.so.controller.RenderController;
import com.so.model.algorithm.AlgorithmResolver;
import com.so.model.core.CriticalSection;
import com.so.model.core.Process;
import com.so.util.Constants;
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
                return a.getBurst() - b.getBurst();
            }
        });
    }

    private void findAlternativeProcess() {
        Process currentProcess = criticalSection.getCurrentProcess();
        final Integer currentProcessIndex = criticalSection.getIndexCurrentProcess();
        for (int i = 0; i < currentProcessIndex; ++i) {
            final Process process = criticalSection.getQueueProcess().get(i);
            if (!process.getCalculated()) {
                if (Objects.equals(process.getIncommingTime(), currentProcess.getIncommingTime())) {
                    if (process.getBurst() < currentProcess.getBurst()) {
                        Util.generateRemanentProcess(currentProcess, criticalSection, String.format("%s%s",
                                currentProcess.getName(), "-"), currentProcess.getExecutedTime());
                        Util.calculateProcess(currentProcess, criticalSection);
                        criticalSection.setIndexCurrentProcess(i);
                    }
                }
            }
        }
    }

    @Override
    public void lock() {
    }

    @Override
    public void resolve() throws InterruptedException {
        Process currentProcess;
        do {
            orderProcessList();
            criticalSection.setProcessInCriticalSection();
            currentProcess = criticalSection.getCurrentProcess();

            if (Objects.nonNull(currentProcess)) {
                findAlternativeProcess();
                if (Objects.equals(currentProcess.getExecutedTime(), currentProcess.getBurst())) {
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
