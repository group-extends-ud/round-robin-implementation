package com.so.controller;

import com.so.model.core.CriticalSection;
import com.so.model.core.Process;
import com.so.view.page.Modal;
import com.so.util.Util;
import com.so.util.Constants;
import java.util.Objects;

public class ModalController {

    private Modal modalView;

    public ModalController(Modal modalView) {
        this.modalView = modalView;
    }

    private void createProcess(CriticalSection criticalSection) {
        Process process = new Process();
        process.setName(modalView.getProcessName());
        process.setIncommingTime(Integer.valueOf(modalView.getCommingTime()));
        process.setBurst(Integer.valueOf(modalView.getBurstTime()));
        process.setExecutedTime(0);
        criticalSection.addProcess(process);
        RenderController.getRenderController().notifyRender();
        modalView.dispose();
    }

    public void initListenrs() {
        modalView.getBtnAddProcess().addActionListener((e) -> {
            try {
                final Process currentProcess = CriticalSection.getInstance().getCurrentProcess();
                final Integer executed = Objects.nonNull(currentProcess)? currentProcess.getExecutedTime() : 0;
                final Integer incommingTime = Integer.valueOf(modalView.getCommingTime());
                if (incommingTime >= Util.getMaxIncommingTime() && incommingTime >= Util.getMaxEndTime() + executed) {
                    CriticalSection criticalSection = CriticalSection.getInstance();
                    createProcess(criticalSection);
                } else {
                    Util.notifyError("Asegurese de que el tiempo de llegada sea mayor o igual");
                }
            } catch (NumberFormatException err) {
                Util.notifyError("Asegurese de ingresar numeros");
            }
        });
    }

}
