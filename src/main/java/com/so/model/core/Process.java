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
    private Integer displayJobExecuted = null;

}
