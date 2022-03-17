package com.so.model.core;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Process {

    private String name;
    private Integer incommingTime;
    private Integer burst;
    private Integer startTime;
    private Integer endTime;
    private Integer turnaroundTime;
    private Integer waitingTime;

    //Variables extra
    private Integer lockedTime = 0;
    private Integer executedTime;
    private Integer displayJobExecuted = null;
    private Integer counterSubProcess = 0;
    private Process parentProcess;

}
