package com.so.util;

import java.util.List;

import com.so.model.core.CriticalSection;
import com.so.model.core.Process;

public class Util {

    public static String[][] getProcessListData(){
        CriticalSection criticalSection = CriticalSection.getInstance();
        List<Process> listProcess = criticalSection.getQueueProcess();
        String[][] response = new String[criticalSection.getQueueProcess().size()][8];
        for(int i = 0; i < response.length; i++){
            Process process = listProcess.get(i);
            response[i][0] = process.getName();
            response[i][1] = String.valueOf(process.getIncommingTime());
            response[i][2] = String.valueOf(process.getBurst());
            response[i][3] = String.valueOf(process.getExecutedTime());
            response[i][4] = String.valueOf(process.getStartTime());
            response[i][5] = String.valueOf(process.getEndTime());
            response[i][6] = String.valueOf(process.getTurnaroundTime());
            response[i][7] = String.valueOf(process.getWaitingTime());
        }
        return response;
    }

    public static Integer getMaxIncommingTime(){
        CriticalSection criticalSection = CriticalSection.getInstance();
        List<Process> listProcess = criticalSection.getQueueProcess();
        Integer maxElement = 0;
        for(Process process:listProcess){
            if(process.getIncommingTime() > maxElement){
                maxElement = process.getIncommingTime();
            }
        }
        return maxElement;
    }

    
}
