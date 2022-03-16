package com.so.model.core;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class CriticalSection {

    private List<Process> queueProcess;
    private Process currentProcess;
    private static CriticalSection instance;

    private CriticalSection(){
        this.queueProcess = new ArrayList<>();
    }

    public static CriticalSection getInstance(){
        if(Objects.isNull(instance)){
            instance = new CriticalSection();
        }
        return instance;
    }

    public List<Process> getQueueProcess(){
        return queueProcess;
    }

    public Process getCurrentProcess(){
        System.out.println(currentProcess);
        return currentProcess;
    }

    public Boolean isQueueEmptyBoolean() {
        return queueProcess.isEmpty();
    }

    public void addProcess(Process process){
        queueProcess.add(process);
    }

    public void setProcessInCriticalSection() {
        System.out.println(queueProcess);
        try {
            currentProcess = queueProcess.remove(0);
        } catch (Exception e) {
            System.out.println("No hay procesos en la cola");
        }
    }

}
