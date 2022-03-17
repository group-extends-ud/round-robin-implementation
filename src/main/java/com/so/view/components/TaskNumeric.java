package com.so.view.components;

import java.util.Date;
import org.jfree.data.gantt.Task;

public class TaskNumeric extends Task {

    public TaskNumeric(String description, long start, long end) {
        super(description, new Date(start), new Date(end));
    }

}