package Concurrencia.Ejemplo1B;

public class Ejemplo1B {
public static void main(String[] args) {
    Thread.currentThread().setName("Principal");
    for (int i = 0; i < 10; i++) {
        final Thread hilo = new Thread(new Trabajador(i));
        hilo.start();
    }
    System.out.println("["+Thread.currentThread().getName()+"] Fin del main");
    }
}