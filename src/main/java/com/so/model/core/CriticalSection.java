package com.so.model.core;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class CriticalSection {

    private List<Process> queueProcess;
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

    public void addProcess(Process process){
        queueProcess.add(process);
    }

}
