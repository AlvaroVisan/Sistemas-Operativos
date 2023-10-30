package Concurrencia.Ejemplo2A;

import Concurrencia.Ejemplo2B.ContadorPrimos;

public class Ejemplo2A {
    private final static int MAX_NUM = 10000;
    private final static int NUM_HILOS = 10;
    public static void main(String[] args) {
        ResultadoInt numPrimos;
        numPrimos = new ResultadoInt();
        for (int i = 0; i < NUM_HILOS; i++) {
            final ContadorPrimos cp=new ContadorPrimos(i,NUM_HILOS,MAX_NUM,numPrimos); //Incorrecto
            final Thread hilo = new Thread(cp);//Incorrecto
            hilo.start();
        }
        System.out.println("TOTAL: " + numPrimos.res);
    }
}
