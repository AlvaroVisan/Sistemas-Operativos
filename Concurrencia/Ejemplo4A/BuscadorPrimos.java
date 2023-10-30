package Concurrencia.Ejemplo4A;

import java.util.List;

public class BuscadorPrimos implements Runnable{
    private final int yo, numTrabajadores, maxNum;
    private final List<Integer> primos;
    public BuscadorPrimos(int num, int numTra, int maxNum, List<Integer> primos){
        yo = num;
        this.numTrabajadores = numTra;
        this.maxNum = maxNum;
        this.primos = primos;
    }
    @Override
    public void run() {
        for (int i = yo; i <= maxNum; i += numTrabajadores)
            if (esPrimo(i))
        primos.add(i);
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
