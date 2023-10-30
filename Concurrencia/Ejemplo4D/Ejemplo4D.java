package Concurrencia.Ejemplo4D;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ejemplo4D {
    private final static int MAX_NUM = 100000;
private final static int NUM_HILOS = 10;
public static void main(String[] args) throws InterruptedException {
    final Thread[] hilos = new Thread[NUM_HILOS];
    final List<Integer> primos = new ArrayList<Integer>();
    final Lock cerrojo = new ReentrantLock();
    for (int i = 0; i < NUM_HILOS; i++) {
        hilos[i] = new Thread(new BuscadorPrimos(i, NUM_HILOS, MAX_NUM, primos,
                                        cerrojo));
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
