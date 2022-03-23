package com.so.util;

public class Constants {

    private Constants() {
    }

    public static final String[] titlesTables = {
        "Nombre", "Tiempo de llegada", "Rafaga", "Ejecutado",
        "T.comienzo", "T.final", "T Retorno", "Espera"
    };

    public static final Integer quantum = 4;
    public static final Integer lockingTime = 4;

    public static class Context {
        
        private static String currentAlgorithm;
        
        public static void setCurrentAlgorithm(String value){
            currentAlgorithm = value;
        }
        
        public static String getCurrentAlgorithm(){
            return currentAlgorithm;
        }

        private Context() {
        }
    }

}
