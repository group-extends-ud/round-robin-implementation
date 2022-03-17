package com.so.model.algorithm.impl;

import com.so.controller.RenderController;
import com.so.model.algorithm.AlgorithmResolver;
import com.so.model.core.CriticalSection;
import com.so.model.core.Process;
import com.so.util.Constants;

import java.util.Objects;

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

        Integer burstExecuted = (Objects.isNull(currentProcess.getDisplayJobExecuted()))
            ?currentProcess.getBurst()
            :currentProcess.getDisplayJobExecuted();

        currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - burstExecuted);
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

                if(currentProcess.getExecutedTime() == currentProcess.getBurst()){
                    if (currentProcess.getBurst() <= Constants.quantum) {
                        calculateProcess(currentProcess, criticalSection);
                    } else {
                        Process remanentProcess = new Process();
                        remanentProcess.setCounterSubProcess(remanentProcess.getCounterSubProcess()+1);
                        remanentProcess.setName(currentProcess.getName()+"#"+currentProcess.getCounterSubProcess());
                        remanentProcess.setIncommingTime(currentProcess.getIncommingTime());
    
                        Integer totalBurstProcess = currentProcess.getBurst();
                        currentProcess.setBurst(Constants.quantum);
                        remanentProcess.setBurst(Math.abs(Constants.quantum - totalBurstProcess));
                        remanentProcess.setExecutedTime(remanentProcess.getBurst());
                        remanentProcess.setDisplayJobExecuted(currentProcess.getBurst() + remanentProcess.getBurst());
                        
                        criticalSection.addProcess(remanentProcess);
                        calculateProcess(currentProcess, criticalSection);
                    }
                }else{
                    currentProcess.setExecutedTime(currentProcess.getExecutedTime() + 1);
                }
                Thread.sleep(1000);
                RenderController.getRenderController().notifyRender();
            } while (criticalSection.getIndexCurrentProcess() < criticalSection.getQueueProcess().size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
