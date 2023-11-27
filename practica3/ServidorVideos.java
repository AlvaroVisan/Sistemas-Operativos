package practica3;

public class ServidorVideos {
    public static void main(String[] args) {
        HiloRecepcion hiloRecepcion = new HiloRecepcion();
        Thread hilo = new Thread(hiloRecepcion);
        hilo.start();
    }
}
