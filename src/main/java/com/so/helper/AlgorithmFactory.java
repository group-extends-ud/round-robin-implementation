/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.so.helper;

import com.so.model.algorithm.AlgorithmResolver;
import java.lang.reflect.Constructor;

/**
 *
 * @author jema0
 */
public class AlgorithmFactory {
    private AlgorithmFactory() {}
    
    public static AlgorithmResolver buildAlgorithm(String algorithm) {
        try {
            final Constructor<AlgorithmResolver> constructor;
            constructor = (Constructor<AlgorithmResolver>) Class.forName(
                    String.format("com.so.model.algorithm.impl.%s", algorithm)
            ).getConstructor();
            return constructor.newInstance();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }
    
}
