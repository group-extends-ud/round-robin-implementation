package com.so.model.algorithm.impl;

import com.so.controller.RenderController;
import com.so.model.algorithm.AlgorithmResolver;
import com.so.model.core.CriticalSection;
import com.so.model.core.Process;
import com.so.util.Constants;

public class RoundRobinResolver extends AlgorithmResolver {

    @Override
    protected void orderProcessList() {
    }

    @Override
    public void lock() {
    }

    private void calculateProcess(Process currentProcess, CriticalSection criticalSection) {
        currentProcess.setStartTime(
                (criticalSection.getIndexCurrentProcess() == 0) ? 0
                        : criticalSection.getQueueProcess().get(criticalSection.getIndexCurrentProcess() - 1)
                                .getEndTime());
        currentProcess.setEndTime(currentProcess.getStartTime() + currentProcess.getBurst());
        currentProcess.setTurnaroundTime(currentProcess.getEndTime() - currentProcess.getIncommingTime());
        currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getBurst());
        
        criticalSection.setIndexCurrentProcess(criticalSection.getIndexCurrentProcess()+1);
    }

  
    @Override
    public void resolve() {
        try {
            Process currentProcess;
            CriticalSection criticalSection = CriticalSection.getInstance();
            do {
                criticalSection.setProcessInCriticalSection();
                currentProcess = criticalSection.getCurrentProcess();

                if (currentProcess.getBurst() <= Constants.quantum) {
                    calculateProcess(currentProcess, criticalSection);
                } else {
                    Process remanentProcess = new Process();
                    remanentProcess.setName(currentProcess.getName()+"*");
                    remanentProcess.setIncommingTime(currentProcess.getIncommingTime());

                    Integer totalBurstProcess = currentProcess.getBurst();
                    currentProcess.setBurst(Math.abs(currentProcess.getBurst() - Constants.quantum));
                    remanentProcess.setBurst(totalBurstProcess - currentProcess.getBurst());
                    
                    criticalSection.addProcess(remanentProcess);
                    calculateProcess(currentProcess, criticalSection);
                }
                /**
                 * Aca se hace toda la logica
                 */
                System.out.println(currentProcess);
                Thread.sleep(1000);
                RenderController.getRenderController().notifyRender();

            } while (criticalSection.getIndexCurrentProcess() < criticalSection.getQueueProcess().size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
