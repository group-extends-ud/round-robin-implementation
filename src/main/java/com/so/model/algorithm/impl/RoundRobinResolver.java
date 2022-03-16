package com.so.model.algorithm.impl;

import com.so.model.algorithm.AlgorithmResolver;
import com.so.model.core.CriticalSection;
import com.so.model.core.Process;

public class RoundRobinResolver extends AlgorithmResolver {

    @Override
    protected void orderProcessList() {}

    @Override
    public void lock() {}

    @Override
    public void resolve() {
        try {
            Process currentProcess;
            do {
                CriticalSection.getInstance().setProcessInCriticalSection();
                currentProcess = CriticalSection.getInstance().getCurrentProcess();

                /**
                 * Aca se hace toda la logica
                 */

                 System.out.println(currentProcess);

                Thread.sleep(1000);
            }while(!CriticalSection.getInstance().isQueueEmptyBoolean());
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
