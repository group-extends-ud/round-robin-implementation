package com.so.controller;

import com.so.model.core.CriticalSection;
import com.so.model.core.Process;
import com.so.view.page.Modal;

public class ModalController {

    private Modal modalView;

    public ModalController(Modal modalView){
        this.modalView = modalView;
    }

    public void initListenrs(){
        modalView.getBtnAddProcess().addActionListener((e) -> {
            try{
                CriticalSection criticalSection = CriticalSection.getInstance();
                Process process = new Process();
                process.setName(modalView.getProcessName());
                process.setIncommingTime(Integer.valueOf(modalView.getCommingTime()));
                process.setBurst(Integer.valueOf(modalView.getBurstTime()));
                criticalSection.addProcess(process);

                RenderController.getRenderController().notifyRender();

                modalView.dispose();
            }catch(NumberFormatException err){
                modalView.notifyError("Asegurese de ingresar numeros");
            }
        });
    }
    
}
