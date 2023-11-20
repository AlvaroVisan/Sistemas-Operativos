package Concurrencia.FIFO_Cola_Compartida;

public class Productor implements Runnable {
    private final ColaFIFO<String> fifo;
    private final String quien;

    public Productor(ColaFIFO<String> fifo, int n) {
        this.fifo = fifo;
        this.quien = "Productor #" + n + " mete ";
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            try {
                fifo.meter(quien + i);
                i++;
            } catch (ExcepcionColaLlena e) {
                System.out.println(quien + "Cola llena");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

