package practica3;


public class ServidorVideos {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            HiloRecepcion hiloRecepcion = new HiloRecepcion();
            Thread hilo = new Thread(hiloRecepcion);
            hilo.start();
        }
    }
}
