package com.so.util;

import java.util.List;
import java.util.Objects;

import com.so.model.core.CriticalSection;
import com.so.model.core.Process;
import javax.swing.JOptionPane;

public class Util {

    public static String[][] getProcessListData() {
        CriticalSection criticalSection = CriticalSection.getInstance();
        List<Process> listProcess = criticalSection.getQueueProcess();
        String[][] response = new String[criticalSection.getQueueProcess().size()][8];
        for (int i = 0; i < response.length; i++) {
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

    public static Integer getMaxIncommingTime() {
        CriticalSection criticalSection = CriticalSection.getInstance();
        List<Process> listProcess = criticalSection.getQueueProcess();
        Integer maxElement = 0;
        for (Process process : listProcess) {
            if (process.getIncommingTime() > maxElement) {
                maxElement = process.getIncommingTime();
            }
        }
        return maxElement;
    }

    public static Process getLastProcessExecuted(List<Process> list) {
        Integer highestEndTime = 0;
        Process highestEndTimeProcess = list.get(0);

        for (Process process : list) {
            if (process.getCalculated()) {
                if (process.getEndTime() > highestEndTime) {
                    highestEndTime = process.getEndTime();
                    highestEndTimeProcess = process;
                }
            }
        }

        return highestEndTimeProcess;
    }

    public static void calculateProcess(Process currentProcess, CriticalSection criticalSection) {
        Process lastProcessExecuted = getLastProcessExecuted(criticalSection.getQueueProcess());
        
        currentProcess.setStartTime((Objects.nonNull(lastProcessExecuted.getEndTime()))
                ? lastProcessExecuted.getEndTime()
                : currentProcess.getIncommingTime()
        );
        
        currentProcess.setEndTime(currentProcess.getStartTime() + currentProcess.getBurst() + currentProcess.getLockedTime());
        currentProcess.setTurnaroundTime(currentProcess.getEndTime() - currentProcess.getIncommingTime());

        Integer burstExecuted = (Objects.isNull(currentProcess.getDisplayJobExecuted()))
                ? currentProcess.getBurst()
                : currentProcess.getDisplayJobExecuted();

        currentProcess.setCalculated(Boolean.TRUE);

        currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - burstExecuted);
        criticalSection.setIndexCurrentProcess(criticalSection.getIndexCurrentProcess() + 1);
    }

    public static Process generateRemanentProcess(Process currentProcess, CriticalSection criticalSection, String name, Integer burst) {
        Process remanentProcess = new Process();
        remanentProcess.setCounterSubProcess(currentProcess.getCounterSubProcess() + 1);
        remanentProcess.setName(name);
        remanentProcess.setIncommingTime(currentProcess.getIncommingTime());

        Integer totalBurstProcess = currentProcess.getBurst();
        currentProcess.setBurst(burst);
        remanentProcess.setBurst(Math.abs(currentProcess.getBurst() - totalBurstProcess));
        remanentProcess.setExecutedTime(remanentProcess.getBurst());
        remanentProcess.setDisplayJobExecuted(currentProcess.getBurst() + remanentProcess.getBurst());

        criticalSection.addProcess(remanentProcess);
        return remanentProcess;
    }

    public static Process generateRemanentProcess(Process currentProcess, CriticalSection criticalSection, String name) {
        return generateRemanentProcess(currentProcess, criticalSection, name, Constants.quantum);
    }

    public static void notifyError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
