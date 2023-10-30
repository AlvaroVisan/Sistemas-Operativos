package Concurrencia.Ejemplo4B;

import Concurrencia.Ejemplo2A.ResultadoInt;

public class ContadorPrimos implements Runnable {
    private final int yo, numTrabajadores, maxNum;
    private final ResultadoInt numPrimos;
    public ContadorPrimos(int num, int numTra, int maxNum, ResultadoInt numPrimos){
        yo = num;
        this.numTrabajadores = numTra;
        this.maxNum = maxNum;
        this.numPrimos = numPrimos;
    }
    @Override
    public void run() {
        for (int i = yo; i <= maxNum; i += numTrabajadores)
            if (esPrimo(i))
                synchronized(numPrimos) {
                    numPrimos.res++;
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
