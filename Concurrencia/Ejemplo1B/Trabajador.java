package Concurrencia.Ejemplo1B;

public  class Trabajador implements Runnable {
    private final int num;
    public Trabajador(int num) {
    this.num = num;
}
    @Override
    public void run() {
        Thread.currentThread().setName("Trabajador " + String.valueOf(num));
        System.out.println("["+Thread.currentThread().getName()+"] Hola mundo!");
    }
}
