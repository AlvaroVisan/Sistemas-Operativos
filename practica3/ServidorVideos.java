package practica3;

import java.io.IOException;

import ssoo.videos.servidor.ReceptorPeticiones;

public class ServidorVideos {
    public static void main(String[] args) {
        try {
            ReceptorPeticiones receptorPeticiones = new ReceptorPeticiones();
            
            for(int i = 0; i < 10; i++) {
                HiloRecepcion hiloRecepcion = new HiloRecepcion(receptorPeticiones.recibirPeticion());
                Thread hilo = new Thread(hiloRecepcion);
                hilo.start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
