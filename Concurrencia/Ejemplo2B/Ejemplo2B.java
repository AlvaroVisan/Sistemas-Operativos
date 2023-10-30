package Concurrencia.Ejemplo2B;

import Concurrencia.Ejemplo2A.ResultadoInt;

public class Ejemplo2B {
    private final static int MAX_NUM = 100000;
    private final static int NUM_HILOS = 10;
    public static void main(String[] args) throws InterruptedException {
        final Thread[] hilos = new Thread[NUM_HILOS];
        final ResultadoInt numPrimos = new ResultadoInt();
        for (int i = 0; i < NUM_HILOS; i++) {
            hilos[i] = new Thread(new ContadorPrimos(i, NUM_HILOS, MAX_NUM, numPrimos));
            hilos[i].start();
        }
        for (int i = 0; i < NUM_HILOS; i++)
            hilos[i].join();
        System.out.println("TOTAL: " + numPrimos.res);
    }
}
