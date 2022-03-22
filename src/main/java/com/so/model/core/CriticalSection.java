package com.so.model.core;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class CriticalSection {

    private final List<Process> queueProcess;
    private Process currentProcess;
    private static CriticalSection instance;
    private Integer indexCurrentProcess = 0;

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
        return currentProcess;
    }

    public Boolean isQueueEmptyBoolean() {
        return queueProcess.isEmpty();
    }
    
    public void setIndexCurrentProcess(Integer indexCurrentProcess){
        this.indexCurrentProcess = indexCurrentProcess;
    }

    public Integer getIndexCurrentProcess(){
        return indexCurrentProcess;
    }
    
    public Integer getIndexCurrentProcess(Process currentProcess){
        return queueProcess.indexOf(currentProcess);
    }

    public void addProcess(Process process){
        queueProcess.add(process);
    }

    public void setProcessInCriticalSection() {
        try {
            currentProcess = queueProcess.get(indexCurrentProcess);
        } catch (Exception e) {
            System.out.println("No hay procesos en la cola");
        }
    }

}
