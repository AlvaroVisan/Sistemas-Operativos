package Concurrencia.FIFO_Cola_Compartida;

public class ProductorConsumidorTest
{
 public static void main ( String[] args )
 {
 final ColaFIFO<String> fifo = new ColaFIFO<String> ( 20 );
 final int total = Integer.parseInt ( args[0] );
 for ( int i = 1; i <= total; i++)
 {
 Productor p = new Productor ( fifo, i );
 Consumidor c = new Consumidor ( fifo, i );
Thread hilo_p = new Thread(p);
Thread hilo_c = new Thread(c);
hilo_p.start ();
hilo_c.start ();
 }
 }
}
