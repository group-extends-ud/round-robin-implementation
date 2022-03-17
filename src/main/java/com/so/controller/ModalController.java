package com.so.controller;

import com.so.model.core.CriticalSection;
import com.so.model.core.Process;
import com.so.view.page.Modal;
import com.so.util.Util;

public class ModalController {

    private Modal modalView;

    public ModalController(Modal modalView) {
        this.modalView = modalView;
    }

    public void initListenrs() {
        modalView.getBtnAddProcess().addActionListener((e) -> {
            try {
                if (Integer.valueOf(modalView.getCommingTime()) >= Util.getMaxIncommingTime()) {
                    CriticalSection criticalSection = CriticalSection.getInstance();
                    Process process = new Process();
                    process.setName(modalView.getProcessName());
                    process.setIncommingTime(Integer.valueOf(modalView.getCommingTime()));
                    process.setBurst(Integer.valueOf(modalView.getBurstTime()));
                    process.setExecutedTime(0);
                    criticalSection.addProcess(process);
                    RenderController.getRenderController().notifyRender();
                    modalView.dispose();
                }else{
                    Util.notifyError("Asegurese de que el tiempo de llegada sea mayor o igual");
                }
            } catch (NumberFormatException err) {
                Util.notifyError("Asegurese de ingresar numeros");
            }
        });
    }

}
