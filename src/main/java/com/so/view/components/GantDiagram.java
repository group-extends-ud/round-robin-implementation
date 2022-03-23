package com.so.view.components;

import java.util.Objects;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import javax.swing.*;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

public class GantDiagram {

    private final JFreeChart graficador;
    private final TaskSeriesCollection dataset;
    private final TaskSeries taskseries = new TaskSeries("");
    private static GantDiagram instance;

    private GantDiagram() {
        this.dataset = new TaskSeriesCollection();
        this.dataset.add(taskseries);
        this.graficador = ChartFactory.createGanttChart("", "", "", dataset, true, true, false);
    }

    public static GantDiagram getInstance() {
        if (Objects.isNull(instance)) {
            instance = new GantDiagram();
        }
        return instance;
    }

    public void addProcess(String nameProcess, Integer start, Integer end) {
        taskseries.add(new TaskNumeric(nameProcess, start, end));
    }

    public void removeAllElements() {
        taskseries.removeAll();
    }

    public JPanel getGrafica() {
        return new ChartPanel(this.graficador);
    }

}
