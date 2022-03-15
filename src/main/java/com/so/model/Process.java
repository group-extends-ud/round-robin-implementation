package com.so.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Process {

    private String name;

    private Integer incommingTime, jobTime, waitingTime, turnaroundTime, endTime;
    
}
