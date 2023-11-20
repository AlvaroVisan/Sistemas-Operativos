package Concurrencia.FIFO_Cola_Compartida;

public class Consumidor implements Runnable {
    private ColaFIFO<String> fifo;
    private final String quien;

    public Consumidor(ColaFIFO<String> fifo, int n) {
        this.fifo = fifo;
        this.quien = "Consumidor #" + n + " saca ";
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10 ) {
            try {
                System.out.println ( quien + fifo.sacar() );
                i++;
            } catch ( ExcepcionColaVacia e ) {
                System.out.println ( quien + "Cola vacía" );
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

