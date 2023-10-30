package Concurrencia.Ejemplo4C;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BuscadorPrimos implements Runnable {
    private final int yo, numTrabajadores, maxNum;
    private final List<Integer> primos;
    private final Lock cerrojo;
    public BuscadorPrimos(int num, int numTra, int maxNum, List<Integer> primos){
        yo = num;
        this.numTrabajadores = numTra;
        this.maxNum = maxNum;
        this.primos = primos;
        cerrojo = new ReentrantLock();
    }
    @Override
    public void run() {
        for (int i = yo; i <= maxNum; i += numTrabajadores)
            if (esPrimo(i)) {
                cerrojo.lock();
            try {
                primos.add(i);
            } finally {
                cerrojo.unlock();
            }
        }
    }
    private boolean esPrimo(int n) {
        if (n < 2)
            return false;
        else {
            for (int i = 2; i <= n/2; i++)
                if ((n % i) == 0)
                    return false;
            return true;
        }
    }
}
