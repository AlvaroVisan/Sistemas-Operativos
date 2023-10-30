package Concurrencia.Ejemplo3;

import Concurrencia.Ejemplo2A.ResultadoInt;
import Concurrencia.Ejemplo2B.ContadorPrimos;

public class Ejemplo3 {
    private final static int MAX_NUM = 100000;
    private final static int NUM_HILOS = 10;
    public static void main(String[] args) throws InterruptedException {
        final Thread[] hilos = new Thread[NUM_HILOS];
        final ResultadoInt[] numPrimos = new ResultadoInt[NUM_HILOS];
        int numPrimosTotal;
        for (int i = 0; i < NUM_HILOS; i++)
            numPrimos[i] = new ResultadoInt();
        for (int i = 0; i < NUM_HILOS; i++) {
            hilos[i] = new Thread(new ContadorPrimos(i,NUM_HILOS,MAX_NUM,numPrimos[i]));
            hilos[i].start();
        }
        numPrimosTotal = 0;
        for (int i = 0; i < NUM_HILOS; i++) {
            hilos[i].join();
            numPrimosTotal += numPrimos[i].res;
        }
        System.out.println("TOTAL: " + numPrimosTotal);
    }
}
