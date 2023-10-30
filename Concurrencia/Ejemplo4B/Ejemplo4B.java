package Concurrencia.Ejemplo4B;

import java.util.ArrayList;
import java.util.List;

import Concurrencia.Ejemplo2A.ResultadoInt;

public class Ejemplo4B {
    private final static int MAX_NUM = 100000;
    private final static int NUM_HILOS = 10;
    public static void main(String[] args) throws InterruptedException {
        final Thread[] hilos = new Thread[NUM_HILOS];
        final ResultadoInt numPrimos = new ResultadoInt();

        final List<Integer> primos = new ArrayList<Integer>();
        for (int i = 0; i < NUM_HILOS/2; i++) {
            hilos[i] = new Thread(new BuscadorPrimos(i, NUM_HILOS, MAX_NUM, primos));
            hilos[i].start();
        }
        for (int i = 0; i < NUM_HILOS/2; i++) {
            hilos[i] = new Thread(new ContadorPrimos(i, NUM_HILOS, MAX_NUM, numPrimos));
            hilos[i].start();
        }
        for (int i = 0; i < NUM_HILOS; i++)
            hilos[i].join();
        System.out.println("TOTAL: " + primos.size());
        for (int n : primos)
            System.out.print(" " + n);
        System.out.println();
    }
}
